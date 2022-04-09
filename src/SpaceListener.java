import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SpaceListener implements KeyListener {

    private boolean pressed;

    public SpaceListener() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            this.pressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            this.pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }
}