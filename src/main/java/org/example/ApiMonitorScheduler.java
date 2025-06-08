package org.example;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ApiMonitorScheduler {

    public static void start(List<ApiService> apis, int intervalInSeconds) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("\n=== 🕒 API Health Check: " + java.time.LocalDateTime.now() + " ===");
            for (ApiService api : apis) {
                ApiChecker.check(api);
            }
        }, 0, intervalInSeconds, TimeUnit.SECONDS);
    }
}
