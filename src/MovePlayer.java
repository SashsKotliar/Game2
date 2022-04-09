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
        boolean lost = false;
        for (Ball ball : this.myPlay.getComputerBall()) {
            if (this.myPlay.getCannon().checkCollision(ball)) {
                lost = true;
                break;
            }
        }
        if (lost) {
            if (!touching) {
                myPlay.getCannon().lost(this.myPlay.getLife());
                this.myPlay.setLife(this.myPlay.getLife() + 1);
            }
            this.touching = true;
        } else this.touching = false;
        if (this.myPlay.getLife() >= 3) {
            this.myPlay.stop();
        }
    }
}
