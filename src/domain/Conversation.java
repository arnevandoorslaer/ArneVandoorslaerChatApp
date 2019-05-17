package domain;

import java.util.ArrayList;

public class Conversation {
    private Person person1;
    private Person person2;
    private ArrayList<String> content;

    public Conversation(Person person1, Person person2, ArrayList<String> content) {
        setPerson1(person1);
        setPerson2(person2);
        this.content = content;
    }

    public Conversation(Person person1, Person person2) {
        this(person1, person2, new ArrayList<>());
    }

    public Conversation() {
        this(null, null, new ArrayList<>());
    }

    public Person getPerson1() {
        return person1;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    public void update(String text, String sender) {
        content.add(sender + ": " + text);
    }
}
