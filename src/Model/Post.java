package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post implements DBInterface {
    String text;
    String date;
    String userName;
    String postID;
    int likes;

    @Override
    public void getFromRow(ResultSet result) {
        try {
            text = result.getString("text");
            date = result.getString("datetime");
            userName = result.getString("username");
            postID = result.getString("postID");
            likes = result.getInt("likesNumber");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
