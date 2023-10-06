package edu.wm.cs.cs301.memorygame;
import java.util.Scanner;

public class MemoryGame {
	private GameBoard board;
	private Alphabet a;
	Scanner s = new Scanner(System.in);
	
	public MemoryGame() {	
		String difficulty = getDifficulty();
		String alphabet = getAlphabet();
		
		this.board = createBoard(difficulty, alphabet);
		GamePiece[][] gameBoard = board.getBoard();
		
		int turn = 1;
		boolean finished = false;
		
		while(!finished) {
			System.out.println("Turn: " + turn);
			printBoard(gameBoard);
			
			GamePiece firstCard = getCard(gameBoard);
			firstCard.setVisible(true);
			if (firstCard.getSymbol() == 'q') {
				System.out.println("Game aborted.");
				break;
			}
			
			System.out.println("Turn: " + turn);
			printBoard(gameBoard);

			GamePiece secondCard = getCard(gameBoard);
			secondCard.setVisible(true);
			if (secondCard.getSymbol() == 'q') {
				System.out.println("Game aborted.");
				break;
			}
			
			System.out.println("Turn: " + turn);
			printBoard(gameBoard);
			
			if (firstCard.equals(secondCard)) {
				System.out.println("Match!");
			} else {
				System.out.println("No match. This turn has ended.");
				firstCard.setVisible(false);
				secondCard.setVisible(false);
			}
			
			if (checkWon(gameBoard)) {
				System.out.println("Game won in " + turn + " turns!");
				break;
			}
			
			waitForEnter();
			turn++;
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
	
	public GamePiece getCard(GamePiece[][] gameBoard) {
		int upperRowBound = gameBoard.length;
		int upperColBound = gameBoard[0].length;
		
		GamePiece quit = new CharacterGamePiece('q');
		
		boolean badInput = true;
		while(badInput) {
			System.out.print("Choose a tile [R C] or type 'quit' to exit: ");
			String input = s.nextLine().toLowerCase();
			
			try {
				if (input.equals("quit")) {
					return quit;
				}
				
				if (input.length() != 3) {
					throw new Exception();
				}
				
				Integer r = Integer.valueOf(input.substring(0, 1));
				char middle = input.charAt(1);
				Integer c = Integer.valueOf(input.substring(2, 3));
				
				if (1 <= r && r <= upperRowBound && middle == ' ' && 1 <= c && c <= upperColBound) {
					GamePiece inputCard = gameBoard[Integer.valueOf(input.substring(0, 1)) - 1][Integer.valueOf(input.substring(2, 3)) - 1];
					if (inputCard.isVisible()) {
						throw new Exception();
					}
					return inputCard;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid input.");
			}
		}
		return quit;
	}
	
	public void waitForEnter() {
		System.out.print("Press <ENTER> to continue: ");
		@SuppressWarnings("unused")
		String input = s.nextLine().toLowerCase();
		System.out.println("");
	}
	
	public boolean checkWon(GamePiece[][] gameBoard) {
		boolean won = true;
		
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[0].length; j++) {
				if (!gameBoard[i][j].isVisible()) {
					won = false;
				}
			}
		}
		
		return won;
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