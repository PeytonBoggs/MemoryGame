package edu.wm.cs.cs301.memorygame;
import java.util.Scanner;

public class MemoryGame {
	private GameBoard board;
	Scanner s = new Scanner(System.in);
	
	
	
	public MemoryGame() {
		System.out.print("Enter the number of rows:");
		int rows = s.nextInt();
		System.out.print("Enter the number of cols:");
		int cols = s.nextInt();
		
		
		board = new GameBoard(rows, cols);
	}
}
