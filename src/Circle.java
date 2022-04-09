import java.awt.*;

public class Circle {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Circle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillOval(this.x, this.y, this.width, this.height);
    }

    private int getNewDimension(int num) {
        return (num > 40) ? num / 5 : 0;
    }

    public void raiseABallW() {
        this.width = getNewDimension(this.width);
    }

    public void raiseABallH() {
        this.height = getNewDimension(this.height);
    }

    public void moveRight() {
        this.x +=Const.SPEED_PLAYER;
    }

    public void moveLeft() {
        this.x -=Const.SPEED_PLAYER;
    }

    public void moveDown() {
        this.y++;
    }

}