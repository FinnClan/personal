package PartTwo;

public class SudokuBoardFinn {

	int[] boardCells = null;
	final int boardSize;
	int boxWidth, boxHeight;

	public SudokuBoardFinn(int boxWidth, int boxHeight) {

		this.boxWidth = boxWidth;
		this.boxHeight = boxHeight;
		boardCells = new int[(boxWidth * boxHeight) * (boxWidth * boxHeight)];
		boardSize = boxWidth * boxHeight;

	}// end constructor

	public int getBoxForCell(int cellNum) {

		return (getCellRow(cellNum) / boxHeight) * boardSize + (getCellCol(cellNum) / boxWidth);

	}// end getBoxForCell

	public void setCellValue(int cellNum, int value) {

		if ((value <= boardSize) && (value >= 0) && (value >= 0) && (cellNum < boardCells.length)) {
			boardCells[cellNum] = value;
		} // end if
		else {
			System.out.println("Unable to set value " + value + " in cell.");
		} // end else

	}// end setCellValue

	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < boardCells.length; i++) {
			if(i % boardSize == 0) {
				sb.append("\n");
			}//end if
			sb.append(boardCells[i]);
		}//end for

		return sb.toString();

	}// end printBoard

	public int getCellRow(int cellNum) {

		return cellNum / boardSize;

	}// end getCellRow

	public int getCellCol(int cellNum) {

		return cellNum % boardSize;

	}// end getCellCol

	public int getCellVal(int cellNum) {

		return boardCells[cellNum];

	}// end getCellVal

	public int getBoardCellsLength() {
		return boardCells.length;
	}// end getBoardCells

	public int getBoxWidth() {
		return boxWidth;
	}// end getBoxWidth

	public int getBoxHeight() {
		return boxHeight;
	}// end getBoxHeight
	
	public int[] getBoardCells() {
		return boardCells;
	}//end getBoardCells
	
	public int getBoardSize() {
		return boardSize;
	}//end getBoardSize

}// end class
