import application.game.HumanVsComputer;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class HumanVsComputerTest {
    private String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};

    @Test
    public void getComputerMoveReturnsBestMove() {
        HumanVsComputer game = new HumanVsComputer(initialBoard, "O");
        assertEquals(8, game.getComputerMove());
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithFirstMove() {
        HumanVsComputer game = new HumanVsComputer(initialBoard, "O");
        String[] expectedFirstMove = new String[] {"", "", "", "", "", "", "", "", "O"};
        assertArrayEquals(expectedFirstMove, game.getBoard());
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithSecondMove() {
        String[] firstHumanMove = new String[] {"", "", "", "", "", "", "", "", "X"};
        HumanVsComputer game = new HumanVsComputer(firstHumanMove, "O");
        String[] expectedSecondMove = new String[] {"", "", "", "", "O", "", "", "", "X"};
        assertArrayEquals(expectedSecondMove, game.getBoard());
    }
}
