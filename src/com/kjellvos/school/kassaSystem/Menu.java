package com.kjellvos.school.kassaSystem;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by kjevo on 2/19/17.
 */
public class Menu {
    private Main main;
    private Menu menu;
    private Scene scene;
    private Pane menuPane;


    private Font font;
    private Button newOrderButton, checkInternetOrdersButton;
    private Text newOrderText, checkInternetOrdersText;
    private int numberOfButtons = 2;    //WARNING DON'T FORGET TO CHANGE THIS WHEN YOU ADD A BUTTON!!

    private double height = 600D, width = 800D, padding = 10D, topBottomPadding, leftRightPadding;

    public Menu(Main main){
        this.main = main;
    }

    public Scene getScene(){
        menu = this;
        menuPane = new Pane();
        font = new Font("Verdana", main.getWidth()/40); //default size is 20
        createButtons();
        scene = new Scene(menuPane);
        resizeScene();
        return scene;
    }

    /*
    If you want to add another button you should create a new button variable and a new text variable,
    and add them to createButtons and resizeItemsInPane();
     */

    private void createButtons() {
        /*==================================================================
         * NEW ORDERS BUTTON
         */
        newOrderText = new Text("Nieuwe bestelling invoeren.");    //Should be text and should be changed if copy and pasted to create a new one
        newOrderText.setFont(font);                                     //Should be text and should be changed if copy and pasted to create a new one
        newOrderButton = new Button(newOrderText.getText().toString()); //Should be button and should be changed if copy and pasted to create a new one
        newOrderButton.addEventHandler(MouseEvent.MOUSE_CLICKED,        //Should be button and should be changed if copy and pasted to create a new one
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        main.changeScene(new AddNewOrder(main).getScene(1));
                    }
                });
        newOrderButton.setFont(font);                                   //Should be button and should be changed if copy and pasted to create a new one
        menuPane.getChildren().add(newOrderButton);                     //Should be button and should be changed if copy and pasted to create a new one
        newOrderButton.applyCss();                                      //Should be button and should be changed if copy and pasted to create a new one
        newOrderButton.layout();                                        //Should be button and should be changed if copy and pasted to create a new one


        /*==================================================================
         * INTERNET ORDERS BUTTON
         */
        checkInternetOrdersText = new Text("Internet orders bekijken.");                   //Should be text and should be changed if copy and pasted to create a new one
        checkInternetOrdersText.setFont(font);                                                  //Should be text and should be changed if copy and pasted to create a new one
        checkInternetOrdersButton = new Button(checkInternetOrdersText.getText().toString());   //Should be button and should be changed if copy and pasted to create a new one
        //checkInternetOrdersButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
         //       event -> stage.setScene(new Scene(new Pane()))
        //);
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
}
