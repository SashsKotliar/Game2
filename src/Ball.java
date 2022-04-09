
import java.awt.*;
import java.util.Random;

public class Ball extends Circle {
    private int hp;
    private int speedX = 2;
    private int speedY = -4;
    private static final Random random = new Random();

    private Ball(int x, int y, int hp, Color color, int directionX, int directionY) {
        super(x, y, hpToSize(hp), hpToSize(hp), color);
        assert Math.abs(directionX * directionY) != 1;
        speedX *= directionX;
        speedY *= directionY;
        this.hp = hp;
    }

    public Ball(int x, int y, Color color) {
        this(x, y, Const.START_HP_BALL, color);
    }

    public Ball(Ball ball) {
        this(ball.getX(), ball.getY(), ball.hp, ball.getColor());
        speedX = -ball.speedX;
        speedY = ball.speedY;
    }

    public Ball(int x, int y, int hp, Color color) {
        this(x,
                y,
                hp,
                color,
                getRandomDir(),
                getRandomDir());
    }


    private static int hpToSize(int hp) {
        return 30 + 8 * hp;
    }

    private static int getRandomDir() {
        int dir = random.nextInt(2) * 2 - 1;
        Utils.sleep(10);
        System.out.println(dir);
        return dir;
    }

    public void hit() {
        this.hp--;
        this.setHeight(hpToSize(this.hp));
        this.setWidth(hpToSize(this.hp));
        up();
    }

    public boolean isDead() {
        return hp <= 0;
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

    public int getSpeedX() {
        return speedX;
    }

    public void step() {
        this.setX(this.getX() + this.getSpeedX());
        this.setY(this.getY() + this.getSpeedY());
    }

    public boolean isDown() {
        return speedY > 0;
    }

    public void up() {
        if (isDown())
            flipY();
    }
}
