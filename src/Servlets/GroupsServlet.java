package Servlets;

import Model.Group;
import Utilities.DatabaseConnection;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GroupServlet", urlPatterns = {"/groups"})

public class GroupsServlet extends OverServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = formatResponse(response).getWriter();
        ArrayList<Group> groups = new DatabaseConnection().getGroups(request.getParameter("userID"));
        Gson encoder = new Gson();
        out.print(encoder.toJson(groups));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = formatResponse(resp).getWriter();
        String username = req.getReader().readLine();
        String password = req.getParameter("password");
        //Response response = new Response();
        //response.add("message","hello "+ username);
        out.print("{\"message\":\"hello "+ String.valueOf(req.getParameterMap().size())+" "+ username+"\"}");
    }
}
