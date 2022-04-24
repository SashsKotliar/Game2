import java.util.ArrayList;

public class MoveBullet extends MyRunnable {
    private MyAlien alien;

    private SpaceListener spaceDetector;
    private ArrayList<Bullet> bullets;


    public MoveBullet(PlayPanel myPlay, MyAlien alien, SpaceListener spaceDetector) {
        super(myPlay);
        this.alien = alien;
        this.bullets = myPlay.getBullets();
        this.spaceDetector = spaceDetector;
    }

    @Override
    public void _run() {
        if (spaceDetector.isPressed()) {
            Bullet bullet1 = new Bullet(alien.getGun1().getX(), alien.getGun1().getY());
            Bullet bullet2 = new Bullet(alien.getGun2().getX(), alien.getGun2().getY());
            Bullet bullet3 = new Bullet(alien.getGun3().getX(), alien.getGun3().getY());
            synchronized (bullets) {
                if (this.bullets.size() == 0) {
                    this.bullets.add(bullet1);
                    this.bullets.add(bullet2);
                    this.bullets.add(bullet3);
                }
            }
        }
        ArrayList<Ball> balls = myPlay.getComputerBall();
        synchronized (balls) {
            synchronized (bullets) {
                ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
                ArrayList<Ball> ballsToRemove = new ArrayList<>();
                ArrayList<Ball> ballsToAdd = new ArrayList<>();

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
                            myPlay.setPoints();
//                            myPlay.setPoints();
//                            myPlay.setPointsPlayer();
                            bulletsToRemove.add(bullet);
                            if (ball.isDead()) {
                                ballsToRemove.add(ball);
                            } else {
                                ballsToAdd.add(new Ball(ball));
                            }
                        }
                    }
                }

                bullets.removeAll(bulletsToRemove);
                balls.removeAll(ballsToRemove);
                balls.addAll(ballsToAdd);
            }
        }
        this.myPlay.repaint();

    }

}