package cs149;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class PageRepAlgo {
	private static LinkedList<ProcessMem> jobQueue;
	private static LinkedList<ProcessMem> runableQueue;
	private static LinkedList<MemPage> memory;
	public static void main(String[] args) {
		// create a process queue
		jobQueue = new LinkedList<ProcessMem>();
		runableQueue = new LinkedList<ProcessMem>();

		// coutner for used pages in memory
		int freePages = 100;

		// create memory and disk memory
		memory = new LinkedList<MemPage>();
		for (int i = 1; i < 100; i++) {
			memory.add(new MemPage(""));
		}
		LinkedList<Integer> diskMemory = new LinkedList();

		// create the 150 jobs
		for (int i = 1; i < 11; i++) {
			jobQueue.add(new ProcessMem(i));
		}

		// sort the process list by arrival time
		Collections.sort(jobQueue);

		// print sorted queue
		System.out.println("PName\t\tATime\t\tNpage");
		for (ProcessMem p : jobQueue) {
			System.out.println(p);
		}

		/*
		 * while(time <= pQueue.peek().arrival_time){ //set process enter/start
		 * time
		 * 
		 * pQueue.peek().timeEnter = time; runableQueue.add(pQueue.pop());
		 * time++; }//end while
		 */ 
		
		//Run PAGE REPLACEMENT ALGORITHM SIMULATOR
		int time = 0;
		while (time < 600) {// for 600
			System.out.println("Running runable queue");
			if(NumberOfFreePages(memory) > 4){
				System.out.println("Adding Jobs to Runnable.");
				if(!jobQueue.isEmpty() && jobQueue.peek().arrival_time == time) {
					runableQueue.add(jobQueue.pop());
				}
			}//end if
			
			//Check Runnable Queue and allocate memory
			allocateProcessMemory();
			time++;
		}//end while

	}

	private static void allocateProcessMemory() throws NoSuchElementException {
		// TODO Auto-generated method stub
		//runableQueue, memory
		if(NumberOfFreePages(memory) > 4){
			//run FIFO, LRU, LFU, MFU, Random
		}
		else
		{
			//run FCFS normally
			if(NumberOfFreePages(memory)>0 && !runableQueue.isEmpty())
			{
				boolean memSet = false;
				while(memSet == false)
				{
					
					int pSize = runableQueue.peek().pSize;
					Random r = new Random();
					int rand = r.nextInt(pSize);
					
					if(runableQueue.peek().page.get(rand).inMem == false)
					{
						ListIterator<MemPage> memIter = memory.listIterator();
						while(memIter.hasNext())
						{
							MemPage memPage = memIter.next();
							if(memPage.free == true)
							{
								//Alloate memory from Free Page List
								memPage.free = false;
								memPage.processID = runableQueue.peek().page.get(rand).processID;
								memIter.set(memPage);
								
								//number of Free Process Memory
								System.out.println("Number of Free Pages: "+NumberOfFreePages(memory));
								
								//Set Process-X page inMem to TRUE
								runableQueue.peek().page.get(rand).inMem = true;
								//break while statements
								memSet = true;
								break;
							}
						}
					}
				}
				
				//reset while statements
				memSet = false;
				
			}
		}
		
	}

	/**
	 * Method counts the number of free pages.
	 * 
	 * @param Linked
	 *            list of memory
	 * @return number of free pages
	 */
	public static int NumberOfFreePages(LinkedList<MemPage> l) {
		int freeSpace = 0;
		for (MemPage m : l) {
			if (m.free)
				freeSpace++;
		}
		return freeSpace;
	}// end main

}
