import application.game.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private final String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};
    private final String[] boardWithXWinner = new String[] {"X", "X", "X", "O", "", "", "", "O", "O"};
    private final String[] boardWithOWinner = new String[] {"O", "O", "O", "X", "", "", "", "X", "X"};
    private final String[] boardWithTie = new String[] {"X", "X", "O", "O", "O", "X", "X", "O", "O"};


    @Test
    public void hasWinnerReturnsTrueIfThereIsAWinner() throws Exception {
        Game game = new Game(boardWithXWinner);
        assertTrue(game.hasWinner());
    }

    @Test
    public void getBoardReturnsCurrentBoard() throws Exception {
        Game game = new Game(boardWithXWinner);
        assertArrayEquals(boardWithXWinner, game.getBoard());
    }

    @Test
    public void hasWinnerReturnsFalseForInitialBoard() throws Exception {
        Game game = new Game(initialBoard);
        assertFalse(game.hasWinner());
    }

    @Test
    public void getGameStatusReturnsWinnerIfXWins() throws Exception {
        Game game = new Game(boardWithXWinner);
        assertEquals("player1Wins", game.getStatus());
    }

    @Test
    public void getGameStatusReturnsWinnerIfOWins() throws Exception {
        Game game = new Game(boardWithOWinner);
        assertEquals("player2Wins", game.getStatus());
    }

    @Test
    public void isWinnerReturnsTrueIfXWins() throws Exception {
        Game game = new Game(boardWithXWinner);
        assertTrue(game.isWinner("X"));
        assertFalse(game.isWinner("O"));
    }

    @Test
    public void isWinnerReturnsTrueIfOWins() throws Exception {
        Game game = new Game(boardWithOWinner);
        assertTrue(game.isWinner("O"));
        assertFalse(game.isWinner("X"));
    }

    @Test
    public void isTieReturnsIfTie() throws Exception {
        Game game = new Game(boardWithTie);
        assertTrue(game.isTie());
        assertFalse(game.hasWinner());
    }

    @Test
    public void getGameStatusReturnsTie() throws Exception  {
        Game game = new Game(boardWithTie);
        assertEquals("tie", game.getStatus());
    }

    @Test
    public void getGameStatusReturnsGameInProgress() throws Exception  {
        Game game = new Game(initialBoard);
        assertEquals("in progress", game.getStatus());
    }


    @Test
    public void getGameIsCompletedForTie() throws Exception {
        Game tieGame = new Game(boardWithTie);
        Game winGame = new Game(boardWithXWinner);
        Game newGame = new Game(initialBoard);

        assertFalse(newGame.isCompleted());
        assertTrue(tieGame.isCompleted());
        assertTrue(winGame.isCompleted());
    }
}
