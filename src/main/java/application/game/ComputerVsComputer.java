package application.game;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import tic_tac_toe.iOS_functions$minimax_move;

public class ComputerVsComputer extends Game {
    private String[] board;
    private String marker;

    public ComputerVsComputer(String[] board, String marker) {
        super(board, marker);
        this.board = board;
        this.marker = marker;
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
        long value = (Long) iOS_functions$minimax_move.invokeStatic(board, marker);
        return (int) value;
    }
}

