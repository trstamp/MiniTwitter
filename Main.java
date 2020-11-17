package sample;

import com.sun.source.tree.Tree;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class Main extends Application{

    private static List<User> twitterUsers = new LinkedList<>();
    private static List<UserGroup> twitterGroups = new LinkedList<>();
    private static Parent root;

    @Override
    public void start(Stage mainFrame) throws Exception{
        root = FXMLLoader.load(getClass().getResource("adminpanel.fxml"));

        mainFrame.setTitle("Mini Twitter");
        mainFrame.setScene(new Scene(root, 600, 340));
        mainFrame.getScene().getStylesheets().add("MiniTwitterFX.css");
        mainFrame.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static List<User> getTwitterUsers(){
        return twitterUsers;
    }

    public static List<UserGroup> getTwitterGroups(){
        return  twitterGroups;
    }

}
