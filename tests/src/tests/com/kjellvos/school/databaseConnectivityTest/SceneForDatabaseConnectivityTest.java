package com.kjellvos.school.databaseConnectivityTest;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Observable;

/**
 * Created by kjevo on 2/20/17.
 */
public class SceneForDatabaseConnectivityTest {
    private MainForDatabaseConnectivityTest main;
    private Scene scene;
    private Group group;
    private HBox hBox;
    private VBox vBox;

    private TableView<ValuesForDatabaseConnectivityTest> tableView;
    private TableColumn idColumn, valueColumn;
    private ObservableList<ValuesForDatabaseConnectivityTest> data;

    private Label label, textFieldLabel;
    private TextField textField;
    private Button button;

    public SceneForDatabaseConnectivityTest(MainForDatabaseConnectivityTest main, ObservableList<ValuesForDatabaseConnectivityTest> data){
        this.main = main;
        this.data = data;
    }

    public Scene getScene(){
        group = new Group();
        hBox = new HBox();
        vBox = new VBox();

        label = new Label("Values from Database:");
        vBox.getChildren().add(label);

        tableView = new TableView<ValuesForDatabaseConnectivityTest>();
        tableView.setEditable(false);

        idColumn = new TableColumn("ID");
        idColumn.setPrefWidth(250);
        idColumn.setCellValueFactory(new PropertyValueFactory<ValuesForDatabaseConnectivityTest, Integer>("id"));

        valueColumn = new TableColumn("Value");
        valueColumn.setPrefWidth(250);
        valueColumn.setCellValueFactory(new PropertyValueFactory<ValuesForDatabaseConnectivityTest, Integer>("value"));

        tableView.setItems(data);

        tableView.getColumns().addAll(idColumn, valueColumn);
        vBox.getChildren().add(tableView);

        textFieldLabel = new Label("Value to add to database:");
        textField = new TextField();

        button = new Button("Add to DB!");
        button.setOnMouseClicked(event -> {
            main.getDatabaseStuffForDatabaseConnectivityTest().insertValueToDatabase(Integer.parseInt(textField.getText().toString()));
            textField.setText(null);
        });

        hBox.getChildren().addAll(textFieldLabel, textField, button);
        vBox.getChildren().add(hBox);

        group.getChildren().add(vBox);

        scene = new Scene(group);
        return scene;
    }

    public void refreshTableView(ObservableList<ValuesForDatabaseConnectivityTest> data) {
        this.data = data;
        tableView.setItems(data);
    }
}
