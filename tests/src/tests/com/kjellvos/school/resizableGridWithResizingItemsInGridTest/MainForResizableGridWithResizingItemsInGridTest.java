package com.kjellvos.school.resizableGridWithResizingItemsInGridTest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by kjevo on 2/21/17.
 */
public class MainForResizableGridWithResizingItemsInGridTest extends Application {
    private GridHandler gridHandler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        gridHandler = new GridHandler(this, new Pane());

        gridHandler.add(3, 2, new Button("3,2"));
        gridHandler.add(0, 2, new Button("0,2"));
        gridHandler.add(1, 2, new Button("1,2"));
        gridHandler.add(2, 2, new Button("2,2"));

        gridHandler.add(1, 0, new Button("1,0"));
        gridHandler.add(0, 1, new Button("0,1"));
        gridHandler.add(0, 0, new Button("0,0"));
        gridHandler.add(1, 1, new Button("1,1"));

        gridHandler.add(8, 7, new Button("8,7"));
        gridHandler.add(5, 6, new Button("5,6"));
        gridHandler.add(4, 3, new Button("4,3"));
        gridHandler.add(8, 20, new Button("8,20"));

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
        gridHandler.calculateAllPositionAndSize();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
