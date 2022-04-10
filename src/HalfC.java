import java.awt.*;

public class HalfC extends ShapeDefined {
    private int statAngle;
    private int andAngle;

    public HalfC(int x, int y, int width, int height, int statAngle, int andAngle, Color color) {
        super(x,y,width, height, color);
        this.statAngle = statAngle;
        this.andAngle = andAngle;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(this.getColor());
        graphics.fillArc(this.getX(), this.getY(), this.getW(), this.getH(), this.statAngle, this.andAngle);
    }

    public void moveRight() {
        this.setX(this.getH()+Const.SPEED_PLAYER);
    }

    public void moveLeft() {
        this.setX(this.getX()-Const.SPEED_PLAYER);
    }

    public void moveUp(int distance) {
        this.setY(this.getY()-distance);
    }

    public int getStatAngle() {
        return statAngle;
    }

    public int getAndAngle() {
        return andAngle;
    }


}