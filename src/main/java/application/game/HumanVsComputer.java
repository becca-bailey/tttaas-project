package application.game;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import tic_tac_toe.iOS_functions$minimax_move;
import tic_tac_toe.iOS_functions$easy_computer_move;

public class HumanVsComputer extends Game {
    private String[] board;
    private String marker;
    private String computerDifficulty;

    public HumanVsComputer(String[] board, String marker, String computerDifficulty) {
        super(board, marker, computerDifficulty);
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

    private boolean gameIsCompleted() {
        return super.hasWinner() || super.isTie();
    }

    public int getComputerMove() {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("tic-tac-toe.iOS_functions"));
        long value;
        if (computerDifficulty.equals("hard")) {
            value = (Long) iOS_functions$minimax_move.invokeStatic(board);
        } else {
            value = (Long) iOS_functions$easy_computer_move.invokeStatic(board);
        }
        return (int) value;
    }
}

