package org.john;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.john.api.APIRequestHandler;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public record Context (
        APIRequestHandler apiRequestHandler
) {

    private static final Logger LOGGER = Logger.getLogger(Context.class.getName());

    public void sendHTMLFile(Path path, OutputStream out) {
        System.out.println("Hello");
        try {
            byte[] bytes = Files.readAllBytes(path);

            out.write("HTTP/1.1 200 OK\r\n".getBytes());
            out.write("Content-Type: text/html\r\n".getBytes());
            out.write(("Content-Length: " + bytes.length + "\r\n").getBytes());
            out.write("\r\n".getBytes());
            out.write(bytes);

            out.flush();
            out.close();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while handling GET request", e);
        }
    }

    public void sendJSON(Object object, OutputStream out) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        try {
            String json = ow.writeValueAsString(object);

            out.write("HTTP/1.1 200 OK\r\n".getBytes());
            out.write("Content-Type: text/html\r\n".getBytes());
            out.write(("Content-Length: " + json.getBytes().length + "\r\n").getBytes());
            out.write("\r\n".getBytes());
            out.write(json.getBytes());

            out.flush();
            out.close();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while handling POST request", e);
        }
    }
}
