#include "ku_cfs.h"

void print_list(Node* fst){
	Node* temp;
	for(temp=fst;temp;temp=temp->next){
		printf("pid: %d, vtime: %f, nice: %f\n", temp->pid, temp->vtime, temp->nice);
	}
}

Node* add(Node *node, int vtime, pid_t pid, float nice){
	Node* tmp = (Node*)malloc(sizeof(Node));

	if(tmp == NULL){
		exit(0);
	}
	tmp->vtime = vtime;
	tmp->nice = nice;
	tmp->pid = pid;
	tmp->next = NULL;
	if (node != NULL){
		node->next = tmp;
	}
	return tmp;
}

Node* get_last_node(Node *node){
	Node* temp;
	temp = node;
	while(1){
		if(temp->next == NULL){
			return temp;
		}
		temp = temp->next;
	}
}

void calcu_set_list(){
	
}
void timer_handler(int signum){
    static int count = 0;
    printf("expired %d\n", ++count);
	kill(head->next->pid, SIGSTOP);
	// cal -> set
	calcu_set_list();
	head = head->next;
	kill(head->next->pid, SIGCONT);
	if(finish_time == count){
		exit(0);
	}
}

void init_timer(){
	struct sigaction sa;
    struct itimerval timer;
 
    memset(&sa, 0, sizeof(sa));
    sa.sa_handler = &timer_handler;
    sigaction(SIGVTALRM, &sa, NULL);
 
    timer.it_interval.tv_sec = 1;
    timer.it_interval.tv_usec = 0;
    timer.it_value.tv_sec = 1;
    timer.it_value.tv_usec = 0;

	setitimer(ITIMER_VIRTUAL, &timer, NULL);
}

int main(int argc, char *argv[]){
	int tmp_arr[6];
	int tmp_idx = 0;
	char start_char[2];

	for(int i=1; i<6; i++){
		tmp_arr[i] = atoi(argv[i]);
	}

	finish_time = atoi(argv[6]);

	head = add(NULL, 0, 0, 0);

	for(int l=0;l<5;l++){
		tmp_sum += tmp_arr[l];
	}

	for(int i=0; i<tmp_sum; i++){
		start_char[0] = 'A'+i;
		start_char[1] = '\0';
		pids[i] = fork();
		if(pids[i] < 0){
			return -1;
		}else if(pids[i] == 0){
			execl("./ku_app", "ku_app", start_char, NULL);
		}
	}

	sleep(2);
	
	for(int k=0;k<5;k++){
		if(tmp_arr[k] != 0){
			for(int j=0; j<tmp_arr[k]; j++){
				add(get_last_node(head), 0, pids[tmp_idx], calc_nice[k]);
				tmp_idx++;
			}
		}
	}
	print_list(head);
	kill(pids[0], SIGCONT);
	init_timer();
	while(1){
	
	}
	
	return 0;
}