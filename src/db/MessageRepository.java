package db;

import domain.Conversation;
import domain.Person;

public interface MessageRepository {
    public Conversation getConversation(Person sender, Person reciever);

    public void addToConversation(Person sender, Person reciever, String message);

    public void startConversation(Person sender, Person reciever);
}
