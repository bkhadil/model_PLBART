package com.example.we_application;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CodeBERTClient {
    private static final String API_URL = "https://api-inference.huggingface.co/models/microsoft/codebert-base";
    private static final String TOKEN = "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";

    public String generateDocumentation(String code) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + TOKEN);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Ensure the JSON format is correct
        String inputJson = "{\"inputs\": \"" + code + "\"}";
        try (OutputStream os = connection.getOutputStream()) {
            os.write(inputJson.getBytes());
            os.flush();
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    errorResponse.append(line);
                }
                throw new IOException("Error response from server: " + errorResponse.toString());
            }
        }
    }
}
