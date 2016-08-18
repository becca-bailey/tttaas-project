import application.game.Game;
import application.game.HumanVsHuman;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {
    private String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};
    String[] boardWithWinner = new String[] {"X", "X", "X", "O", "", "", "", "O", "O"};
    String[] boardWithTie = new String[] {"X", "X", "O", "O", "O", "X", "X", "O", "O"};


    @Test
    public void hasWinnerReturnsTrueIfThereIsAWinner() throws Exception {
        Game game = new HumanVsHuman(boardWithWinner, "O", "easy");
        assertTrue(game.hasWinner());
    }

    @Test
    public void hasWinnerReturnsFalseForInitialBoard() throws Exception {
        Game game = new HumanVsHuman(initialBoard, "O", "easy");
        assertFalse(game.hasWinner());
    }

    @Test
    public void getGameStatusReturnsWinner() {
        Game game = new HumanVsHuman(boardWithWinner, "O", "easy");
        assertEquals("player1Wins", game.getStatus());
    }

    @Test
    public void getGameStatusReturnsTie() {
        Game game = new HumanVsHuman(boardWithTie, "O", "easy");
        assertEquals("tie", game.getStatus());
    }

    @Test
    public void getGameStatusReturnsGameInProgress() {
        Game game = new HumanVsHuman(initialBoard, "O", "easy");
        assertEquals("in progress", game.getStatus());
    }
}
