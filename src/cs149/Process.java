package cs149;

import java.util.Random;

public class Process implements Comparable{
	int arrival_time;
	double service_time;
	int priority;
	int processID;
	double finish_time;
	double remaining_time;
	double wt;
	double last_run;
	
	public Process(int id){
		Random rand = new Random();
		// set seed
		//rand.setSeed(System.currentTimeMillis());
		processID = id;
		finish_time = 0;
		last_run = 0;
		arrival_time = rand.nextInt(100); // will return num between 0 and 99
		wt = -(arrival_time);
		service_time = rand.nextInt(11); // will return num between 0 and 10
		if (service_time == 0) service_time += 0.1; // service_time = 0.1 .. 10
		remaining_time = service_time;
		priority = rand.nextInt(5);
		if (priority == 0) priority += 1; // priority between 1 .. 4
		
		//System.out.println("\t"+arrival_time+ "\t\t\t"+ service_time);
	}
	

	public void setWt(double d){
		this.wt = d;
	}
	
	public double getWt(){
		return this.wt;
	}
	

	public void setLast_run(double lrtime){
		this.last_run = lrtime;
	}
	
	public double getLast_run(){
		return this.last_run;
	}
	
	public void setRemaining_time(double rtime){
		this.remaining_time = rtime;
	}
	
	public double getRemaining_time(){
		return this.remaining_time;
	}
	
	public void setfinish_time(double ftime){
		this.finish_time = ftime;
	}
	
	public double getfinish_time(){
		return finish_time;
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
	
	public int getPriority(){
		return this.priority;
	}
	
	@Override
	public int compareTo(Object p) {
		return this.getArrival_time() - ((Process) p).getArrival_time();
	}// end compareTo
	
	@Override
    public String toString() {
        return this.processID+"\t\t"+this.getArrival_time()+ "\t\t"+ this.getService_time()+ "\t\t"+ this.getfinish_time();
    }
}// end class process