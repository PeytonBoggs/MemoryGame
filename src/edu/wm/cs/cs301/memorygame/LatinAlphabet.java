package edu.wm.cs.cs301.memorygame;

public class LatinAlphabet implements Alphabet {
	private char[] latinAlphabet;
	
	public LatinAlphabet() {
		this.latinAlphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 
				'B'};
	}
	
	public char[] toCharArray() {
		return latinAlphabet;
	}
}