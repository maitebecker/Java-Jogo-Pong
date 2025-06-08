import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GEOForm {
    private int x, y;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 150;

    public Player(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
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
