package com.kjellvos.school.resizableGridWithResizingItemsInGridTest;

import javafx.scene.Node;

/**
 * Created by kjevo on 2/21/17.
 */
public class GridItem{
    private int xPos, yPos, colSpan, rowSpan;
    private Node UINode;

    public GridItem(int xPos, int yPos, int colSpan, int rowSpan){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
    }

    public GridItem setUINode(Node UINode){
        this.UINode = UINode;
        return this;
    }

    public Node getUINode() {
        return UINode;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getColSpan() {
        return colSpan;
    }

    public int getRowSpan() {
        return rowSpan;
    }
}
