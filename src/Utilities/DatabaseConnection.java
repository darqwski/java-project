package Utilities;

import Model.DBInterface;
import Model.Group;
import Model.Post;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private final static String DBURL = "jdbc:mysql://localhost:3306/facebook";
    private final static String DBUSER = "root";
    private final static String DBDRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection connection;
    private Statement statement;
    private String query;

    public DatabaseConnection() {
        //inicjalizacja parserów
    }
    public ArrayList<Post>getPostFromGroup(String groupID){
        ArrayList<Post> results = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DBURL, DBUSER, "");
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(
                    "SELECT posts.*, users.username, (count(*)-1) AS likesNumber\n" +
                            " FROM posts" +
                            " INNER JOIN users ON users.userID = posts.userID" +
                            " LEFT JOIN likes ON posts.postID = likes.postID" +
                            " WHERE groupID = "+groupID +
                            " GROUP BY postID;"
            );
            while(result.next()) {
                Post newPost = new Post();
                newPost.getFromRow(result);
                results.add(newPost);
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
    public ArrayList<Group> getGroups(String userId){
        ArrayList<Group> results = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DBURL, DBUSER, "");
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT groups.* FROM users_groups " +
                            "INNER JOIN groups on groups.groupID = users_groups.groupID " +
                            "WHERE users_groups.userID = "+userId);
            while(result.next()) {
                Group newGroup = new Group();
                newGroup.getFromRow(result);
                results.add(newGroup);
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public String login(String username, String password){
        String response = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DBURL, DBUSER, "");
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"'");
            while(result.next()) {
                User user = new User();
                user.getFromRow(result);
                response = user.getUserID();
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }
    public void postPost(String userId, String groupID, String text){
        ArrayList<Group> results = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DBURL, DBUSER, "");
            statement = connection.createStatement();
            statement.execute(
                    "INSERT INTO `posts` (`postID`, `userID`, `groupID`, `text`, `datetime`) " +
                            "VALUES (NULL, '"+userId+"', '"+groupID+"', '"+text+"', NOW());"
            );

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void likePost(String userId, String postID){
        ArrayList<Group> results = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DBURL, DBUSER, "");
            statement = connection.createStatement();
            statement.execute(
                    "INSERT INTO `likes` (`likeID`, `postID`, `userID`) VALUES (NULL, "+postID+", "+userId+");"
            );

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
