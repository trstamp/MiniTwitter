package sample;

import com.sun.source.tree.Tree;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {

    private String username = "";

    @FXML
    private TextArea enterUserID;

    @FXML
    private TextArea enterTweet;

    @FXML
    private Button postTweet;

    @FXML
    private TreeView tweetList;

    @FXML
    private Button followUser;
    
    @FXML
    private TreeView followingList;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TreeItem<String> root = new TreeItem<>("");
        TreeItem<String> root2 = new TreeItem<>("Following List: ");
        root.setExpanded(true);
        root2.setExpanded(true);

        tweetList.setRoot(root);
        tweetList.setShowRoot(false);
        tweetList.refresh();

        followingList.setRoot(root2);
        followingList.setShowRoot(true);
        followingList.refresh();

    }

    public void followUser(MouseEvent mouseEvent) {
        String user = enterUserID.getText();

        for(User u : Main.getTwitterUsers()){
            if(u.getID() == user){
                TreeItem<String> item = new TreeItem<>(user);
                followingList.getRoot().getChildren().add(item);
            }
        }
        followingList.refresh();
    }

    public void refresh(MouseEvent mouseEvent) {
        followingList.refresh();
        tweetList.refresh();
    }

    public void postTweet(MouseEvent mouseEvent) {
        String tweet = enterTweet.getText();
        TreeItem<String> item = new TreeItem<>(tweet);
        tweetList.getRoot().getChildren().add(item);
        tweetList.refresh();
    }

    public void setUsername(String s){
        username = s;
    }
}
