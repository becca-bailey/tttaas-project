package application;

import com.server.Server;
import com.server.ServerConfig;
import com.server.routing.RouteInitializer;
import com.server.routing.Router;

import java.io.*;
import java.util.Properties;

public class ProjectRunner {
    private static String rootDirectory = "/Users/Becca/8thLight/tttaas/tttaas-project";

    public static void main(String[] args) {
        try {
            loadProperties();
            addRoutes();
            Server.main(new String[] { "-r", rootDirectory});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadProperties() throws IOException {
        Properties config = new Properties();
        String filename = "config.properties";
        try {
            InputStream input = new FileInputStream(ProjectRunner.rootDirectory + "/config.properties");
            config.load(input);
            ServerConfig.rootDirectory = new File(rootDirectory);
            ServerConfig.packageName = config.getProperty("packageName");
            ServerConfig.routesClass = config.getProperty("routesClass");
            input.close();
        } catch (Exception e) {
            System.err.println("Properties not found");
        }
    }

    private static void addRoutes() throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ServerConfig.router = new Router(ServerConfig.rootDirectory);
        Class initializerClass = Class.forName(ServerConfig.routesClass);
        RouteInitializer initializer = (RouteInitializer) initializerClass.newInstance();
        initializer.initializeRoutes(ServerConfig.router);
    }
}
