import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayPanel extends BasicJPanel {
    private Cannon cannon;
    private KeyPressDetector spaceDetector;
    private ArrayList<Bullet> bulletArrayList = new ArrayList<>();
    private ArrayList<Ball> computerBall;
    private MoveBall moveBallRunnable;
    private Ground g;

    public void movePlayer() {

    }
    public ArrayList<Bullet> getBulletArrayList(){
        return this.bulletArrayList;
    }

    public PlayPanel(int x, int y, int width, int height) {
        super(x, y, width, height, Color.white);
        this.setBounds(x, y, width, height);
        this.computerBall = new ArrayList<>();
        this.cannon = new Cannon();
        this.g=new Ground();
        this.spaceDetector=new KeyPressDetector();
        MovementLoad movement = new MovementLoad(spaceDetector);
        this.addKeyListener(movement);

        moveComputerBallLoop();
        movePlayer1();
        moveBullet();
        getLastBall();
        Utils.sleep(500);
        getLastBall();

        /////לקשר את move player
        ////לקשר את move bullet
    }
    public void moveBullet(){
        MoveBullet moveBall=new MoveBullet(this,this.cannon,this.getBulletArrayList(),this.spaceDetector);
        new Thread(moveBall).start();
    }
    public void  movePlayer1(){
        MovePlayer movementPlayer= new MovePlayer(this,this.cannon);
        new Thread(movementPlayer).start();
    }

    public void moveComputerBallLoop() {
        this.moveBallRunnable = new MoveBall(this);
        new Thread(moveBallRunnable).start();
    }
    public boolean bulletAlive(Bullet bullet){
        return bullet.getBody().getY()>0;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g.paint(g);
        this.cannon.paint(g);
        for (Ball ball : this.computerBall) {
            ball.paint(g);
        }
        for (int i=0; i<this.bulletArrayList.size(); i++) {
            bulletArrayList.get(i).paint(g);
            if (bulletArrayList.get(i).getBody().getY()==0){
                this.bulletArrayList.remove(bulletArrayList.get(i));
            }
        }

    }

    public ArrayList<Ball> getComputerBall() {
        return this.computerBall;
    }

    public void getLastBall() {
        this.computerBall.add(randomBall());
    }

    public Ball randomBall() {
        Random random = new Random();
        Color[] colorMap = new Color[]{Color.red, Color.BLUE, Color.YELLOW, Color.PINK};
        int randomColor = random.nextInt(3);
        int x = random.nextInt(11) + 10;
        return new Ball(random.nextInt(getWidth() - 20) + 20, 20, x, colorMap[randomColor]);
    }

    public void moveBall(Ball ball) {
        int hw = ball.getWidth();
        if (ball.getY() <= 0 || this.getHeight()-g.getGround().getHeight() - hw <= ball.getY())
            ball.flipY();
        if (ball.getX() <= 0 || this.getWidth() - hw <= ball.getX())
            ball.flipX();

        ball.step();
    }
}