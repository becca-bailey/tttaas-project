package application.controller;

import application.game.Game;
import com.server.ResponseData;
import com.server.controller.AbstractController;
import com.server.utilities.Response;
import com.server.utilities.SharedUtilities;
import org.json.JSONObject;

public class StatusController extends AbstractController{

    public String[] board;

    public byte[] get() {
        Game humanGame = new Game(board);
        String status = humanGame.getStatus();
        JSONObject jsonData = createGameJSON(status,board);
        String response = (Response.status(201) + "\r\n" + "Access-Control-Allow-Origin: *" + "\r\n" + "Access-Control-Allow-Methods: POST" + "\r\n" + "Access-Control-Max-Age: 1000" + "\r\n\r\n" + jsonData);
        return response.getBytes();
    }


    public JSONObject createGameJSON(String status, String[] board) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("status", status);
        jsonData.put("board", board);

        return jsonData;
    }

    public void sendResponseData(ResponseData responseData) {
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
}
