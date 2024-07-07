package org.john;

import org.john.webserver.WebServer;
import org.john.utils.http.HttpStatusCode;

import java.nio.file.Path;

public class Application {
    public static void main(String[] args) {
        var server = WebServer.create();

        createRoutes(server);
        server.start();
    }

    private static void createRoutes(WebServer webServer) {
        var apiRequestHandler = webServer.getServerConfig().getApiRequestHandler();

        apiRequestHandler.doGet("/testhtml", (wsh) -> {
            wsh.sendHTML(Path.of("./assets/test.html"), HttpStatusCode.OK);
        });

        apiRequestHandler.doGet("/testjson", (wsh) -> {
            wsh.sendJSON(new Test("John", "lastname"), HttpStatusCode.OK);
        });

        apiRequestHandler.doGet("/testtext", (wsh) -> {
            wsh.sendHTML("Hello this is html", HttpStatusCode.OK);
        });

        apiRequestHandler.doGet("/status", (wsh) -> {
            wsh.sendStatusCode(HttpStatusCode.FOUND);
        });
    }

    record Test(String firstName, String lastName) {}
}