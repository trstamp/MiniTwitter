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

public class Main extends Application {

    private static List<User> twitterUsers = new LinkedList<>();
    private static List<UserGroup> twitterGroups = new LinkedList<>();

    @Override
    public void start(Stage mainFrame) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("adminpanel.fxml"));

        Controller ct = new Controller();
        //TreeView<String> tree = ct.getTree();

        TreeItem<String> treeRoot = new TreeItem<>("Root");
        ct.setTreeRoot(treeRoot);
        treeRoot.setExpanded(true); //Root will be expanded on launch


        // Pre-set users and groups
        UserGroup cs = new UserGroup("CS Nerds");

        User steve = new User("Steve Minecraft");
        twitterUsers.add(steve);
        User tyler = new User("Tyler Stamp");
        twitterUsers.add(tyler);
        User camille = new User("Camille Johnson");
        twitterUsers.add(camille);
        User ryan = new User("Ryan Atienza");
        twitterUsers.add(ryan);
        User joe = new User("Joe Canada");
        twitterUsers.add(joe);
        User ash = new User("Ash Ketchum");
        twitterUsers.add(ash);

        for(User element : twitterUsers){
            TreeItem<String> newItem = new TreeItem<>(element.getID());
            treeRoot.getChildren().add(newItem);
        }

        mainFrame.setTitle("Mini Twitter");
        mainFrame.setScene(new Scene(root, 600, 340));
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
