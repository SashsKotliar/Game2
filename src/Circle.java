import java.awt.*;

public class Circle extends ShapeDefined{

    public Circle(int x, int y, int width, int height, Color color) {
        super(x,y,width, height, color);
    }

    public void paint(Graphics graphics) {
        graphics.setColor(this.getColor());
        graphics.fillOval(this.getX(), this.getY(), this.getW(), this.getH());
    }

    private int getNewDimension(int num) {
        return (num > 40) ? num / 5 : 0;
    }

//    public void raiseABallW() {
//        this.width = getNewDimension(this.width);
//    }
//
//    public void raiseABallH() {
//        this.height = getNewDimension(this.height);
//    }

    public void moveRight() {
        this.setX(this.getX()+Const.SPEED_PLAYER);
    }

    public void moveLeft() {
        this.setX(this.getX()-Const.SPEED_PLAYER);
    }
}