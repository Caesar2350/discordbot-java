package org.example.ai;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GrokService {

    private static final String API_URL = "https://api.x.ai/v1/chat/completions"; // example

    public String generateResponse(String prompt) throws Exception {

        String apiKey = System.getenv("GROK_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("GROK_API_KEY missing!");
        }

        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + apiKey);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // JSON body (similar to Gemini but structure may vary)
        JSONObject body = new JSONObject();
        body.put("model", "grok-1");

        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);

        body.put("messages", new org.json.JSONArray().put(message));

        // Send request
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = body.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Read response (simplified)
        java.util.Scanner scanner = new java.util.Scanner(conn.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        JSONObject json = new JSONObject(response);

        return json
                .getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }
}