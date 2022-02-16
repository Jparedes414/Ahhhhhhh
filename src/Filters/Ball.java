package Filters;

public class Ball {
    private int targetRed, targetGreen, targetBlue;
    private int middleRow, middleCol;
    private int rowTotal = 0, colTotal = 0, total = 0;
    private static int MARGIN_OF_ERROR;
    String color;
    public static void setError(int error) {
        MARGIN_OF_ERROR = error;
    }

    public Ball(int targetRed, int targetGreen, int targetBlue, int middleRow, int middleCol, String color) {
        this.targetRed = targetRed;
        this.targetGreen = targetGreen;
        this.targetBlue = targetBlue;
        this.middleCol = middleCol;
        this.middleRow = middleRow;
        this.color = color;
    }

}
