package application;

import com.server.Server;
import com.server.ServerArguments;
import com.server.ServerConfig;
import com.server.routing.RouteInitializer;
import com.server.routing.Router;

import java.io.*;
import java.util.Properties;

public class ProjectRunner {
    public static File rootDirectory;

    public static void main(String[] args) throws Exception {
        ServerArguments arguments = new ServerArguments(args);
        try {
            int portNumber = arguments.getPortNumber();
            rootDirectory = arguments.getRootDirectory();
            loadProperties();
            addRoutes();
            Server.main(new String[] { "-r", rootDirectory.getPath(), "-p", portNumber + ""});
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadProperties() throws IOException {
        Properties config = new Properties();
        String filename = "/config.properties";
        try {
            InputStream input = new FileInputStream(rootDirectory.getPath() + filename);
            config.load(input);
            ServerConfig.rootDirectory = rootDirectory;
            ServerConfig.packageName = config.getProperty("packageName");
            ServerConfig.routesClass = config.getProperty("routesClass");
            input.close();
        } catch (FileNotFoundException e) {
            System.err.println("Properties not found");
        }
    }

    public static void addRoutes() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ServerConfig.router = new Router(ServerConfig.rootDirectory);
        Class initializerClass = Class.forName(ServerConfig.routesClass);
        RouteInitializer initializer = (RouteInitializer) initializerClass.newInstance();
        initializer.initializeRoutes(ServerConfig.router);
    }
}
