import java.util.*;
public class test {
    public static void main(String[] args) {
        List<User> users = User.fetchUsersFromFile("C:/Users/mrrai/OneDrive/Desktop/UM Degree/SEM 2/Data Structure/Assignment/Test3/waduh.txt");
        //List<List<String>> friendList = User.fetchFriendListFromFile("C:/Users/mrrai/OneDrive/Desktop/UM Degree/SEM 2/Data Structure/Assignment/Test3/waduh.txt");
        for (int i = 0; i < users.size(); i++) {
            String username = "user" + (i + 1);
            User user = new User(username);
        }
        
    }
}

/* 
        User user1 = new User("Rocket");
        User user2 = new User("Lyla");
        User user3 = new User("Drax");
        User user4 = new User("Mantis");
        User user5 = new User("Gamora");
        User user6 = new User("Thanos");
        */

        /* 
        System.out.println(user1.showFriendList());

        user1.addFriend("Lyla");
        user1.addFriend("Drax");

        user3.addFriend("Mantis");

        user2.addFriend("Gamora");

        user4.addFriend("Gamora");
        
        user5.addFriend("Thanos");

        
        System.out.println(user1.showFriendList());
        System.out.println(user4.showMutualFriendList());
        //System.out.println("\nAdjacency list:\n" + user1.toString());

        //System.out.println("Degree of conenctions: " + user1.findDegreeOfConnection("Thanos"));

        //user1.removeFriend("Lylla");

        //System.out.println("\nAdjacency list after remove:\n" + user1.toString());

        //System.out.println("Degree of conenctions: " + user1.findDegreeOfConnection("Thanos"));

        //to store graph and save into text file. can store from any user since the graph object is same.
        //user1.storeGraph();

        // //to load graph from the text file (intial graph must be empty)
        // User load = new User(true);
        // System.out.println(load.toString());
        */


        /* 
        User user1 = new User("A");
        
        User user2 = new User("B");
        
        User user3 = new User("C");
        User user4 = new User("D");
        User user5 = new User("E");
        User user6 = new User("F");

        user1.addFriend("B");
        user1.addFriend("D");
        user1.addFriend("E");

        user3.addFriend("B");

        
        user6.addFriend("E");       

        System.out.println(user1.showFriendList());
        System.out.println(user3.showFriendList());
        System.out.println(user6.showFriendList());

        System.out.println(user1.showMutualFriendList());
        */