
import java.awt.*;
import java.util.Random;

public class Ball extends Circle {
    private int hp = 5;
    private int speedX = 1;
    private int speedY = -2;
    private static final Random random = new Random();

    public Ball(int x, int y, int hp, Color color, int directionX, int directionY) {
        super(x, y, hp * 5, hp * 5, color);
        assert Math.abs(directionX * directionY) != 1;
        speedX *= directionX;
        speedY *= directionY;
    }

    public Ball(int x, int y, int hp, Color color) {
        this(x,
                y,
                hp,
                color,
                getRandomDir(),
                getRandomDir());
    }

    public Ball(Ball randomBall) {
        this(randomBall.getX(),
                randomBall.getY(),
                randomBall.hp,
                randomBall.getColor()
                ,getRandomDir(),
                getRandomDir());
    }

    private static int getRandomDir() {
        int dir = random.nextInt(2) * 2 - 1;
        Utils.sleep(10);
        System.out.println(dir);
        return dir;
    }

    public void flipX() {
        this.speedX *= -1;
    }

    public void flipY() {
        this.speedY *= -1;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void step() {
        this.setX(this.getX() + this.getSpeedX());
        this.setY(this.getY() + this.getSpeedY());
    }
}
