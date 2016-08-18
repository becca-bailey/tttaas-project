import application.game.HumanVsComputer;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class HumanVsComputerTest {
    private String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};

    @Test
    public void getComputerMoveReturnsBestMove() {
        HumanVsComputer game = new HumanVsComputer(initialBoard, "O", "hard");
        assertEquals(8, game.getComputerMove());
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithFirstMove() {
        HumanVsComputer game = new HumanVsComputer(initialBoard, "O", "hard");
        String[] expectedFirstMove = new String[] {"", "", "", "", "", "", "", "", "O"};
        assertArrayEquals(expectedFirstMove, game.getBoard());
    }

    @Test
    public void getBoardWithEasyComputerReturnsUpdatedBoardWithRandomLastMove() {
        String[] initialBoard = new String[] {"X", "O", "X", "O", "X", "O", "X", "", "O"};
        HumanVsComputer game = new HumanVsComputer(initialBoard, "O", "easy");
        game.getComputerMove();
        assertEquals(7, game.getComputerMove());
    }

    @Test
    public void getBoardWithEasyComputerReturnsUpdatedBoardWithRandomMove() {
        HumanVsComputer game = new HumanVsComputer(initialBoard, "O", "easy");
        game.getComputerMove();
        int computerMove = game.getComputerMove();
        assertTrue(computerMove <= 8 && computerMove >= 0);
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithSecondMove() {
        String[] firstHumanMove = new String[] {"", "", "", "", "", "", "", "", "X"};
        HumanVsComputer game = new HumanVsComputer(firstHumanMove, "O", "hard");
        String[] expectedSecondMove = new String[] {"", "", "", "", "O", "", "", "", "X"};
        assertArrayEquals(expectedSecondMove, game.getBoard());
    }
}
