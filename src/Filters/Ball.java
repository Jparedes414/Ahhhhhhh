package Filters;

public class Ball {
    private int targetRed, targetGreen, targetBlue;
    private int middleRow, middleCol;
    private int rowTotal = 0, colTotal = 0, total = 0;
    private int MARGIN_OF_ERROR;

    public ball(int targetRed, int targetGreen, int targetBlue, int middleRow, int middleCol, int MARGIN_OF_ERROR ) {
        this.targetRed = targetRed;
        this.targetGreen = targetGreen;
        this.targetBlue = targetBlue;
        this.MARGIN_OF_ERROR = MARGIN_OF_ERROR;
    }
}
