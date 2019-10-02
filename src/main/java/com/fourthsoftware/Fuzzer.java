package com.fourthsoftware;

import javax.xml.ws.Response;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;


public class Fuzzer {
    private String url;
    private List<String> replacements;
    private int connTimeout = 30000;
    private int readTimeout = 30000;
    private enum ResponseCode {
        HTTP_200, HTTP_403, HTTP_302, HTTP_301, HTTP_500, HTTP_404
    }
    private int responseCode;
    //private Map<String, Integer> results;

    public Fuzzer(String url, List<String> replacements) {
        this.url = url;
        this.replacements = replacements;
    }

    public Map<String, Integer> startFuzzing() {
        Map<String, Integer> results = new HashMap<String, Integer>();
        for(String replacement: replacements) {
            try {
                if (url.contains("@FLOOF")) {
                    url = url.replace("@FLOOF", replacement);
                } else {
                    throw new RuntimeException("Use @FLOOF to specify where to replace the words for the fuzzer!");
                }
            } catch(RuntimeException re) {
                System.out.println("Exception occurred:" + re.getMessage());
            }
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setConnectTimeout(connTimeout);
                connection.setReadTimeout(readTimeout);
                connection.setRequestMethod("HEAD");
                responseCode = connection.getResponseCode();

                // Add this result to the map
                results.put(url, responseCode);
            } catch (IOException e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
        }
        return results;
    }
}
