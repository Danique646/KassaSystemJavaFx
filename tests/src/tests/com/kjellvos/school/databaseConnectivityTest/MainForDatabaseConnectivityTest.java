package com.kjellvos.school.databaseConnectivityTest;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by kjevo on 2/20/17.
 */
public class MainForDatabaseConnectivityTest extends Application {
    private SceneForDatabaseConnectivityTest sceneForDatabaseConnectivityTest;
    private DatabaseStuffForDatabaseConnectivityTest databaseStuffForDatabaseConnectivityTest;

    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        databaseStuffForDatabaseConnectivityTest = new DatabaseStuffForDatabaseConnectivityTest(this);
        sceneForDatabaseConnectivityTest = new SceneForDatabaseConnectivityTest(this, databaseStuffForDatabaseConnectivityTest.getData());
        scene = sceneForDatabaseConnectivityTest.getScene();

        /*
        We set up the primary stage
         */
        primaryStage.setTitle("Database Connectivity Test");
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

    public SceneForDatabaseConnectivityTest getSceneForDatabaseConnectivityTest() {
        return sceneForDatabaseConnectivityTest;
    }

    public DatabaseStuffForDatabaseConnectivityTest getDatabaseStuffForDatabaseConnectivityTest() {
        return databaseStuffForDatabaseConnectivityTest;
    }
}
