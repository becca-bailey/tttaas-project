package application.controller;

import application.game.ComputerVsComputer;
import application.game.Game;
import application.game.HumanVsComputer;
import application.game.HumanVsHuman;
import com.server.ResponseData;
import com.server.utilities.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class GameController extends AbstractController{
    private String body;
    private String computerMarker;
    private String computerDifficulty;

    public byte[] get() {
        String welcomeMessage = "<h1>Welcome to the tic-tac-toe API!!</h1>" +
                "<p> To get an appropriate response, send a POST request with a JSON body to this URI. </p>" +
                "<p>A sample request looks like: <code> { \"board\": [\"X\",\"X\",\"X\",\"O\",\"\",\"\",\"\",\"\",\"O\"], \"gameType\": \"humanVsHuman\"} </code></p>" +
                "<p>The Response would be: <code>{\"board\":[\"X\",\"X\",\"X\",\"O\",\"\",\"\",\"\",\"\",\"O\"],\"status\":\"player1Wins\"} </code> </p>";
        return (Response.status(200) + "\r\n\r\n" + welcomeMessage).getBytes();
    }

    public byte[] post() {
        try {
            JSONObject json = new JSONObject(body);
            JSONArray board = json.getJSONArray("board");
            String gameType = json.getString("gameType");
            try {
                computerMarker = json.getString("computerMarker");
            } catch (Exception e){
                computerMarker = "O";
            }
            try {
                computerDifficulty = json.getString("computerDifficulty");
            } catch (Exception e){
                computerDifficulty = "hard";
            }
            System.out.println(gameType);
            System.out.println(computerDifficulty);
            String[] currentBoard = toStringArray(board);
            JSONObject jsonData = new JSONObject();

            Game game = getGame(gameType, currentBoard, computerDifficulty);
            String[] updatedBoard = game.getBoard();

            String status = game.getStatus();

            jsonData.put("status", status);
            jsonData.put("board", updatedBoard);
            System.out.println("Status: " + status + "\n");
            String response = (Response.status(201) + "\r\n" + "Access-Control-Allow-Origin: *" + "\r\n" + "Access-Control-Allow-Methods: POST" + "\r\n" + "Access-Control-Max-Age: 1000" + "\r\n\r\n" + jsonData);
            return response.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
            return ("HTTP/1.1 400 Bad Request \r\n\r\n <h1> Bad Request </h1> <p>A good request looks like this: <code>{ \"board\": [\"X\",\"X\",\"X\",\"O\",\"\",\"\",\"\",\"\",\"O\"], \"gameType\": \"humanVsHuman\"}</code> </p>").getBytes();
        }
    }

    public Game getGame(String gameType, String[] board, String computerDifficulty) {
        if (gameType.equals("computerVsHuman") || gameType.equals("humanVsComputer")) {
            return new HumanVsComputer(board, "O", computerDifficulty);
        }else if (gameType.equals("computerVsComputer")) {
            return new ComputerVsComputer(board, this.computerMarker, computerDifficulty);
        }else {
            return new HumanVsHuman(board, "O", computerDifficulty);
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
}
