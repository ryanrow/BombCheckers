import static org.junit.Assert.*;
import org.junit.Test;

/* Ryan Row */

public class PieceTest {

	@Test
    public void isFireTest() {
    	Board board = new Board(true);
    	Piece pawn = new Piece(true, board, 0, 0, "pawn");
    	assertEquals(pawn.isFire(), true);
    }

    @Test
    public void sideTest() {
    	Board board = new Board(true);
    	Piece pawnFire = new Piece(true, board, 0, 0, "pawn");
    	Piece pawnWater = new Piece(false, board, 0, 0, "pawn");
    	assertEquals(pawnFire.side(), 0);
    	assertEquals(pawnWater.side(), 1);
    }

    @Test
    public void isBombTest() {
    	Board board = new Board(true);
    	Piece bombFire = new Piece(true, board, 0, 0, "bomb");
    	Piece pawnWater = new Piece(false, board, 0, 0, "pawn");
    	assertEquals(bombFire.isBomb(), true);
    	assertEquals(pawnWater.isBomb(), false);
    }

    @Test
    public void isShieldTest() {
    	Board board = new Board(true);
    	Piece pawnFire = new Piece(true, board, 0, 0, "pawn");
    	Piece shieldWater = new Piece(false, board, 0, 0, "shield");
    	assertEquals(pawnFire.isShield(), false);
    	assertEquals(shieldWater.isShield(), true);
    	assertEquals(shieldWater.side(), 1);
    }

    @Test
    public void hasCapturedTest() {
        Board board = new Board(true);
        Piece pawnFire = new Piece(true, board, 0, 0, "pawn");
        Piece pawnWater = new Piece(true, board, 1, 1, "pawn");
        pawnFire.move(2, 2);
        assertEquals(true, pawnFire.hasCaptured());
    }
    
    public static void main(String... args) {
      jh61b.junit.textui.runClasses(PieceTest.class);
  }
}