package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class Negative implements PixelFilter {

    public Negative() {

    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] = (short) (255 - grid[r][c]);
            }
        }

        img.setPixels(grid);
        return img;
    }
}


