package vut;
import java.util.HashMap;
import java.util.Map;

public class ChatStorage {
    private Map<String, ChatSession> sessions = new HashMap<>();

    public ChatSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public ChatSession createSession(String sessionId) {
        ChatSession session = new ChatSession(sessionId);
        sessions.put(sessionId, session);
        return session;
    }

    public void addMessage(String sessionId, ChatMessage message) {
        ChatSession session = sessions.get(sessionId);
        if (session != null) {
            session.addMessage(message);
        }
    }
}
