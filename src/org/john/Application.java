package org.john;

import org.john.server.Server;

import java.nio.file.Path;

public class Application {
    public static void main(String[] args) {
        var server = Server.create();

        createRoutes(server);
        server.start();
    }

    private static void createRoutes(Server server) {
        var apiRequestHandler = server.getServerConfig().getApiRequestHandler();

        apiRequestHandler.doGet("/testhtml", (ctx, out) -> ctx.sendHTMLFile(Path.of("./assets/test.html"), out));
        apiRequestHandler.doGet("/testjson", (ctx, out) -> ctx.sendJSON(new Test("John", "lastname"), out));
    }

    record Test(String firstName, String lastName) {}
}