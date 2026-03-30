package org.example.ai;

public class AIManager {

    private static String currentModel = "gemini";

    private static final GeminiService geminiService = new GeminiService();
    private static final GrokService grokService = new GrokService();

    // Switch AI
    public static void setModel(String model) {
        if (model.equalsIgnoreCase("gemini") || model.equalsIgnoreCase("grok")) {
            currentModel = model.toLowerCase();
        }
    }

    // Get current AI
    public static String getModel() {
        return currentModel;
    }

    // Main method used by commands
    public static String generateResponse(String prompt) throws Exception {

        switch (currentModel) {
            case "grok":
                return grokService.generateResponse(prompt);

            case "gemini":
            default:
                return geminiService.generateResponse(prompt);
        }
    }
}