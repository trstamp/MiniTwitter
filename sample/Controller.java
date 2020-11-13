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

    @FXML
    private TreeView<String> tree;

    @FXML
    private TextArea groupID;

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

    public void openUserView(javafx.scene.input.MouseEvent mouseEvent) throws IOException {

        Stage userView = new Stage();
        Parent userPanel = FXMLLoader.load(getClass().getResource("userview.fxml"));
        userView.setTitle("Placeholder");
        userView.setScene(new Scene(userPanel, 600, 430));
        userView.show();
    }

    public void addUserClicked(MouseEvent mouseEvent) {

    }

    public void addGroupClicked(MouseEvent mouseEvent) {
    }

    public void showUserTotal(MouseEvent mouseEvent) {

    }

    public void showGroupTotal(MouseEvent mouseEvent) {
    }

    public void showMessageTotal(MouseEvent mouseEvent) {
    }

    public void showPositivePercentage(MouseEvent mouseEvent) {
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
