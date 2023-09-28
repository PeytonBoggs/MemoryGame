package edu.wm.cs.cs301.memorygame;

public class GameBoard {
    private final GamePiece[][] board;
    
    public GameBoard(int rows, int cols) {
    	board = new GamePiece[rows][cols];
		System.out.println(board);
		
		int[][] testBoard = new int[rows][cols];
		for(int i = 0; i < testBoard.length; i++) {
			for(int j = 0; j < testBoard[i].length; j++) {
				System.out.print("O");
			}
			System.out.println();
		}
				
		
		System.out.print("Total: " + rows + " rows and " + cols + " columns");
	}
  
	public GameBoard(int rows, int cols, Alphabet a) {
		board = null;
	}
}
