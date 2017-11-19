//thread_sort.cpp
//Thread sorting alg

#include "thread_sort.hpp"

using namespace std;

struct Params
{
    float *start;
    size_t len;
    int depth;
};

pthread_mutex_t mtx = PTHREAD_MUTEX_INITIALIZER;

void *merge_sort_thread(void *pv);


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

void merge_sort_mt(float *start, size_t len, int depth)
{
    if (len < 2)
        return;

    if (depth <= 0 || len < 4)
    {
        merge_sort_mt(start, len/2, 0);
        merge_sort_mt(start+len/2, len-len/2, 0);
    }
    else
    {
        struct Params params = { start, len/2, depth/2 };
        pthread_t thrd;

        pthread_mutex_lock(&mtx);
        printf("Starting subthread...\n");
        pthread_mutex_unlock(&mtx);

        pthread_create(&thrd, NULL, merge_sort_thread, &params);

        merge_sort_mt(start+len/2, len-len/2, depth/2);

        pthread_join(thrd, NULL);

        pthread_mutex_lock(&mtx);
        printf("Finished subthread.\n");
        pthread_mutex_unlock(&mtx);
    }

    merge(start, start+len/2, start+len);
}


void *merge_sort_thread(void *pv)
{
    struct Params *params = (Params*)pv;
    merge_sort_mt(params->start, params->len, params->depth);
    return pv;
}

void merge_sort(float *start, size_t len)
{
    merge_sort_mt(start, len, 4); 
}

int main()
{
    float *data = (float*)malloc(N * sizeof(*data));
    unsigned int i;

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

    free(data);

    return 0;

}