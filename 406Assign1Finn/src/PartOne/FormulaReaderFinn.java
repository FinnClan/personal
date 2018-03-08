//James Finn
//CSC 406 Assignment 1 Part 1/Part 2
//Assigned: 1/23/18
//Due: 1/30/18
//Submitted: 1/30/18

package PartOne;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.io.FileNotFoundException;

public class FormulaReaderFinn {

	int clause, vars;
	int[][] formula;

	public void read(String inFile) throws FileNotFoundException {
		Scanner sc =  new Scanner(new File(inFile));
		Pattern p = Pattern.compile("p\\scnf\\s(\\d+)\\s(\\d+)");
		Matcher m = p.matcher(sc.nextLine());
		
		while(! m.matches()) {
		
			m = p.matcher(sc.nextLine());
			
		}//end while
		
		vars = Integer.parseInt(m.group(1));
		clause = Integer.parseInt(m.group(2));
		int rows = 0;
		
		formula = new int[clause][];
		String[] token; 
		
		while(sc.hasNextLine()) {
				token = (sc.nextLine()).split(" ");
				formula[rows] = new int[token.length -1];
				
				for(int count = 0; count < token.length; count++) {
					if(Integer.parseInt(token[count]) != 0) {
						formula[rows][count] = Integer.parseInt(token[count]);
					}//end if
				}//end for
				
				rows++;
				
		}//end while
		
		sc.close();
	}//end formula reader
	
	public int getNumOfClause() {
		return clause;
	}//end 
	
	public int getNumOfVar() {
		return vars;
	}//end
	
	public int[][] getFormula(){
		return formula;
	}//end
	
}//end class