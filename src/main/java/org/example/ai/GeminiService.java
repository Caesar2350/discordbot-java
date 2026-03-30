package org.example.ai;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class GeminiService {

    private static final String API_KEY = System.getenv("API_KEY");

    public String generateResponse(String prompt) {
        try {
            URL url = new URL(
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                            + API_KEY
            );

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = "{ \"contents\": [{ \"parts\": [{ \"text\": \"" + prompt + "\" }] }] }";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes());
            }

            int status = conn.getResponseCode();

            BufferedReader br;
            if (status >= 200 && status < 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String response = br.lines().collect(Collectors.joining());

            // 🔥 JSON parsing
            JSONObject json = new JSONObject(response);

            if (json.has("candidates")) {
                JSONArray candidates = json.getJSONArray("candidates");

                if (candidates.length() > 0) {
                    JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
                    JSONArray parts = content.getJSONArray("parts");

                    if (parts.length() > 0) {
                        return parts.getJSONObject(0).getString("text");
                    }
                }
            }

            if (json.has("error")) {
                return "❌ API Error: " + json.getJSONObject("error").getString("message");
            }

            return "⚠️ Empty response from AI";

        } catch (Exception e) {   // 👈 THIS WAS MISSING
            e.printStackTrace();
            return "❌ Error: " + e.getMessage();
        }
    }
}