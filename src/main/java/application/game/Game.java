package application.game;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import tic_tac_toe.board$check_each_marker_for_win;
import tic_tac_toe.board$tie_QMARK_;

public abstract class Game {
    private String[] board;

    public Game(String[] board) {
        this.board = board;
    }

    public Boolean hasWinner() {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("tic-tac-toe.board"));
        return (Boolean) board$check_each_marker_for_win.invokeStatic(board);
    }

    public Boolean isTie() {
        IFn require = Clojure.var("clojure.core","require");
        require.invoke(Clojure.read("tic-tac-toe.board"));
        return (Boolean) board$tie_QMARK_.invokeStatic(board);
    }

    public String getStatus() {
        if (this.hasWinner()) {
            return "win";
        } else if (this.isTie()) {
            return "tie";
        } else {
            return "in progress";
        }
    }

    public String[] getBoard() {
        return board;
    }
}
