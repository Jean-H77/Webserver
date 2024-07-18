package org.john.api;

import org.john.webserver.request.RequestMethod;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class APIRequestHandler {
    private final Map<RequestMethod, Map<String, APIRequest>>
                                                apiRequests = new ConcurrentHashMap<>();

    public APIRequestHandler() {
        apiRequests.put(RequestMethod.GET, new HashMap<>());
        apiRequests.put(RequestMethod.POST, new HashMap<>());
        apiRequests.put(RequestMethod.PUT, new HashMap<>());
        apiRequests.put(RequestMethod.DELETE, new HashMap<>());
    }

    public Optional<APIRequest> getAPI(RequestMethod request, String path) {
        return apiRequests.get(request).containsKey(path)
                ? Optional.of(apiRequests.get(request).get(path))
                : Optional.empty();
    }

    public void doGet(String path, APIRequestExecutor apiRequestExecutor) {
        apiRequests.get(RequestMethod.GET).put(path, new APIRequest(path, apiRequestExecutor));
    }

    public void doPost(String path, APIRequestExecutor apiRequestExecutor) {
        apiRequests.get(RequestMethod.POST).put(path, new APIRequest(path, apiRequestExecutor));
    }

    public void doPut(String path, APIRequestExecutor apiRequestExecutor) {
        apiRequests.get(RequestMethod.PUT).put(path, new APIRequest(path, apiRequestExecutor));
    }

    public void doDelete(String path, APIRequestExecutor apiRequestExecutor) {
        apiRequests.get(RequestMethod.DELETE).put(path, new APIRequest(path, apiRequestExecutor));
    }
}
