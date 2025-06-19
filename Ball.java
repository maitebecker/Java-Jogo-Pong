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

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size);
    }

    public void move(int panelWidth, int panelHeight) {
        x += speedX * dx; /// dx = 1 → direita; dx = -1 → esquerda
        y += speedY * dy; // dy = 1 → baixo; dy = -1 → cima

        if (x <= 0 || x >= panelWidth - size) // Se a bola ultrapassar o limite do painel, muda de direção
            dx *= -1;
        if (y <= 0 || y >= panelHeight - size)
            dy *= -1;
    }

    public void setXGrow(boolean grow) {
        this.dx = grow ? 1 : -1; // true → direita, false → esquerda
    }

    public void setYGrow(double newDY) {
        this.dy = newDY > 0 ? 1 : -1; // se bateu abaixo do centro, desce; se acima, sobe
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
