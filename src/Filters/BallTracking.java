package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BallTracking implements PixelFilter {

    public BallTracking() {

    }

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] blue = img.getBlueChannel();
        short[][] green = img.getGreenChannel();
        img.setColorChannels(red, green, blue);
        return img;
    }
}


