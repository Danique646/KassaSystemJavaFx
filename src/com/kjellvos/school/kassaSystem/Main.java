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

    public Database getDatabase(){
        return database;
    }
}
