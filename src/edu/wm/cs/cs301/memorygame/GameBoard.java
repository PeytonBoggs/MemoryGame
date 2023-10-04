package edu.wm.cs.cs301.memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
    private final GamePiece[][] board;
  
	public GameBoard(int rows, int cols, Alphabet a) {
		GamePiece[][] tempBoard = new GamePiece[rows][cols];
		char[] fullAlphabet = a.toCharArray();

		int numCharacters;
		if (rows == 3) {
			numCharacters = 6;
		} else if (rows == 4) {
			numCharacters = 14;
		} else {
			numCharacters = 28;
		}
		
		char[] alphabet = new char[numCharacters];
		System.arraycopy(fullAlphabet, 0, alphabet, 0, numCharacters);
		
		List<Character> symbolList = new ArrayList<Character> ();
		for (char c : alphabet) {
			symbolList.add(c);
		}
		
		symbolList.addAll(symbolList);
		Collections.shuffle(symbolList);
		
    	int symbolIndex = 0;
    	for(int i = 0; i < tempBoard.length; i++) {
    		for (int j = 0; j < tempBoard[i].length; j++) {
    			GamePiece tempGamePiece = new CharacterGamePiece(symbolList.get(symbolIndex));
    			symbolIndex++;
    			tempBoard[i][j] = tempGamePiece;
    		}
    	}
    	
		this.board = tempBoard;
	}
    
    public GamePiece[][] getBoard() {
    	return this.board;
    }
}
