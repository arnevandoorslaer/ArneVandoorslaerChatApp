package controller.handlers;

import domain.Person;
import domain.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Register extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<String>();

        String email = request.getParameter("email");
        String geslacht = request.getParameter("gender");
        int leeftijd = 0;
        try{
            leeftijd = Integer.parseInt(request.getParameter("age"));
        }catch (Exception e){
            errors.add(e.getMessage()) ;
        }
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        String password_confirmation = request.getParameter("confirm_password");
        Person temp = null;


        if (!password.equals(password_confirmation)) {
            errors.add("Passwords do not match.");
        }

        try{
            temp = new Person(email,password,firstname,lastname,geslacht,leeftijd, Role.LID);
        }catch(Exception e){
            errors.add(e.getMessage());
        }
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            return "register.jsp";
        }
        getPersonService().addPerson(temp);
        return destination;
    }
}
