package com.kjellvos.school.resizableGridWithResizingItemsInGridTest;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
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

    private double width = 0D, height = 0D;

    public GridHandler(MainForResizableGridWithResizingItemsInGridTest main, Pane pane){
        this.main = main;
        this.pane = pane;
        itemsInGrid = new ArrayList<GridItem>();
        pane.setPrefWidth(800D);
        pane.setPrefHeight(600D);
    }

    public void add(int xPos, int yPos, Node UINode) {
        add(xPos, yPos, UINode, 1, 1);
    }

    public Vec2d getMaxXAndMaxYFromItemsInGrid(){
        int maxY = 0, maxX = 0;
        itemsInGrid.sort(new GridSorter());
        for (int i = 0; i < itemsInGrid.size(); i++){
            GridItem itemInGrid = itemsInGrid.get(i);
            if ((itemInGrid.getxPos()+itemInGrid.getColSpan()) > maxX) {
                maxX = (itemInGrid.getxPos()+itemInGrid.getColSpan());
            }
            if ((itemInGrid.getyPos()+itemInGrid.getRowSpan()) > maxY) {
                maxY = (itemInGrid.getyPos()+itemInGrid.getRowSpan());
            }
        }
        return new Vec2d(maxX, maxY);
    }

    public void add(int xPos, int yPos, Node UINode, int rowSpan, int colSpan){
        itemsInGrid.sort(new GridSorter());
        Vec2d maxXY = getMaxXAndMaxYFromItemsInGrid();
        System.out.println(maxXY.x + " & " + maxXY.y);
        if (((int) maxXY.x) > xPos+colSpan && ((int) maxXY.y) > yPos+rowSpan) {
            itemsInGrid.remove(xPos + (yPos*maxXY.x));
            itemsInGrid.add(new GridItem(xPos, yPos, colSpan, rowSpan).setUINode(UINode));
        }else{
            for (int i = 0; i < yPos; i++) {
                for (int i2 = 0; i2 < xPos; i2++){
                        itemsInGrid.add(new GridItem(i2, i, 1, 1).setUINode(null));
                }
            }
            itemsInGrid.add(new GridItem(xPos, yPos, colSpan, rowSpan).setUINode(UINode));
        }
    }

    public Scene getGridAsScene(){
        scene = new Scene(pane);
        setupWidthAndHeightChangeListeners();
        width = scene.getWidth();
        height = scene.getHeight();
        itemsInGrid.sort(new GridSorter());

        for (int i = 0; i < itemsInGrid.size(); i++){
            if (itemsInGrid.get(i).getUINode() != null) {
                pane.getChildren().add(itemsInGrid.get(i).getUINode());
            }
        }

        return scene;
    }

    public void calculateAllPositionAndSize(){
        itemsInGrid.sort(new GridSorter());
        Vec2d maxXY = getMaxXAndMaxYFromItemsInGrid();

        for (int i = 0; i < itemsInGrid.size(); i++){
            if (itemsInGrid.get(i).getUINode() != null) {
                calculatePositionAndSize(itemsInGrid.get(i).getUINode(), itemsInGrid.get(i).getxPos(), ((int) maxXY.x), itemsInGrid.get(i).getyPos(), ((int) maxXY.y), 10, itemsInGrid.get(i).getRowSpan(), itemsInGrid.get(i).getColSpan());
            }
        }
    }

    public void calculatePositionAndSize(Node UINode, int xPos, int maxX, int yPos, int maxY, double padding, int rowSpan, int colSpan) {
        Class tempClass = UINode.getClass();
        Font font = new Font("Verdana", height/40);

        double height2 = height - padding;
        double width2 = width - padding;
        double relocateX = padding + (width2/maxX*xPos);
        double relocateY = padding + (height2/maxY*yPos);
        double leftRightPadding, topBottomPadding;
        if (tempClass == javafx.scene.control.Button.class){
            Button button = (Button) UINode;
            Text buttonText = new Text(button.getText().toString());

            button.setFont(font);
            buttonText.setFont(font);
            topBottomPadding = (((height2/maxY)/2)*rowSpan)-(buttonText.getLayoutBounds().getHeight()/2)-(padding/2);
            leftRightPadding = (((width2/maxX)/2)*colSpan)-(buttonText.getLayoutBounds().getWidth()/2)-(padding/2);
            button.setPadding(new Insets(topBottomPadding, leftRightPadding, topBottomPadding, leftRightPadding));
            button.relocate(relocateX, relocateY);
        }else if (tempClass == javafx.scene.control.TableView.class){
            TableView tableView = (TableView) UINode;
            tableView.setPrefHeight(((height2/maxY)*rowSpan)-padding);
            tableView.setPrefWidth(((width2/maxX)*colSpan)-padding);
            tableView.relocate(relocateX, relocateY);
        }else if (tempClass == javafx.scene.text.Text.class){
            Text text = (Text) UINode;
            text.setFont(font);
            text.prefHeight((((height2-padding)/maxY)*rowSpan)-padding);
            text.prefWidth((((width2-padding)/maxX)*colSpan)-padding);
            text.setWrappingWidth((((width2-padding)/maxX)*colSpan)-padding);
            text.relocate(relocateX, relocateY);
        }
    }

    private void setupWidthAndHeightChangeListeners() {
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            width = newValue.doubleValue();
            calculateAllPositionAndSize();
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            height = newValue.doubleValue();
            calculateAllPositionAndSize();
        });
    }
}
