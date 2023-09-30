package edu.wm.cs.cs301.memorygame;
import java.util.Scanner;

public class MemoryGame {
	private GameBoard board;
	Scanner s = new Scanner(System.in);
	
	
	
	public MemoryGame() {
		String difficulty = "";
		
		while(!difficulty.equals("easy") && !difficulty.equals("medium") && !difficulty.equals("hard")) {
			System.out.println("Select difficulty: 'easy', 'medium', or 'hard'.");
			difficulty = s.nextLine().toLowerCase();
			if (difficulty.equals("easy")) {
				board = new GameBoard(3, 4);
			} else if (difficulty.equals("medium")) {
				board = new GameBoard(4, 7);
			} else if (difficulty.equals("hard")) {
				board = new GameBoard(7, 8);
			} else {
				System.out.println("Invalid difficulty.");
			}
		}
	
		GamePiece[][] gameBoard = board.getBoard();
		
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
	}
}
