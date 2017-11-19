
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Server is the main entry point and base class for the server
 * Basically everyting starts here
 */
public class Server {
    static int PORT = 1234;

    public static void main (String args[]) {

        // Setup our server sockets
        ServerSocket serverSocket;

        try {
            // Create new server socket and print port
            serverSocket = new ServerSocket(PORT);
            System.out.println("Starting server on port " + PORT);
            System.out.println(System.getProperty("user.dir"));
            System.out.println();

            // Server loop lasts forever
            while(true) {
                // Accept our new client
                Socket client = serverSocket.accept();

                // Start a new thread for our client
                new Thread(new ClientThread(client)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Client thread is a thread that holds
 * the client socket and read requests, and serves content to the client
 * This is so the multiple clients can be serviced at once
 *
 * Input: ServerSocket
 */
class ClientThread implements Runnable {

    private Socket client;

    ClientThread(Socket c) {
        client = c;
    }

    @Override
    public void run() {

        try {
            // Get the input stream for client data
            InputStreamReader reader = new InputStreamReader(client.getInputStream());
            // Create buffered reader for logging
            BufferedReader in = new BufferedReader(reader);


            // Create an output stream to the client
            OutputStream out = new DataOutputStream(client.getOutputStream());


            // Parse http request
            HttpParser parser = new HttpParser(in);
            String status = parser.parseRequest();
            System.out.println("Done Parsing: " + status);

            byte[] ct ;
            Response res;

            if(status.equals("200 OK")) {
                // Load int content
                ContentFinder cf = parser.getReqContent();
                ct = cf.getContent();

                // Create a response for the client
                res = new Response(ct.length, new Date(), "", status, ct);
            }

            else {
                res = new Response(0, new Date(), "", "301 MOVED", null);
            }


            // Stringify the response and print it
            byte[] response = res.getResponse();
            System.out.println();

            // Send the response to the client
            out.write(response);

            // Close up all the streams and finish the thread
            client.close();
            out.close();

            // Close up our logging streams
            reader.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * HttpParser for parsing http requests
 *
 * Input: Inputstream of data
 * Output: status code
 */
class HttpParser {
    private BufferedReader reader;
    private ContentFinder reqContent;

    HttpParser(BufferedReader in) {
        reader = in;
    }

    String parseRequest() throws IOException {
        String init, status, req[];

        // Set our stats as good for now
        status = "200 OK";
        init = reader.readLine();

        System.out.println("INIT = " + init);

        // Check for requtest
        if(init == null || init.length() == 0) return "404 NOT FOUND";

        // Split up request by blank lines
        req = init.split("\\s");
        // If the length ins't wat expected error out
        if(req.length != 3) return "404 NOT FOUND";
        if(!req[0].equals("GET")) return  "404 NOT FOUND";

        // Decode the URL here and print
        String url = URLDecoder.decode(req[1], "UTF-8");
        System.out.println("DECODE URL = " + url);


        // If request is a / render index
        if(url.equals("/")) {
            reqContent = new ContentFinder("/index.html");
            reqContent.getContent();
            return status;
        }

        // Otherwise try and find it
        reqContent = new ContentFinder(url);
        reqContent.getContent();

        if(reqContent.getContent() == null) return "404 NOT FOUND";

        return status;
    }

    ContentFinder getReqContent() {
        return reqContent;
    }
}

/**
 * Content finder opens the file requested and puts int in a string arraylist
 *
 * Input: URL
 * Output: ArrayList<String>
 */
class ContentFinder {

    private File file;

    ContentFinder(String url) {
        file = new File("www" + url);
        System.out.println("LOOKING FOR = www" + url);
    }

    byte[] getContent() throws IOException {

        byte[] output;

        if(!file.exists()) return null;

        output = Files.readAllBytes(file.toPath());

        System.out.println("Done getting content");

        return output;
    }
}

/**
 * The Response class is a simple java Object
 * that holds the required information for a valid http response
 * Input: content length, last modified, status, content
 * Output: Nice object with all data
 */
class Response {
    /** example header
     HTTP/1.1 200 OK
     Date: Mon, 23 May 2005 22:38:34 GMT
     Content-Type: text/html; charset=UTF-8
     Content-Encoding: UTF-8
     Content-Length: 138
     Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT
     Server: Apache/1.3.3.7 (Unix) (Red-Hat/Linux)
     ETag: "3f80f-1b6-3e1cb03b"
     Accept-Ranges: bytes
     Connection: close
     */

    private String status;
    private String date;
    private String contentType;
    private String contentEncoding;
    private int contentLength ;
    private Date lastModified;
    private String server;
    private String ETAG;
    private String acceptRange;
    private String connection;
    private byte[] content;


    Response(int cl, Date lm, String et, String st, byte[] ct) {
        status = st;
        contentLength = cl;
        lastModified = lm;
        ETAG = et;

        contentType = "text/html; charset=UTF-8";
        contentEncoding = "UTF-8";
        server = "minimal-http";
        acceptRange = "bytes";
        connection = "Close";

        DateFormat df = new SimpleDateFormat("E, d M y HH:mm:ss z");
        Date dt = new Date();
        date = df.format(dt);

        content = ct;
    }

    byte[] getResponse() {
        String header = "HTTP/1.1 " + status + "\n" +
                "Date: " + date + "\n" +
                "Content-Type: " + contentType + "\n" +
                "Content-Encoding: " + contentEncoding + "\n" +
                "Content-Length: " + contentLength + "\n" +
                "Last-Modified: " + lastModified + "\n" +
                "Server: " + server + "\n" +
                "Accept-Ranges: " + acceptRange + "\n" +
                "Connection: " + connection + "\n\n";

        if(content == null) return header.getBytes();

        byte[] hd = header.getBytes();
        byte[] combined = new byte[hd.length + content.length];

        for (int i = 0; i < combined.length; ++i) {
            combined[i] = i < hd.length ? hd[i] : content[i - hd.length];
        }

        return combined;
    }
}
