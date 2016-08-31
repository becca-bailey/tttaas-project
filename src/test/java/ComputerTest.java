import application.game.Computer;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {
    private final String[] initialBoard = new String[]{"", "", "", "", "", "", "", "", ""};

    @Test
    public void getMoveReturnsBestMove() {
        Computer game = new Computer(initialBoard, "O", "hard");
        assertEquals(8, game.getMove());
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithFirstMove() {
        Computer game = new Computer(initialBoard, "O", "hard");
        String[] expectedFirstMove = new String[] {"", "", "", "", "", "", "", "", "O"};
        assertArrayEquals(expectedFirstMove, game.getBoard());
    }

    @Test
    public void getBoardWithEasyComputerReturnsUpdatedBoardWithRandomLastMove() {
        String[] initialBoard = new String[] {"X", "O", "X", "O", "X", "O", "X", "", "O"};
        Computer game = new Computer(initialBoard, "O", "easy");
        game.getMove();
        assertEquals(7, game.getMove());
    }

    @Test
    public void getBoardWithEasyComputerReturnsUpdatedBoardWithRandomMove() {
        Computer game = new Computer(initialBoard, "O", "easy");
        game.getMove();
        int computerMove = game.getMove();
        assertTrue(computerMove <= 8 && computerMove >= 0);
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithSecondMove() {
        String[] firstComputerMove = new String[] {"", "", "", "", "", "", "", "", "X"};
        Computer game = new Computer(firstComputerMove, "O", "hard");
        String[] expectedSecondMove = new String[] {"", "", "", "", "O", "", "", "", "X"};
        assertArrayEquals(expectedSecondMove, game.getBoard());
    }
}