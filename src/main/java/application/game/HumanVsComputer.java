package application.game;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import tic_tac_toe.iOS_functions$minimax_move;

public class HumanVsComputer extends Game {
    private String[] board;

    public HumanVsComputer(String[] board) {
        super(board);
        this.board = board;
    }

    @Override
    public String[] getBoard() {
        if (!gameIsCompleted()) {
            int computerMove = getComputerMove();
            board[computerMove] = "O";
        }
        return board;
    }

    private boolean gameIsCompleted() {
        return super.hasWinner() || super.isTie();
    }

    public int getComputerMove() {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("tic-tac-toe.iOS_functions"));
        long value = (Long) iOS_functions$minimax_move.invokeStatic(board);
        return (int) value;
    }
}

