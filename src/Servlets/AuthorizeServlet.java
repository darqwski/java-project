package Servlets;

import Utilities.DatabaseConnection;
import Utilities.Response;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "AuthorizeServlet", urlPatterns = {"/authorize"})

public class AuthorizeServlet extends OverServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = formatResponse(response).getWriter();
        out.print("Hello World");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = formatResponse(resp).getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String userID = new DatabaseConnection().login(username,password);
        if(userID != null){
            out.print("{\"message\":\"OK\", \"userID\":\""+userID+"\", \"username\":\""+username+"\"}");
        }
        else {
            out.print("{\"message\":\"Wrong password, try again!\"}");
        }
    }
}
