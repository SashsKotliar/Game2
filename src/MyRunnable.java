public abstract class MyRunnable implements Runnable {
    private boolean running = true;
    protected final PlayPanel myPlay;
    private int speed;

    public MyRunnable(PlayPanel myPlay,int speed) {
        this.myPlay = myPlay;
        this.speed=speed;
    }

    public void stop() {
        running = false;
    }

    public abstract void _run();

    @Override
    public void run() {
        while (running) {
            this._run();
            Utils.sleep(speed);
        }
    }
}
