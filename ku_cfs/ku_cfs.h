#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <signal.h>
#include <unistd.h>
#define ITIMER_VIRTUAL 1

pid_t pids[3];
int *pid_array;
int tmp_sum = 0;
float calc_nice[] = {0.64, 0.8, 1, 1.25, 1.5625};
float total_vtime = 0;
int finish_time = 0;
struct itimerval {
	struct timeval it_interval;
	struct timeval it_value;
};

typedef struct node{
	float vtime;
	float nice;
	pid_t pid;
	struct node* next;
}Node;

Node* head;
