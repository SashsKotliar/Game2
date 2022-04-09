import javax.swing.*;
import java.awt.*;

public class Cannon {
    public static final int NONE = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int BODY_X = 400;
    public static final int BODY_Y = 380;
    public static final int BODY_W = 50;
    public static final int BODY_H = 160;
    public static final int WING_W = 90;
    public static final int WING_H = 90;
    public static final int LIFE_X = BODY_X + 10;
    public static final int LIFE_W = 30;
    public static final int LIFE_H = 30;
    public static final int RIGHT_ANGLE = 90;
    public static final int WHEEL_W_H = 25;
    public static final int LEFT_WING_X = BODY_X - 40;
    public static final int LEFT_WING_Y = BODY_Y + 110;
    public static final int RIGHT_WING_X = BODY_X;
    public static final int RIGHT_WING_Y = BODY_Y + 110;
    public static final int LEFT_WHEEL_X = BODY_X - 10;
    public static final int LEFT_WHEEL_Y = BODY_Y + 143;
    public static final int RIGHT_WHEEL_X = BODY_X + 35;
    public static final int RIGHT_WHEEL_Y = BODY_Y + 143;
    public static final int LIFE1_Y = BODY_Y + 100;
    public static final int LIFE2_Y = LIFE1_Y - 35;
    public static final int LIFE3_Y = LIFE2_Y - 35;


    private MyRectangle body;
    private HalfC rightWing;
    private HalfC leftWing;
    private Circle leftWheel;
    private Circle rightWheel;
    private Circle[] life;
    private Circle life1;
    private Circle life2;
    private Circle life3;
    private Bullet bullet;
    private ImageIcon player;


    public Cannon() {
        this.body = new MyRectangle(BODY_X, BODY_Y, BODY_W, BODY_H, Color.black);
        this.rightWing = new HalfC(RIGHT_WING_X, RIGHT_WING_Y, WING_W, WING_H, NONE, RIGHT_ANGLE, Color.DARK_GRAY);
        this.leftWing = new HalfC(LEFT_WING_X, LEFT_WING_Y, WING_W, WING_H, RIGHT_ANGLE, RIGHT_ANGLE, Color.DARK_GRAY);
        this.leftWheel = new Circle(LEFT_WHEEL_X, LEFT_WHEEL_Y, WHEEL_W_H, WHEEL_W_H, Color.black);
        this.rightWheel = new Circle(RIGHT_WHEEL_X, RIGHT_WHEEL_Y, WHEEL_W_H, WHEEL_W_H, Color.black);
        this.life = new Circle[]{new Circle(LIFE_X, LIFE1_Y, LIFE_W, LIFE_H, Color.RED),
                new Circle(LIFE_X, LIFE2_Y, LIFE_W, LIFE_H, Color.ORANGE),
                new Circle(LIFE_X, LIFE3_Y, LIFE_W, LIFE_H, Color.green)};
        this.bullet = new Bullet();

    }

    public int getBodyX() {
        return this.body.getX();
    }

    public int getBodyY() {
        return this.body.getY();
    }

    public void paint(Graphics graphics) {
        this.bullet.paint(graphics);
        this.body.paint(graphics);
        this.rightWing.paint(graphics);
        this.leftWing.paint(graphics);
        this.leftWheel.paint(graphics);
        this.rightWheel.paint(graphics);
        this.life[0].paint(graphics);
        this.life[1].paint(graphics);
        this.life[2].paint(graphics);

    }

    public void moveUp() {
        this.bullet.moveUp();
    }

    public void moveRight() {
        this.body.moveRight();
        this.rightWing.moveRight();
        this.leftWing.moveRight();
        this.leftWheel.moveRight();
        this.rightWheel.moveRight();
        this.life[0].moveRight();
        this.life[1].moveRight();
        this.life[2].moveRight();
        this.bullet.moveRight();
    }

    public void moveLeft() {
        this.body.moveLeft();
        this.leftWheel.moveLeft();
        this.rightWheel.moveLeft();
        this.leftWing.moveLeft();
        this.rightWing.moveLeft();
        this.life[0].moveLeft();
        this.life[1].moveLeft();
        this.life[2].moveLeft();
        this.bullet.moveLeft();
    }


    public void newBullet() {
        this.bullet = new Bullet(this.getBodyX() + 25, this.getBodyY());
    }

    public boolean checkCollision(Ball ball) {
        boolean collision = false;
        Rectangle myCollision = new Rectangle(ball.getX(),
                ball.getY(),
                ball.getWidth(),
                ball.getHeight());
        if (myCollision.intersects(body.getX(), body.getY(), body.getWidth(), body.getHeight())) {
            collision = true;
        }
        return collision;
    }

    public void lost(int num) {
        if (num < this.life.length && num >= 0) {
            this.life[num].setHeight(0);
            this.life[num].setWidth(0);
        }
    }


    public void moveTo(int direction) {
        int movement = direction*Const.SPEED_PLAYER;
        this.body.setX(this.body.getX() + movement);
        this.leftWheel.setX(this.leftWheel.getX() + movement);
        this.rightWheel.setX(this.rightWheel.getX() + movement);
        this.leftWing.setX(this.leftWing.getX() + movement);
        this.rightWing.setX(this.rightWing.getX() + movement);
        this.life[0].setX(this.life[0].getX() + movement);
        this.life[1].setX(this.life[1].getX() + movement);
        this.life[2].setX(this.life[2].getX() + movement);

    }

}