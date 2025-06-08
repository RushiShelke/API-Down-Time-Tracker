package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<ApiService> apis = ApiConfigLoader.loadApiConfig();
            ApiMonitorScheduler.start(apis, 300); // check every 5 minutes (300 sec)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
