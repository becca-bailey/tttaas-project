package application.controller;

import application.game.*;
import com.server.ResponseData;
import com.server.content.FileHandler;
import com.server.controller.AbstractController;
import com.server.utilities.Response;
import com.server.utilities.SharedUtilities;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class GameController extends AbstractController {
    public String body;
    public String[] board;
    public String gameType;
    public String computerDifficulty;
    public String computerMarker;


    public byte[] get() {
        byte[] html = getFileContentsFromFilename("./Welcome.html");
        return SharedUtilities.addByteArrays((Response.status(200) + "\r\n\r\n").getBytes(), html);
    }

    public String getJSONPropertyWithDefault(JSONObject json, String property, String defaultValue) {
        if (!json.isNull(property)) {
            return json.getString(property);
        } else {
            return defaultValue;
        }
    }

    public JSONObject createGameJSON(String status, String[] board) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("status", status);
        jsonData.put("board", board);

        return jsonData;
    }

    public void setPropertiesFromRequestBody(String body) throws JSONException {
        JSONObject json = new JSONObject(body);
        board = toStringArray(json.getJSONArray("board"));
        gameType = json.getString("gameType");
        computerMarker = getJSONPropertyWithDefault(json, "computerMarker", Marker.player2);
        computerDifficulty = getJSONPropertyWithDefault(json, "computerDifficulty", "hard");
    }

    public byte[] post() {
        try {
            setPropertiesFromRequestBody(body);
        } catch (JSONException e) {
            byte[] html = getFileContentsFromFilename("./InvalidJSON.html");
            return SharedUtilities.addByteArrays(("HTTP/1.1 400 Bad Request\r\n\r\n").getBytes(), html);
        }
        Game game = getGame(gameType, board);
        String[] updatedBoard = game.getBoard();

        String status = game.getStatus();
        JSONObject jsonData = createGameJSON(status,updatedBoard);

        String response = (Response.status(201) + "\r\n" + "Access-Control-Allow-Origin: *" + "\r\n" + "Access-Control-Allow-Methods: POST" + "\r\n" + "Access-Control-Max-Age: 1000" + "\r\n\r\n" + jsonData);
        return response.getBytes();
    }

    public Game getGame(String gameType, String[] board) {
        if (gameType.equals("computerVsHuman") || gameType.equals("humanVsComputer")) {
            return new HumanVsComputer(board, this.computerDifficulty);
        } else {
            return new ComputerVsComputer(board, this.computerMarker, this.computerDifficulty);
        }
    }

    public String[] toStringArray(JSONArray board) {
        String[] arr = new String[board.length()];
        for(int i=0; i<arr.length; i++) {
            arr[i]=board.optString(i);
        }
        return arr;
    }

    public void sendResponseData(ResponseData responseData) {
        this.body = responseData.requestBody;
    }

    public byte[] getFileContentsFromFilename(String filename) {
        FileHandler handler = new FileHandler(new File(filename));
        return handler.getFileContents();
    }
}
