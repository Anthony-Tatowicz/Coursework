// Anthony Tatowicz
// process_sort.h
// Sort using multiple processes

#ifndef _PROCESS_SORT_H
#define _PROCESS_SORT_H

#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <cstring>
#include <pthread.h>
#include <sched.h>
#include <unistd.h>

#include "csv_parser.hpp"

#define COL 5
#define N 100
#define P 4
#define STACK_SIZE (1024 * 1024) 


#endif