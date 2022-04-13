import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayPanel extends BasicJPanel {
    public static final int START_LIVES = 3;

    private SpaceListener spaceDetector;
    private ArrayList<Bullet> bullets;
    private ArrayList<Ball> computerBall;
    private ArrayList<MyRunnable> allRunnableMethods;
    private int spentLives;
    //private Cannon cannon;
    private MyAlien alien;
    private int points;
    private int level;
    private JLabel levelText;
    private final ImageIcon imageIcon = new ImageIcon("C:\\Users\\Sasha\\Desktop\\תואר ראשון\\שנה 1 סמסטר א\\מבוא למדעי המחשב א\\GameBack.jpg");


    public PlayPanel(int x, int y, int width, int height) {
        super(x, y, width, height, Color.WHITE);
        this.setBounds(x, y, width, height);
        this.computerBall = new ArrayList<>();
        this.bullets=new ArrayList<>();
        this.allRunnableMethods=new ArrayList<>();
        //this.cannon = new Cannon(this.getWidth(),this.getHeight());
        this.alien = new MyAlien();
        this.spaceDetector = new SpaceListener();
        SpaceListener movement = new SpaceListener();
        this.spentLives = 0;
        this.points = 0;
        this.level=1;
        this.addKeyListener(movement);
        this.addKeyListener(spaceDetector);
        initPlay();

    }
    public void initPlay(){
        movePlayer();
        moveBullet();
        this.addLastBall();
        moveComputerBallLoop();
        message();

    }
    public void  setLevelText(){
        this.levelText.setText("Level  :"+this.level+"\n"+"   Lives left: "+(START_LIVES - this.spentLives)
        +"   Score: "+this.points);
    }
    public void message(){
        this.levelText=new JLabel("Level: "+this.level+"    Lives left: "+(START_LIVES - this.spentLives)
                +"   Score: "+this.points,SwingConstants.CENTER);
        levelText.setBounds(0,0,this.getWidth(),Const.FONT.getSize());
        levelText.setFont(Const.FONT);
        levelText.setForeground(Color.green.darker());
        this.add(levelText);
    }
    public void setPoints() {
        this.points++;
        setLevelText();
    }
    public void setLevel(){
        this.level++;
        setLevelText();
    }

    public MyAlien getAlien() {
        return this.alien;
    }

    public int getLife() {
        return this.spentLives;
    }

    public ArrayList<Bullet> getBullets() {
        return this.bullets;
    }


    public void moveBullet() {
        MoveBullet moveBullet = new MoveBullet(this, this.alien, this.spaceDetector);
        this.allRunnableMethods.add(moveBullet);
        new Thread(moveBullet).start();
    }

    public void movePlayer() {
        MovePlayer movementPlayer = new MovePlayer(this, this.alien);
        this.allRunnableMethods.add(movementPlayer);
        new Thread(movementPlayer).start();
    }

    public void moveComputerBallLoop() {
        MoveBall moveBall = new MoveBall(this);
        this.allRunnableMethods.add(moveBall);
        new Thread(moveBall).start();
    }

    public void stop() {
        for (MyRunnable myRunnable : this.allRunnableMethods) {
            myRunnable.stop();
        }
        BasicJPanel gameOver = new BasicJPanel(0, this.getHeight() / 2 - 100, this.getWidth(), 100, Color.RED);
        gameOver.title("Game over!", 0, gameOver.getHeight());
        this.add(gameOver);
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        synchronized (computerBall) {
            super.paintComponent(g);
            this.imageIcon.paintIcon(this,g,-25,0);
            this.alien.paint(g);
            synchronized (computerBall) {
                for (Ball ball : this.computerBall) {
                    ball.paint(g);
                }
            }
            synchronized (bullets) {
                for (Bullet bullet : this.bullets) {
                    bullet.paint(g);
                }
            }
        }
        //this.cannon.paint(g);
        this.alien.paint(g);
    }

    public ArrayList<Ball> getComputerBall() {
        return this.computerBall;
    }

    public void addLastBall() {
        this.computerBall.add(randomBall());
    }

    public Ball randomBall() {
        Random random = new Random();
        Color color = Color.getHSBColor((float)Math.random(), 1, (float)Math.random());
        return new Ball(random.nextInt(getWidth() - 200) + 20, 20, color);
    }

    public void moveBall(Ball ball) {
        int hw = ball.getW();
        if (ball.getY() <= 0 || this.getHeight()<= ball.getY() + hw)
            ball.flipY();
        if (ball.getX() <= 0 || this.getWidth() <= ball.getX() + hw)
            ball.flipX();

        ball.step();
    }

    public void hit() {
        spentLives++;
        this.setLevelText();
    }
}