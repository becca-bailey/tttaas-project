import application.game.Computer;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class ComputerTest {
    private final String[] initialBoard = new String[]{"", "", "", "", "", "", "", "", ""};

    @Test
    public void getBoardReturnsUpdatedBoardWithFirstMove() {
        Computer computer = new Computer(initialBoard, "O", "hard");
        String[] expectedFirstMove = new String[] {"", "", "", "", "", "", "", "", "O"};
        assertArrayEquals(expectedFirstMove, computer.getBoard());
    }

    @Test
    public void getBoardWithEasyComputerReturnsUpdatedBoardWithRandomLastMove() {
        String[] initialBoard = new String[] {"X", "O", "X", "O", "X", "O", "X", "", "O"};
        String[] expectedBoard = new String[] {"X", "O", "X", "O", "X", "O", "X", "O", "O"};
        Computer computer = new Computer(initialBoard, "O", "easy");
        assertArrayEquals(expectedBoard, computer.getBoard());
    }

    @Test
    public void getBoardWithEasyComputerReturnsUpdatedBoardWithRandomMove() {
        String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};
        Computer computer = new Computer(initialBoard, "O", "easy");
        assertTrue(Arrays.asList(computer.getBoard()).contains("O"));
    }

    @Test
    public void getBoardReturnsUpdatedBoardWithSecondMove() {
        String[] firstComputerMove = new String[] {"", "", "", "", "", "", "", "", "X"};
        Computer computer = new Computer(firstComputerMove, "O", "hard");
        String[] expectedSecondMove = new String[] {"", "", "", "", "O", "", "", "", "X"};
        assertArrayEquals(expectedSecondMove, computer.getBoard());
    }
}