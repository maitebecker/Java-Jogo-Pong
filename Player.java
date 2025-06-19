import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player extends GEOForm {
    private int x, y;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 150;
    private int numPlayer;
    private int speedY = 10;
    private int dy = 0;

    public Player(int numPlayer) {
        this.numPlayer = numPlayer;
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

    public void move(int panelHeight) {
        int newY = y + (dy * speedY);
        if (newY >= 0 && newY <= panelHeight - HEIGHT) {
            y = newY; // atualiza se estiver dentro da tela
        }
    }

    public void setYGrow(boolean grow) {
        this.dy = grow ? 1 : -1; // Define a direção do movimento vertical: true = desce, false = sobe
    }

    public void processKeyUp(KeyEvent ke, int panelHeight) {
        if ((numPlayer == 1 && ke.getKeyCode() == KeyEvent.VK_W)
                || (numPlayer == 2 && ke.getKeyCode() == KeyEvent.VK_O)) {
            dy = 0;
        }
        if ((numPlayer == 1 && ke.getKeyCode() == KeyEvent.VK_S)
                || (numPlayer == 2 && ke.getKeyCode() == KeyEvent.VK_L)) {
            dy = 0;
        }
    }

    public void processKeyDown(KeyEvent ke, int panelHeight) {
        if ((numPlayer == 1 && ke.getKeyCode() == KeyEvent.VK_W)
                || (numPlayer == 2 && ke.getKeyCode() == KeyEvent.VK_O)) {
            setYGrow(false); 
        }
        if ((numPlayer == 1 && ke.getKeyCode() == KeyEvent.VK_S)
                || (numPlayer == 2 && ke.getKeyCode() == KeyEvent.VK_L)) {
            setYGrow(true);
        }
    }
}
