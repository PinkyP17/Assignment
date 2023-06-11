public class Test {
    public static void main(String[] args) {
        User user1 = new User("Kamal", "Samad");
        User user2 = new User("Alice", "12");
        User user3 = new User("Kita", "lala");
        User user4 = new User("Aqua", "22");
        User user5 = new User("James", "bb");
        User user6 = new User("Kucha", "1b");
        
        //user1 friend 
        user2.addFriend(user1);
        user3.addFriend(user1);
        user4.addFriend(user1);
        user5.addFriend(user1);

        
        user1.friendMenu();
        user1.chatManager();

        user3.chatManager();

        user1.chatManager();
        //System.out.println(user1.toString());

        /* 
        User user1 = new User("John");
            User user2 = new User("Jane");
            User user3 = new User("Alice");
    
            user1.addFriend(user2);
            user1.addFriend(user3);
    
            //user.listFriends();
            System.out.println();
    
            user1.chatManager();
            //user2.chatManager();
        */
        

    }
    
}
