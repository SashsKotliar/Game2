public class MovePlayer extends MyRunnable {
    private static final  int SPEED=2;
    private MovementPlayer movementPlayer;
    private Cannon cannon;

    public MovePlayer(PlayPanel playPanel, Cannon myCannon) {
        super(playPanel,SPEED);
        this.cannon = myCannon;
    }

    @Override
    public void _run() {
        MovementPlayer movement = new MovementPlayer(this.cannon);
        myPlay.setFocusable(true);
        myPlay.setVisible(true);
        myPlay.requestFocus();
        myPlay.addKeyListener(movement);
        switch (this.cannon.getDirection()) {
            case Cannon.RIGHT:
                cannon.moveRight();
                if (cannon.getBodyX() == 495) {
                    cannon.moveLeft();
                }
                break;
            case Cannon.LEFT:
                cannon.moveLeft();
                if (cannon.getBodyX() == 40) {
                    cannon.moveRight();
                }
                break;

        }
    }
}
