import java.util.*;


class User {
    private String name;
    private String password;
    private List<User> friends;
    private List<User> sentRequests;
    private List<User> receivedRequests;
    private static ConnectionGraph connectionGraph;

    public User(String username, String password) {
        // Graph implementation
        this.name = username;
        this.password = password;
        this.friends = new ArrayList<>();
        this.sentRequests = new ArrayList<>();
        this.receivedRequests = new ArrayList<>();
        if (connectionGraph == null) {
            connectionGraph = new ConnectionGraph();  // Create the graph object only once
        }
        this.connectionGraph.addVertex(this);
    }
    //to load graph
    public User(boolean loadGraph){
        if(loadGraph){
            if (connectionGraph == null) {                 // initially graph must be empty
                connectionGraph = new ConnectionGraph();  // Create the graph object only once
                //connectionGraph.loadAdjacency();
            }else System.out.println("error loading graph");
        }
    }

    public boolean checkEdge(User user){
        return connectionGraph.hasEdge(this, user);
    }

    
    //adding friend to the user
    public void addFriend(User friend){
        if (!this.friends.contains(friend) && !this.sentRequests.contains(friend)) {
            friend.receiveFriendRequest(this); 
            this.sentRequests.add(friend); 
            //connectionGraph.addEdge(this, friend); //adding graph edge to the user and friend
        }
    }

    public void receiveFriendRequest(User friend) {
        if (!this.receivedRequests.contains(friend)) {
            this.receivedRequests.add(friend); 
        }
    }

    public void acceptFriendRequest(User friend) {
        if (this.receivedRequests.contains(friend)) {
            this.receivedRequests.remove(friend); 
            this.friends.add(friend); 
            friend.acceptedFriendRequest(this); // Notify the friend that the request has been accepted
        }
    }

    
    public void deleteFriend(User friend) {
        if (this.friends.contains(friend)) {
            this.friends.remove(friend); 
            friend.removedFriend(this); // Notify the friend that you are no longer friends
            //connectionGraph.removeEdge(this, username); -> can add it here or we can prompt it in the removedFriend method
        }
    }
    

    public void acceptedFriendRequest(User friend) {
        this.friends.add(friend); // Add the user to the friends' list
        connectionGraph.addEdge(this, friend);
    }

    public void rejectFriendRequest(User friend) {
        //System.out.println(friend.name + "has rejected your friend request");
    }

    public void removedFriend(User friend){
        connectionGraph.removeEdge(this, friend);
    }


    private void deleteFriend() {
        System.out.println("Delete Friend:");
        List<User> friends = getFriends();
        if (friends.isEmpty()) {
            System.out.println("You have no friends to delete.");
        } else {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the name of the friend to delete: ");
            String friendName = input.nextLine();
    
            boolean friendFound = false;
            for (User friend : friends) {
                if (friend.getName().equals(friendName)) {
                    deleteFriend(friend);
                    System.out.println(friendName + " has been deleted from your friends list.");
                    friendFound = true;
                    break;
                }
            }
    
            if (!friendFound) {
                System.out.println("Friend not found in your friends list.");
            }
        }
        System.out.println();
    }

    //a  method to automate a friend menu where we can have menu to show friend list, pending friend req and also delete existing friend
    public void friendMenu(){
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.println();
        System.out.println("User : " + getName());
        do {
            System.out.println("Friend Menu:");
            System.out.println("1. Show Friend List");
            System.out.println("2. Show Pending Friend Requests");
            System.out.println("3. Show Sent Friend Request");
            //add chat menu
            System.out.println("4. Delete Friend");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // Consume the newline character
    
            switch (choice) {
                case 1:
                    showFriendList();
                    break;
                case 2:
                    handleFriendRequests();
                    break;
                case 3:
                    showSentRequests();
                    break;
                case 4:
                    deleteFriend();
                    break;
                case 0:
                    System.out.println("Exiting Friend Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0); 
        System.out.println();
    }

    //method to show the list of friends
    private void showFriendList() {
        System.out.println("Friend List:");
        List<User> friends = getFriends();
        if (friends.isEmpty()) {
            System.out.println("You have no friends.");
        } else {
            for (User friend : friends) {
                System.out.println("- " + friend.getName());
            }
        }
        System.out.println();
    }


    //handle friend req method
    public void handleFriendRequests() {
        Scanner input = new Scanner(System.in);
        List<User> pending = this.getReceivedRequests();

        System.out.println("["+this.name+"]"+" Recived Friend Requests: ");
        for(int i=0;i<pending.size();i++){
            User friend = pending.get(i);
            System.out.println((i + 1) + ". " + friend.getName());
        }

        if(pending.isEmpty()){
            System.out.println("No pending friend request");
            return;
        }

        System.out.print("Enter the action that you wanted to make: [1 to Accept, 2 to Reject]");
        int choice = input.nextInt();

        if (choice < 1 || choice > 2) {
            System.out.println("Invalid choice. Request not processed.");
            return;
        }
    
        if (choice == 1) {
            System.out.println("Please choose who you want to accept: ");
            for (int i = 0; i < pending.size(); i++) {
                User friend = pending.get(i);
                System.out.println((i + 1) + ". " + friend.getName());
            }
        
            int accept = input.nextInt();
            User friendToAccept = pending.get(accept - 1);
            this.acceptFriendRequest(friendToAccept);
            System.out.println("Friend request from " + friendToAccept.getName() + " accepted.");
        } else {
            System.out.println("Please choose who you want to reject: ");
            for (int i = 0; i < pending.size(); i++) {
                User friend = pending.get(i);
                System.out.println((i + 1) + ". " + friend.getName());
            }
        
            int reject = input.nextInt();
            User friendToReject = pending.get(reject - 1);
            //this.rejectFriendRequest(friendToReject); donnow the specific use of this method yet but just leaving it here for future use
            System.out.println("Friend request from " + friendToReject.getName() + " rejected.");
        }
        
    }

    //show what friend request the user has sent to who 
    public void showSentRequests() {
        System.out.println("Sent Friend Requests:");
        if (sentRequests.isEmpty()) {
            System.out.println("No sent friend requests.");
        } else {
            for (int i = 0; i < sentRequests.size(); i++) {
                User friend = sentRequests.get(i);
                System.out.println((i + 1) + ". " + friend.getName());
            }
        }
    }

    


    /* 
    //remove friend -> remove edge
    public void removeFriend(User username){
        connectionGraph.removeEdge(this, username);
    }
    */




    public String showMutualFriendList(){
        return "Mutual friend list of " + name + ": " + connectionGraph.showSecondDegreeConnections(this);
    }

    public int findDegreeOfConnection(User username){
        return connectionGraph.findDegreeOfConnection(this, username);
    }

    //to visualise current graph
    public String toString() {
        return connectionGraph.showAdjacency();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<User> getSentRequests() {
        return sentRequests;
    }

    public List<User> getReceivedRequests() {
        return receivedRequests;
    }



    //adding chat manager

    public void chatManager() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have entered the chat manager. Choose a friend to chat with:");

        int choice = 1;
        for (User friend : friends) {
            System.out.println(choice + ". " + friend.getName());
            choice++;
        }

        int friendChoice = scanner.nextInt();
        scanner.nextLine();

        if (friendChoice >= 1 && friendChoice <= friends.size()) {
            User friend = friends.get(friendChoice - 1);
            System.out.println("You are now chatting with " + friend.getName());
            startChatting(friend);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private void startChatting(User friend) {
        Chat chat = new Chat(this, friend);
        chat.loadChatHistory(); // Load chat history from file

        chat.printChat(); //Display chat history
    
        Scanner scanner = new Scanner(System.in);
        String message;
    
        while (true) {
            System.out.print("You: ");
            message = scanner.nextLine();
            if (message.equalsIgnoreCase("exit")) {
                break;
            }
            chat.addMessage(this.getName() + ": " + message);
        }
    
        
        chat.saveChatHistory();
    }
}