package edu.wm.cs.cs301.memorygame;

public class GreekAlphabet implements Alphabet {
	private char[] greekAlphabet;
	
	public GreekAlphabet() {
		this.greekAlphabet = new char[]{'α', 'β', 'γ', 'δ', 'ε', 'ζ', 'η', 'θ', 'ι', 'κ', 'λ', 'μ', 'ν', 'ξ', 'ο', 'π', 'ρ', 'σ', 'τ', 'υ', 'φ', 'χ', 'ψ', 'ω', 'Ω', 'Δ', 'Σ', 
				'Γ'};
	}
	
	public char[] toCharArray() {
		return greekAlphabet;
	}
}