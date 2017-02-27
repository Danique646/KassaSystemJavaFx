package com.kjellvos.school.kassaSystem.itemexplorer;

import com.kjellvos.school.gridHandler.GridHandler;
import com.kjellvos.school.kassaSystem.database.Database;
import com.kjellvos.school.kassaSystem.database.Item;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.Stack;

/**
 * Created by kjevo on 2/22/17.
 */
public class Main extends Application {
    private Stage primaryStage;
    private AddItem addItem;
    private Database database;

    private Stack<Scene> scenes = new Stack<Scene>();
    private Scene scene;

    private double height = 600D, width = 800D;

    private GridHandler gridHandler;

    private Button addNewItem;
    private TableView tableView;
    private TableColumn IDTableColumn, nameTableColumn, descriptionTableColumn, priceTableColumn, defaultPriceTableColumn, validFromTableColumn, validTillTableColumn, imageTableColumn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        database = new Database();
        addItem = new AddItem(this);
        gridHandler = new GridHandler();

        addNewItem = new Button("Nieuw item toevoegen aan de database!");
        addNewItem.setOnMouseClicked(event -> {
            changeScene(addItem.getScene());
        });

        tableView = new TableView();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        IDTableColumn = new TableColumn("ID");
        IDTableColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));

        nameTableColumn = new TableColumn("Naam");
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));

        descriptionTableColumn = new TableColumn("Beschrijving");
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));

        priceTableColumn = new TableColumn("Prijs");
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));

        defaultPriceTableColumn = new TableColumn("De default prijs?");
        defaultPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Item, CheckBox>("defaultPrice"));

        validFromTableColumn = new TableColumn("Geldig vanaf");
        validFromTableColumn.setCellValueFactory(new PropertyValueFactory<Item, Date>("validFrom"));

        validTillTableColumn = new TableColumn("Geldig tot");
        validTillTableColumn.setCellValueFactory(new PropertyValueFactory<Item, Date>("validTill"));

        imageTableColumn = new TableColumn("Afbeelding");
        imageTableColumn.setCellValueFactory(new PropertyValueFactory<Item, Button>("showImageButton"));

        tableView.setItems(getDatabase().getData());

        tableView.getColumns().addAll(IDTableColumn, nameTableColumn, descriptionTableColumn, priceTableColumn, defaultPriceTableColumn, validFromTableColumn, validTillTableColumn, imageTableColumn);

        gridHandler.add(0, 0, addNewItem, 1, 5);
        gridHandler.add(0, 1, tableView, 15, 5);

        scene = scenes.push(gridHandler.getGridAsScene());
        /*
        We set up the primary stage
         */
        primaryStage.setTitle("Item Explorer For Kassa System");
        primaryStage.setMinWidth(800);
        primaryStage.setWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void changeScene(Scene scene){
        this.scene = scene;
        scenes.push(this.scene);
        primaryStage.setScene(this.scene);
        primaryStage.show();
    }

    public void returnToPreviousScene(){
        System.out.println(scenes.size());
        if (scenes.size() > 1) {
            scenes.pop();
            primaryStage.setScene(scenes.peek());
        }
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public Database getDatabase(){
        return database;
    }
}
