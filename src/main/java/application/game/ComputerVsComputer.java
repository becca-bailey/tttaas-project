package application.game;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import tic_tac_toe.iOS_functions$easy_computer_move;
import tic_tac_toe.iOS_functions$minimax_move;

public class ComputerVsComputer extends Game {
    private final String[] board;
    private final String marker;
    private final String computerDifficulty;

    public ComputerVsComputer(String[] board, String marker, String computerDifficulty) {
        super(board);
        this.board = board;
        this.marker = marker;
        this.computerDifficulty = computerDifficulty;
    }

    @Override
    public String[] getBoard() {
        if (!gameIsCompleted()) {
            int computerMove = getComputerMove();
            board[computerMove] = marker;
        }
        return board;
    }

    public int getComputerMove() {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("tic-tac-toe.iOS_functions"));
        long value;
        if (computerDifficulty.equals("hard")) {
            value = (Long) iOS_functions$minimax_move.invokeStatic(board, marker);
        } else {
            value = (Long) iOS_functions$easy_computer_move.invokeStatic(board);
        }
        return (int) value;
    }
}

