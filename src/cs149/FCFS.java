package cs149;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class FCFS {
	
		final static int NUMBER_OF_JOBS = 30;
		@SuppressWarnings("unchecked")
		public static void main(String[] args){
			
	//using queue		
			LinkedList<Process> pQueue = new LinkedList<Process>();
			LinkedList<Process> doneQueue = new LinkedList<Process>();
			double total_avgAt = 0;
			double total_avgWt = 0;
			double total_thput = 0;
			double total_avgRt = 0;
			System.out.println("ATurnAround Time\tAWait Time\tThroughput\tAResponse Time");
		for(int j = 0; j <5; j++){
			for(int i = 1; i <= NUMBER_OF_JOBS; i++){
				Process p = new Process(i);
				//System.out.println(p);
				pQueue.add(p);
			}//end for
			
			double quantum1 = 0;
			int done1 = NUMBER_OF_JOBS;
			int wtime = 0;
			while(!pQueue.isEmpty()){
				if(pQueue.peek().getArrival_time() > quantum1) {
					quantum1++;
				} else {
					if(pQueue.peek().getRemaining_time() > 0 && pQueue.peek().getArrival_time() <= quantum1 ){
						
						//decrement remaining time for the process
						pQueue.peek().setRemaining_time(pQueue.peek().getRemaining_time() - 1);
						//last quantum of running the process will be saved as finish time
						pQueue.peek().setfinish_time(quantum1);
						//increment the quantum
						quantum1++;
						// stores the last run time to calculate the wait time
						pQueue.peek().setLast_run(quantum1);
					}// end if
					// checked if process is done and removes the process from the Run-queue
					if(pQueue.peek().getRemaining_time() <= 0 ){ 
						//store the wait time
						pQueue.peek().setWt(pQueue.peek().getfinish_time() - (pQueue.peek().getArrival_time() + pQueue.peek().getService_time()));
						doneQueue.add(pQueue.remove(0));
					}//end if
					else{
						pQueue.add(pQueue.remove(0));
					}//end else
				}//end else
				
			}//end while
			//System.out.println("Size of doneQueue: "+ doneQueue.size());
			double responsetime = 0;
			double waitTime = 0;
			double turnaorundtime = 0;
			double total_tt = 0;
			for(Process p: doneQueue){
				responsetime = responsetime + p.getArrival_time();
				waitTime = waitTime + p.getWt();
				turnaorundtime = p.getfinish_time() - p.getArrival_time();
				total_tt += turnaorundtime;
	 			//System.out.print(p);
	 			//System.out.println("\t\t"+turnaorundtime);
		    }//end for
			
			
			
			
			System.out.printf("   %.2f\t\t   %.2f\t   %.2f\t\t   %.2f\n",total_tt/NUMBER_OF_JOBS,waitTime/NUMBER_OF_JOBS,quantum1/NUMBER_OF_JOBS,responsetime/NUMBER_OF_JOBS);
			
			total_avgAt = total_avgAt + total_tt/NUMBER_OF_JOBS;
			total_avgWt = total_avgWt + waitTime/NUMBER_OF_JOBS;
			total_thput = total_thput + quantum1/NUMBER_OF_JOBS;
			total_avgRt = total_avgRt + responsetime/NUMBER_OF_JOBS;
			doneQueue.clear();
		 }//end for X5	
		System.out.println("\n5 Run Average:");
		System.out.println("ATurnAround Time\tAWait Time\tThroughput\tAResponse Time");
		System.out.printf("   %.2f\t\t   %.2f\t   %.2f\t\t   %.2f\n",total_avgAt/5,total_avgWt/5,total_thput/5,total_avgRt/5);
		
		}//end main
}// end FCFS

	/*
		final static int NUMBER_OF_JOBS = 30;
		public static void main(String[] args){
			
	//using queue		
			PriorityQueue<Process> pQueue = new PriorityQueue<Process>();
			Queue<Process> doneQueue = new LinkedList<Process>();
			
//		for(int j = 0; j <5; j++){
			for(int i = 1; i <= NUMBER_OF_JOBS; i++){
				Process p = new Process(i);
				//System.out.println(p);
				pQueue.add(p);
			}//end for
			
			
			int quantum1 = 0;
			int done1 = NUMBER_OF_JOBS;
			int wtime = 0;
			while(!pQueue.isEmpty()){
				if(pQueue.peek().getArrival_time() > quantum1) {
					quantum1++;
				} else {
					if(pQueue.peek().getRemaining_time() > 0 && pQueue.peek().getArrival_time() <= quantum1 ){
						
						//decrement remaining time for the process
						pQueue.peek().setRemaining_time(pQueue.peek().getRemaining_time() - 1);
						//last quantum of running the process will be saved as finish time
						pQueue.peek().setfinish_time(quantum1);
						// will store the cumulative wait time
						wtime = (quantum1 - pQueue.peek().getLast_run()) + pQueue.peek().getWt();
						pQueue.peek().setWt(wtime);
						//increment the quantum
						quantum1++;
						// stores the last run time to calculate the wait time
						pQueue.peek().setLast_run(quantum1);
					}// end if
					// checked if process is done and removes the process from the Run-queue
					if(pQueue.peek().getRemaining_time() <= 0 ){ 
						doneQueue.add(pQueue.remove());
					}//end if
					else{
						pQueue.add(pQueue.remove());
					}//end else
				}//end else
				
			}//end while
			//System.out.println("Size of doneQueue: "+ doneQueue.size());
			double responsetime = 0;
			double waitTime = 0;
			double turnaorundtime = 0;
			double total_tt = 0;
			for(Process p: doneQueue){
				responsetime = p.getfinish_time() - p.getArrival_time();
				waitTime = waitTime + p.getWt();
				turnaorundtime = p.getfinish_time() - p.getArrival_time();
				total_tt += turnaorundtime;
	 			//System.out.print(p);
	 			//System.out.println("\t\t"+turnaorundtime);
		    }//end for
			
			
			System.out.println("Average Turn Around Time: "+ total_tt/NUMBER_OF_JOBS);
			System.out.println("Average Wait Time       : "+ waitTime/NUMBER_OF_JOBS);
	//	 }//end for X5	
		}//end main
}// end FCFS


package cs149;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {
	final static int NUMBER_OF_JOBS = 30;
	public static void main(String[] args){
		
//using queue		
		Queue<Process> pQueue = new LinkedList<Process>();
		Queue<Process> doneQueue = new LinkedList<Process>();
		
	for(int j = 0; j <5; j++){
		for(int i = 1; i <= NUMBER_OF_JOBS; i++){
			Process p = new Process(i);
			//System.out.println(p);
			pQueue.add(p);
		}//end for
		
		int quantum1 = 0;
		int done1 = NUMBER_OF_JOBS;
		int wtime = 0;
		while(!pQueue.isEmpty()){
			if(pQueue.peek().getArrival_time() > quantum1) {
				quantum1++;
			} else {
				if(pQueue.peek().getRemaining_time() > 0 && pQueue.peek().getArrival_time() <= quantum1 ){
					
					//decrement remaining time for the process
					pQueue.peek().setRemaining_time(pQueue.peek().getRemaining_time() - 1);
					//last quantum of running the process will be saved as finish time
					pQueue.peek().setfinish_time(quantum1);
					// will store the cumulative wait time
					wtime = (quantum1 - pQueue.peek().getLast_run()) + pQueue.peek().getWt();
					pQueue.peek().setWt(wtime);
					//increment the quantum
					quantum1++;
					// stores the last run time to calculate the wait time
					pQueue.peek().setLast_run(quantum1);
				}// end if
				// checked if process is done and removes the process from the Run-queue
				if(pQueue.peek().getRemaining_time() <= 0 ){ 
					doneQueue.add(pQueue.remove());
				}//end if
				else{
					pQueue.add(pQueue.remove());
				}//end else
			}//end else
			
		}//end while
		//System.out.println("Size of doneQueue: "+ doneQueue.size());
		double responsetime = 0;
		double waitTime = 0;
		double turnaorundtime = 0;
		double total_tt = 0;
		for(Process p: doneQueue){
			responsetime = p.getfinish_time() - p.getArrival_time();
			waitTime = waitTime + p.getWt();
			turnaorundtime = p.getfinish_time() - p.getArrival_time();
			total_tt += turnaorundtime;
 			//System.out.print(p);
 			//System.out.println("\t\t"+turnaorundtime);
	    }//end for
		
		
		System.out.println("Average Turn Around Time: "+ total_tt/NUMBER_OF_JOBS);
		System.out.println("Average Wait Time       : "+ waitTime/NUMBER_OF_JOBS);
	 }//end for X5	
	}//end main
}// end round robin

*/