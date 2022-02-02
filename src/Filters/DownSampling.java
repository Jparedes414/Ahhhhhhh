package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class DownSampling implements PixelFilter {
    int scale = 2;
    public DownSampling() {

    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        short[][] down = new short[grid.length/2][grid[0].length/2];
        for (int i = 0; i < down.length; i++) {
            for (int j = 0; j < down[0].length; j++) {
                int x = i*2;
                int y = j*2;
                int avg = (grid[x][y]+grid[x+1][y]+grid[x][y+1]+grid[x+1][y+1])/4;
                down[i][j] = (short) avg;
            }
        }
        img.setPixels(down);

        return img;
    }
}




