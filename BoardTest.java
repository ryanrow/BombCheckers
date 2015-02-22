import static org.junit.Assert.*;
import org.junit.Test;

/* Ryan Row */

public class BoardTest {

	/* Tests the board's constructor, both an empty board, and the default configurations. */
	@Test
	public void constructorAndPieceAtTest() {
		Board board = new Board(true);
		Piece testPiece = null;
		assertEquals(testPiece, board.pieceAt(0,0));
		assertEquals(testPiece, board.pieceAt(2,0));
		assertEquals(testPiece, board.pieceAt(5,1));
		assertEquals(testPiece, board.pieceAt(7,8));

		board = new Board(false);
		assertEquals(true, board.pieceAt(0,0).isFire());
		assertEquals(true, board.pieceAt(7,1).isFire());
		assertEquals(false, board.pieceAt(3,7).isFire());
		assertEquals(false, board.pieceAt(2,6).isFire());
		assertEquals(testPiece, board.pieceAt(1,0));
	}

	@Test
	public void pieceAtTest() {
		Board board = new Board(false);
		assertEquals(true, board.pieceAt(5, 5).isBomb());
		assertEquals(true, board.pieceAt(4, 2).isBomb());
		assertEquals(false, board.pieceAt(5, 5).isFire());
		assertEquals(true, board.pieceAt(1, 1).isShield());
		assertEquals(true, board.pieceAt(4, 6).isShield());
		assertEquals(false, board.pieceAt(0, 0).isBomb());
	}

    /* Tests if the player can select the correct pieces, and not the enemy's pieces.
     * Also tests if the player tries to select null pieces, and pieces out of the 
     * checker board. */
    @Test
    public void canSelectTest() {
    	Board board = new Board(false);   	
    	assertEquals(true, board.canSelect(0, 0));
    	assertEquals(true, board.canSelect(1, 1));
    	assertEquals(true, board.canSelect(2, 2));
    	assertEquals(false, board.canSelect(7, 7));
    	assertEquals(false, board.canSelect(7, 6));
    	assertEquals(false, board.canSelect(5, 5));
    	assertEquals(false, board.canSelect(-1, 8));
    	assertEquals(false, board.canSelect(0, 8));
    }

    @Test
    public void canSelectWaterTest() {
    	Board board = new Board(false);

    	board.endTurn();
    	assertEquals(true, board.canSelect(7, 7));
    	assertEquals(false, board.canSelect(2, 2));
    	assertEquals(true, board.canSelect(5, 5));
    	assertEquals(false, board.canSelect(-1, 8));
    }

    /* Tests if the player can select another piece, if they already have a piece selected. */
    @Test
    public void selectWithPieceTest() {
    	Board board = new Board(false);

    	board.select(1, 1);
    	assertEquals(false, board.canSelect(7,7));
    	assertEquals(false, board.canSelect(-1,8));
    	assertEquals(false, board.canSelect(6,6));
    	assertEquals(true, board.canSelect(0,0));
    	assertEquals(true, board.canSelect(6,2));
    }

    /* Tests if the player can select a piece, and then move the piece. */
    @Test
    public void canSelectAndMoveTest() {
    	Board board = new Board(false);
    	Piece firePiece = board.pieceAt(2,2);
    	Piece empty = null;
    	board.select(2, 2);

    	assertEquals(firePiece, board.pieceAt(2,2));
    	assertEquals(true, board.canSelect(1, 3));
    	assertEquals(true, board.canSelect(3, 3));
    	assertEquals(false, board.canSelect(2, 3));
    	assertEquals(false, board.canSelect(4, 4));

    	board.select(3,3);
    	assertEquals(empty, board.pieceAt(2,2));
    	assertEquals(firePiece, board.pieceAt(3, 3));
    }

    @Test
    public void removeTest() {
    	Board board = new Board(false);
    	Piece fire = board.pieceAt(7, 1);
    	Piece water = board.pieceAt(3, 7);

    	assertEquals(fire, board.pieceAt(7,1));
    	assertEquals(water, board.pieceAt(3,7));

    	board.remove(7, 1);
    	assertNotEquals(fire, board.pieceAt(7,1));
    	assertEquals((Piece) null, board.pieceAt(7,1));

    	board.remove(3, 7);
    	assertNotEquals(water, board.pieceAt(3, 7));
    	assertEquals((Piece) null, board.pieceAt(3, 7));
    }

    @Test
    public void winnterTest() {
    	Board board = new Board(false);

    	assertEquals(null, board.winner());

    	board = new Board(true);
    	Piece pawnFire = new Piece(true, board, 0, 0, "pawn");
    	board.place(pawnFire, 0, 0);
    	assertEquals(pawnFire, board.pieceAt(0,0));
    	assertEquals("Fire", board.winner());

    	board.remove(0, 0);
    	Piece pawnWater = new Piece(false, board, 0, 0, "pawn");
    	board.place(pawnWater, 5, 5);
    	assertEquals(pawnWater, board.pieceAt(5,5));
    	assertEquals("Water", board.winner());
    }

    @Test
    public void placeTest() {
    	Board board = new Board(true);
    	Piece bombFire = new Piece(true, board, 0, 0, "bomb");
    	Piece shieldWater = new Piece(false, board, 0, 0, "water");
    	board.place(bombFire, 3, 3);
    	assertEquals(bombFire, board.pieceAt(3,3));

    	board.place(shieldWater, 3, 3);
    	assertEquals(shieldWater, board.pieceAt(3,3));
    }

    @Test
    public void canEndTurnTest() {
    	Board board = new Board(false);
    	board.select(2, 2);
    	board.select(3, 3);
    	assertEquals(true, board.canEndTurn());
    }

    public static void main(String... args) {
    	jh61b.junit.textui.runClasses(BoardTest.class);
    }
}


























