import application.game.HumanVsComputer;
import org.junit.Test;

import static org.junit.Assert.*;

public class HumanVsComputerTest {
    private final String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};

    @Test
    public void getComputerMoveReturnsBestMove() {
        HumanVsComputer game = new HumanVsComputer(initialBoard, "hard");
        assertEquals(8, game.getComputerMove());
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithFirstMove() {
        HumanVsComputer game = new HumanVsComputer(initialBoard, "hard");
        String[] expectedFirstMove = new String[] {"", "", "", "", "", "", "", "", "O"};
        assertArrayEquals(expectedFirstMove, game.getBoard());
    }

    @Test
    public void getBoardWithEasyComputerReturnsUpdatedBoardWithRandomLastMove() {
        String[] initialBoard = new String[] {"X", "O", "X", "O", "X", "O", "X", "", "O"};
        HumanVsComputer game = new HumanVsComputer(initialBoard, "easy");
        game.getComputerMove();
        assertEquals(7, game.getComputerMove());
    }

    @Test
    public void getBoardWithEasyComputerReturnsUpdatedBoardWithRandomMove() {
        HumanVsComputer game = new HumanVsComputer(initialBoard, "easy");
        game.getComputerMove();
        int computerMove = game.getComputerMove();
        assertTrue(computerMove <= 8 && computerMove >= 0);
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithSecondMove() {
        String[] firstHumanMove = new String[] {"", "", "", "", "", "", "", "", "X"};
        HumanVsComputer game = new HumanVsComputer(firstHumanMove, "hard");
        String[] expectedSecondMove = new String[] {"", "", "", "", "O", "", "", "", "X"};
        assertArrayEquals(expectedSecondMove, game.getBoard());
    }
}
