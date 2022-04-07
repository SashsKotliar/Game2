import javax.swing.*;
import java.awt.*;

public class MainGame extends BasicJFrame {
    private boolean end = false;

    public MainGame(int w, int h) {
        super(w, h);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlayPanel gamePanel = new PlayPanel(0,0, this.getWidth(), this.getHeight());
        this.add(gamePanel);
        this.end = false;


    }




}
