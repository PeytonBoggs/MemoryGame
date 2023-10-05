package edu.wm.cs.cs301.memorygame;
import java.util.Scanner;

public class MemoryGame {
	private GameBoard board;
	private Alphabet a;
	Scanner s = new Scanner(System.in);
	
	public MemoryGame() {	
		String difficulty = getDifficulty();
		String alphabet = getAlphabet();
		
		GameBoard board = createBoard(difficulty, alphabet);
		GamePiece[][] gameBoard = board.getBoard();
		
		int turn = 1;
		boolean finished = false;
		
		while(!finished) {
			System.out.println("Turn: " + turn);
			printBoard(gameBoard);
			
			String firstCardRC = getRC(gameBoard);
			gameBoard[Integer.valueOf(firstCardRC.substring(0, 1)) - 1][Integer.valueOf(firstCardRC.substring(2, 3)) - 1].setVisible(true);
			
			System.out.println("Turn: " + turn);
			printBoard(gameBoard);
			
			String secondCardRC = getRC(gameBoard);
			gameBoard[Integer.valueOf(secondCardRC.substring(0, 1)) - 1][Integer.valueOf(secondCardRC.substring(2, 3)) - 1].setVisible(true);
			
			System.out.println("Turn: " + turn);
			printBoard(gameBoard);
			
			finished = true;
		}
	}
	
	public String getDifficulty() {
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
		return selectedDifficulty;
	}
	
	public String getAlphabet() {
		String selectedAlphabet = "";
		boolean badInput = true;
		while (badInput) {
			System.out.print("Select symbol set: 'latin' or 'greek': ");
			selectedAlphabet = s.nextLine().toLowerCase();
			if (selectedAlphabet.equals("latin") || selectedAlphabet.equals("greek")) {
				badInput = false;
			} else {
				System.out.println("Invalid symbol set.");
			}
		}
		return selectedAlphabet;
	}
	
	public GameBoard createBoard(String difficulty, String alphabet) {
		if (alphabet.equals("latin")) {
			this.a = new LatinAlphabet();
			if (difficulty.equals("easy")) {
				board = new GameBoard(3, 4, a);
			} else if (difficulty.equals("medium")) {
				board = new GameBoard(4, 7, a);
			} else {
				board = new GameBoard(7, 8, a);
			}
		} else {
			this.a = new GreekAlphabet();
			if (difficulty.equals("easy")) {
				board = new GameBoard(3, 4, a);
			} else if (difficulty.equals("medium")) {
				board = new GameBoard(4, 7, a);
			} else {
				board = new GameBoard(7, 8, a);
			}
		}
		return board;
	}
	
	public String getRC(GamePiece[][] gameBoard) {
		int upperRowBound = gameBoard.length;
		int upperColBound = gameBoard[0].length;
		
		boolean badInput = true;
		while(badInput) {
			System.out.print("Choose a tile [R C] or type 'quit' to exit: ");
			String input = s.nextLine().toLowerCase();
			
			try {
				if (input.equals("quit")) {
					return input;
				}
				
				if (input.length() != 3) {
					throw new Exception();
				}
				
				Integer r = Integer.valueOf(input.substring(0, 1));
				char middle = input.charAt(1);
				Integer c = Integer.valueOf(input.substring(2, 3));

				if (1 <= r && r <= upperRowBound && middle == ' ' && 1 <= c && c <= upperColBound) {
					return input;
				}
			} catch (Exception e) {
				System.out.println("Invalid input.");
			}
		}
		return "";
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