package com.kjellvos.school.kassaSystem.itemexplorer;

import com.kjellvos.school.gridHandler.GridHandler;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by kjevo on 2/22/17.
 */
public class AddItem {
    Main main;
    GridHandler gridHandler;

    Button pickImageButton, uploadButton;
    Text pickImageText, enterNameText, enterDescriptionText;
    TextField enterNameTextField, enterDescriptionTextField;

    FileChooser fileChooser = new FileChooser();
    File file;

    public AddItem(Main main){
        this.main = main;
        gridHandler = new GridHandler();
    }

    public Scene getScene(){
        pickImageText = new Text("Selecteer een Afbeelding:");
        pickImageButton = new Button("Selecteer een afbeelding.");
        pickImageButton.setOnMouseClicked(event -> handlePickImageButtonClick());
        gridHandler.add(0, 0, pickImageText);
        gridHandler.add(1,0, pickImageButton);

        enterNameText = new Text("Voer de naam in van het product:");
        enterNameTextField = new TextField();
        gridHandler.add(0, 1, enterNameText);
        gridHandler.add(1, 1, enterNameTextField);

        enterDescriptionText = new Text("Voer de beschrijving in van het product:");
        enterDescriptionTextField = new TextField();
        gridHandler.add(0, 2, enterDescriptionText);
        gridHandler.add(1, 2, enterDescriptionTextField);

        uploadButton = new Button("Upload naar database!");
        uploadButton.setOnMouseClicked(event -> handleUploadButtonClick());
        gridHandler.add(0, 3, uploadButton, 1, 2);

        return gridHandler.getGridAsScene();
    }

    private void handleUploadButtonClick() {
        main.getDatabase().uploadToItemsAndImages(file, enterNameTextField.getText().toString(), enterDescriptionTextField.getText().toString());
    }

    private void handlePickImageButtonClick() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Kies afbeelding.");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        file = fileChooser.showOpenDialog(main.getPrimaryStage());
    }
}
