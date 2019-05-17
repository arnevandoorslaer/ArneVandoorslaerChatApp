package domain;

import java.util.ArrayList;

public class FriendList {

    private ArrayList<Person> friends;

    public FriendList() {
        friends = new ArrayList<>();
    }

    public void addFriend(Person friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
        }
    }

    public ArrayList<Person> getAll() {
        return friends;
    }
}
