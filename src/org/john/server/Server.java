package org.john.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
    private final Executor threadPool = Executors.newVirtualThreadPerTaskExecutor();
    private final ServerConfig serverConfig;

    private boolean isRunning;

    private Server(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public static Server create(ServerConfig serverConfig) {
        return new Server(serverConfig);
    }

    public static Server create() {
        return new Server(ServerConfig.DEFAULT);
    }

    public void start() {
        try(ServerSocket serverSocket = new ServerSocket(serverConfig.getPort())) {
            isRunning = true;

            while(isRunning) {
                Socket socket = serverSocket.accept();
                socket.setTcpNoDelay(true);
                ClientHandler clientHandler = new ClientHandler(socket, serverConfig.getContext());
                threadPool.execute(clientHandler);
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in the john.server loop", e);
        }
    }

    public void stop() {
        isRunning = false;
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
