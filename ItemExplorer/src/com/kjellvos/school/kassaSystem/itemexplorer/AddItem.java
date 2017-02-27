package com.kjellvos.school.kassaSystem.itemexplorer;

import com.kjellvos.school.gridHandler.GridHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by kjevo on 2/22/17.
 */
public class AddItem {
    Main main;
    GridHandler gridHandler;

    Button pickImageButton, uploadButton, backToLastMenubutton;
    Text pickImageText, enterNameText, enterDescriptionText, validFromDatePickerText, validTillDatePickerText, defaultPriceCheckboxText, priceTextFieldText;
    DatePicker validFromDatePicker, validTillDatePicker;
    CheckBox defaultPriceCheckbox;
    LocalDate validFromDate, validTillDate;
    TextField enterNameTextField, enterDescriptionTextField, priceTextField;

    FileChooser fileChooser;
    File file;

    public AddItem(Main main){
        this.main = main;
        gridHandler = new GridHandler();
    }

    public Scene getScene(){
        backToLastMenubutton = new Button("Terug naar vorig menu.");
        backToLastMenubutton.setOnMouseClicked(event -> main.returnToPreviousScene());
        gridHandler.add(0, 0, backToLastMenubutton, 1, 2);

        priceTextFieldText = new Text("Voer de prijs in:");
        priceTextField = new TextField();
        priceTextField.setText("€00.00");
        priceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("[€]+[0-9]{1,10}\\.[0-9]{2}")) {
                priceTextField.setText(newValue);
            }else{
                if (oldValue != null) {
                    priceTextField.setText(oldValue);
                }else{
                    priceTextField.setText("");
                }
            }
        });
        gridHandler.add(0, 1, priceTextFieldText);
        gridHandler.add(1, 1, priceTextField);

        validFromDatePickerText = new Text("Voer in vanaf wanneer deze prijs geldig is:");
        validFromDatePicker = new DatePicker();
        validFromDate = LocalDate.now();
        validFromDatePicker.setValue(validFromDate);
        validFromDatePicker.setOnAction(event -> validFromDatePicker.setValue(LocalDate.now()));
        gridHandler.add(0, 2, validFromDatePickerText);
        gridHandler.add(1, 2, validFromDatePicker);

        validTillDatePickerText = new Text("Voer in tot wanneer deze prijs geldig is:");
        validTillDatePicker = new DatePicker();
        validTillDate = LocalDate.of(9999, 12, 31);
        validTillDatePicker.setValue(validTillDate);
        validTillDatePicker.setOnAction(event -> validTillDatePicker.setValue(validTillDate));
        gridHandler.add(0, 3, validTillDatePickerText);
        gridHandler.add(1, 3, validTillDatePicker);

        defaultPriceCheckboxText = new Text("Is dit de standaard prijs?");
        defaultPriceCheckbox = new CheckBox("Ja!");
        defaultPriceCheckbox.setSelected(true);
        defaultPriceCheckbox.setOnAction(event -> defaultPriceCheckbox.setSelected(true));
        gridHandler.add(0, 4, defaultPriceCheckboxText);
        gridHandler.add(1, 4, defaultPriceCheckbox);

        pickImageText = new Text("Selecteer een Afbeelding:");
        pickImageButton = new Button("Selecteer een afbeelding.");
        pickImageButton.setOnMouseClicked(event -> handlePickImageButtonClick());
        gridHandler.add(0, 5, pickImageText);
        gridHandler.add(1,5, pickImageButton);

        enterNameText = new Text("Voer de naam in van het product:");
        enterNameTextField = new TextField();
        gridHandler.add(0, 6, enterNameText);
        gridHandler.add(1, 6, enterNameTextField);

        enterDescriptionText = new Text("Voer de beschrijving in van het product:");
        enterDescriptionTextField = new TextField();
        gridHandler.add(0, 7, enterDescriptionText);
        gridHandler.add(1, 7, enterDescriptionTextField);

        uploadButton = new Button("Upload naar database!");
        uploadButton.setOnMouseClicked(event -> handleUploadButtonClick());
        gridHandler.add(0, 8, uploadButton, 1, 2);

        return gridHandler.getGridAsScene();
    }

    private void handleUploadButtonClick() {
        main.getDatabase().uploadToItemsAndImages(
                file,
                enterNameTextField.getText().toString(),
                enterDescriptionTextField.getText().toString(),
                Double.parseDouble(priceTextField.getText().substring(1, priceTextField.getText().length())),
                true,
                Date.valueOf(validFromDatePicker.getValue()),
                Date.valueOf(validTillDatePicker.getValue())
                );
    }

    private void handlePickImageButtonClick() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Kies afbeelding.");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        file = fileChooser.showOpenDialog(main.getPrimaryStage());
    }
}
