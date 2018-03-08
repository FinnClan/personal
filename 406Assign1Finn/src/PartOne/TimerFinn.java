//James Finn
//CSC 406 Assignment 1 Part 1/Part 2
//Assigned: 1/23/18
//Due: 1/30/18
//Submitted: 1/30/18

package PartOne;

public class TimerFinn{

	private long beginTime = 0;
	private long finishTime = 0;
	
	public TimerFinn() {
		
		
	}//end constructor
	
	public void start() {
		
		this.beginTime = System.currentTimeMillis();
		
	}//end start
	
	public void stop() {
		
		this.finishTime = System.currentTimeMillis();
		
	}//end stop
	
	public long getDuration() {
		
		return (finishTime - beginTime);
		
	}//end getDuration
	
}//end class
