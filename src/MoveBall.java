
public class MoveBall extends MyRunnable {
    private static final int SPEED=4;


    public MoveBall(PlayPanel myPlay) {
        super(myPlay,SPEED);
    }

    @Override
    public void _run() {
        for (Ball ball : myPlay.getComputerBall()) {
            myPlay.moveBall(ball);
        }
        if (myPlay.getComputerBall().size()<=1){
            myPlay.getComputerBall().add(myPlay.randomBall());
        }
        this.myPlay.repaint();
    }

}
