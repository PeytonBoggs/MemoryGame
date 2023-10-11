package edu.wm.cs.cs301.memorygame;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MemoryGame {
	private GameBoard board;
	private Alphabet a;
	Scanner s = new Scanner(System.in);
		
	//Calls to get welcome message and starts a new game
	public MemoryGame() {	
		printWelcomeMessage();
		waitForEnter();
		
		playGame();
	}
	
	//Plays one instance of a game
	public void playGame() {
		try {
			printLeaderboard();
		} catch (IOException e) {
			System.out.println("IOException caught.");
			e.printStackTrace();
		}
		
		String difficulty = getDifficulty();
		String alphabet = getAlphabet();
		
		this.board = createBoard(difficulty, alphabet);
		GamePiece[][] gameBoard = board.getBoard();
		
		int turn = 1;
		boolean quit = false;
		
		while(!quit) {
			System.out.println("Turn: " + turn);
			printBoard(gameBoard);
			
			GamePiece firstCard = getCard(gameBoard);
			firstCard.setVisible(true);
			if (firstCard.getSymbol() == 'q') {
				System.out.println("Game aborted.");
				quit = true;
				break;
			}
			
			System.out.println("Turn: " + turn);
			printBoard(gameBoard);

			GamePiece secondCard = getCard(gameBoard);
			secondCard.setVisible(true);
			if (secondCard.getSymbol() == 'q') {
				System.out.println("Game aborted.");
				quit = true;
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
		
		System.out.println("Revealed Board:");
		revealBoard(gameBoard);
		
		if (!quit) {
			try {
				updateLeaderboard(difficulty, turn);
			} catch (IOException e) {
				System.out.println("IOException caught.");
				e.printStackTrace();
			}	
		}
		
		boolean playAgain = getPlayAgain();
		if (playAgain) {
			playGame();
		}
	}
	
	//Prints a welcome message to console
	public void printWelcomeMessage() {
		System.out.println("Welcome to Memory Game!");
		System.out.println("In this game, you'll be shown a grid of cards that are initially flipped over.");
		System.out.println("There will be numbers along the grid that indicate which row and column a card is on.");
		System.out.println("Each turn, you can select two cards to be flipped over using the grid notation [row column].");
		System.out.println("If the two cards match, they will stay revealed - if not, they will be flipped back over.");
		System.out.println("The object is to reveal all cards in as few turns as possible. Good luck!");
	}
	
	//Waits for the user to hit enter to continue
	public void waitForEnter() {
		System.out.print("Press <ENTER> to continue: ");
		@SuppressWarnings("unused")
		String input = s.nextLine().toLowerCase();
		System.out.println("");
	}
	
	//Returns a valid input for the game's difficulty
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
	
	//Returns a valid input for the game's alphabet
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
	
	//Returns a new board with selected difficulty and alphabet
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
	
	//Prints the current state of the board
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
	
	//Validates the user's input and returns the valid selected game piece
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
	
	//Returns true if the game is won, false if not
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
	
	//Makes all game pieces visible and prints board
	public void revealBoard(GamePiece[][] gameBoard) {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				gameBoard[i][j].setVisible(true);	
			}
		}
		printBoard(gameBoard);
	}
	
	//Returns an array of data from the leaderboard
	public String[] getLeaderboard() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("resources/leaderboard.txt"));
		String[] toReturn = new String[6];
		
		String easy = br.readLine();
		if (easy == null || easy.equals(",") || easy.equals("")) {
			toReturn[0] = null;
			toReturn[1] = null;
		} else {
			toReturn[0] = easy.substring(0, easy.indexOf(","));
			toReturn[1] = easy.substring(easy.indexOf(",")+1, easy.length());	
		}
		
		String medium = br.readLine();
		if (medium == null || medium.equals(",") || medium.equals("")) {
			toReturn[2] = null;
			toReturn[3] = null;
		} else {
			toReturn[2] = medium.substring(0, medium.indexOf(","));
			toReturn[3] = medium.substring(medium.indexOf(",")+1, medium.length());	
		}
		
		String hard = br.readLine();
		if (hard == null || hard.equals(",") || hard.equals("")) {
			toReturn[4] = null;
			toReturn[5] = null;
		} else {
			toReturn[4] = hard.substring(0, hard.indexOf(","));
			toReturn[5] = hard.substring(hard.indexOf(",")+1, hard.length());	
		}
		
		br.close();
		return toReturn;
	}
	
	//Prints the leaderboard to console
	public void printLeaderboard() throws IOException {		
		String[] leaderboard = getLeaderboard();
		
		System.out.println("Current Leaderboard:");
		System.out.println("====================");
		if (leaderboard[0] == null) {
			System.out.println("Easy - ");
		} else {
			System.out.println("Easy - " + leaderboard[0] + " with " + leaderboard[1] + " points");	
		}
		if (leaderboard[2] == null) {
			System.out.println("Medium - ");
		} else {
			System.out.println("Medium - " + leaderboard[2] + " with " + leaderboard[3] + " points");	
		}
		if (leaderboard[4] == null) {
			System.out.println("Hard - ");
		} else {
			System.out.println("Hard - " + leaderboard[4] + " with " + leaderboard[5] + " points");	
		}
		System.out.println();
	}
	
	//Updates the leaderboard if applicable
	public void updateLeaderboard(String difficulty, int turn) throws IOException {	
		String stringRecord = "-1";
		String[] leaderboard = getLeaderboard();
		
		if (difficulty.equals("easy") && leaderboard[1] != null) {
			stringRecord = leaderboard[1];
		} else if (difficulty.equals("medium") && leaderboard[3] != null) {
			stringRecord = leaderboard[3];
		} else if (difficulty.equals("hard") && leaderboard[5] != null) {
			stringRecord = leaderboard[5];
		}
		
		Integer intRecord = 0;
		intRecord = Integer.valueOf(stringRecord);
		
		if (turn > intRecord && intRecord != -1) {
			return;
		} else if (turn == intRecord) {
			System.out.println("Tied Record!");
			return;
		}
		
		System.out.println("New Record!");
		System.out.print("Enter your name: ");
		String newName = s.nextLine();
		System.out.println();
		
		
		FileWriter fw = new FileWriter("resources/leaderboard.txt");
		
		String easy = "";
		String medium = "";
		String hard = "";
		
		if (leaderboard[0] != null) {
			easy = leaderboard[0] + "," + leaderboard[1];
		}
		if (leaderboard[2] != null) {
			medium = leaderboard[2] + "," + leaderboard[3];
		}
		if (leaderboard[4] != null) {
			hard = leaderboard[4] + "," + leaderboard[5];
		}
		
		if (difficulty.equals("easy")) {
			fw.write(newName + "," + turn + "\n" + medium + "\n" + hard);
		} else if (difficulty.equals("medium")) {
			fw.write(easy + "\n" + newName + "," + turn + "\n" + hard);
		} else {
			fw.write(easy + "\n" + medium + "\n" + newName + "," + turn);
		}
		
		fw.close();
	}
	
	//Validates a user's answer and returns true if yes, false if no
	public boolean getPlayAgain() {
		boolean playAgain = false;
		boolean badInput = true;
		while (badInput) {
			System.out.print("Play again? 'yes' or 'no':");
			String input = s.nextLine().toLowerCase();
			if (input.equals("yes")) {
				playAgain = true;
				badInput = false;
			} else if (input.equals("no")) {
				badInput = false;
			} else {
				System.out.println("Invalid input.");
			}
		}
		return playAgain;
	}
}