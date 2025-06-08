package org.example;

public class ApiService {
    private String name;
    private String url;

    public ApiService() {}

    public ApiService(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
