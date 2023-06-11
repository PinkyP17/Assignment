import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Fetch {
    private List<String> users;
    private List<String> friendLists;

    public Fetch(String filePath) {
        users = new ArrayList<>();
        friendLists = new ArrayList<>();
        readCSV(filePath);
    }

    private void readCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length >= 1) {
                    String user = columns[0].trim();
                    users.add(user);

                    if (columns.length >= 2) {
                        String friendList = columns[1].trim();
                        friendLists.add(friendList);
                    } else {
                        friendLists.add(""); // Add an empty string if friendlist is empty
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getUsers() {
        return users;
    }

    public List<String> getFriendLists() {
        return friendLists;
    }

    public static void main(String[] args) {
        String fileName = "C:/Users/mrrai/OneDrive/Desktop/UM Degree/SEM 2/Data Structure/Assignment/CodePlayground/okay/testData.csv";
        Fetch fetch = new Fetch(fileName);

        // Access the users and friendLists lists
        List<String> users = fetch.getUsers();
        List<String> friendLists = fetch.getFriendLists();

        // Print all the users
        for (String user : users) {
            System.out.println("User: " + user);
        }
    }
}

