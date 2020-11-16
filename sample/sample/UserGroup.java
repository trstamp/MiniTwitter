package sample;

import java.util.LinkedList;
import java.util.List;

public class UserGroup implements Visitable{

    private String ID;
    private List<User> users;


    public UserGroup(String ID){
        ID = this.ID;
        users = new LinkedList<>();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitUserGroup(this);
    }
}
