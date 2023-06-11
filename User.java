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