package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BallTracking2 implements PixelFilter {
    int CHECK = 10;
    int MARGIN_OF_ERROR = 50;
    int RADIUS_CHECK = 5;
    int targetRed = 180;
    int targetGreen = 75;
    int targetBlue = 10;
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
                if (inRangeofTargetVal(i, j, red, green, blue)) {
                    grid[i][j] = 0;
                } else{
                    grid[i][j] = 255;
                }
            }
        }
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[0].length; j++) {

            }

        }

/*
        img.setColorChannels(red, green, blue);
*/
        img.setPixels(grid);
        return img;
    }

    private boolean inRangeofTargetVal(int row, int col, short[][]red, short[][]green, short[][]blue) {
        if (red[row][col] > targetRed - MARGIN_OF_ERROR && red[row][col] < targetRed + MARGIN_OF_ERROR ) {
            if (green[row][col] > targetGreen - MARGIN_OF_ERROR && green[row][col] < targetGreen + MARGIN_OF_ERROR) {
                if (blue[row][col] > targetBlue - MARGIN_OF_ERROR && blue[row][col] < targetBlue + MARGIN_OF_ERROR) {
                    return true;
                }
            }
        }
        return false;
    }


    private int[] CalcCenter(int x, int y, short[][] red, short[][] green, short[][] blue) {
        int[] ball_info = new int[2];
        int radY = 0;
        int radX = 0;
        while (radY< red.length && Math.abs(red[x][y] - red[radY][y]) > MARGIN_OF_ERROR && Math.abs(green[x][y] - green[radY][y]) > MARGIN_OF_ERROR && blue[x][y] - blue[radY][y] > MARGIN_OF_ERROR){
            radY++;
        }
        radY = radY/2;
        while (radX< red[0].length && Math.abs(red[x][y] - red[radY][radX]) > MARGIN_OF_ERROR && Math.abs(green[x][y] - green[radY][radX]) > MARGIN_OF_ERROR && blue[x][y] - blue[radY][radX] > MARGIN_OF_ERROR){
            radX++;
        }
        return ball_info;
    }

    public boolean checkDown(int x, int y, short[][] red, short[][] green, short[][] blue){
        for (int i = 1; i < CHECK; i++) {
            if (Math.abs(red[x][y] - red[i][y]) > MARGIN_OF_ERROR){
                return false;
            }
            if (Math.abs(green[x][y] - green[i][y]) > MARGIN_OF_ERROR){
                return false;
            }
            if (Math.abs(blue[x][y] - blue[i][y]) > MARGIN_OF_ERROR){
                return false;
            }
        }
        return true;
    }
}
