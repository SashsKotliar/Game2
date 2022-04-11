import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class MainStartOption {
    private JFrame mainFrame;

    public static void main(String[] args) {
        MainStartOption start = new MainStartOption(Const.MAIN_WINDOW_W, Const.MAIN_WINDOW_H);
    }

    public MainStartOption(int w, int h) {
        this.mainFrame = new BasicJFrame(w, h);
        myBottoms();
        this.mainFrame.setVisible(true);
        BasicJPanel basicJPanel= new BasicJPanel(0,0,w,h,Color.cyan);
        this.mainFrame.add(basicJPanel);
    }


    public void myBottoms() {
        this.mainBottomsOption((this.mainFrame.getWidth() - Const.BUTTON_W) / 2, Const.BUTTON_Y_START, Const.BUTTON_W, Const.BUTTON_H,
                "Explanation of the game"
                , this::ExplanationGame);
        this.mainBottomsOption((this.mainFrame.getWidth() - Const.BUTTON_W) / 2, Const.BUTTON_Y_START + Const.BUTTON_H, Const.BUTTON_W, Const.BUTTON_H, "Start",
                this::startGame);
    }

    public JFrame standardJFrameWindow() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(Const.MAIN_WINDOW_W / 2, Const.MAIN_WINDOW_H / 2);
        jFrame.setResizable(false);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        return jFrame;
    }

    public JFrame ExplanationGame() {
        JFrame jFrame = standardJFrameWindow();
        BasicJPanel panel=new BasicJPanel(0,0,jFrame.getWidth(),jFrame.getHeight(),Color.cyan);
        jFrame.add(panel);
        title(Const.EXPLANATION,panel);
        return jFrame;
    }

    public MainGame startGame() {
        return new MainGame(Const.MAIN_WINDOW_W, Const.MAIN_WINDOW_H);
    }

    public void title(String title, JPanel panel) {
        JLabel jLabel = new JLabel(title, SwingConstants.CENTER);
        jLabel.setFont(Const.FONT1);
        jLabel.setOpaque(true);
        jLabel.setForeground(Color.cyan);
        jLabel.setBounds(0, 0, panel.getWidth(), Const.TITLE_H);
        panel.add(jLabel);
    }

    public void mainBottomsOption(int x, int y, int w, int h, String titleOn, Supplier<JFrame> supplier) {
        Button button = new Button(titleOn);
        button.setFont(Const.FONT1);
        button.setBounds(x, y, w, h);
        button.setForeground(Color.cyan);
        button.setBackground(Color.black);
        button.addActionListener(e -> {
            JFrame frame = supplier.get();
            frame.setVisible(true);
        });
        this.mainFrame.add(button);
    }
}

