package controller.handlers;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class addFriend extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<String>();
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");
        String friendName = request.getParameter("friend");

        Person friend;
        try {
            friend = getPersonService().getByName(friendName);
            try {
                if (!person.getUserId().equals(friend.getUserId())) {
                    person.addFriend(friend);
                    friend.addFriend(person);
                    getPersonService().updatePersons(person);
                    getPersonService().updatePersons(friend);
                } else {
                    errors.add("Je kan jezelf niet toevoegen he!");
                }
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }
        return "{}";
    }
}
