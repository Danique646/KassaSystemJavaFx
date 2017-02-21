package com.kjellvos.school.kassaSystem;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by kjevo on 2/19/17.
 */
public class AddNewOrder {
    private Main main;
    private Pane newOrderPane;
    private Scene scene;
    private Font font;

    private Text lastMenuText, scanText, payText;
    private Button lastMenuButton, scanButton, payButton;

    private double topBottomPadding, leftRightPadding;
    private static final double padding = 10D,
                                numberOfButtons = 3;

    public AddNewOrder(Main main){
        this.main = main;
    }

    public Scene getScene(){
        newOrderPane = new Pane();

        createButtons();
        resizeScene();

        scene = new Scene(newOrderPane);
        return scene;
    }

    private void createButtons() {
        font = new Font("Verdana", main.getWidth()/40); //default size is 20

        /*==========================================
        LAST MENU BUTTON
         */
        lastMenuText = new Text("Terug naar menu.");    //Should be text and should be changed if copy and pasted to create a new one
        lastMenuText.setFont(font);                                     //Should be text and should be changed if copy and pasted to create a new one
        lastMenuButton = new Button(lastMenuText.getText().toString()); //Should be button and should be changed if copy and pasted to create a new one
        lastMenuButton.setOnMouseClicked(event -> {                     //Should be button and should be changed if copy and pasted to create a new one

        });
        lastMenuButton.setFont(font);                                   //Should be button and should be changed if copy and pasted to create a new one
        newOrderPane.getChildren().add(lastMenuButton);                         //Should be button and should be changed if copy and pasted to create a new one
        lastMenuButton.applyCss();                                      //Should be button and should be changed if copy and pasted to create a new one
        lastMenuButton.layout();

        /*==========================================
        SCAN BUTTON
         */
        scanText = new Text("Scannen.");    //Should be text and should be changed if copy and pasted to create a new one
        scanText.setFont(font);                                     //Should be text and should be changed if copy and pasted to create a new one
        scanButton = new Button(scanText.getText().toString());     //Should be button and should be changed if copy and pasted to create a new one
        scanButton.setOnMouseClicked(event -> {                     //Should be button and should be changed if copy and pasted to create a new one

        });
        scanButton.setFont(font);                                   //Should be button and should be changed if copy and pasted to create a new one
        newOrderPane.getChildren().add(scanButton);                         //Should be button and should be changed if copy and pasted to create a new one
        scanButton.applyCss();                                      //Should be button and should be changed if copy and pasted to create a new one
        scanButton.layout();

        /*==========================================
        PAY BUTTON
         */
        payText = new Text("Afrekenen.");    //Should be text and should be changed if copy and pasted to create a new one
        payText.setFont(font);                                     //Should be text and should be changed if copy and pasted to create a new one
        payButton = new Button(payText.getText().toString());     //Should be button and should be changed if copy and pasted to create a new one
        payButton.setOnMouseClicked(event -> {                     //Should be button and should be changed if copy and pasted to create a new one

        });
        payButton.setFont(font);                                   //Should be button and should be changed if copy and pasted to create a new one
        newOrderPane.getChildren().add(payButton);                         //Should be button and should be changed if copy and pasted to create a new one
        payButton.applyCss();                                      //Should be button and should be changed if copy and pasted to create a new one
        payButton.layout();
    }

    private void resizeScene() {
        double  height = main.getHeight(),
                width = main.getWidth(),
                spaceForOtherItemsTopBottom = height*0.8,
                spaceForButtonsTopBottom = height*0.2;

        font = new Font("Verdana", height/40); // default size is 20

        double relocateX = padding;//Initialize to padding so it goes right amount from top
        lastMenuButton.setFont(font);                                                                                                           //Should be button and should be changed if copy and pasted to create a new one
        lastMenuText.setFont(font);                                                                                                             //Should be text and should be changed if copy and pasted to create a new one
        topBottomPadding = (((spaceForButtonsTopBottom-padding)/2)-(padding/2)-(lastMenuText.getLayoutBounds().getHeight()/2))*0.2;             //Should be text and should be changed if copy and pasted to create a new one
        leftRightPadding = (((width/numberOfButtons)/2)-(padding/2)-(lastMenuText.getLayoutBounds().getWidth()/2)/numberOfButtons);                                                     //Should be text and should be changed if copy and pasted to create a new one
        lastMenuButton.setPadding(new Insets(topBottomPadding, leftRightPadding, topBottomPadding, leftRightPadding));                          //Should be button and should be changed if copy and pasted to create a new one
        lastMenuButton.relocate(relocateX, padding);                                                                                            //Should be button and should be changed if copy and pasted to create a new one
        relocateX += (leftRightPadding*2) + padding + lastMenuText.getLayoutBounds().getHeight();                                               //Should be text and should be changed if copy and pasted to create a new one

        scanButton.setFont(font);                                                                                                           //Should be button and should be changed if copy and pasted to create a new one
        scanText.setFont(font);                                                                                                             //Should be text and should be changed if copy and pasted to create a new one
        topBottomPadding = (((spaceForButtonsTopBottom-padding)/2)-(padding/2)-(scanText.getLayoutBounds().getHeight()/2))*0.2;             //Should be text and should be changed if copy and pasted to create a new one
        leftRightPadding = (width/2)-padding-(scanText.getLayoutBounds().getWidth()/2)/numberOfButtons;                                                     //Should be text and should be changed if copy and pasted to create a new one
        scanButton.setPadding(new Insets(topBottomPadding, leftRightPadding, topBottomPadding, leftRightPadding));                          //Should be button and should be changed if copy and pasted to create a new one
        scanButton.relocate(relocateX, padding);                                                                                            //Should be button and should be changed if copy and pasted to create a new one
        relocateX += (leftRightPadding*2) + padding + scanText.getLayoutBounds().getHeight();                                               //Should be text and should be changed if copy and pasted to create a new one

        payButton.setFont(font);                                                                                                            //Should be button and should be changed if copy and pasted to create a new one
        payText.setFont(font);                                                                                                              //Should be text and should be changed if copy and pasted to create a new one
        topBottomPadding = (((spaceForButtonsTopBottom-padding)/2)-(padding/2)-(payText.getLayoutBounds().getHeight()/2))*0.2;              //Should be text and should be changed if copy and pasted to create a new one
        leftRightPadding = (width/2)-padding-(payText.getLayoutBounds().getWidth()/2)/numberOfButtons;                                                      //Should be text and should be changed if copy and pasted to create a new one
        payButton.setPadding(new Insets(topBottomPadding, leftRightPadding, topBottomPadding, leftRightPadding));                           //Should be button and should be changed if copy and pasted to create a new one
        payButton.relocate(relocateX, padding);                                                                                             //Should be button and should be changed if copy and pasted to create a new one
        relocateX += (leftRightPadding*2) + padding + payText.getLayoutBounds().getHeight();                                                //Should be text and should be changed if copy and pasted to create a new one
    }
}
