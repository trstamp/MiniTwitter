package sample;

import java.util.LinkedList;
import java.util.List;

public class User extends Subject implements Visitable {

    private String ID;
    private List<String> followers;
    private List<String> followings;
    private List<String> messages;
    private List<String> newsFeed;

    public User(String i_d){
        ID = i_d;
        followers = new LinkedList<>();
        followings = new LinkedList<>();
        newsFeed = new LinkedList<>();
    }

    public void follow(String ID){
        followings.add(ID);
    }

    public String sendTweet(String msg){
        return msg;
    }

    public String getID(){
        return ID;
    }

    public List<String> getMessages(){
        return messages;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitUser(this);
    }

    public List<String> getFollowings(){
        return followings;
    }

    public List<String> getNewsFeed() {
        return newsFeed;
    }
}
