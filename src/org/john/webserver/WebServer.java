package org.john.webserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebServer {
    private static final Logger LOGGER = Logger.getLogger(WebServer.class.getName());
    private final Executor threadPool = Executors.newVirtualThreadPerTaskExecutor();
    private final WebServerConfig webServerConfig;

    private boolean isRunning;

    private WebServer(WebServerConfig webServerConfig) {
        this.webServerConfig = webServerConfig;
    }

    public static WebServer create(WebServerConfig webServerConfig) {
        return new WebServer(webServerConfig);
    }

    public static WebServer create() {
        return new WebServer(WebServerConfig.DEFAULT);
    }

    public void start() {
        try(ServerSocket serverSocket = new ServerSocket(webServerConfig.getPort())) {
            isRunning = true;

            while(isRunning) {
                Socket socket = serverSocket.accept();
                socket.setTcpNoDelay(true);
                ClientHandler clientHandler = new ClientHandler(socket, webServerConfig.getApiRequestHandler());
                threadPool.execute(clientHandler);
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in the john.server loop", e);
        }
    }

    public void stop() {
        isRunning = false;
    }

    public WebServerConfig getServerConfig() {
        return webServerConfig;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
