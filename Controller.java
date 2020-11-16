package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private TreeItem<String> currentTreeItem;
    private List<String> users;
    private List<String> groups;

    private Visitor checkMessage = new MessageTotalVisitor();
    private Visitor checkUserTotal = new UserTotalVisitor();
    private Visitor checkGroupTotal = new GroupTotalVisitor();
    private Visitor checkPositive = new PositiveMessageVisitor();

    @FXML
    private TreeView<String> tree;

    @FXML
    private TextArea userID;

    @FXML
    private TextArea groupID;

    @FXML
    private Button addUser;

    @FXML
    private Button addGroup;

    @FXML
    private Button UserView;

    @FXML
    private Button showUserTotal;

    @FXML
    private Button showMessageTotal;

    @FXML
    private Button showGroupTotal;

    @FXML
    private Button showPositiveWordPercentage;

    public Controller(){
        users = new LinkedList<>();
        groups = new LinkedList<>();
        tree = new TreeView();
        groupID = new TextArea();
        UserView = new Button();
        showUserTotal = new Button();
        showMessageTotal = new Button();
        showGroupTotal = new Button();
        showPositiveWordPercentage = new Button();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public TreeView<String> getTree(){
        return tree;
    }

    public void setTreeRoot(TreeItem<String> root){
        tree.setRoot(root);
    }



    public void openUserView(javafx.scene.input.MouseEvent mouseEvent) throws IOException {

        Stage userView = new Stage();
        Parent userPanel = FXMLLoader.load(getClass().getResource("userview.fxml"));
        userView.setTitle("Placeholder");
        userView.setScene(new Scene(userPanel, 600, 430));
        userView.show();

    }

    public void addUserClicked(MouseEvent mouseEvent) {
        String user = userID.getText();

        User newUser = new User(user);
        TreeItem<String> item = new TreeItem<>();

        Main.getTwitterUsers().add(newUser);
        tree.getRoot().getChildren().add(item);

    }

    public void addGroupClicked(MouseEvent mouseEvent) {


    }

    public void showUserTotal(MouseEvent mouseEvent) {
        int counter = 0;
        for(User user : Main.getTwitterUsers()){
            checkUserTotal.visitUser(user);
            user.accept(checkUserTotal);
            counter++;
        }
        System.out.println("Number of users: " + counter);
    }

    public void showGroupTotal(MouseEvent mouseEvent) {
        int counter = 0;
        for(UserGroup group : Main.getTwitterGroups()){
            checkGroupTotal.visitUserGroup(group);
            group.accept(checkGroupTotal);
            counter++;
        }
        System.out.println("Number of users: " + counter);
    }

    public void showMessageTotal(MouseEvent mouseEvent) {
        int total = 0;
        for(User user : Main.getTwitterUsers()){
            checkMessage.visitUser(user);
            user.accept(checkMessage);
            total += user.getMessages().size();
        }
        System.out.println("Total number of messages: " + total);
    }

    public void showPositivePercentage(MouseEvent mouseEvent) {
        String[] happy = {"happy","good","great"};
        int happyCount = 0;
        int total = 0;

        for(User user : Main.getTwitterUsers()){
            checkPositive.visitUser(user);
            user.accept(checkPositive);
            total += user.getMessages().size();
            for(String message : user.getMessages()){
                for(int i = 0; i < happy.length; i++){
                    if(message.contains(happy[i]))
                        happyCount++;
                }
            }
        }
        double happyP = happyCount/total;
        System.out.println("Positive Percentage: " + happyP);
    }

    public void returnTreeItem(MouseEvent mouseEvent) {

        tree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> stringTreeItem, TreeItem<String> t1) {
                currentTreeItem = (TreeItem<String>) t1;
            }
        });
    }

    public void refresh(MouseEvent mouseEvent) {

    }

}
