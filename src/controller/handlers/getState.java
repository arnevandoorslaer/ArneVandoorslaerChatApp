package controller.handlers;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class getState extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");
        String stateJSON = this.toJSON(person.getState());

        return stateJSON;
    }

    private String toJSON(String state) {
        StringBuffer json = new StringBuffer();

        json.append("{ \"state\" : \"");
        json.append(state);
        json.append("\"}");

        return json.toString();
    }
}
