import application.ProjectRunner;
import com.server.ServerConfig;
import com.server.routing.Route;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ProjectRunnerTest {

    @Test
    public void loadPropertiesSetsServerProperties() throws Throwable {
        ProjectRunner.rootDirectory = new File(".");
        ProjectRunner.loadProperties();
        assertEquals(ProjectRunner.rootDirectory, ServerConfig.rootDirectory);
        assertEquals("application", ServerConfig.packageName);
        assertEquals("application.Routes", ServerConfig.routesClass);
    }

    @Test
    public void loadPropertiesDoesNotSetServerPropertiesIfNoProperties() throws Throwable {
        ProjectRunner.rootDirectory = new File("./test");
        ProjectRunner.loadProperties();
        assertNotEquals(ProjectRunner.rootDirectory, ServerConfig.rootDirectory);
    }

    @Test
    public void addRoutesAddsARouteForGameController() throws Throwable {
        ProjectRunner.rootDirectory = new File(".");
        ProjectRunner.loadProperties();
        ProjectRunner.addRoutes();
        Route gameRoute = new Route("/game");
        TestCase.assertEquals(gameRoute.getPaths(), ServerConfig.router.getExistingRoute("/game").getPaths());
    }
}