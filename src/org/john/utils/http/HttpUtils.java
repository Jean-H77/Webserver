package org.john.utils.http;

public final class HttpUtils {

    private HttpUtils() {}

    public static String createHeaders(HttpStatusCode statusCode, String contentType, int contentLength) {
        StringBuilder headers = new StringBuilder();

        headers
                .append("HTTP/1.1 ")
                .append(statusCode.getCode())
                .append(" ")
                .append(statusCode.getMessage())
                .append("\r\n")
                .append("Content-Type: ")
                .append(contentType)
                .append("\r\n")
                .append("Content-Length: ")
                .append(contentLength)
                .append("\r\n")
                .append("\r\n");

        return headers.toString();
    }
}
