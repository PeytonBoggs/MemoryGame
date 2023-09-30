package edu.wm.cs.cs301.memorygame;

public class GameBoard {
    private final GamePiece[][] board;
  
//	public GameBoard(int rows, int cols, Alphabet a) {
//		board = null;
//	}
    
    public GameBoard(int rows, int cols) {
    	GamePiece[][] tempBoard = new GamePiece[rows][cols];
    	
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
