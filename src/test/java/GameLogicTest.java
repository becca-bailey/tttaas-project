import application.game.GameLogic;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameLogicTest {
    private final String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};
    private final String[] boardWithXWinner = new String[] {"X", "X", "X", "O", "", "", "", "O", "O"};
    private final String[] boardWithOWinner = new String[] {"O", "O", "O", "X", "", "", "", "X", "X"};
    private final String[] boardWithTie = new String[] {"X", "X", "O", "O", "O", "X", "X", "O", "O"};


    @Test
    public void hasWinnerReturnsTrueIfThereIsAWinner() throws Exception {
        GameLogic gameLogic = new GameLogic(boardWithXWinner);
        assertTrue(gameLogic.hasWinner());
    }

    @Test
    public void hasWinnerReturnsFalseForInitialBoard() throws Exception {
        GameLogic gameLogic = new GameLogic(initialBoard);
        assertFalse(gameLogic.hasWinner());
    }

    @Test
    public void getGameLogicStatusReturnsWinnerIfXWins() throws Exception {
        GameLogic gameLogic = new GameLogic(boardWithXWinner);
        assertEquals("player1Wins", gameLogic.getStatus());
    }

    @Test
    public void getGameLogicStatusReturnsWinnerIfOWins() throws Exception {
        GameLogic gameLogic = new GameLogic(boardWithOWinner);
        assertEquals("player2Wins", gameLogic.getStatus());
    }

    @Test
    public void isWinnerReturnsTrueIfXWins() throws Exception {
        GameLogic gameLogic = new GameLogic(boardWithXWinner);
        assertTrue(gameLogic.isWinner("X"));
        assertFalse(gameLogic.isWinner("O"));
    }

    @Test
    public void isWinnerReturnsTrueIfOWins() throws Exception {
        GameLogic gameLogic = new GameLogic(boardWithOWinner);
        assertTrue(gameLogic.isWinner("O"));
        assertFalse(gameLogic.isWinner("X"));
    }

    @Test
    public void isTieReturnsIfTie() throws Exception {
        GameLogic gameLogic = new GameLogic(boardWithTie);
        assertTrue(gameLogic.isTie());
        assertFalse(gameLogic.hasWinner());
    }

    @Test
    public void getGameLogicStatusReturnsTie() throws Exception  {
        GameLogic gameLogic = new GameLogic(boardWithTie);
        assertEquals("tie", gameLogic.getStatus());
    }

    @Test
    public void getGameLogicStatusReturnsGameLogicInProgress() throws Exception  {
        GameLogic gameLogic = new GameLogic(initialBoard);
        assertEquals("in progress", gameLogic.getStatus());
    }


    @Test
    public void getGameLogicIsCompletedForTie() throws Exception {
        GameLogic tieGameLogic = new GameLogic(boardWithTie);
        GameLogic winGameLogic = new GameLogic(boardWithXWinner);
        GameLogic newGameLogic = new GameLogic(initialBoard);

        assertFalse(newGameLogic.isCompleted());
        assertTrue(tieGameLogic.isCompleted());
        assertTrue(winGameLogic.isCompleted());
    }
}
