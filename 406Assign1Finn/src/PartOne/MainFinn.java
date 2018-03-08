//James Finn
//CSC 406 Assignment 1 Part 1/Part 2
//Assigned: 1/23/18
//Due: 1/30/18
//Submitted: 1/30/18

package PartOne;

public class MainFinn {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Wrong number of arguments provided!");
			System.exit(1);
		}//end if
		
		 
		TimerFinn counter = new TimerFinn();
		DPSolverFinn solution = new DPSolverFinn();
		
		counter.start();
		
		solution.DPReader(args[0]);
		
		counter.stop();
		
		System.out.print("The time elapsed is: " + counter.getDuration() + " milliseconds.");
		
	}//end main
	
	public void solve() {
		
	}//goes in DPSolver

}//end class