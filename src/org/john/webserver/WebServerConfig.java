package org.john.webserver;


import org.john.api.APIRequestHandler;

public class WebServerConfig {
    public static WebServerConfig DEFAULT = create(8080, new APIRequestHandler());

    private final int port;
    private final APIRequestHandler apiRequestHandler;

    private WebServerConfig(int port, APIRequestHandler apiRequestHandler) {
        this.port = port;
        this.apiRequestHandler = apiRequestHandler;
    }

    public static WebServerConfig create(int port, APIRequestHandler apiRequestHandler) {
        return new WebServerConfig(port, apiRequestHandler);
    }

    public int getPort() {
        return port;
    }

    public APIRequestHandler getApiRequestHandler() {
        return apiRequestHandler;
    }
}
