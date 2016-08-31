import application.controller.HumanGameController;
import com.server.ResponseData;
import com.server.request.Parameters;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class HumanGameControllerTest {
    private final HumanGameController controller = new HumanGameController();
    private final String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};

    @Test
    public void sendResponseDataSetsTheBoardOfTheControllerWithAnEmptyBoard() throws Throwable {
        Parameters parameters = new Parameters("board=[%22%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22]");
        ResponseData data = new ResponseData();
        data.sendParameters(parameters.getDecodedParameters());
        controller.sendResponseData(data);
        assertArrayEquals(controller.board, initialBoard);
    }


    @Test
    public void sendResponseDataSetsTheBoardOfTheControllerWithABoardInProgress() throws Throwable {
        String[] expectedBoard = new String[] {"X", "", "", "", "", "", "", "", ""};
        Parameters parameters = new Parameters("board=[%22X%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22]");
        ResponseData data = new ResponseData();
        data.sendParameters(parameters.getDecodedParameters());
        controller.sendResponseData(data);
        assertArrayEquals(controller.board, expectedBoard);
    }

    @Test
    public void getRequestReturnsInProgressResponse() throws Throwable {
        Parameters parameters = new Parameters("board=[%22X%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22]");
        ResponseData data = new ResponseData();
        data.sendParameters(parameters.getDecodedParameters());
        controller.sendResponseData(data);
        String response = new String(controller.get());
        assertTrue(response.contains("{\"board\":[\"X\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],\"status\":\"in progress\"}"));
    }

    @Test
    public void getRequestReturnsPlayer1Wins() throws Throwable {
        Parameters parameters = new Parameters("board=[%22X%22,%22X%22,%22X%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22]");
        ResponseData data = new ResponseData();
        data.sendParameters(parameters.getDecodedParameters());
        controller.sendResponseData(data);
        String response = new String(controller.get());
        assertTrue(response.contains("{\"board\":[\"X\",\"X\",\"X\",\"\",\"\",\"\",\"\",\"\",\"\"],\"status\":\"player1Wins\"}"));
    }

    @Test
    public void getRequestReturnsPlayer2Wins() throws Throwable {
        Parameters parameters = new Parameters("board=[%22O%22,%22O%22,%22O%22,%22%22,%22%22,%22%22,%22%22,%22%22,%22%22]");
        ResponseData data = new ResponseData();
        data.sendParameters(parameters.getDecodedParameters());
        controller.sendResponseData(data);
        String response = new String(controller.get());
        assertTrue(response.contains("{\"board\":[\"O\",\"O\",\"O\",\"\",\"\",\"\",\"\",\"\",\"\"],\"status\":\"player2Wins\"}"));
    }

    @Test
    public void getRequestReturnsItIsATie() throws Throwable {
        Parameters parameters = new Parameters("board=[%22O%22,%22O%22,%22X%22,%22X%22,%22X%22,%22O%22,%22O%22,%22X%22,%22X%22]");
        ResponseData data = new ResponseData();
        data.sendParameters(parameters.getDecodedParameters());
        controller.sendResponseData(data);
        String response = new String(controller.get());
        assertTrue(response.contains("{\"board\":[\"O\",\"O\",\"X\",\"X\",\"X\",\"O\",\"O\",\"X\",\"X\"],\"status\":\"tie\"}"));
    }

    @Test
    public void createGameJSONReturnsAJSONObjectWithAStatusAndBoard() throws Throwable {
        JSONObject jsonData = controller.createGameJSON("in progress", initialBoard);
        assertEquals("in progress", jsonData.get("status"));
        assertEquals(initialBoard, jsonData.get("board"));
    }
}
