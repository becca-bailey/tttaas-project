package application;

import com.server.routing.RouteInitializer;
import com.server.routing.Router;

public class Routes implements RouteInitializer {

    public void initializeRoutes(Router router) {
        router.addRoute("GET", "/api/status", "HumanGame");
        router.addRoute("POST", "/api/computer_move", "ComputerGame");
    }
}
