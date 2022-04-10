import java.awt.*;

public class MyRectangle extends ShapeDefined{

    public MyRectangle(int x, int y, int width, int height, Color color) {
        super(x,y,width,height, color);
    }

    public void paint(Graphics graphics) {
        graphics.setColor(this.getColor());
        graphics.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
    }

    public void moveRight() {
        this.setX(this.getX()+Const.SPEED_PLAYER);
    }

    public void moveLeft() {
        this.setX(this.getX()-Const.SPEED_PLAYER);
    }
}
