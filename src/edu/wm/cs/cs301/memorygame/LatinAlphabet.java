package edu.wm.cs.cs301.memorygame;

public class LatinAlphabet implements Alphabet {
	private char[] latinAlphabet;
	
	public LatinAlphabet() {
		this.latinAlphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 
				'b'};
	}
	
	public char[] toCharArray() {
		return latinAlphabet;
	}
}