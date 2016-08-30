import application.Routes;
import com.server.routing.Route;
import com.server.routing.Router;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class RoutesTest {

    @Test
    public void itHasAGetAndPostRoute() throws Throwable {
        Router router = new Router(new File("."));
        Routes routes = new Routes();
        routes.initializeRoutes(router);
        Route gameRoute = new Route("/api/computer_move");
        assertEquals(gameRoute.getPaths(), router.getExistingRoute("/api/computer_move").getPaths());
    }
}
