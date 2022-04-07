import java.awt.*;

public class HalfC {
    private int x;
    private int y;
    private int width;
    private int height;
    private int statAngle;
    private int andAngle;
    private Color color;

    public HalfC(int x, int y, int width, int height, int statAngle, int andAngle, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.statAngle = statAngle;
        this.andAngle = andAngle;
        this.color = color;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillArc(this.x, this.y, this.width, this.height, this.statAngle, this.andAngle);
    }
    public void moveRight () {
        this.x++;
    }

    public void moveLeft () {
        this.x--;
    }
    public void moveUp() {
        this.y--;
    }
}