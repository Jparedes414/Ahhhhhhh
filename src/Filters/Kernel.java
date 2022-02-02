package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class Kernel implements PixelFilter {
    private double[][] boxBlur = {
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1}
    };
    private double[][] prewit = {
            {-1,-1,-1},
            {-1,8,-1},
            {-1,-1,-1}
    };
    private double[][] gaussian=
            {
        {0, 0, 0, 0, 0, 0, 0},
                    {0, 5, 18, 32, 18, 5, 0},
                    {0, 18, 64, 100, 64, 18, 0} ,
                    {5, 32, 100, 100, 100, 32, 5},
                    {0, 18, 64, 100, 64, 18, 0},
                    {0, 5, 18, 32, 18, 5, 0},
                    {0, 0, 0, 0, 0, 0, 0}
    };
    private double[][] horizontal=
            {
                    {-1,-1,-1},
                    {2,2,2},
                    {-1,-1,-1}
            };
    private double[][] blurKernel =
            {       {1.0/9, 1.0/9, 1.0/9},
                    {1.0/9, 1.0/9, 1.0/9},
                    {1.0/9, 1.0/9, 1.0/9}   };

    private double[][] outlineKernel =
            {
                    {-1, -1, -1},
                    {-1, 8, -1},
                    {-1, -1, -1}   };

    private double[][] embossKernel =
            {
                    {-2, -1, 0},
                    {-1, 1, 1},
                    {0, 1, 2}   };
    private double[][] kernel;
    public Kernel() {
        kernel = gaussian;
    }
    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] blue = img.getBlueChannel();
        short[][] green = img.getGreenChannel();
        short[][] red2 = new short[red.length][red[0].length];
        short[][] blue2 = new short[red.length][red[0].length];
        short[][] green2 = new short[red.length][red[0].length];
        for (int row = kernel.length/2; row < red.length-(kernel.length/2); row++) {
            for (int col = kernel[0].length/2; col < red[0].length-(kernel[0].length/2); col++) {
                applyKernel(red, red2, row, col);
                applyKernel(green, green2, row, col);
                applyKernel(blue, blue2, row, col);
            }
        }
        img.setColorChannels(red2, green2, blue2);
        return img;
    }

    private void applyKernel(short[][] arr, short[][] arr2, int row, int col) {
        double output = 0;
        double kernalWeight = 0;
        for (int r = 0; r < kernel.length; r++) {
            for (int c = 0; c < kernel[0].length; c++) {
                double kernelVal = kernel[r][c];
                kernalWeight+= kernelVal;
                int pixelVal = arr[row+r-(kernel.length/2)][col+c-(kernel[0].length/2)];

                output += (kernelVal*pixelVal);
            }
        }
        if (kernalWeight != 0) {
            output = output / kernalWeight;
        }
        if (output<0){
            output = 0;
        }
        if (output>255){
            output = 255;
        }
        arr2[row][col] = (short) output;
    }


}

