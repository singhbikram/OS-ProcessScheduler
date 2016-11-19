package cs149;

public class Page {
	int pageID;
	String processID;
	//time in memeory
	int inTime = 0;
	boolean inMem = false;
	MemPage mempage;
	int memPageIndex = 0;
	
	// constructor for the page
	public Page(int id, ProcessMem p){
		pageID = id;
		processID = p.pName;
	}

}
