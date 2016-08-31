package application.controller;

import application.game.Computer;
import application.game.Game;
import application.game.Marker;
import com.server.ResponseData;
import com.server.ServerResponse;
import com.server.content.FileHandler;
import com.server.controller.AbstractController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

public class ComputerGameController extends AbstractController {
    public String body;
    public String[] board;
    public String gameType;
    public String computerDifficulty;
    public String computerMarker;
    private Map<String, String> headers;


    public byte[] get() {
        byte[] html = getFileContentsFromFilename("./Welcome.html");
        ServerResponse response = new ServerResponse(200);
        return response.getFullResponse(new String(html));
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
            ServerResponse response = new ServerResponse(400);
            return response.getFullResponse(new String(html));
        }

        Game game = new Game(board);

        if (!game.isCompleted()) {
            Computer computer = new Computer(game.board, this.computerMarker, this.computerDifficulty);
            this.board = computer.getBoard();
        }

        return getResponse(this.board, game.getStatus());
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
        this.headers = responseData.headers;
    }

    public byte[] getFileContentsFromFilename(String filename) {
        FileHandler handler = new FileHandler(new File(filename));
        return handler.getFileContents();
    }

    private byte[] getResponse(String[] board, String status) {
        JSONObject jsonData = createGameJSON(status, board);
        ServerResponse response = new ServerResponse(201);
        String origin;
        try {
            origin = headers.get("Origin");
        } catch (NullPointerException e) {
            origin = "null";
        }
        response.addHeader("Access-Control-Allow-Origin", origin);
        response.addHeader("Access-Control-Allow-Methods", "POST");
        response.addHeader("Access-Control-Max-Age", "1000");
        return response.getFullResponse(jsonData.toString());
    }
}
