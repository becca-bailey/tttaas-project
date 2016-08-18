import application.controller.GameController;
import application.game.Game;
import application.game.HumanVsComputer;
import application.game.HumanVsHuman;
import org.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GameControllerTest {
    private GameController controller = new GameController();
    private String[] initialBoard = new String[] {"", "", "", "", "", "", "", "", ""};

    @Test
    public void getGameFromGameTypeReturnsAHumanVsHumanGame() throws Throwable {
        Game game = controller.getGame("humanVsHuman", initialBoard, "easy");
        assertEquals(HumanVsHuman.class, game.getClass());
    }

    @Test
    public void getGameFromGameTypeReturnsAHumanVsComputerGame() throws Throwable {
        Game game = controller.getGame("humanVsComputer", initialBoard, "easy");
        Game game2 = controller.getGame("computerVsHuman", initialBoard, "easy");
        assertEquals(HumanVsComputer.class, game.getClass());
        assertEquals(HumanVsComputer.class, game2.getClass());
    }

    @Test
    public void toStringArrayReturnsStringArrayFromJSONArray() throws Throwable {
        JSONArray jsonArray = new JSONArray(initialBoard);
        String[] stringArray = controller.toStringArray(jsonArray);
        assertArrayEquals(initialBoard, stringArray);
    }
}
