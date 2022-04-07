import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementLoad implements KeyListener {

    private KeyPressDetector spaceDetector;

    public MovementLoad(KeyPressDetector spaceDetector) {
        this.spaceDetector = spaceDetector;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                this.spaceDetector.setSpacePressed(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}