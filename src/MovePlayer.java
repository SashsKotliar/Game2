import java.util.ArrayList;

public class MovePlayer extends MyRunnable {
    private MovementPlayer movementPlayer;
    private Cannon cannon;
    private boolean touching = false;

    public MovePlayer(PlayPanel playPanel, Cannon myCannon) {
        super(playPanel);
        this.cannon = myCannon;


        movementPlayer = new MovementPlayer(this.cannon);
        myPlay.setFocusable(true);
        myPlay.setVisible(true);
        myPlay.requestFocus();
        myPlay.addKeyListener(movementPlayer);
    }

    @Override
    public void _run() {
        cannon.moveTo(movementPlayer.getDirection());
        ArrayList<Ball> balls = this.myPlay.getComputerBall();
        boolean lost = false;
        synchronized (balls) {
            for (Ball ball : balls) {
                if (this.myPlay.getCannon().checkCollision(ball)) {
                    lost = true;
                    break;
                }
            }
        }
        if (lost) {
            if (!touching) {
                myPlay.getCannon().lost(this.myPlay.getLife());
                myPlay.hit();

                if (this.myPlay.getLife() >= 3) {
                    this.myPlay.stop();
                }
            }
            this.touching = true;
        } else this.touching = false;

        if (this.myPlay.getLife() >= 3) {
            this.myPlay.stop();
        }
    }
}
