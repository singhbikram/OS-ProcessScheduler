/**
 * 
 */
package cs149;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author Bikram Sigh
 *
 */
public class ProcessMem implements Comparable{
		String pName;
		int arrival_time;
		double service_time;
		int pSize;
		int[] memSise = {5, 11, 17, 31};
		int index;
		LinkedList<Page> page = new LinkedList<Page>();
		int timeEnter = 0;
		int timeExit = 0;
		
		
		public ProcessMem(int id){
			Random rand = new Random();
			// set seed
			//rand.setSeed(System.currentTimeMillis());
			pName = "P["+id+"]";
			arrival_time = rand.nextInt(100); // will return num between 0 and 99
			service_time = rand.nextInt(4) + 1; // will return num between 1 and 5
			index = rand.nextInt(3);
			pSize = memSise[index]; 
			for(int i = 0; i < index; i++){
				page.add(new Page(i, this));
			}
			
		}
		
		public int getArrival_time(){
			return arrival_time;
		}
		
		public void setService_time(double stime){
			this.service_time = stime;
		}
		
		public double getService_time(){
			return service_time;
		}
		
		@Override
		public int compareTo(Object p) {
			return this.getArrival_time() - ((ProcessMem) p).getArrival_time();
		}// end compareTo
		
		@Override
	    public String toString() {
	        return this.pName + "\t\t" +this.getArrival_time()+ "\t\t"+ this.pSize;
	    }
}// end class process

