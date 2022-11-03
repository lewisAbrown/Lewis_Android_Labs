package algonquin.cst2335.brow1396;

public class ChatMessage {

    String message;
    String timeSent;
    boolean isSentButton;

public void ChatRoom(String m, String t, boolean sent)
    {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }
}
