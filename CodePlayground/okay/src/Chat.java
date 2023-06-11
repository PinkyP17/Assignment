import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Chat {
    private User user1;
    private User user2;
    private List<String> messages;

    public Chat(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.messages = new ArrayList<>();
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public void printChat() {
        System.out.println("Chat History:");
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public void saveChatHistory() {
        try {
            String fileName = generateChatHistoryFileName();
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String message : messages) {
                writer.write(message);
                writer.newLine();
            }
            writer.close();
            System.out.println("Chat history saved successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving chat history: " + e.getMessage());
        }
    }

    private String generateChatHistoryFileName() {
        String[] names = {user1.getName(), user2.getName()};
        java.util.Arrays.sort(names);
        return names[0] + "_" + names[1] + "_chat_history.txt";
    }

    public void loadChatHistory() {
        try {
            String fileName = generateChatHistoryFileName();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                messages.add(line);
            }
            reader.close();
            System.out.println("Chat history loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while loading chat history: " + e.getMessage());
        }
    }
}

