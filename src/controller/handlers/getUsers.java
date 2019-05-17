package controller.handlers;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class getUsers extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Person> users = (ArrayList<Person>) getPersonService().getPersons();
        if (users.size() > 0) {
            String usersJSON = this.toJSON(users);
            return usersJSON;
        }
        return "{}";
    }

    private String toJSON(ArrayList<Person> users) {
        StringBuffer json = new StringBuffer();
        json.append("{\"users\":");

        json.append("[");
        getJsonFromOneUser(users.get(0), json);

        for (int i = 1; i < users.size(); i++) {
            json.append(",");
            getJsonFromOneUser(users.get(i), json);
        }
        json.append("]}");
        return json.toString();
    }

    private void getJsonFromOneUser(Person user, StringBuffer json) {
        json.append("{ \"firstname\" : \"");
        json.append(user.getFirstName());
        json.append("\",\"lastname\" : \"");
        json.append(user.getLastName());
        json.append("\",\"email\" : \"");
        json.append(user.getUserId());
        json.append("\",\"age\" : \"");
        json.append(user.getLeeftijd());
        json.append("\",\"gender\" : \"");
        json.append(user.getGeslacht());
        json.append("\"}");
    }
}
