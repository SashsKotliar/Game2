import java.util.ArrayList;

public class MovePlayer extends MyRunnable {
    private MovementPlayer movementPlayer;
    private MyAlien alien;
    private boolean touching = false;

    public MovePlayer(PlayPanel playPanel, MyAlien myAlien) {
        super(playPanel);
        this.alien = myAlien;


        movementPlayer = new MovementPlayer(this.alien);
        myPlay.setFocusable(true);
        myPlay.setVisible(true);
        myPlay.requestFocus();
        myPlay.addKeyListener(movementPlayer);
    }

    @Override
    public void _run() {
        alien.moveTo(movementPlayer.getDirection());
        ArrayList<Ball> balls = this.myPlay.getComputerBall();
        boolean lost = false;
        synchronized (balls) {
            for (Ball ball : balls) {
                if (this.myPlay.getAlien().checkCollision(ball)) {
                    lost = true;
                    break;
                }
            }
        }
        if (lost) {
            if (!touching) {
                myPlay.getAlien().lost(this.myPlay.getLife());
                myPlay.hit();
//                myPlay.setLivesLeft();

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
