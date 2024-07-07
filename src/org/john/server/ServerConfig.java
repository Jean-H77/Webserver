package org.john.server;


import org.john.Context;
import org.john.api.APIRequestHandler;

public class ServerConfig {
    public static ServerConfig DEFAULT = create(8080, new Context(new APIRequestHandler()));

    private final int port;
    private final Context context;

    private ServerConfig(int port, Context context) {
        this.port = port;
        this.context = context;
    }

    public static ServerConfig create(int port, Context context) {
        return new ServerConfig(port, context);
    }

    public int getPort() {
        return port;
    }

    public APIRequestHandler getApiRequestHandler() {
        return context.apiRequestHandler();
    }

    public Context getContext() {
        return context;
    }
}
