package edu.wm.cs.cs301.memorygame;

public class CharacterGamePiece implements GamePiece {
	private final Character symbol;
	private boolean visible;
	
	public CharacterGamePiece(char s) {
		this.symbol = s;
		this.visible = false;
	}

	public Character getSymbol() {
		return this.symbol;
	}
	
	public void setVisible(boolean v) {
		visible = !visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public boolean equals(GamePiece other) {
		if (symbol == other.getSymbol()) {
			return true;
		}
		return false;
	}
	
}
