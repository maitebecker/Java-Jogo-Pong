import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Goal extends GEOForm {
    private int x, y;
    public static final int WIDTH = 15;
    public static final int HEIGHT = 400;

    public Goal() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}