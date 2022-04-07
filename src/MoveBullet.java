import java.util.ArrayList;

public class MoveBullet extends MyRunnable {
    private static final int SPEED = 1;
    private Cannon cannon;
    private KeyPressDetector spaceDetector;
    private ArrayList<Bullet> bullets;


    public MoveBullet(PlayPanel myPlay, Cannon cannon, ArrayList<Bullet> bullets, KeyPressDetector spaceDetector) {
        super(myPlay, SPEED);
        this.cannon = cannon;
        this.bullets = bullets;
        this.spaceDetector = spaceDetector;
    }

    @Override
    public void _run() {
        if (spaceDetector.isSpacePressed()) {
            Bullet bullet1 = new Bullet(cannon.getBodyX() + 25, cannon.getBodyY());
            this.bullets.add(bullet1);
            spaceDetector.setSpacePressed(false);
        }
        for (int i=0; i< bullets.size(); i++) {
            bullets.get(i).moveUp();
            if (bullets.get(i).getBody().getY()==0){
                bullets.remove(bullets.get(i));
            }
        }
        this.myPlay.repaint();

    }

}

