package db;

import domain.Conversation;
import domain.Person;

import java.util.ArrayList;

public class MessageRepositoryStub implements MessageRepository {
    private ArrayList<Conversation> conversations;

    public MessageRepositoryStub() {
        conversations = new ArrayList<>();
    }

    @Override
    public Conversation getConversation(Person person1, Person person2) {
        for (Conversation conversation : conversations) {
            if ((conversation.getPerson1().getUserId().equals(person1.getUserId()) && conversation.getPerson2().getUserId().equals(person2.getUserId()))
                    || (conversation.getPerson2().getUserId().equals(person1.getUserId()) && conversation.getPerson1().getUserId().equals(person2.getUserId()))) {
                return conversation;
            }
        }
        startConversation(person1, person2);
        return getConversation(person1, person2);
    }

    @Override
    public void startConversation(Person person1, Person person2) {
        conversations.add(new Conversation(person1, person2));
    }

    @Override
    public void addToConversation(Person person1, Person person2, String message) {
        Conversation conversation = getConversation(person1, person2);
        if (conversation == null) {
            startConversation(person1, person2);
            conversation = getConversation(person1, person2);
        }
        conversation.update(message, person1.getFirstName());
    }
}
