package edu.wm.cs.cs301.memorygame;
import java.util.Scanner;

public class MemoryGame {
	private GameBoard board;
	private Alphabet a;
	Scanner s = new Scanner(System.in);
	
	public MemoryGame() {	
		String selectedDifficulty = "";
		boolean badInput = true;
		while(badInput) {
			System.out.print("Select difficulty: 'easy', 'medium', or 'hard': ");
			selectedDifficulty = s.nextLine().toLowerCase();
			if (selectedDifficulty.equals("easy") || selectedDifficulty.equals("medium") || selectedDifficulty.equals("hard")) {
				badInput = false;
			} else {
				System.out.println("Invalid difficulty.");
			}
		}
		
		String selectedAlphabet = "";
		badInput = true;
		while (badInput) {
			System.out.print("Select symbol set: 'latin' or 'greek': ");
			selectedAlphabet = s.nextLine().toLowerCase();
			if (selectedAlphabet.equals("latin") || selectedAlphabet.equals("greek")) {
				badInput = false;
			} else {
				System.out.println("Invalid symbol set.");
			}
		}
		
		if (selectedAlphabet.equals("latin")) {
			this.a = new LatinAlphabet();
			if (selectedDifficulty.equals("easy")) {
				board = new GameBoard(3, 4, a);
			} else if (selectedDifficulty.equals("medium")) {
				board = new GameBoard(4, 7, a);
			} else {
				board = new GameBoard(7, 8, a);
			}
		} else {
			this.a = new GreekAlphabet();
			if (selectedDifficulty.equals("easy")) {
				board = new GameBoard(3, 4, a);
			} else if (selectedDifficulty.equals("medium")) {
				board = new GameBoard(4, 7, a);
			} else {
				board = new GameBoard(7, 8, a);
			}
		}
	
		GamePiece[][] gameBoard = board.getBoard();
		
		int turn = 1;
		boolean finished = false;
		
		while(!finished) {
			System.out.println("Turn: " + turn);
			printBoard(gameBoard);
			System.out.print("Choose a tile [R C] or type 'quit' to exit: ");
			String firstInput = s.nextLine();
			
			finished = true;
		}
	}
	
	public void printBoard(GamePiece[][] gameBoard) {
		System.out.print("     ");
		for (int i = 0; i < gameBoard[0].length; i++) {
			System.out.print((i+1) + "   ");
		}
		System.out.println();
		
		System.out.print("   =");
		for (int i = 0; i < gameBoard[0].length; i++) {
			System.out.print("====");
		}
		System.out.println("");
		
		for (int i = 0; i < gameBoard.length; i++) {
			System.out.print(i+1 + " ||");
			for (int j = 0; j < gameBoard[i].length; j++) {
				System.out.print(" " + gameBoard[i][j].getSymbol() + " |");
			}
			System.out.println("|");
			if (i+1 != gameBoard.length) {
				System.out.print("  --");
				for (int j = 0; j < gameBoard[i].length; j++) {
					System.out.print("----");
				}
				System.out.println("-");
			}
		}
		
		System.out.print("   =");
		for (int i = 0; i < gameBoard[0].length; i++) {
			System.out.print("====");
		}
		System.out.println();
	}
}