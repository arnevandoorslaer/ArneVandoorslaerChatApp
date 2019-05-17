package controller.handlers;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class setState extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");
        String state = request.getParameter("state");
        person.setState(state);

        getPersonService().getPerson(person.getUserId()).setState(state);
        getPersonService().updatePersons(person);
        return "{}";
    }

}
