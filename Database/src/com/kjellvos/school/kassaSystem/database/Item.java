package com.kjellvos.school.kassaSystem.database;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;

import java.sql.Date;
import java.text.DecimalFormat;

/**
 * Created by kjevo on 2/22/17.
 */
public class Item {
    private int id;
    private String name, description;
    private ImageView imageView;
    private Button showImageButton;
    private double price;
    private CheckBox defaultPrice;
    private Date validFrom, validTill;

    private DecimalFormat decimalFormat = new DecimalFormat(".##");

    public Item(int id, String name, String description, ImageView imageView, Button showImageButton, double price, CheckBox defaultPrice, Date validFrom, Date validTill){
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
        this.price = price;
        this.defaultPrice = defaultPrice;
        this.validFrom = validFrom;
        this.validTill = validTill;
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

    public String getPrice() {
        return decimalFormat.format(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDefaultPrice(CheckBox defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public CheckBox getDefaultPrice() {
        return defaultPrice;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTill() {
        return validTill;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }
}
