import application.controller.GameController;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameControllerTest {
    GameController controller = new GameController();
    @Test
    public void hasWinnerReturnsTrueIfThereIsAWinner() throws Exception {
        String[] boardWithWinner = new String[] {"X", "X", "X", "O", "", "", "", "O", "O"};
        assertTrue(controller.hasWinner(boardWithWinner));
    }

    @Test
    public void hasWinnerReturnsFalseForInitialBoard() throws Exception {
        String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};
        assertFalse(controller.hasWinner(initialBoard));
    }
}
