CC=g++
CFLAGS= -I. -g

thread: src/thread_sort.cpp src/csv_parser.cpp
	${CC} ${CFLAGS} src/thread_sort.cpp src/csv_parser.cpp -o build/thread

process: process_sort.cpp csv_parser.cpp
	${CC} ${CFLAGS} process_sort.cpp csv_parser.cpp -o build/process


