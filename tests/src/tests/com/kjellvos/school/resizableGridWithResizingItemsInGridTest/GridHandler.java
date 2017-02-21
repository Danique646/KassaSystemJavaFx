package com.kjellvos.school.resizableGridWithResizingItemsInGridTest;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by kjevo on 2/21/17.
 */
public class GridHandler {
    private MainForResizableGridWithResizingItemsInGridTest main;
    private Pane pane;
    private Scene scene;
    private ArrayList<GridItem> itemsInGrid;
    private VBox[] vBox;
    private HBox[] hBox;

    private double width = 0D, height = 0D;

    public GridHandler(MainForResizableGridWithResizingItemsInGridTest main, Pane pane){
        this.main = main;
        this.pane = pane;
        itemsInGrid = new ArrayList<GridItem>();
        pane.setPrefWidth(800D);
        pane.setPrefHeight(600D);
    }

    public void add(int xPos, int yPos, Node UINode) {
        itemsInGrid.sort(new GridSorter());
        int maxY = 0, maxX = 0;
        if (itemsInGrid.size() != 0) {
            maxY = itemsInGrid.get(itemsInGrid.size() - 1).getyPos();
            maxX = itemsInGrid.get(itemsInGrid.size() - 1).getxPos();
        }
        if (maxX > xPos && maxY > yPos) {
            itemsInGrid.remove(xPos + (yPos*maxX));
            itemsInGrid.add(new GridItem(xPos, yPos).setUINode(UINode));
        }else{
            for (int i = 0; i < yPos; i++) {
                for (int i2 = 0; i2 < xPos; i2++){
                    if (itemsInGrid.size() != 0) {
                         itemsInGrid.add(new GridItem(i2, i).setUINode(null));
                    }else{
                        itemsInGrid.add(new GridItem(i2, i).setUINode(null));
                    }
                }
            }
            itemsInGrid.add(new GridItem(xPos, yPos).setUINode(UINode));
        }
    }

    public Scene getGridAsScene(){
        scene = new Scene(pane);
        setupWidthAndHeightChangeListeners();
        width = scene.getWidth();
        height = scene.getHeight();
        itemsInGrid.sort(new GridSorter());

        for (int i = 0; i < itemsInGrid.size(); i++){
            System.out.println("X: " + itemsInGrid.get(i).getxPos() + " & Y: " + itemsInGrid.get(i).getyPos());
        }

        int maxY = 0, maxX = 0;
        if (itemsInGrid.size() != 0) {
            maxY = itemsInGrid.get(itemsInGrid.size() - 1).getyPos();
            maxX = itemsInGrid.get(itemsInGrid.size() - 1).getxPos();
        }



        for (int i = 0; i < itemsInGrid.size(); i++){
            if (itemsInGrid.get(i).getUINode() != null) {
                pane.getChildren().add(itemsInGrid.get(i).getUINode());
            }
        }

        return scene;
    }

    public void calculateAllPositionAndSize(){
        int maxY = 0, maxX = 0;
        itemsInGrid.sort(new GridSorter());
        if (itemsInGrid.size() != 0) {
            maxY = itemsInGrid.get(itemsInGrid.size() - 1).getyPos();
            maxX = itemsInGrid.get(itemsInGrid.size() - 1).getxPos();
        }

        for (int i = 0; i < itemsInGrid.size(); i++){
            if (itemsInGrid.get(i).getUINode() != null) {
                calculatePositionAndSize(itemsInGrid.get(i).getUINode(), itemsInGrid.get(i).getxPos(), maxX, itemsInGrid.get(i).getyPos(), maxY, 10);
            }
        }
    }

    public void calculatePositionAndSize(Node UINode, int xPos, int maxX, int yPos, int maxY, double padding) {
        maxX++;
        maxY++;

        Class tempClass = UINode.getClass();
        Font font = new Font("Verdana", height/40);

        double height2 = height - padding;
        double width2 = width - padding;
        double leftRightPadding, topBottomPadding;
        if (tempClass == javafx.scene.control.Button.class){
            Button button = (Button) UINode;
            Text buttonText = new Text(button.getText().toString());

            button.setFont(font);
            buttonText.setFont(font);
            topBottomPadding = ((((height2-padding)/maxY)/2)-(padding/2))-(buttonText.getLayoutBounds().getHeight()/2);
            leftRightPadding = ((((width2-padding)/maxX)/2)-(padding/2))-(buttonText.getLayoutBounds().getWidth()/2);
            button.setPadding(new Insets(topBottomPadding, leftRightPadding, topBottomPadding, leftRightPadding));
            double relocateX = padding + (width2/maxX*xPos);
            double relocateY = padding + (height2/maxY*yPos);
            button.relocate(relocateX, relocateY);
        }
        scene.widthProperty().add(10D);
        scene.heightProperty().add(10D);
    }

    private void setupWidthAndHeightChangeListeners() {
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            width = newValue.doubleValue();
            calculateAllPositionAndSize();
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            height = newValue.doubleValue();
            System.out.println(height);
            calculateAllPositionAndSize();
        });
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }
}
