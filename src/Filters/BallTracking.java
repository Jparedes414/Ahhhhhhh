package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BallTracking implements PixelFilter {
    int CHECK = 10;
    int MARGIN_OF_ERROR = 40;
    int RADIUS_CHECK = 5;
    public BallTracking() {

    }

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] blue = img.getBlueChannel();
        short[][] green = img.getGreenChannel();
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[0].length; j++) {
                if (checkDown(i, j, red, green, blue)){
                    int[] ball_info = CalcCenter(i, j, red, green, blue);
                }
            }
        }
        img.setColorChannels(red, green, blue);
        return img;
    }

    private int[] CalcCenter(int x, int y, short[][] red, short[][] green, short[][] blue) {
        int[] ball_info = new int[2];
        int radY = 0;
        while (radY< red.length && Math.abs(red[x][y] - red[radY][y]) > MARGIN_OF_ERROR && Math.abs(green[x][y] - green[radY][y]) > MARGIN_OF_ERROR && blue[x][y] - blue[radY][y] > MARGIN_OF_ERROR){
            radY++;
        }
        ball_info[0] = radY;
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


