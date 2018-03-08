package PartTwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.PrintWriter;

import PartOne.TimerFinn;

import java.io.IOException;

public class SudokuToSatReducerFinn {

	static final String tempFileName = "reducedSudoku.cnf";
	private final Writer writer;
	SudokuBoardFinn board = null;
	private StringBuilder sb = new StringBuilder();

	public SudokuToSatReducerFinn(File file) throws IOException {
		writer = new PrintWriter(tempFileName);
		solve(file);
	}// end constructor

	private void solve(File file) throws IOException {

		createBoard(file);
		TimerFinn timer = new TimerFinn();

		timer.start();

		reduceBoard();

		timer.stop();
		System.out.println("The time elapsed is: " + timer.getDuration() + " milliseconds.");
		writer.flush();
		writer.close();

	}// end solve

	private void createBoard(File inFile) {

		Scanner sc = null;
		try {
			sc = new Scanner((inFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Pattern p = Pattern.compile("c");

		while (sc.findInLine(p) != null) {
			sc.nextLine();
		} // end while

		int boxWidth = sc.nextInt();
		int boxHeight = sc.nextInt();

		board = new SudokuBoardFinn(boxWidth, boxHeight);

		int i = 0;
		for (int col = 0; col < board.getBoardCellsLength(); col++) {
			board.setCellValue(i, sc.nextInt());
			i++;
		} // end for

		sc.close();

	}// end createBoard

	private void reduceBoard() throws IOException {
		sb.append("p cnf " + ((board.getBoardCellsLength() * board.getBoardSize()) + " "
				+ ((numChooseTwo(board.getBoardSize(), 2) + 1) * (board.getBoardSize() * board.getBoardSize() * 4)
						+ (existingValues(board.getBoardCells())))
				+ "\n"));

		for (int i = 0; i < board.getBoardCellsLength(); i++) {
			if (board.getCellVal(i) != 0) {
				sb.append(encode(board.getCellRow(i), board.getCellCol(i), board.getCellVal(i)) + " 0\n");
			} // end if
		} // end for

		for (int i = 0; i < board.getBoardSize(); i++) {
			for (int j = 1; j <= board.getBoardSize(); j++) {
				atLeastOneInRow(i, j);
				atMostOneInRow(i, j);
				writer.write(sb.toString());
				sb = new StringBuilder();
				atLeastOneInCol(i, j);
				atMostOneInCol(i, j);
				writer.write(sb.toString());
				sb = new StringBuilder();
				atLeastOneInBox(i, j);
				atMostOneInBox(i, j);
				writer.write(sb.toString());
				sb = new StringBuilder();
				atLeastOneInRange(i, j);
				atMostOneInRange(i, j);
				writer.write(sb.toString());
				sb = new StringBuilder();
			} // end inner for
		} // end outer for
	}// end reduceBoard

	private void atLeastOneInRow(int row, int value) throws IOException {

		for (int col = 0; col < board.getBoardSize(); col++) {
			sb.append(encode(row, col, value) + " ");
		} // end for
		sb.append("0\n");

	}// end atLeastOneInRow

	private void atMostOneInRow(int row, int value) throws IOException {
		for (int col = 0; col < board.getBoardSize() - 1; col++) {
			for (int extraCol = col + 1; extraCol < board.getBoardSize(); extraCol++) {
				sb.append("-" + encode(row, col, value) + " ");
				sb.append("-" + encode(row, extraCol, value) + " 0\n");
			} // end inner for
		} // end outer for

	}// end atMostOneInRow

	private void atLeastOneInCol(int col, int value) throws IOException {
		for (int row = 0; row < board.getBoardSize(); row++) {
			sb.append(encode(row, col, value) + " ");
		} // end for
		sb.append("0\n");

	}// end atLeastOneInRow

	private void atMostOneInCol(int col, int value) throws IOException {

		for (int row = 0; row < board.getBoardSize() - 1; row++) {
			for (int extraRow = row + 1; extraRow < board.getBoardSize(); extraRow++) {
				sb.append("-" + encode(row, col, value) + " ");
				sb.append("-" + encode(row, extraRow, value) + " 0\n");
			} // end inner for
		} // end outer for

	}// end atMostOneInRow

	private void atLeastOneInBox(int box, int value) throws IOException {

		for (int i = 0; i < board.getBoardSize(); i++) {
			sb.append(encode(board.getCellRow(cellIndex(box, i)), board.getCellCol(cellIndex(box, i)), value) + " ");
		} // end for

		sb.append("0\n");

	}// end atLeastOneInRow

	private void atMostOneInBox(int box, int value) throws IOException {

		for (int i = 0; i < board.getBoardSize() - 1; i++) {
			for (int j = i + 1; j < board.getBoardSize(); j++) {
				sb.append("-" + encode(board.getCellRow(cellIndex(box, i)), board.getCellCol(cellIndex(box, i)), value)
						+ " ");
				sb.append("-" + encode(board.getCellRow(cellIndex(box, j)), board.getCellCol(cellIndex(box, j)), value)
						+ " ");
				sb.append("0\n");
			} // end inner for
		} // end outer for
	}// end atMostOneInRow

	private void atLeastOneInRange(int row, int col) throws IOException {
		for (int i = 1; i <= board.getBoardSize(); i++) {
			sb.append(encode(row, col, i) + " ");
		} // end for
		sb.append("0\n");
	}// end atLeastOneInRow

	private void atMostOneInRange(int row, int col) throws IOException {
		for (int i = 0; i < board.getBoardSize() - 1; i++) {
			for (int j = i + 1; j < board.getBoardSize(); j++) {
				sb.append("-" + encode(row, col, i) + " ");
				sb.append("-" + encode(row, col, j) + " 0\n");
			} // end inner for
		} // end outer for

	}// end atMostOneInRow

	private int cellIndex(int box, int boxCell) {
		return (box / board.getBoxHeight()) * board.getBoxHeight()
				+ (boxCell / board.getBoxHeight()) * board.getBoardSize()
				+ (box % board.getBoxWidth()) * board.getBoxWidth() + (boxCell % board.getBoxWidth());
	}// end cellIndex

	private String encode(int row, int col, int searchValue) {
		return Integer.toString(
				(row * board.getBoardSize() * board.getBoardSize()) + (col * board.getBoardSize()) + searchValue);
	}// end encode

	private int numChooseTwo(int boardSize, int choose) {
		if (choose == 0) {
			return 1;
		} // end if
		else if (choose == 1) {
			return 1;
		} // end else
		else {
			return (boardSize * (boardSize - 1)) / 2;
		} // end else
	}// end numChooseTwo

	private int existingValues(int[] boardCells) {
		int count = 0;
		for (int i = 0; i < boardCells.length; i++) {
			if (boardCells[i] != 0) {
				count++;
			} // end if
		} // end for

		return count;
	}// end existingValues

}// end class
