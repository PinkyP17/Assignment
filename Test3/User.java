import java.util.*;
import java.io.*;

/* 
class User {
    private String username;
    private static ConnectionGraph connection;

    //create new user -> create new vertex
    public User(String username) {
        this.username = username;
        if (connection == null) {
            connection = new ConnectionGraph();  // Create the graph object only once
        }
        this.connection.addVertex(username);
    }

    //to load graph
    public User(boolean loadGraph){
        if(loadGraph){
            if (connection == null) { // initially graph must be empty
                connection = new ConnectionGraph();  // Create the graph object only once
                connection.loadAdjacency();
            }else System.out.println("error loading graph");
        }
    }

    //add new friend -> create new edge
    public void addFriend(String username) {
        connection.addEdge(this.username, username);
    }

    //remove friend -> remove edge
    public void removeFriend(String username){
        connection.removeEdge(this.username, username);
    }

    public String showFriendList(){
        return "Friend list of " + username + ": " + connection.showFirstDegreeConnections(username);
    }

    public String showMutualFriendList(){
        return "Mutual friend list of " + username + ": " + connection.showSecondDegreeConnections(username);
    }

    public String showFriendAndMutialFriendList(){
        return "Friend and mutual friend list of " + username + ": " + connection.showFirstAndSecondDegreeConnections(username);
    }

    public int findDegreeOfConnection(String username){
        return connection.findDegreeOfConnection(this.username, username);
    }

    
    //to visualise current graph
    public String toString() {
        return connection.showAdjacency();
    }
    

    //store the graph object into text file, then loaded with the loadAdjacency() method 
    public void storeGraph(){
        connection.storeAdjacency();
    }

}
*/

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User {
    private String username;
    private static ConnectionGraph connection;

    public User(String username) {
        this.username = username;
        if (connection == null) {
            connection = new ConnectionGraph();  // Create the graph object only once
        }
        this.connection.addVertex(username);
    }

    public User(boolean loadGraph) {
        if (loadGraph) {
            if (connection == null) {
                connection = new ConnectionGraph();  // Create the graph object only once
                connection.loadAdjacency();
            } else {
                System.out.println("error loading graph");
            }
        }
    }

    public void addFriend(String username) {
        connection.addEdge(this.username, username);
    }

    public void removeFriend(String username) {
        connection.removeEdge(this.username, username);
    }

    public String showFriendList() {
        return "Friend list of " + username + ": " + connection.showFirstDegreeConnections(username);
    }

    
    public String showMutualFriendList() {
        return "Mutual friend list of " + username + ": " + connection.showSecondDegreeConnections(username);
    }
    

    /* 
    public String showMutualFriendList(String targetUsername) {
        return "Mutual friend list between " + username + " and " + targetUsername + ": " +
                connection.showSecondDegreeConnections(username, targetUsername);
    }
    */

    public String showFriendAndMutualFriendList() {
        return "Friend and mutual friend list of " + username + ": " + connection.showFirstAndSecondDegreeConnections(username);
    }

    public int findDegreeOfConnection(String username) {
        return connection.findDegreeOfConnection(this.username, username);
    }

    public void storeGraph() {
        connection.storeAdjacency();
    }

    
    public static List<User> fetchUsersFromFile(String txtFile) {
    List<User> userList = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",", 2);
            if (values.length == 2) {
                String user = values[0].trim();
                String friendsString = values[1].trim();

                String[] friends = friendsString.split(",");
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
    
    public String getUsername() {
        return username;
    }

    /* 
    public static List<List<String>> fetchFriendListFromFile(String txtFile) {
        List<List<String>> friendList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", 2);
                if (values.length == 2) {
                    String friendsString = values[1].trim();

                    // Remove the quotation marks from the friendsString
                    friendsString = friendsString.replace("\"", "");

                    String[] friends = friendsString.split(",");
                    List<String> userFriends = new ArrayList<>();
                    for (String friend : friends) {
                        userFriends.add(friend.trim());
                        
                    }
                    friendList.add(userFriends);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return friendList;
    }
    */
}



