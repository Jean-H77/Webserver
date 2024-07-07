package org.john.api;

import org.john.server.request.RequestMethod;

import java.util.*;

public class APIRequestHandler {
    private final Map<RequestMethod, List<APIRequest>> apiRequests = new HashMap<>();

    public APIRequestHandler() {
        apiRequests.put(RequestMethod.GET, new ArrayList<>());
        apiRequests.put(RequestMethod.POST, new ArrayList<>());
        apiRequests.put(RequestMethod.PUT, new ArrayList<>());
        apiRequests.put(RequestMethod.DELETE, new ArrayList<>());
    }

    public Optional<APIRequest> getAPI(RequestMethod request, String path) {
        return apiRequests.get(request)
                .stream()
                .filter(Objects::nonNull)
                .filter(it -> it.path().equalsIgnoreCase(path))
                .findFirst();
    }

    public void doGet(String path, APIRequestExecutor apiRequestExecutor) {
        apiRequests.get(RequestMethod.GET).add(new APIRequest(path, apiRequestExecutor));
    }

    public void doPost(String path, APIRequestExecutor apiRequestExecutor) {
        apiRequests.get(RequestMethod.POST).add(new APIRequest(path, apiRequestExecutor));
    }

    public void doPut(String path, APIRequestExecutor apiRequestExecutor) {
        apiRequests.get(RequestMethod.PUT).add(new APIRequest(path, apiRequestExecutor));
    }

    public void doDelete(String path, APIRequestExecutor apiRequestExecutor) {
        apiRequests.get(RequestMethod.DELETE).add(new APIRequest(path, apiRequestExecutor));
    }
}
