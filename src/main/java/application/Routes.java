package application;

import com.server.routing.RouteInitializer;
import com.server.routing.Router;

public class Routes implements RouteInitializer {

    public void initializeRoutes(Router router) {
        router.addRoute("GET", "/game");
        router.addRoute("POST", "/game");
    }
}
