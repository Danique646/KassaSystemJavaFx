package com.kjellvos.school.kassaSystem;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created by kjevo on 2/19/17.
 */
public class AddNewOrder {
    Main main;
    private Pane newOrderPane;
    Scene scene;

    public AddNewOrder(Main main){
        this.main = main;
    }

    public Scene getScene(int number){
        newOrderPane = new Pane();
        Button button = new Button("GO BACK! this is number " + number);
        button.setOnMouseClicked(event -> {
            main.returnToPreviousScene();
        });
        newOrderPane.getChildren().add(button);
        button = new Button("CONTINUE to number " + (number+1));
        button.relocate(100, 100);
        button.setOnMouseClicked(event -> {
            main.changeScene(this.getScene(number+1));
        });
        newOrderPane.getChildren().add(button);
        scene = new Scene(newOrderPane);
        return scene;
    }
}
