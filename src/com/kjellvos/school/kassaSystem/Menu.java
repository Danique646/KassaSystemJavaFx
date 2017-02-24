package com.kjellvos.school.kassaSystem;

import com.kjellvos.school.gridHandler.GridHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Created by kjevo on 2/19/17.
 */
public class Menu {
    private Main main;
    private Menu menu;

    private GridHandler gridHandler;

    private Button newOrderButton, checkInternetOrdersButton;

    public Menu(Main main){
        this.main = main;
    }

    public Scene getScene(){
        menu = this;

        newOrderButton = new Button("Nieuwe bestelling toevoegen.");
        newOrderButton.setOnMouseClicked(event -> main.changeScene(new AddNewOrder(main).getScene()));
        checkInternetOrdersButton = new Button("Internet orders bekijken.");


        gridHandler = new GridHandler();
        gridHandler.add(0, 0, newOrderButton);
        gridHandler.add(0, 1, checkInternetOrdersButton);

        return gridHandler.getGridAsScene();
    }
}
