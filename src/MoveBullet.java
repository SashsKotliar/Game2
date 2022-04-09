import java.util.ArrayList;

public class MoveBullet extends MyRunnable {
    private Cannon cannon;

    private KeyPressDetector spaceDetector;
    private ArrayList<Bullet> bullets;
    private ArrayList<Ball> balls;
    private Ball ball;
    private boolean touching;


    public MoveBullet(PlayPanel myPlay, Cannon cannon, ArrayList<Bullet> bullets, KeyPressDetector spaceDetector) {
        super(myPlay);
        this.cannon = cannon;
        this.bullets = bullets;
        this.spaceDetector = spaceDetector;
        this.touching=false;
    }

    @Override
    public void _run() {
        if (spaceDetector.isSpacePressed()) {
            Bullet bullet1 = new Bullet(cannon.getBodyX() + 25, cannon.getBodyY());
            this.bullets.add(bullet1);
            spaceDetector.setSpacePressed(false);
        }
        ArrayList<Ball> balls = myPlay.getComputerBall();
        synchronized (balls) {
            ArrayList<Bullet> bulletsToRemove=new ArrayList<>();
            ArrayList<Ball> ballsToRemove=new ArrayList<>();

            for (Bullet bullet : bullets) {
                bullet.moveUp();
                if (bullet.getBody().getY() <= 0) {
                    bulletsToRemove.add(bullet);
                }
            }

            for (Bullet bullet : bullets) {
                for (Ball ball : balls) {
                    if (bullet.collision(ball)) {
                        ball.hit();
                        bulletsToRemove.add(bullet);
                        if (ball.isDead()){
                            ballsToRemove.add(ball);
                        }
                    }
                }
            }
            bullets.removeAll(bulletsToRemove);
            myPlay.removeBalls(ballsToRemove);
        }
        this.myPlay.repaint();

    }

}