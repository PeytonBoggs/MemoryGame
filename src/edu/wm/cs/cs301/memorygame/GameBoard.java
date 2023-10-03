package edu.wm.cs.cs301.memorygame;

public class GameBoard {
    private final GamePiece[][] board;
    private char[] alphabet;
  
	public GameBoard(int rows, int cols, Alphabet a) {
		GamePiece[][] tempBoard = new GamePiece[rows][cols];
		alphabet = a.toCharArray();
    	
    	for(int i = 0; i < tempBoard.length; i++) {
    		for (int j = 0; j < tempBoard[i].length; j++) {
    			GamePiece tempGamePiece = new CharacterGamePiece('x');
    			tempBoard[i][j] = tempGamePiece;
    		}
    	}
    	
		this.board = tempBoard;
	}
    
    public GamePiece[][] getBoard() {
    	return this.board;
    }
}
