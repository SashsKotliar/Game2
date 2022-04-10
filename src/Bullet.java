import java.awt.*;


public class Bullet {
    public static final int X = 423;
    public static final int Y = 600;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 15;
    public static final int LOAD = 4;

    private MyRectangle body;
    private HalfC bulletHead;

    public Bullet() {
        this.body = new MyRectangle((Const.MAIN_WINDOW_W - Cannon.GUN_W) / 2, Const.MAIN_WINDOW_H-100-10-Cannon.GUN_H, WIDTH, HEIGHT, Color.blue);
        this.bulletHead = new HalfC(body.getX(), body.getY() - 7, WIDTH, HEIGHT, 360, 180, Color.CYAN);
    }


    public Bullet(int x, int y) {
        this.body = new MyRectangle(x, y, WIDTH, HEIGHT, Color.blue);
        this.bulletHead = new HalfC(x, y, WIDTH, HEIGHT, 360, 180, Color.CYAN);
    }

    public void paint(Graphics graphics) {
        this.body.paint(graphics);
        this.bulletHead.paint(graphics);

    }

    public void moveUp() {
        this.body.moveUp(Const.BULLET_SPEED);
        this.bulletHead.moveUp(Const.BULLET_SPEED);
    }


    public MyRectangle getBody() {
        return body;
    }

    public boolean collision(Ball ball) {
        Rectangle ballCollision = new Rectangle(ball.getX(), ball.getY(), ball.getW(), ball.getH());
        Rectangle headBullet = new Rectangle(this.bulletHead.getX(), this.bulletHead.getY(), this.bulletHead.getW(), this.bulletHead.getH());

        return ballCollision.intersects(headBullet);
    }


}