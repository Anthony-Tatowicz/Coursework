// Anthony Tatowicz
// process_sort.cpp
// Sort using multiple processes


#include "process_sort.h"

using namespace std;

pthread_mutex_t mtx = PTHREAD_MUTEX_INITIALIZER;

struct Params
{
    float *start;
    size_t len;
    int depth;
};

struct msg 
{
    long type;
    float *data;
};

void *merge_sort_process(void *pv);


void merge(float *start, float *mid, float *end)
{
    float *res = (float*)malloc((end - start)*sizeof(*res));
    float *lhs = start, *rhs = mid, *dst = res;

    while (lhs != mid && rhs != end)
        *dst++ = (*lhs <= *rhs) ? *lhs++ : *rhs++;

    while (lhs != mid)
        *dst++ = *lhs++;

    while (rhs != end)
        *dst++ = *rhs++;


    memcpy(start, res, (end - start)*sizeof(*res));
    free(res);
}

void merge_sort_mp(float *start, size_t len, int depth)
{
    if (len < 2)
        return;

    if (depth <= 0 || len < 4)
    {
        merge_sort_mp(start, len/2, 0);
        merge_sort_mp(start+len/2, len-len/2, 0);
    }
    else
    {
        struct Params params = { start, len/2, depth/2 };
        pid_t pid;
        int status;

        pthread_mutex_lock(&mtx);
        printf("Starting subprocess...\n");
        pthread_mutex_unlock(&mtx);

        // pid = clone(merge_sort_process, pchild_stack + STACK_SIZE, SIGCHLD, NULL);
        // pid = vfork();
        merge_sort_process(&params);
        pid = vfork();

        merge_sort_mp(start+len/2, len-len/2, depth/2);

        pid = wait(&status);

        pthread_mutex_lock(&mtx);
        printf("Finished subprocess.\n");
        pthread_mutex_unlock(&mtx);
    }

    merge(start, start+len/2, start+len);
}


void *merge_sort_process(void *pv)
{
    struct Params *params = (Params*)pv;
    merge_sort_mp(params->start, params->len, params->depth);
    return pv;
}

void merge_sort(float *start, size_t len)
{
    merge_sort_mp(start, len, P); 
}

int main()
{
    unsigned int i;
    float *data = new float[N];

	csv_parser csv("data.csv");

    for (i = 0; i < N; i++)
    {
		string val = csv.get_value(i + 2, COL);
        data[i] = stof(val);
        printf("%4f ", data[i]);

        if ((i + 1) % 8 == 0)
            printf("\n");
    }
    printf("\n");

    merge_sort(data, N);
    for (i = 0; i < N; i++)
    {
        printf("%4f ", data[i]);

        if ((i + 1) % 8 == 0)
            printf("\n");
    }
    printf("\n");

    return 0;

}