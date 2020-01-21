package Servlets;

import Utilities.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "LikesServlet", urlPatterns = {"/likes"})

public class LikesServlet extends OverServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = formatResponse(resp).getWriter();
        new DatabaseConnection().likePost(req.getParameter("userID"),req.getParameter("postID"));
        out.print("{'message':'Like given correctly"+req.getParameter("userID")+","+req.getParameter("postID")+"'}");
    }
}
