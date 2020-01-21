package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Group implements DBInterface {

    String name;
    String groupID;
    String membersNumber;
    String postsNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getMembersNumber() {
        return membersNumber;
    }

    public void setMembersNumber(String membersNumber) {
        this.membersNumber = membersNumber;
    }

    public String getPostsNumber() {
        return postsNumber;
    }

    public void setPostsNumber(String postsNumber) {
        this.postsNumber = postsNumber;
    }

    @Override
    public void getFromRow(ResultSet result) {
        try {
            name = result.getString("name");
            groupID = result.getString("groupID");
            membersNumber = result.getString("membersNumber");
            postsNumber = result.getString("postsNumber");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
