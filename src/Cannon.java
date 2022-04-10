import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cannon {
    public static final int CANNON_W = 150;
    public static final int CANNON_H = 90;
    public static final int BALL_W_H = 25;
    public static final int BALL_START_Y = 20;
    public static final int BALL_START_X = 20;
    public static final int DISTANCE_X = 30;
    public static final int GUN_W = 10;
    public static final int GUN_H = 10;
    private ArrayList<ShapeDefined> myPlayer;
    private MyRectangle[] life;
    Circle body;

    public Cannon(int windowW, int windowH) {
        this.body = new Circle((windowW - CANNON_W) / 2, windowH - 100, CANNON_W, CANNON_H, Color.black.darker());
        this.myPlayer = new ArrayList<>();
        myPlayer.add(new MyRectangle(body.getX() + DISTANCE_X, body.getY() - 10, body.getW() - DISTANCE_X * 2, GUN_H * 2, Color.black.darker()));
        myPlayer.add(new MyRectangle((Const.MAIN_WINDOW_W - GUN_W) / 2, myPlayer.get(0).getY() - GUN_H, GUN_W, GUN_H, Color.black));
        myPlayer.add(body);
        myPlayer.add(new Circle(this.body.getX() + BALL_START_X, body.getY() + BALL_START_Y, BALL_W_H, BALL_W_H, Color.green.darker()));
        myPlayer.add(new Circle(this.body.getX() + BALL_START_X + DISTANCE_X, body.getY() + BALL_START_Y, BALL_W_H, BALL_W_H, Color.green.darker()));
        myPlayer.add(new Circle(this.body.getX() + BALL_START_X + DISTANCE_X * 2, body.getY() + BALL_START_Y, BALL_W_H, BALL_W_H, Color.green.darker()));
        myPlayer.add(new Circle(this.body.getX() + BALL_START_X + DISTANCE_X * 3, body.getY() + BALL_START_Y, BALL_W_H, BALL_W_H, Color.green.darker()));
        MyRectangle life = new MyRectangle(body.getX() + DISTANCE_X+5, body.getY() - 5, 20, GUN_H, Color.GREEN);
        MyRectangle life2 = new MyRectangle(life.getX() + life.getW() + 10, life.getY(), life.getW(), life.getH(), life.getColor());
        MyRectangle life3 = new MyRectangle(life2.getX() + life2.getW() + 10, life2.getY(), life2.getW(), life2.getH(), life.getColor());
        this.life = new MyRectangle[]{life, life2, life3};

    }

    public void paint(Graphics graphics) {
        for (ShapeDefined shape : this.myPlayer) {
            shape.paint(graphics);
        }
        for (int i=0; i< life.length; i++){
            life[i].paint(graphics);
        }
    }

    public void moveTo(int direction) {
        int movement = direction * Const.SPEED_PLAYER;
        int futurePlace = body.getX() + movement;
        if (futurePlace < 0 || Const.MAIN_WINDOW_W - body.getW() < futurePlace)
            return;
        for (ShapeDefined shape : this.myPlayer) {
            shape.setX(shape.getX() + movement);
        }
        for (int i = 0; i < life.length; i++) {
            this.life[i].setX(this.life[i].getX() + movement);
        }
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
    public int getBodyX() {
        return this.body.getX();
    }

    public int getBodyY() {
        return this.body.getY();
    }

}
