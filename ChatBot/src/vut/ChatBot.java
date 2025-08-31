package vut;
import java.util.HashMap;
import java.util.Map;

public class ChatBot {
    private static Map<String, String[]> knowledgeBase = new HashMap<>();
    private static Map<String, String[]> stepsBase = new HashMap<>();

    static {
        knowledgeBase.put("check balance", new String[]{"Here's how to check your account balance:"});
        stepsBase.put("check balance", new String[]{
            "Open the Capitec app",
            "Log in with your PIN",
            "Your balance will show on the home screen",
            "Tap 'View Details' for more information"
        });

        knowledgeBase.put("transfer money", new String[]{"Here's how to transfer money:"});
        stepsBase.put("transfer money", new String[]{
            "Open the Capitec app and log in",
            "Tap 'Payments' on the home screen",
            "Select 'Transfer' or 'Pay Someone'",
            "Choose the recipient or add a new one",
            "Enter the amount and confirm",
            "Review details and enter your PIN"
        });

        knowledgeBase.put("forgot pin", new String[]{"For security, you'll need to reset your app PIN."});
        stepsBase.put("forgot pin", new String[]{
            "On the login screen, tap 'Forgot PIN'",
            "Follow the verification steps",
            "If you can't reset, visit your nearest Capitec branch"
        });
    }

    public static ChatMessage getResponse(String sessionId, String content) {
        String lower = content.toLowerCase();
        for (String key : knowledgeBase.keySet()) {
            if (lower.contains(key)) {
                String text = knowledgeBase.get(key)[0];
                String[] steps = stepsBase.getOrDefault(key, new String[]{});
                StringBuilder sb = new StringBuilder(text);
                if (steps.length > 0) {
                    sb.append("\n");
                    for (int i = 0; i < steps.length; i++) {
                        sb.append(i + 1).append(". ").append(steps[i]).append("\n");
                    }
                }
                return new ChatMessage(sessionId, sb.toString(), false);
            }
        }
        return new ChatMessage(sessionId, "Hi! I can help with checking balances, transfers, airtime, and more.", false);
    }
}
