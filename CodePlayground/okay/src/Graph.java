import java.util.*;

public class Graph {
    private List<String> vertices;
    private List<List<String>> edges;
    
    public Graph(){
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(String user){
        if(!vertices.contains(user)){
            vertices.add(user);
            edges.add(new ArrayList<>()); // Add an empty list for the edges of this vertex
        }
    }

    public void addEdge(int index1, int index2) {
        if (index1 >= 0 && index1 < vertices.size() && index2 >= 0 && index2 < vertices.size()) {
            edges.get(index1).add(vertices.get(index2));
            edges.get(index2).add(vertices.get(index1));
        }
    }

    public List<String> getVertices(){
        return vertices;
    }
    
    public List<String> getEdges(int index) {
        if (index >= 0 && index < vertices.size()) {
            return edges.get(index);
        }
        return new ArrayList<>(); // Return an empty list if index is out of bounds
    }

    public boolean hasMutualFriend(String userA, String userB) {
        int indexA = vertices.indexOf(userA);
        int indexB = vertices.indexOf(userB);
    
        if (indexA == -1 || indexB == -1) {
            // Either userA or userB does not exist in the graph
            return false;
        }
    
        List<String> friendsA = edges.get(indexA);
        List<String> friendsB = edges.get(indexB);
    
        for (String friend : friendsA) {
            if (friendsB.contains(friend)) {
                // Found a mutual friend
                return true;
            }
        }
    
        return false;
    }

    public static void main(String[] args) {
        String fileName = "C:/Users/mrrai/OneDrive/Desktop/UM Degree/SEM 2/Data Structure/Assignment/CodePlayground/okay/testData.csv";
        Fetch fetch = new Fetch(fileName);
        List<String> users = fetch.getUsers();
        List<String> friendLists = fetch.getFriendLists();

        // Create a new graph
        Graph graph = new Graph();

        // Add vertices for each user to the graph
        for (String user : users) {
            graph.addVertex(user);
        }

        // Add edges between users based on friend lists
        for (int i = 0; i < friendLists.size(); i++) {
            String friendList = friendLists.get(i);
            String[] friends = friendList.split(";");
            for (String friend : friends) {
                int friendIndex = users.indexOf(friend.trim());
                if (friendIndex != -1) {
                    graph.addEdge(i, friendIndex);
                }
            }
        }

        // Print the connections for each user
        List<String> vertices = graph.getVertices();
        for (int i = 0; i < vertices.size(); i++) {
            String user = vertices.get(i);
            List<String> connections = graph.getEdges(i);
            System.out.println("User: " + user);
            System.out.println("Connections: " + connections);
            System.out.println();
        }

        boolean hasMutualFriend = graph.hasMutualFriend("Shuib","kamal");
        if (hasMutualFriend) {
            System.out.println("User A and User B have a mutual friend.");
        } else {
            System.out.println("User A and User B do not have a mutual friend.");
        }
    }
}

