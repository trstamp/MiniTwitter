package sample;

import java.util.LinkedList;
import java.util.List;

public class UserGroup implements Visitable{

    private String ID;
    private List<User> users;


    public UserGroup(String i_d){
        ID = i_d;
        users = new LinkedList<>();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitUserGroup(this);
    }

    public List<User> getUsers(){
        return users;
    }

    public String getID(){
        return ID;
    }
}
