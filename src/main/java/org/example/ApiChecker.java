package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class ApiChecker {

    private static final Logger logger = Logger.getLogger(ApiChecker.class.getName());
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void check(ApiService api) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(api.getUrl()))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.warning(LocalDateTime.now() + " | DOWN: " + api.getName() +
                        " | URL: " + api.getUrl() + " | Status Code: " + response.statusCode());
            } else {
                System.out.println(LocalDateTime.now() + " | OK: " + api.getName());
            }
        } catch (Exception e) {
            logger.severe(LocalDateTime.now() + " | ERROR: " + api.getName() + " | Exception: " + e.getMessage());
        }
    }
}
