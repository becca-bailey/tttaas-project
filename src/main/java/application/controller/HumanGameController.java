package application.controller;

import application.game.Game;
import com.server.ResponseData;
import com.server.ServerResponse;
import com.server.controller.AbstractController;
import com.server.utilities.SharedUtilities;
import org.json.JSONObject;

import java.util.Map;

public class HumanGameController extends AbstractController{
    private Map<String, String> headers;

    public String[] board;

    public byte[] get() {
        Game game = new Game(board);
        return getResponse(board, game.getStatus());
    }


    public JSONObject createGameJSON(String status, String[] board) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("status", status);
        jsonData.put("board", board);

        return jsonData;
    }

    public void sendResponseData(ResponseData responseData) {
        this.headers = responseData.headers;
        String[] emptyBoard = new String[] {"","","","","","","","",""};
        String boardAsString = responseData.parameters.get("board");
        board = emptyBoard;
        if (boardAsString != null) {
            board = SharedUtilities.findAllMatches("(?<=\")(\\w*)(?=\")", boardAsString).toArray(new String[0]);
        }
        if (board.length < 9) {
            board = emptyBoard;
        }
    }

    private byte[] getResponse(String[] board, String status) {
        JSONObject jsonData = createGameJSON(status, board);
        ServerResponse response = new ServerResponse(200);
        String origin;
        try {
            origin = headers.get("Origin");
        } catch (NullPointerException e) {
            origin = "null";
        }
        response.addHeader("Access-Control-Allow-Origin", origin);
        response.addHeader("Access-Control-Max-Age", "1000");
        return response.getFullResponse(jsonData.toString());
    }
}
