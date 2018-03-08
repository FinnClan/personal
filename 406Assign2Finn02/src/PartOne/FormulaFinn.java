package PartOne;

import java.io.FileNotFoundException;
import java.util.*;

public class FormulaFinn {

	LinkedList<Integer> clauseList = null;
	FormulaReaderFinn fr = null;
	int[] truthValues = null;
	int[][] formula = null;
	int numVar = 0;
	int numClause = 0;

	public int[][] read(String fileName) {
		fr = new FormulaReaderFinn();

		try {
			fr.read(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found");
			System.exit(2);
		}

		numVar = fr.getNumOfVar();
		numClause = fr.getNumOfClause();

		formula = fr.getFormula();
		truthValues = new int[numVar + 1];
		
		clauseList = new LinkedList<Integer>();

		for (int i = 0; i < numClause; i++) {
			clauseList.add(i);
		} // end for

		clauseList.add(-1);
		return formula;

	}// end read

	public boolean isFormulaEmpty() {

		if (clauseList.getFirst() == -1 || clauseList.isEmpty() == true) {
			return true;
		} // end if
		else {
			return false;
		} // end else

	}// end isFormulaEmpty

	public boolean isClauseEmpty(int clauseNum) {

		for(int i = 0; i < formula[clauseNum].length; i++) {
			if(truthValues[Math.abs(formula[clauseNum][i])] == 0) {
				return false;
			}//end if
		}//end for

		return true;

	}// end isClauseEmpty

	public boolean hasEmptyClause() {

		for (int i : clauseList) {
			if (i == -1) {
				break;
			} // end if
			if (isClauseEmpty(i)) {
				return true;
			} // end for
			
		} // end for
		return false;

	}// end hasEmptyClause

	public int firstAvailable() {
		for (int i = 1; i < truthValues.length; i++) {
			if (truthValues[i] == 0)
				return i;
		} // end for
		return 0;

	}// end firstAvailable

	public LinkedList<Integer> seperateClauses() {

		LinkedList<Integer> tempList = new LinkedList<Integer>();
		int i = 0;
		while (clauseList.get(i) != -1) {
			tempList.add(clauseList.get(i));
			i++;
		} // end while
		tempList.add(clauseList.get(i));

		return tempList;

	}// end seperateClauses

	public void assign(int var, int value) {

		LinkedList<Integer> tempList = seperateClauses();
		LinkedList<Integer> satList = new LinkedList<Integer>();
		LinkedList<Integer> unsatList = new LinkedList<Integer>();
		truthValues[var] = value;
		
		while (tempList.peekFirst() != -1) {
			if (isClauseSatisfied(tempList.getFirst())) {
				satList.addFirst(tempList.removeFirst());
			} // end if
			else {
				unsatList.add(tempList.removeFirst());
			} // end else
		} // end while

		satList.addAll(tempList);
		satList.addFirst(-1);
		unsatList.addAll(satList);
		
		clauseList = unsatList;

	}// end assign

	public boolean isClauseSatisfied(int cno) {
		
		for (int i : formula[cno]) {
			if (i * truthValues[Math.abs(i)] > 0) {
				return true;
			}//end if
		} // end for
		
		return false;

	}// end isClauseSatisfied

	public void printAssignment() {
		System.out.println("The variable values are: ");
		
		for (int i = 1; i <= truthValues.length - 1; i++) {
			if (truthValues[i] == 0) {
				System.out.println("x" + i + ": unassigned");
			} // end if
			else if (truthValues[i] == 1) {
				System.out.println("x" + i + ": TRUE");
			} // end else if
			else if (truthValues[i] == -1) {
				System.out.println("x" + i + ": FALSE");
			} // end else if

		} // end for

	}// end printAssignment

	public void printFormula() {
		System.out.println("The array is: \n");
		for (int[] i : formula) {
			for (int j : i) {
				System.out.println(j);
			} // end for
		} // end for

	}// end printFormula

	public void print() {
		System.out.println("The clauses are: ");
		for (Integer i : clauseList) {
			System.out.print(i + " ");
		} // end for
		System.out.println();
	}// end print

	public void unset(int pos) {
		truthValues[pos] = 0;

		if (clauseList.indexOf(-1) != clauseList.size() - 1) {
			clauseList.removeFirstOccurrence(-1);
		} // end if

	}// end unset

}// end class
