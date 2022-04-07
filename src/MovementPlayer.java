import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementPlayer implements KeyListener {
    private Cannon player;



    public MovementPlayer(Cannon player) {
        this.player = player;

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                this.player.setDirection(Cannon.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                this.player.setDirection(Cannon.LEFT);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}