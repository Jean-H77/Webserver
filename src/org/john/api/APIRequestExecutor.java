package org.john.api;

import org.john.Context;

import java.io.OutputStream;

public interface APIRequestExecutor {
    void execute(Context context, OutputStream out);
}
