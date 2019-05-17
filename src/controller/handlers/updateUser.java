package controller.handlers;


import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class updateUser extends AsyncRequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        int age = 0;
        try {
            age = Integer.parseInt(request.getParameter("age"));
        } catch (Exception e) {
            //do nothing}
        }
        String gender = request.getParameter("gender");
        Person temp = getPersonService().getPerson(email);
        temp.setLeeftijd(age);
        temp.setFirstName(firstname);
        temp.setLastName(lastname);
        temp.setGeslacht(gender);
        System.out.println(temp);
        getPersonService().updatePersons(temp);

        //System.out.println(updated);
        return "{}";
    }
}
