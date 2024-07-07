package org.john.server.request;

public record Request(
        RequestMethod requestMethod,
        String URI,
        String protocol
) {

    public static Request fromString(String request) {
        String[] headers = request.split("[\\r\\n]+");
        String[] firstHeader = headers[0].split(" ");
        RequestMethod method = RequestMethod.valueOf(firstHeader[0]);
        String URI = firstHeader[1];
        String protocol = firstHeader[2];
        return new Request(method, URI, protocol);
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestMethod=" + requestMethod +
                ", URI='" + URI + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
