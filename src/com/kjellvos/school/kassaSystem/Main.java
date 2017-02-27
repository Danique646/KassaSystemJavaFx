package com.kjellvos.school.kassaSystem;

import com.kjellvos.school.kassaSystem.database.Database;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {
    private Stage primaryStage;
    private Stack<Scene> scenes = new Stack<Scene>();
    private Scene scene;

    private Database database;

    private double height = 600D, width = 800D;
    /*
    Domino's pizza systeem
     */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        database = new Database();
        scene = scenes.push(new Menu(this).getScene());

        /*
        We set up the primary stage
        */
        primaryStage.setTitle("Kassa");
        primaryStage.setMinWidth(800);
        primaryStage.setWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();

        /*
        We create the change listeners for the width and height
        */
        setupWidthAndHeightChangeListeners();

    }

    public void changeScene(Scene scene){
        this.scene = scene;
        scenes.push(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void returnToPreviousScene(){
        if (scenes.size() > 1) {
            scenes.pop();
            primaryStage.setScene(scenes.peek());
        }
    }

    public void setupWidthAndHeightChangeListeners() {
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            width = newValue.doubleValue();
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            height = newValue.doubleValue();
        });
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public Database getDatabase(){
        return database;
    }
}
