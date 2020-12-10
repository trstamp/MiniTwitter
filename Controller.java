package sample;

import com.sun.source.tree.Tree;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, Observer {

    private String lastUser;
    private Controller2 ct;

    private TreeItem<String> currentTreeItem;

    private Visitor checkMessage = new MessageTotalVisitor();
    private Visitor checkUserTotal = new UserTotalVisitor();
    private Visitor checkGroupTotal = new GroupTotalVisitor();
    private Visitor checkPositive = new PositiveMessageVisitor();

    @FXML
    private TreeView treeView;

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
        groupID = new TextArea();
        UserView = new Button();
        showUserTotal = new Button();
        showMessageTotal = new Button();
        showGroupTotal = new Button();
        showPositiveWordPercentage = new Button();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TreeItem<String> root = new TreeItem<>("Root");
        treeView.setRoot(root);
        root.setExpanded(true);

        treeView.refresh();

    }

    @FXML
    void openUserView(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader((getClass().getResource("userview.fxml")));
            AnchorPane userPanel = loader.load();
            Stage userView = new Stage();

            String title = String.valueOf(currentTreeItem.getValue());
            ct = new Controller2();
            ct.setUsername(title);

            userView.setScene(new Scene(userPanel, 600, 430));
            userView.setTitle(title);
            userView.show();

        } catch (Exception e) {
            System.out.println("Cannot open window.");
        }
    }

    public void addUserClicked(MouseEvent mouseEvent) {
        String user = userID.getText();
        boolean existingUser = false;

        User newUser = new User(user);
        TreeItem<String> item = new TreeItem<>(user);

        // Verify Twitter Users
        for(User u : Main.getTwitterUsers()) {
            if(u.getID() == user){
                existingUser = true;
                System.out.println("User already created.");
            }
        }

        if(existingUser = false){
            Main.getTwitterUsers().add(newUser);
            treeView.getRoot().getChildren().add(item);
        }

        System.out.println("Time of user creation: " + System.currentTimeMillis());

        treeView.refresh();

    }

    public void addGroupClicked(MouseEvent mouseEvent) {
        String group = groupID.getText();
        boolean existingGroup = false;

        Text txt = new Text(group);
        txt.setStyle("-fx-font-weight: bold");

        UserGroup newGroup = new UserGroup(group);
        TreeItem<String> item = new TreeItem<>(group);

        // Verify Group
        for(UserGroup ug : Main.getTwitterGroups()){
            if(ug.getID() == group){
                existingGroup = true;
                System.out.println("Group already created.");
            }
        }

        if(existingGroup = false) {
            Main.getTwitterGroups().add(newGroup);
            treeView.getRoot().getChildren().add(item);
        }

        System.out.println("Time of group creation: " + System.currentTimeMillis());

        treeView.refresh();


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
        System.out.println("Number of groups: " + counter);
    }

    public void showMessageTotal(MouseEvent mouseEvent) {
        int total = 0;
        if(Main.getTwitterUsers() != null){
            for(User user : Main.getTwitterUsers()){
                checkMessage.visitUser(user);
                user.accept(checkMessage);
                total += user.getMessages().size();
            }
        }
        System.out.println("Total number of messages: " + total);
    }

    public void showPositivePercentage(MouseEvent mouseEvent) {
        String[] happy = {"happy","good","great"};
        double happyP;
        int happyCount = 0;
        int total = 0;

        for(User user : Main.getTwitterUsers()){
            checkPositive.visitUser(user);
            user.accept(checkPositive);
            total += user.getMessages().size();
            if(user.getMessages() != null){
                for(String message : user.getMessages()){
                    for(int i = 0; i < happy.length; i++){
                        if(message.contains(happy[i]))
                            happyCount++;
                    }
                }
            }
        }
        if(total != 0){
            happyP = happyCount/total;
        }
        else{
            happyP = 0.0;
        }

        System.out.println("Positive Percentage: " + happyP);

    }

    public void returnTreeItem(MouseEvent mouseEvent) {
        currentTreeItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
    }

    public TreeView<String> getTree(){
        return treeView;
    }

    public void userToGroup() {
        User user = new User(userID.getText());
        Main.getTwitterUsers().add(user);
        TreeItem<String> item = new TreeItem<>(userID.getText());
        currentTreeItem.getChildren().add(item);
    }



    public TreeItem<String> getCurrentTreeItem(){
        return currentTreeItem;
    }

    @Override
    public void update(Subject subject) {
        if(subject instanceof User){

        }
    }

    public void returnLastUpdatedUser(MouseEvent mouseEvent) {
        ct.getLastUser();
    }
}
