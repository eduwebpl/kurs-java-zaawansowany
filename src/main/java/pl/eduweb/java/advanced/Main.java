package pl.eduweb.java.advanced;


import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<User, String> userDescriptions = new HashMap<>();
        User user1 = new User(null, "John", "Who");

        userDescriptions.put(user1, "user1 description");
        System.out.println(userDescriptions.get(user1));

        user1.setId(1L);

        System.out.println(userDescriptions.get(user1));
        System.out.println(userDescriptions);
    }

}
