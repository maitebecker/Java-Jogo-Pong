import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GEOForm {
    private final int size;
    private int x, y;
    private double speedX = 5, speedY = 5;
    private int dx = 1, dy = 1;

    public Ball(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size);
    }

    public void move(int panelWidth, int panelHeight) {
        x += speedX * dx; // Se dx = 1, ela soma +speedX e vai pra direita.
        y += speedY * dy; // Se dx = -1, ela subtrai e vai pra esquerda.

        if (x <= 0 || x >= panelWidth - size) // Se a bola ultrapassar o limite do painel, muda de direção
            dx *= -1;
        if (y <= 0 || y >= panelHeight - size)
            dy *= -1;
    }

    public void setXGrow(boolean grow) {
        this.dx = grow ? 1 : -1;
    }

    // Se a bola bate mais em cima do jogador, newDY é negativo → bola sobe.
    public void setYGrow(double newDY) {
        this.dy = newDY > 0 ? 1 : -1;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
