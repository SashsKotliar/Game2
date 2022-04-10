import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayPanel extends BasicJPanel {
    private Cannon cannon;
    private SpaceListener spaceDetector;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Ball> computerBall;
    private ArrayList<MyRunnable> allRunnableMethods = new ArrayList<>();
    private ImageIcon player;
//    private Ground g;
    private int life;

    public PlayPanel(int x, int y, int width, int height) {
        super(x, y, width, height, Color.gray);
        this.setBounds(x, y, width, height);
        this.computerBall = new ArrayList<>();
        this.cannon = new Cannon();
//        this.g = new Ground();
        this.spaceDetector = new SpaceListener();
        SpaceListener movement = new SpaceListener();
        this.addKeyListener(movement);
        this.addKeyListener(spaceDetector);
        movePlayer();
        moveBullet();
        this.addLastBall();
        moveComputerBallLoop();
        this.life = 0;
        this.player = new ImageIcon("assets/blob_https___224b5342-5b33-465f-b23c-7ef161095e5c.poki-gdn.com_76b8e865-5ebf-40ff-a2c2-f0772e351730");

    }

    public Cannon getCannon() {
        return this.cannon;
    }

    public int getLife() {
        return this.life;
    }

    public ArrayList<Bullet> getBullets() {
        return this.bullets;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void moveBullet() {
        MoveBullet moveBullet = new MoveBullet(this, this.cannon, this.spaceDetector);
        this.allRunnableMethods.add(moveBullet);
        new Thread(moveBullet).start();
    }

    public void movePlayer() {
        MovePlayer movementPlayer = new MovePlayer(this, this.cannon);
        this.allRunnableMethods.add(movementPlayer);
        new Thread(movementPlayer).start();
    }

    public void moveComputerBallLoop() {
        MoveBall moveBall = new MoveBall(this);
        this.allRunnableMethods.add(moveBall);
        new Thread(moveBall).start();
    }

    public void stop() {
        for (MyRunnable myRunnable : this.allRunnableMethods) {
            myRunnable.stop();
        }
        BasicJPanel gameOver = new BasicJPanel(0, this.getHeight() / 2 - 100, this.getWidth(), 100, Color.RED);
        gameOver.title("Game over!", 0, gameOver.getHeight());
        this.add(gameOver);
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        synchronized (computerBall) {
            super.paintComponent(g);
            this.cannon.paint(g);
//            this.g.paint(g);
            synchronized (computerBall) {
                for (Ball ball : this.computerBall) {
                    ball.paint(g);
                }
            }
            synchronized (bullets) {
                for (Bullet bullet : this.bullets) {
                    bullet.paint(g);
                }
            }
            this.player.paintIcon(this, g, 0, 0);
        }
    }

    public ArrayList<Ball> getComputerBall() {
        return this.computerBall;
    }

    public void addLastBall() {
        this.computerBall.add(randomBall());
    }

    public Ball randomBall() {
        Random random = new Random();
        Color color = Color.getHSBColor((float)Math.random(), 1, (float)Math.random());
        return new Ball(random.nextInt(getWidth() - 200) + 20, 20, color);
    }

    public void moveBall(Ball ball) {
        int hw = ball.getW();
        if (ball.getY() <= 0 || this.getHeight() - hw <= ball.getY())
            ball.flipY();
        if (ball.getX() <= 0 || this.getWidth() <= ball.getX() + hw - 10)
            ball.flipX();

        ball.step();
    }

    public void hit() {
        life++;
    }
}