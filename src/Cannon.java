import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

    private ArrayList<ShapeDefined> allMyBody;
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
        this.allMyBody = new ArrayList<>();
        this.body = new MyRectangle(BODY_X, BODY_Y, BODY_W, BODY_H, Color.black);
        this.allMyBody.add(body);
        this.allMyBody.add(new HalfC(RIGHT_WING_X, RIGHT_WING_Y, WING_W, WING_H, NONE, RIGHT_ANGLE, Color.DARK_GRAY));
        this.allMyBody.add(new HalfC(LEFT_WING_X, LEFT_WING_Y, WING_W, WING_H, RIGHT_ANGLE, RIGHT_ANGLE, Color.DARK_GRAY));
        this.allMyBody.add(new Circle(LEFT_WHEEL_X, LEFT_WHEEL_Y, WHEEL_W_H, WHEEL_W_H, Color.black));
        this.allMyBody.add(new Circle(RIGHT_WHEEL_X, RIGHT_WHEEL_Y, WHEEL_W_H, WHEEL_W_H, Color.black));
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
        for (ShapeDefined shape : this.allMyBody) {
            shape.paint(graphics);
        }
        for (int i = 0; i < life.length; i++) {
            life[i].paint(graphics);
        }
    }

    public void moveUp() {
        this.bullet.moveUp();
    }

    public void moveRight() {
        for (ShapeDefined shape:this.allMyBody) {
            shape.moveRight();
        }
        for (int i = 0; i <life.length ; i++) {
            life[i].moveRight();
        }
        this.bullet.moveRight();
    }

    public void moveLeft() {
        for (ShapeDefined shape:this.allMyBody) {
            shape.moveLeft();
        }
        for (int i = 0; i <life.length ; i++) {
            life[i].moveLeft();
        }
        this.bullet.moveLeft();
    }


    public void newBullet() {
        this.bullet = new Bullet(this.getBodyX() + 25, this.getBodyY());
    }

    public boolean checkCollision(Ball ball) {
        boolean collision = false;
        Rectangle myCollision = new Rectangle(ball.getX(),
                ball.getY(),
                ball.getW(),
                ball.getH());
        if (myCollision.intersects(body.getX(), body.getY(), body.getW(), body.getH())) {
            collision = true;
        }
        return collision;
    }

    public void lost(int num) {
        if (num < this.life.length && num >= 0) {
            this.life[num].setH(0);
            this.life[num].setW(0);
        }
    }


    public void moveTo(int direction) {
        int movement = direction * Const.SPEED_PLAYER;
        int futurePlace = body.getX() + movement;
        if (futurePlace < 0 || Const.MAIN_WINDOW_W - body.getW() < futurePlace)
            return;
        for (ShapeDefined shape:this.allMyBody) {
            shape.setX(shape.getX()+movement);
        }
        for (int i = 0; i < life.length; i++) {
            this.life[i].setX(this.life[i].getX()+movement);
        }
    }

}