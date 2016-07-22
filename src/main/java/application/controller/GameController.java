package application.controller;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import com.server.ResponseData;
import com.server.utilities.Response;
import com.server.utilities.SharedUtilities;
import tic_tac_toe.*;

import java.util.List;

public class GameController extends AbstractController{
    private String body;

    public byte[] get() {
        return (Response.status(200) + "\r\n\r\n").getBytes();
    }

    public byte[] post() {
        try {
            List<String> boardFromJson = SharedUtilities.findAllMatches("(?<=\")(\\w*)(?=\")", body.split(":")[1]);
            String[] currentBoard = boardFromJson.toArray(new String[boardFromJson.size()]);
            boolean gameHasWinner = hasWinner(currentBoard);
            boolean gameIsTie = isTie(currentBoard);
            String status = getGameStatus(gameHasWinner, gameIsTie);
            System.out.println("Status: " + status);
            String response = (Response.status(201) + "\r\n\r\n" + status);
            return response.getBytes();
        } catch (Exception e) {
            return Response.status(404).getBytes();
        }
    }

    public void sendResponseData(ResponseData responseData) {
        this.body = responseData.requestBody;
    }

    public Boolean hasWinner(String[] board) {
        IFn require = Clojure.var("clojure.core","require");
        require.invoke(Clojure.read("tic-tac-toe.game_loop"));
        boolean xWins = (Boolean) board$check_each_set_of_possible_moves.invokeStatic(board, "X");
        boolean oWins = (Boolean) board$check_each_set_of_possible_moves.invokeStatic(board, "O");
        return xWins || oWins ;
    }

    public Boolean isTie(String[] board) {
        IFn require = Clojure.var("clojure.core","require");
        require.invoke(Clojure.read("tic-tac-toe.game_loop"));
        return (Boolean) board$tie_QMARK_.invokeStatic(board);
    }

    public String getGameStatus(Boolean gameHasWinner, Boolean gameIsTie) {
        if (gameHasWinner) {
            return "win";
        } else if (gameIsTie) {
            return "tie";
        } else {
            return "in progress";
        }
    }
}
