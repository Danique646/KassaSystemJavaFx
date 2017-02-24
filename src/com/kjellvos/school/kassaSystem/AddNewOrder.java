package com.kjellvos.school.kassaSystem;

import com.kjellvos.school.gridHandler.GridHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.Optional;

/**
 * Created by kjevo on 2/19/17.
 */
public class AddNewOrder {
    private Main main;

    private Button lastMenuButton, scanButton, payButton;
    private TableView itemsTableView;

    private GridHandler gridHandler;

    private ObservableList<Item> data;


    public AddNewOrder(Main main){
        this.main = main;
    }

    public Scene getScene(){
        lastMenuButton = new Button("Vorige menu.");
        lastMenuButton.setOnMouseClicked(event -> main.returnToPreviousScene());
        scanButton = new Button("Scannen.");
        scanButton.setOnMouseClicked(event -> showScanWindow());
        payButton = new Button("Afrekenen");

        data = main.getDatabase().getData();
        itemsTableView = new TableView(data);

        gridHandler = new GridHandler();
        gridHandler.add(0, 0, lastMenuButton);
        gridHandler.add(1, 0, scanButton);
        gridHandler.add(2, 0, payButton);
        gridHandler.add(0, 1, itemsTableView, 5 ,3);

        return gridHandler.getGridAsScene();
    }

    private void setupData() {
        data = FXCollections.observableArrayList();

    }

    public void showScanWindow() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Item scannen");
        dialog.setHeaderText("Voer aub de product code in.");

        ButtonType gescandButtonType = new ButtonType("Gescand!", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, gescandButtonType);

        TextField productCode = new TextField();
        productCode.setPromptText("Product Code");

        Node gescandButton = dialog.getDialogPane().lookupButton(gescandButtonType);
        gescandButton.setDisable(true);

        productCode.textProperty().addListener((observable, oldValue, newValue) -> {
            gescandButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(productCode);

        Platform.runLater(() -> productCode.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == gescandButtonType){
                return productCode.getText().toString();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(productCodeResult -> {
            System.out.println(productCodeResult);
        });
    }
}
