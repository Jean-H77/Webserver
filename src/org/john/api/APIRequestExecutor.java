package org.john.api;

import org.john.webserver.WebServerHandler;

@FunctionalInterface
public interface APIRequestExecutor {
    void execute(WebServerHandler webServerHandler);
}
