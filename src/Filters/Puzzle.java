package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;
import java.util.ArrayList;

public class Puzzle implements PixelFilter {

    int sideX, sideY;
    private DImage img;

    public Puzzle() {
        sideX = Integer.parseInt(JOptionPane.showInputDialog(null, "Input x side: "));
        sideY = Integer.parseInt(JOptionPane.showInputDialog(null, "Input y side: "));
    }
    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] blue = img.getBlueChannel();
        short[][] green = img.getGreenChannel();
        short[][] red2 = new short[red.length][red[0].length];
        short[][] blue2 = new short[red.length][red[0].length];
        short[][] green2 = new short[red.length][red[0].length];
        ArrayList<Integer> arrX = new ArrayList<>();
        ArrayList<Integer> arrY = new ArrayList<>();
        ArrayList<Integer> arr2X = new ArrayList<>();
        ArrayList<Integer> arr2Y = new ArrayList<>();
        setUpArrays(arrX, arrY, arr2X, arr2Y, red);
        int remove = (int)(Math.random()*arrX.size());
        int rx = arrX.get(remove);
        int ry = arrY.get(remove);
        for (int i = 0; i < sideX; i++) {
            for (int j = 0; j < sideY; j++) {
                int stuff = (int)(Math.random()*arr2X.size());
                int x = arr2X.get(stuff);
                int y = arr2Y.get(stuff);
                int num = (int)(Math.random()*arrX.size());
                for (int r = 0; r < red.length/sideX; r++) {
                    for (int c = 0; c < red[0].length/sideY; c++) {
                        red2[x+r][y+c] = red[arrX.get(num)+r][arrY.get(num)+c];
                        green2[x+r][y+c] = green[arrX.get(num)+r][arrY.get(num)+c];
                        blue2[x+r][y+c] = blue[arrX.get(num)+r][arrY.get(num)+c];
                    }
                }
                arrX.remove(num);
                arrY.remove(num);
                arr2X.remove(stuff);
                arr2Y.remove(stuff);
            }
        }
        fillIn(rx, ry, red, red2, green2, blue2);
        img.setColorChannels(red2, green2, blue2);
        return img;
    }
    public void fillIn(int rx, int ry, short[][] arr, short[][] red, short[][] green, short[][] blue){
        for (int i = rx; i < rx+arr.length/sideX; i++) {
            for (int j = ry; j < ry+arr[0].length/sideY; j++) {
                red[i][j] = 255;
                green[i][j] = 255;
                blue[i][j] = 255;
            }
        }
    }
    public void setUpArrays(ArrayList<Integer> arrX, ArrayList<Integer> arrY, ArrayList<Integer> arr2X, ArrayList<Integer> arr2Y, short[][] arr){
        for (int i = 0; i < sideX; i++) {
            for (int j = 0; j < sideY; j++) {
                int x = (arr.length/sideX)*i;
                int y = (arr[0].length/sideY)*j;
                arrX.add(x);
                arrY.add(y);
                arr2X.add(x);
                arr2Y.add(y);
            }
        }
    }
}