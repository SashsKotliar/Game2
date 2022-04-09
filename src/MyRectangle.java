import java.awt.*;

public class MyRectangle {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public MyRectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }

    public void moveRight() {
        this.x += Const.SPEED_PLAYER;
    }

    public void moveLeft() {
        this.x -= Const.SPEED_PLAYER;
    }

    public void moveUp() {
        this.y--;
    }

    public void moveUp(int distance) {
        this.y -= distance;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
