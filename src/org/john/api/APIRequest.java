package org.john.api;

public record APIRequest(
        String path,
        APIRequestExecutor apiRequestExecutor
) {
}
