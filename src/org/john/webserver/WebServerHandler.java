package org.john.webserver;

import org.john.utils.http.HttpStatusCode;
import org.john.utils.http.HttpUtils;
import org.john.utils.json.JSONUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public record WebServerHandler(
        String path,
        InputStream in,
        OutputStream out
) {

    private static final Logger LOGGER = Logger.getLogger(WebServerHandler.class.getName());

    public void sendHTML(Path path, HttpStatusCode statusCode) {
        try {
            byte[] bytes = Files.readAllBytes(path);
            String content = HttpUtils.createHeaders(statusCode, "text/html", bytes.length);

            out.write(content.getBytes());
            out.write(bytes);

            out.flush();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while handling GET request", e);
        }
    }

    public void sendHTML(String text, HttpStatusCode statusCode) {
        try {
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
            String content = HttpUtils.createHeaders(statusCode, "text/html", bytes.length);

            out.write(content.getBytes(StandardCharsets.UTF_8));
            out.write(bytes);

            out.flush();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while handling GET request", e);
        }
    }

    public void sendJSON(Object object, HttpStatusCode statusCode) {
        try {
            String json = JSONUtils.toJSON(object);
            if(json == null) {
                return;
            }

            String content = HttpUtils.createHeaders(statusCode, "text/html", json.length());

            out.write(content.getBytes(StandardCharsets.UTF_8));
            out.write(json.getBytes());

            out.flush();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while handling GET request", e);
        }
    }

    public void sendStatusCode(HttpStatusCode statusCode) {
        try {
            String content = HttpUtils.createHeaders(statusCode, "text/html", 0);

            out.write(content.getBytes(StandardCharsets.UTF_8));

            out.flush();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while sending status code", e);
        }
    }
}
