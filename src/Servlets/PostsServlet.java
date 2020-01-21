package Servlets;

import Model.Group;
import Model.Post;
import Utilities.DatabaseConnection;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "PostsServlet", urlPatterns = {"/posts"})

public class PostsServlet extends OverServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = formatResponse(response).getWriter();
        String groupID = request.getParameter("groupID");
        ArrayList<Post> posts = new DatabaseConnection().getPostFromGroup(groupID);
        Gson encoder = new Gson();
        out.print(encoder.toJson(posts));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = formatResponse(resp).getWriter();
        String groupID = request.getParameter("groupID");
        String userID = request.getParameter("userID");
        String text = request.getParameter("text");
        try {
            new DatabaseConnection().postPost(userID,groupID,text);
        } catch (Exception e){

        }
        out.print("{'message':'Message added correctly'}");
    }
}
