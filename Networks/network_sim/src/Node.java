public class Node {
    private final int MAX_NODES = 6;
    private final int MAX_LINKS = 4;

    long id;
    int cost;
    int hops;
    int[][] dvTable = new int[MAX_NODES][MAX_LINKS];
    

}
