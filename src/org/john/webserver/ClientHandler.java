package org.john.webserver;

import org.john.api.APIRequestHandler;
import org.john.webserver.request.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());
    private final Socket socket;
    private final OutputStream outputStream;
    private final InputStream inputStream;
    private final APIRequestHandler apiRequestHandler;

    public ClientHandler(Socket socket, APIRequestHandler apiRequestHandler) throws IOException {
        this.socket = socket;
        this.outputStream = socket.getOutputStream();
        this.inputStream = socket.getInputStream();
        this.apiRequestHandler = apiRequestHandler;
    }

    @Override
    public void run() {
        handleRequest();
    }

    private void handleRequest() {
        try {
            if(inputStream.available() == 0) {
                return;
            }

            String string = new String(inputStream.readNBytes(inputStream.available()));
            Request request = Request.fromString(string);

            apiRequestHandler.getAPI(request.requestMethod(), request.URI())
                    .ifPresent (
                            apiRequest -> apiRequest.apiRequestExecutor().execute(new WebServerHandler(request.URI(), inputStream, outputStream))
                    );

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while handling request", e);

        } finally {
            if (socket != null) {
                try {
                    socket.close();

                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error while closing socket", e);
                }
            }
        }
    }
}
