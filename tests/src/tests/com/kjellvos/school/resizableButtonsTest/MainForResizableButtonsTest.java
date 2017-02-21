package com.kjellvos.school.resizableButtonsTest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by kjevo on 2/18/17.
 */
public class MainForResizableButtonsTest extends Application {
    private Scene scene;
    private Pane menuPane;

    private Font font;
    private Button newOrderButton, checkInternetOrdersButton;
    private Text newOrderText, checkInternetOrdersText;
    private int numberOfButtons = 2;

    private double height = 600D, width = 800D, padding = 10D, topBottomPadding, leftRightPadding;

    @Override
    public void start(Stage primaryStage){
        scene = getScene();

        /*
        We set up the primary stage
         */
        primaryStage.setTitle("Resizable Buttons Test");
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

    public Scene getScene(){
        menuPane = new Pane();
        font = new Font("Verdana", width/40); //default size is 20
        createButtons();
        scene = new Scene(menuPane);
        resizeScene();
        setupWidthAndHeightChangeListeners();
        return scene;
    }

    private void setupWidthAndHeightChangeListeners() {
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            resizeScene();
            width = newValue.doubleValue();
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            resizeScene();
            height = newValue.doubleValue();
        });
    }


    /*
    If you want to add another button you should create a new button variable and a new text variable,
    and add them to createButtons and resizeItemsInPane();
     */
    private void createButtons() {
        /*==================================================================
         * NEW ORDERS BUTTON
         */
        newOrderText = new Text("Test button 1");    //Should be text and should be changed if copy and pasted to create a new one
        newOrderText.setFont(font);                                     //Should be text and should be changed if copy and pasted to create a new one
        newOrderButton = new Button(newOrderText.getText().toString()); //Should be button and should be changed if copy and pasted to create a new one
        newOrderButton.setFont(font);                                   //Should be button and should be changed if copy and pasted to create a new one
        menuPane.getChildren().add(newOrderButton);                     //Should be button and should be changed if copy and pasted to create a new one
        newOrderButton.applyCss();                                      //Should be button and should be changed if copy and pasted to create a new one
        newOrderButton.layout();                                        //Should be button and should be changed if copy and pasted to create a new one


        /*==================================================================
         * INTERNET ORDERS BUTTON
         */
        checkInternetOrdersText = new Text("Test button 2");                   //Should be text and should be changed if copy and pasted to create a new one
        checkInternetOrdersText.setFont(font);                                                  //Should be text and should be changed if copy and pasted to create a new one
        checkInternetOrdersButton = new Button(checkInternetOrdersText.getText().toString());   //Should be button and should be changed if copy and pasted to create a new one
        checkInternetOrdersButton.setFont(font);                                                //Should be button and should be changed if copy and pasted to create a new one
        menuPane.getChildren().add(checkInternetOrdersButton);                                  //Should be button and should be changed if copy and pasted to create a new one
        checkInternetOrdersButton.applyCss();                                                   //Should be button and should be changed if copy and pasted to create a new one
        checkInternetOrdersButton.layout();                                                     //Should be button and should be changed if copy and pasted to create a new one
    }

    private void resizeScene() {
        font = new Font("Verdana", height/40); // default size is 20

        double relocateY = padding;                                                                                             //initialize to padding so that the first item goes that amount from the top
        newOrderButton.setFont(font);
        newOrderText.setFont(font);
        topBottomPadding = ((((height-padding)/numberOfButtons)/2)-(padding/2)-(newOrderText.getLayoutBounds().getHeight()/2));
        leftRightPadding = (width/2)-padding-(newOrderText.getLayoutBounds().getWidth()/2);
        newOrderButton.setPadding(new Insets(topBottomPadding, leftRightPadding, topBottomPadding, leftRightPadding));
        newOrderButton.relocate(padding, relocateY);
        relocateY += (topBottomPadding*2) + padding + newOrderText.getLayoutBounds().getHeight();

        checkInternetOrdersButton.setFont(font);
        checkInternetOrdersText.setFont(font);
        topBottomPadding = ((((height-padding)/numberOfButtons)/2)-(padding/2)-(checkInternetOrdersText.getLayoutBounds().getHeight()/2));
        leftRightPadding = (width/2)-padding-(checkInternetOrdersText.getLayoutBounds().getWidth()/2);
        checkInternetOrdersButton.setPadding(new Insets(topBottomPadding, leftRightPadding, topBottomPadding, leftRightPadding));
        checkInternetOrdersButton.relocate(padding, relocateY);
        relocateY += (topBottomPadding*2) + padding + newOrderText.getLayoutBounds().getHeight();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
