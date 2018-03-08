package PartTwo;

import java.io.*;

public class MainFinn {

	public static void main(String args[]) throws IOException {
		if(args.length != 1) {
			System.out.println("Wrong number of arguments provided!");
			System.exit(1);
		}//end if
		
		new SudokuToSatReducerFinn(new File(args[0]));
		
	}//end main

}//end class
