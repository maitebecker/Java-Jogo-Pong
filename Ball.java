import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GEOForm {
    private final int size;
    private int x, y;
    private double speedX = 4, speedY = 4;
    private int dx = 1, dy = 1;
    private final double maxSpeed = 10;

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
        x += speedX * dx; //dx = 1 x aumenta e bola vai para a direita.
        y += speedY * dy; //dy = 1 y aumenta e bola vai para baixo.

        if (x <= 0 || x >= panelWidth - size)
            dx *= -1;
        if (y <= 0 || y >= panelHeight - size)
            dy *= -1;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public void setXGrow(boolean grow) {
        this.dx = grow ? 1 : -1;
    }

    public void setYGrow(boolean grow) {
        this.dy = grow ? 1 : -1;
    }

    public void increaseSpeed() {
        if (speedX < maxSpeed) {
            speedX += 0.5;
        }

        if (speedY < maxSpeed) {
            speedY += 0.5;
        }
    }

}