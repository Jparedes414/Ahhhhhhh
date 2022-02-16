package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

public class BallTracking2 implements PixelFilter {
    int CHECK = 10;
    int MARGIN_OF_ERROR = 25;
    int RADIUS_CHECK = 5;
    int targetRed = 180;
    int targetGreen = 75;
    int targetBlue = 10;
    int targetRed1 = 35;
    int targetGreen1 = 97;
    int targetBlue1 = 40;
    int targetRed2 = 0;
    int targetGreen2 = 15;
    int targetBlue2 = 95;
    int middleRow, middleCol;
    int middleRow1, middleCol1;
    int middleRow2, middleCol2;
    int rowTotal = 0, colTotal = 0, total = 0;
    int rowTotal1 = 0, colTotal1 = 0, total1 = 0;
    int rowTotal2 = 0, colTotal2 = 0, total2 = 0;

    public BallTracking2() {
    }


    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        short[][] red = img.getRedChannel();
        short[][] blue = img.getBlueChannel();
        short[][] green = img.getGreenChannel();
        ArrayList<Ball> balls = new ArrayList<>();
        balls.add(new Ball(targetRed, targetGreen, targetBlue, middleRow, middleCol, "red"));
        balls.add(new Ball(targetRed1, targetGreen1, targetBlue1, middleRow1, middleCol1, "red"));
        balls.add(new Ball(targetRed2, targetGreen2, targetBlue2, middleRow2, middleCol2, "red"));
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[0].length; j++) {
                if (inRangeofTargetVal(red[i][j], green[i][j], blue[i][j], "red")) {
                    grid[i][j] = 0;
                    red[i][j] = 255;
                }else if (inRangeofTargetVal(red[i][j], green[i][j], blue[i][j], "green")) {
                    grid[i][j] = 1;
                    green[i][j] = 255;

                }else if(inRangeofTargetVal(red[i][j], green[i][j], blue[i][j], "blue")){
                    grid[i][j] = 2;
                    blue[i][j] = 255;
                } else{
                    grid[i][j] = 255;
                }
//                if (grid[i][j] == 0){
//                    check(grid, i, j);
//                }
            }
        }
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[0].length; j++) {
                if (grid[i][j] == 0) {
                    rowTotal =  rowTotal + i;
                    colTotal = colTotal + j;
                    total = total + 1;
                } else if ( grid[i][j] == 1){
                    rowTotal1 =  rowTotal1 + i;
                    colTotal1 = colTotal1 + j;
                    total1 = total1 + 1;
                } else if ( grid[i][j] == 2){
                    rowTotal2 =  rowTotal2 + i;
                    colTotal2= colTotal2 + j;
                    total2 = total2 + 1;
                }
            }
        }
        if (total > 1) {
            middleRow = rowTotal / total;
            middleCol = colTotal / total;
        }
        if (total1 > 1) {
            middleRow1 = rowTotal1 / total1;
            middleCol1 = colTotal1 / total1;
        }
        if (total2 > 1) {
            middleRow2 = rowTotal2 / total2;
            middleCol2 = colTotal2 / total2;
        }
        System.out.println(middleRow + ", " + middleCol + "     " + middleRow1 + ", " + middleCol1 + "     " + middleRow2 + ", " + middleCol2);




        img.setColorChannels(red, green, blue);
        return img;
    }

    public boolean inRangeofTargetVal(int red, int green, int blue, String color) {
        if (color.equals("red")) {
            if (Doink(red, green, blue, targetRed, targetGreen, targetBlue)){
                return true;
            }
        }else if (color.equals("green")){
            if (Doink(red, green, blue, targetRed1, targetGreen1, targetBlue1)){
                return true;
            }
        }else if (color.equals("blue")){
            if (Doink(red, green, blue, targetRed2, targetGreen2, targetBlue2)){
                return true;
            }
        }
        return false;
    }

    public boolean Doink(int red, int green, int blue, int targetRed, int targetGreen, int targetBlue){
        if (red > targetRed - MARGIN_OF_ERROR && red < targetRed + MARGIN_OF_ERROR) {
            if (green > targetGreen - MARGIN_OF_ERROR && green < targetGreen + MARGIN_OF_ERROR) {
                if (blue > targetBlue - MARGIN_OF_ERROR && blue < targetBlue + MARGIN_OF_ERROR) {
                    return true;
                }
            }
        }
        return false;
    }
}
