package cs149;

public class MemPage {
	boolean free;
	String processID;
	
	public MemPage(String s){
		free = true;
		processID = s;
	}

}
