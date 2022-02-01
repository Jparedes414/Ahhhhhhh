package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class DoNothingFilter implements PixelFilter {

    // Testing

    @Override
    public DImage processImage(DImage img) {
        // we don't change the input image at all!
        return img;
    }
}

