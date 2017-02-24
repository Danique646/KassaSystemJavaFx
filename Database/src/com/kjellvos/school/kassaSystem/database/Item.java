package com.kjellvos.school.kassaSystem.itemexplorer;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;

/**
 * Created by kjevo on 2/22/17.
 */
public class Item {
    private int id;
    private String name;
    private String description;
    private ImageView imageView;
    private Button showImageButton;

    public Item(int id, String name, String description, ImageView imageView, Button showImageButton){
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageView = imageView;
        this.showImageButton = showImageButton;
        showImageButton.setOnMouseClicked(event -> {
            Dialog<String> dialog = new Dialog();
            dialog.setTitle("Afbeelding product");
            imageView.setFitWidth(200D);
            imageView.setFitHeight(200D);
            dialog.getDialogPane().setContent(imageView);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

            dialog.showAndWait();
        });
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button getShowImageButton() {
        return showImageButton;
    }

    public void setShowImageButton(Button showImageButton) {
        this.showImageButton = showImageButton;
    }
}
