import application.controller.GameController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameControllerTest {
    GameController controller = new GameController();
    String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};

    @Test
    public void hasWinnerReturnsTrueIfThereIsAWinner() throws Exception {
        String[] boardWithWinner = new String[] {"X", "X", "X", "O", "", "", "", "O", "O"};
        assertTrue(controller.hasWinner(boardWithWinner));
    }

    @Test
    public void hasWinnerReturnsFalseForInitialBoard() throws Exception {
        assertFalse(controller.hasWinner(initialBoard));
    }

    @Test
    public void getGameStatusReturnsWinner() {
        Boolean hasWinner = true;
        Boolean isTie = false;
        assertEquals("win", controller.getGameStatus(hasWinner, isTie));
    }

    @Test
    public void getGameStatusReturnsTie() {
        Boolean isTie = true;
        Boolean hasWinner = false;
        assertEquals("tie", controller.getGameStatus(hasWinner, isTie));
    }

    @Test
    public void getGameStatusReturnsGameInProgress() {
        Boolean isTie = false;
        Boolean hasWinner = false;
        assertEquals("in progress", controller.getGameStatus(hasWinner, isTie));
    }

    @Test
    public void getComputerMoveReturnsBestMove() {
        assertEquals(8, controller.getComputerMove(initialBoard));
    }
}
