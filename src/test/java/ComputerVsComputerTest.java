import application.game.ComputerVsComputer;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ComputerVsComputerTest {
    private final String[] initialBoard = new String[]{"", "", "", "", "", "", "", "", ""};

    @Test
    public void getComputerMoveReturnsBestMove() {
        ComputerVsComputer game = new ComputerVsComputer(initialBoard, "O", "hard");
        assertEquals(8, game.getComputerMove());
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithFirstMove() {
        ComputerVsComputer game = new ComputerVsComputer(initialBoard, "O", "hard");
        String[] expectedFirstMove = new String[] {"", "", "", "", "", "", "", "", "O"};
        assertArrayEquals(expectedFirstMove, game.getBoard());
    }

    @Test
    public void getBoardWithEasyComputerReturnsUpdatedBoardWithRandomLastMove() {
        String[] initialBoard = new String[] {"X", "O", "X", "O", "X", "O", "X", "", "O"};
        ComputerVsComputer game = new ComputerVsComputer(initialBoard, "O", "easy");
        game.getComputerMove();
        assertEquals(7, game.getComputerMove());
    }

    @Test
    public void getBoardWithEasyComputerReturnsUpdatedBoardWithRandomMove() {
        ComputerVsComputer game = new ComputerVsComputer(initialBoard, "O", "easy");
        game.getComputerMove();
        int computerMove = game.getComputerMove();
        assertTrue(computerMove <= 8 && computerMove >= 0);
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithSecondMove() {
        String[] firstComputerMove = new String[] {"", "", "", "", "", "", "", "", "X"};
        ComputerVsComputer game = new ComputerVsComputer(firstComputerMove, "O", "hard");
        String[] expectedSecondMove = new String[] {"", "", "", "", "O", "", "", "", "X"};
        assertArrayEquals(expectedSecondMove, game.getBoard());
    }
}