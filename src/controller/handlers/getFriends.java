package controller.handlers;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class getFriends extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");
        if (person.getFriends().size() > 0) {
            String friendJSON = this.toJSON(person.getFriends());
            return friendJSON;
        }
        return "";
    }

    private String toJSON(ArrayList<Person> friends) {
        StringBuilder json = new StringBuilder();
        json.append("{\"friends\":");

        json.append("[");
        json.append("{ \"name\" : \"");
        json.append(friends.get(0).getFirstName());
        json.append("\",\"state\" : \"");
        json.append(friends.get(0).getState());
        json.append("\"}");

        for (int i = 1; i < friends.size(); i++) {
            json.append(",{ \"name\" : \"");
            json.append(friends.get(i).getFirstName());
            json.append("\",\"state\" : \"");
            json.append(friends.get(i).getState());
            json.append("\"}");
        }
        json.append("]}");
        return json.toString();
    }
}
