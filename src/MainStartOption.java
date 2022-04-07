import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class MainStartOption {
    private JFrame mainFrame;

    public static void main(String[] args) {
        MainStartOption start = new MainStartOption(Constance.MAIN_WINDOW_W, Constance.MAIN_WINDOW_H);
    }

    public MainStartOption(int w, int h) {
        this.mainFrame = new BasicJFrame(w, h);
        this.title("My app!", mainFrame);
        myBottoms();
        this.mainFrame.setVisible(true);
    }

    public void myBottoms() {
        this.mainBottomsOption((this.mainFrame.getWidth() - Constance.BUTTON_W) / 2, Constance.BUTTON_Y_START, Constance.BUTTON_W, Constance.BUTTON_H,
                "Explanation of the game"
                , this::ExplanationGame);
        this.mainBottomsOption((this.mainFrame.getWidth() - Constance.BUTTON_W) / 2, Constance.BUTTON_Y_START + Constance.BUTTON_H, Constance.BUTTON_W, Constance.BUTTON_H, "Start",
                this::startGame);
    }

    public JFrame standardJFrameWindow() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(Constance.MAIN_WINDOW_H / 2, Constance.MAIN_WINDOW_W / 2);
        jFrame.setResizable(false);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        return jFrame;
    }

    public JFrame ExplanationGame() {
        JFrame jFrame = standardJFrameWindow();
        title(Constance.EXPLANATION, jFrame);
        return jFrame;
    }

    public MainGame startGame() {
        return new MainGame(Constance.MAIN_WINDOW_W, Constance.MAIN_WINDOW_H);
    }

    public void title(String title, JFrame jFrame) {
        JLabel jLabel = new JLabel(title, SwingConstants.CENTER);
        jLabel.setFont(Constance.FONT);
        jLabel.setOpaque(true);
        jLabel.setForeground(Color.cyan);
        jLabel.setBounds(0, 0, jFrame.getWidth(), Constance.TITLE_H);
        jFrame.add(jLabel);
    }

    public void mainBottomsOption(int x, int y, int w, int h, String titleOn, Supplier<JFrame> supplier) {
        Button button = new Button(titleOn);
        button.setFont(Constance.FONT);
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

