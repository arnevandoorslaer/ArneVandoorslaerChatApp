package domain;

import db.MessageRepositoryStub;
import db.PersonRepository;
import db.PersonRepositoryStub;

import java.util.List;

public class PersonService {
    private PersonRepository personRepository = new PersonRepositoryStub();
    private MessageRepositoryStub messageRepositoryStub = new MessageRepositoryStub();

    public PersonService() {
        messageRepositoryStub.addToConversation(personRepository.get("jan@ucll.be"), personRepository.get("an@ucll.be"), "hey an");
        messageRepositoryStub.addToConversation(personRepository.get("an@ucll.be"), personRepository.get("jan@ucll.be"), "hey jan");

        messageRepositoryStub.addToConversation(personRepository.get("jan@ucll.be"), personRepository.get("bib@ucll.be"), "hey bib");
        messageRepositoryStub.addToConversation(personRepository.get("bib@ucll.be"), personRepository.get("jan@ucll.be"), "hey jan");
    }

    public void startConversation(Person person1, Person person2) {
        messageRepositoryStub.startConversation(person1, person2);
    }

    public void addToConversation(Person person1, Person person2, String message) {
        messageRepositoryStub.addToConversation(person1, person2, message);
    }

    public Conversation getConversation(Person person1, Person person2) {
        return messageRepositoryStub.getConversation(person1, person2);
    }

    public Person getPerson(String personId) {
        return getPersonRepository().get(personId);
    }

    public List<Person> getPersons() {
        return getPersonRepository().getAll();
    }

    public void addPerson(Person person) {
        getPersonRepository().add(person);
    }

    public void updatePersons(Person person) {
        getPersonRepository().update(person);
    }

    public void deletePerson(String id) {
        getPersonRepository().delete(id);
    }

    public Person getAuthenticatedUser(String email, String password) {
        return getPersonRepository().getAuthenticatedUser(email, password);
    }

    public Person getByName(String name) {
        return getPersonRepository().getByName(name);
    }

    private PersonRepository getPersonRepository() {
        return personRepository;
    }
}
