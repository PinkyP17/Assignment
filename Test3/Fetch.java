import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Fetch {
    public static List<User> fetchUsersFromFile(String txtFile) {
        List<User> userList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", 2);
                if (values.length == 2) {
                    String user = values[0].trim();
                    String friendsString = values[1].trim();

                    // Remove quotation marks from the friendsString
                    String cleanedFriendsString = friendsString.replaceAll("\"", "");

                    String[] friends = cleanedFriendsString.split(",");
                    User newUser = new User(user);
                    for (String friend : friends) {
                        newUser.addFriend(friend.trim());
                    }
                    userList.add(newUser);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }
}


