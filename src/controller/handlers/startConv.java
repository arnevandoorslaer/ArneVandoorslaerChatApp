package controller.handlers;


import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class startConv extends AsyncRequestHandler {
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
                return toJSON(getPersonService().getConversation(person, friend).getContent());
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

    private String toJSON(ArrayList<String> conversation) {
        StringBuilder json = new StringBuilder();
        json.append("{\"content\":[");
        json.append("\"").append(conversation.get(0)).append("\"");
        for (int i = 1; i < conversation.size(); i++) {
            json.append(",");
            json.append("\"").append(conversation.get(i)).append("\"");
        }
        json.append("]}");
        return json.toString();
    }
}
