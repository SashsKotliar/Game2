import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class PlayPanel extends BasicJPanel {
    private Cannon cannon;
    private KeyPressDetector spaceDetector;
    private ArrayList<Bullet> bulletArrayList = new ArrayList<>();
    private ArrayList<Ball> computerBall;
    private ArrayList<MyRunnable> allRunnableMethods = new ArrayList<>();
    private ImageIcon player;
    private Ground g;
    private int life;

    public PlayPanel(int x, int y, int width, int height) {
        super(x, y, width, height, Color.white);
        this.setBounds(x, y, width, height);
        this.computerBall = new ArrayList<>();
        this.cannon = new Cannon();
        this.g = new Ground();
        this.spaceDetector = new KeyPressDetector();
        MovementLoad movement = new MovementLoad(spaceDetector);
        this.addKeyListener(movement);
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

    public ArrayList<Bullet> getBulletArrayList() {
        return this.bulletArrayList;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void moveBullet() {
        MoveBullet moveBullet = new MoveBullet(this, this.cannon, this.getBulletArrayList(), this.spaceDetector);
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
        BasicJPanel gameOver = new BasicJPanel(0, this.getHeight() / 2 - 100, this.getWidth(), 100, Color.RED);
        gameOver.title("Game over!", 0, gameOver.getHeight());
        this.add(gameOver);
        for (MyRunnable myRunnable : this.allRunnableMethods) {
            myRunnable.stop();
        }
    }

    protected void paintComponent(Graphics g) {
        synchronized (this) {
            super.paintComponent(g);
            this.cannon.paint(g);
            this.g.paint(g);
            for (Ball ball : this.computerBall) {
                ball.paint(g);
            }
            for (int i = 0; i < this.bulletArrayList.size(); i++) {
                bulletArrayList.get(i).paint(g);
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
        Color[] colorMap = new Color[]{Color.red, Color.BLUE, Color.YELLOW, Color.PINK};
        int randomColor = random.nextInt(3);
        int x = random.nextInt(11) + 10;
        Ball ball = new Ball(random.nextInt(getWidth() - 200) + 20, 20, x, colorMap[randomColor]);
        return ball;
    }

    public void moveBall(Ball ball) {
        int hw = ball.getWidth();
        if (ball.getY() <= 0 || this.getHeight() - g.getGround().getHeight() - hw <= ball.getY())
            ball.flipY();
        if (ball.getX() <= 0 || this.getWidth() <= ball.getX() + hw - 10)
            ball.flipX();

        ball.step();
    }

    public void removeBalls(Collection<? extends Ball> ballsToRemove) {
        this.computerBall.removeAll(ballsToRemove);
    }
}