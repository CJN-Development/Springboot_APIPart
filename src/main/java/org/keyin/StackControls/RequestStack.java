package org.keyin.StackControls;

import java.time.LocalDateTime;

public class RequestStack {
    private String httpMethod;
    private String url;
    private LocalDateTime timestamp;

    public RequestStack(String httpMethod, String url, LocalDateTime timestamp) {
        this.httpMethod = httpMethod;
        this.url = url;
        this.timestamp = timestamp;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
