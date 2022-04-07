import java.awt.*;

public class Cannon  {
    public static final int NONE = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int BODY_X = 400;
    public static final int BODY_Y = 300;
    public static final int BODY_W = 50;
    public static final int BODY_H = 160;

    private int direction;
    //private int direction2;
    private MyRectangle body;
    private HalfC rightWing;
    private HalfC leftWing;
    private Circle leftWheel;
    private Circle rightWheel;
    private Circle life1;
    private Circle life2;
    private Circle life3;
    private Bullet bullet;


    public Cannon() {
        this.body = new MyRectangle(BODY_X, BODY_Y, BODY_W, BODY_H, Color.black);
        this.rightWing = new HalfC(BODY_X, BODY_Y + 110, 90, 90, 0, 90, Color.DARK_GRAY);
        this.leftWing = new HalfC(BODY_X - 40, BODY_Y+ 110, 90, 90, 90, 90, Color.DARK_GRAY);
        this.leftWheel = new Circle(BODY_X - 10, BODY_Y + 143, 25, 25, Color.black);
        this.rightWheel = new Circle(BODY_X + 35, BODY_Y + 143, 25, 25, Color.black);
        this.life1 = new Circle(BODY_X + 10, BODY_Y + 100, 30, 30, Color.RED);
        this.life2 = new Circle(BODY_X + 10, BODY_Y + 65, 30, 30, Color.ORANGE);
        this.life3 = new Circle(BODY_X + 10, BODY_Y + 30, 30, 30, Color.green);
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
        this.life1.paint(graphics);
        this.life2.paint(graphics);
        this.life3.paint(graphics);

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
        this.life1.moveRight();
        this.life2.moveRight();
        this.life3.moveRight();
        this.bullet.moveRight();
    }

    public void moveLeft() {
        this.body.moveLeft();
        this.leftWheel.moveLeft();
        this.rightWheel.moveLeft();
        this.leftWing.moveLeft();
        this.rightWing.moveLeft();
        this.life1.moveLeft();
        this.life2.moveLeft();
        this.life3.moveLeft();
        this.bullet.moveLeft();
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }



    public void newBullet() {
        this.bullet = new Bullet(this.getBodyX()+25, this.getBodyY());
    }



}