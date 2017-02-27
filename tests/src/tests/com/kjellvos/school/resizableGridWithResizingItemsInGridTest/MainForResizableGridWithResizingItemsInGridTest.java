package com.kjellvos.school.resizableGridWithResizingItemsInGridTest;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by kjevo on 2/21/17.
 */
public class MainForResizableGridWithResizingItemsInGridTest extends Application {
    private GridHandler gridHandler;

    /*
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        gridHandler = new GridHandler(this, new Pane());

        gridHandler.add(0, 0, new Button("Vorig menu!"));
        gridHandler.add(1, 0, new Button("Scannen!"));
        gridHandler.add(2, 0, new Button("Afrekenen"));

        gridHandler.add(0, 1, new TableView<>(), 5, 3);

        gridHandler.add(0, 6, new Text("Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! Hello! This is a test text!! "), 1, 3);
        /*
        We set up the primary stage
         */
        primaryStage.setTitle("Database Connectivity Test");
        primaryStage.setMinWidth(800);
        primaryStage.setWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setHeight(600);
        primaryStage.setScene(gridHandler.getGridAsScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
