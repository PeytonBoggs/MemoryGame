package edu.wm.cs.cs301.memorygame;

public class CharacterGamePiece implements GamePiece {
	private final Character symbol;
	private boolean visible;
	
	public CharacterGamePiece(char s) {
		this.symbol = s;
		this.visible = false;
	}

	public Character getSymbol() {
		if (visible) {
			return this.symbol;
		}
		return '?';
	}
	
	public void setVisible(boolean v) {
		visible = v;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public boolean equals(GamePiece other) {		
		if (symbol.compareTo(other.getSymbol()) == 0) {
			return true;
		}
		return false;
	}
	
}
