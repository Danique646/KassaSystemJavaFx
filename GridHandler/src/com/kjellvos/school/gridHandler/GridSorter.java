package com.kjellvos.school.gridHandler;

/**
 * Created by kjevo on 2/21/17.
 */
public class GridSorter implements java.util.Comparator<GridItem> {
    @Override
    public int compare(GridItem o1, GridItem o2) {
        if (o1.getyPos() == o2.getyPos() && o1.getxPos() > o2.getxPos()) {
            return 1;
        }else if(o1.getyPos() == o2.getyPos() && o1.getxPos() < o2.getxPos()){
            return -1;
        }else if (o1.getyPos() != o2.getyPos() && o1.getyPos() > o2.getyPos()) {
            return 1;
        }else if (o1.getyPos() != o2.getyPos() && o1.getyPos() < o2.getyPos()){
            return -1;
        }
        return 0;
    }
}
