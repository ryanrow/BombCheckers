/* Ryan Row */

public class Piece {
	private boolean isFirePiece;
	private Board board;
	private int xPos;
	private int yPos;
	private String pieceType;
	private boolean hasCaptured;
	private boolean isKing = false;

	public Piece(boolean isFire, Board b, int x, int y, String type) {
		isFirePiece = isFire;
		board = b;
		xPos = x;
		yPos = y;
		pieceType = type;
	}

	/* Returns whether a piece is a fire piece or not. */
	public boolean isFire() {
		return isFirePiece;
	}

	/* Returns 0 if the piece is a fire piece
	or 1 if the piece is a water piece */
	public int side() {
		if (isFirePiece) {
			return 0;
		}
		return 1;
	}

	/* Returns whether or not the piece has been crowned. */
	public boolean isKing() {
		if (isFire() && yPos == 7 && !isKing) {
			isKing = true;
		} else if (!isFire() && yPos == 0 && !isKing) {
			isKing = true;
		}
		return isKing;
	}

	/* Returns whether or not the piece is a bomb piece. */
	public boolean isBomb() {
		return ("bomb" == pieceType);
	}

	/* Returns whether or not the piece is a shield piece. */
	public boolean isShield() {
		return ("shield" == pieceType);
	}

	public void move(int x, int y) {
		if (Math.abs(yPos - y) >= 2) {
			hasCaptured = true;
			board.remove((xPos + x)/2, (yPos + y)/2); 
			if (isBomb()) {
				for (int a = x - 1; a <= x + 1; a++) {
					for (int b = y - 1; b <= y + 1; b++) {
						Piece destroy = board.pieceAt(a,b);
						if (destroy == null || !destroy.isShield()) {
							board.remove(a,b);
						} 
					}
				}
			}
		}
		xPos = x;
		yPos = y;
	}

	public boolean hasCaptured() {
		return hasCaptured;
	}

	/* Called at the end of each turn on the piece that moved.
	resets hasCaptured to false, for next turn. */
	public void doneCapturing() {
		hasCaptured = false;
	}

}





