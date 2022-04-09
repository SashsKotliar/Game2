import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class ImagePanel extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private Image image;
    private int iWidth2;
    private int iHeight2;

    public ImagePanel(String str) {
        this.image = Toolkit.getDefaultToolkit().createImage(str);
        this.iWidth2 = image.getWidth(this) / 2;
        this.iHeight2 = image.getHeight(this) / 2;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int x = this.getParent().getWidth() / 2 - iWidth2;
            int y = this.getParent().getHeight() / 2 - iHeight2;
            g.drawImage(image, x, y, this);
        }
    }
}
