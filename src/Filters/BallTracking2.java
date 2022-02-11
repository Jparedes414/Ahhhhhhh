package Filters;

import Interfaces.PixelFilter;
import core.DImage;

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
    int middleRow, middleCol;
    int middleRow1, middleCol1;
    int rowTotal = 0, colTotal = 0, total = 0;
    int rowTotal1 = 0, colTotal1 = 0, total1 = 0;

    public BallTracking2() {
    }


    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        short[][] red = img.getRedChannel();
        short[][] blue = img.getBlueChannel();
        short[][] green = img.getGreenChannel();
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[0].length; j++) {
                if (inRangeofTargetVal(red[i][j], green[i][j], blue[i][j], "red")) {
                    grid[i][j] = 0;
                    red[i][j] = 255;
                }else if (inRangeofTargetVal(red[i][j], green[i][j], blue[i][j], "green")) {
                    grid[i][j] = 1;
                    green[i][j] = 255;

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
                }
            }
        }
        if (total > 1) {
            middleRow = rowTotal / total;
            middleCol = colTotal / total;
            System.out.println(middleRow + ", " + middleCol + "     " + middleRow1 + ", " + middleCol1);
        }
        if (total1 > 1) {
            middleRow1 = rowTotal1 / total1;
            middleCol1 = colTotal1 / total1;
        }
        middleRow = 0;
        middleCol = 0;
        rowTotal = 0;
        colTotal = 0;
        total = 0;
        middleRow1 = 0;
        middleCol1 = 0;
        rowTotal1 = 0;
        colTotal1 = 0;
        total1 = 0;
        //



        img.setColorChannels(red, green, blue);
        return img;
    }

    private boolean inRangeofTargetVal(int red, int green, int blue, String color) {
        if (color.equals("red")) {
            if (red > targetRed - MARGIN_OF_ERROR && red < targetRed + MARGIN_OF_ERROR) {
                if (green > targetGreen - MARGIN_OF_ERROR && green < targetGreen + MARGIN_OF_ERROR) {
                    if (blue > targetBlue - MARGIN_OF_ERROR && blue < targetBlue + MARGIN_OF_ERROR) {
                        return true;
                    }
                }
            }
        }else if (color.equals("green")){
            if (red > targetRed1 - MARGIN_OF_ERROR && red < targetRed1 + MARGIN_OF_ERROR) {
                if (green > targetGreen1 - MARGIN_OF_ERROR && green < targetGreen1 + MARGIN_OF_ERROR) {
                    if (blue > targetBlue1 - MARGIN_OF_ERROR && blue < targetBlue1 + MARGIN_OF_ERROR) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

//    public boolean check(short[][]grid, int row, int col){
//        for (int i = 1; i < CHECK; i++) {
//            if (grid[row][col + i] == 0){
//
//                return false;
//            }
//        }
//        int j = col;
//        while(grid[row][j] != 0 && j < grid[0].length){
//            j++;
//
//        }
//        top = row;
//        bottom = j;
//        middleCol = top + bottom / 2;
//        int r = row;
//
//        while (grid[r][middleCol] != 0 && r > 0){
//            r--;
//        }
//        left = r;
//        right = row + r;
//        middleRow = left + right / 2;
//        System.out.println(middleRow + ", " + middleCol);
//        return true;
//    }
}
