import java.awt.*;

public class Ground {
    private MyRectangle ground;

    public Ground(){
        this.ground = new MyRectangle(0, Cannon.BODY_Y + Cannon.BODY_H,
                Constance.MAIN_WINDOW_W, Constance.MAIN_WINDOW_H - Cannon.BODY_Y - Cannon.BODY_H-Cannon.WHEEL_W_H, Color.gray);
    }

    public MyRectangle getGround() {
        return ground;
    }

    public void setGround(MyRectangle ground) {
        this.ground = ground;
    }

    public void paint(Graphics graphics){

        this.ground.paint(graphics);
    }
}