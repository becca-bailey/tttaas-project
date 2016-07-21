package application;

import com.server.Server;
import com.server.ServerArguments;
import com.server.ServerConfig;
import com.server.routing.RouteInitializer;
import com.server.routing.Router;

import java.io.*;
import java.util.Properties;

public class ProjectRunner {
    private static File rootDirectory;

    public static void main(String[] args) {
        try {
            ServerArguments arguments = new ServerArguments(args);
            rootDirectory = arguments.getRootDirectory();
            loadProperties();
            addRoutes();
            Server.main(new String[] { "-r", rootDirectory.getPath()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadProperties() throws IOException {
        Properties config = new Properties();
        String filename = "config.properties";
        try {
            InputStream input = new FileInputStream(rootDirectory.getPath() + "/config.properties");
            config.load(input);
            ServerConfig.rootDirectory = rootDirectory;
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
