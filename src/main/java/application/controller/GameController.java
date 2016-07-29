package application.controller;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import com.server.ResponseData;
import com.server.utilities.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import tic_tac_toe.board$check_each_set_of_possible_moves;
import tic_tac_toe.board$tie_QMARK_;
import tic_tac_toe.iOS_functions$minimax_move;

public class GameController extends AbstractController{
    private String body;

    public byte[] get() {
        return (Response.status(200) + "\r\n\r\n").getBytes();
    }

    public byte[] post() {
        try {
            JSONObject json = new JSONObject(body);
            JSONArray board = json.getJSONArray("board");
            String gameType = json.getString("gameType");
            System.out.println(gameType);
            String[] currentBoard = toStringArray(board);
            JSONObject jsonData = new JSONObject();

            boolean gameHasWinner = hasWinner(currentBoard);
            boolean gameIsTie = isTie(currentBoard);

            String status = getGameStatus(gameHasWinner, gameIsTie);
            jsonData.put("status", status);
            System.out.println("Status: " + status + "\n");
            String response = (Response.status(201) + "\r\n\r\n" + jsonData);
            return response.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).getBytes();
        }
    }

    private String[] toStringArray(JSONArray board) {
        String[] arr = new String[board.length()];
        for(int i=0; i<arr.length; i++) {
            arr[i]=board.optString(i);
        }
        return arr;
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

    private Boolean isTie(String[] board) {
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

    public Object getComputerMove(String[] board) {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("tic-tac-toe.iOS_functions"));
        long value = (Long) iOS_functions$minimax_move.invokeStatic(board);
        return (int) value;
    }
}
