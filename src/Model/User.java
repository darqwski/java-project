package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements DBInterface {
    String username;
    String userID;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public void getFromRow(ResultSet result) {
        try {
            username = result.getString("username");
            userID = result.getString("userID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
