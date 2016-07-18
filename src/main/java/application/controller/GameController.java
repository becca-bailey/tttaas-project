package application.controller;

import com.server.ResponseData;

import com.server.utilities.Response;
import tic_tac_toe.*;
import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class GameController extends AbstractController{

    public byte[] get() {
        return Response.status(200).getBytes();
    }

    public void sendResponseData(ResponseData responseData) {
        String body = responseData.requestBody;
        System.out.println(body);
    }


    public Boolean hasWinner(String[] board) {
        IFn require = Clojure.var("clojure.core","require");
        IFn toArray = Clojure.var("clojure.core","to-array");
        require.invoke(Clojure.read("tic-tac-toe.game_loop"));
        Object boardSeq = board$winning_combinations.invokeStatic(3);
        Object[] possible_wins = (Object[]) toArray.invoke(boardSeq);
        Boolean winner = false;
        for (int i = 0; i < possible_wins.length; i++) {
            if ((Boolean) board$is_a_winning_combination_QMARK_.invokeStatic(board, possible_wins[i], "X") || (Boolean) board$is_a_winning_combination_QMARK_.invokeStatic(board, possible_wins[i], "O") ) {
                winner = true;
            }
        }
        return winner;
    }

    public Boolean isTie(String[] board) {
        return (board.length == board.length - count(board, "")) && !hasWinner(board);
    }

    public int count(String[] array, String element) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                count++;
            }
        }
        return count;
    }
}
