
public class test {
    public static void main(String[] args) {
        User user1 = new User("rocket");
        User user2 = new User("lylla");
        User user3 = new User("drax");
        User user4 = new User("mantis");
        User user5 = new User("gamora");
        User user6 = new User("thanos");

        System.out.println(user1.showFriendList());

        user1.addFriend("lylla");
        user1.addFriend("drax");

        user3.addFriend("mantis");

        user2.addFriend("gamora");

        user4.addFriend("gamora");
        
        user5.addFriend("thanos");


        System.out.println(user1.showFriendList());
        System.out.println(user1.showMutualFriendList());
        System.out.println("\nAdjacency list:\n" + user1.toString());

        System.out.println("Degree of conenctions: " + user1.findDegreeOfConnection("thanos"));

        user1.removeFriend("lylla");

        System.out.println("\nAdjacency list after remove:\n" + user1.toString());

        System.out.println("Degree of conenctions: " + user1.findDegreeOfConnection("thanos"));

        //to store graph and save into text file. can store from any user since the graph object is same.
        user1.storeGraph();

        // //to load graph from the text file (intial graph must be empty)
        // User load = new User(true);
        // System.out.println(load.toString());
        
    }
}