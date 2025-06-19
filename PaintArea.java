import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PaintArea extends JPanel {
    private static final int TIMER_DELAY = 15;

    // Objetos principais do jogo
    private Ball ball = new Ball(80, 60, 30);
    private Goal goal1 = new Goal();
    private Goal goal2 = new Goal();
    private Player player1 = new Player(1);
    private Player player2 = new Player(2);

    GEOForm sprites[] = { goal1, goal2, ball, player1, player2 };
    int YPlayer1, YPlayer2;
    Score score;

    boolean pointScored = false;

    KeyListener kl = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent ke) {

        }

        @Override
        public void keyPressed(KeyEvent ke) {
            player1.processKeyDown(ke, getHeight());
            player2.processKeyDown(ke, getHeight());
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            player1.processKeyUp(ke, getHeight());
            player2.processKeyUp(ke, getHeight());
        }
    };

    Timer t = new Timer(TIMER_DELAY, (ActionEvent e) -> {
        ball.move(getWidth(), getHeight());
        player1.move(getHeight());
        player2.move(getHeight());
        checkCollisions();
        repaint();
    });

    public PaintArea() {
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();
        score = new Score();

        /*
         * Será chamado automaticamente sempre que o painel PaintArea for
         * redimensionado.
         */
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                goal1.setPosition(0, (getHeight() - Goal.HEIGHT) / 2);
                goal2.setPosition(getWidth() - Goal.WIDTH, (getHeight() - Goal.HEIGHT) / 2);

                YPlayer1 = (getHeight() - Player.HEIGHT) / 2;
                YPlayer2 = (getHeight() - Player.HEIGHT) / 2;

                player1.setPosition(40, YPlayer1);
                player2.setPosition((getWidth() - Player.WIDTH) - 40, YPlayer2);
            }
        });

        t.start();
        addKeyListener(kl);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Linha tracejada no meio
        g.setColor(Color.WHITE);
        for (int i = 0; i < getHeight(); i += 30) {
            g.fillRect(getWidth() / 2 - 2, i, 4, 15);
        }

        // Desenha os objetos do jogo (goleiras, jogadores, bola)
        for (GEOForm gf : sprites) {
            gf.draw(g);
        }
        score.draw(g, getWidth());

        Toolkit.getDefaultToolkit().sync();
    }

    private void checkCollisions() {
        Rectangle ballBounds = ball.getBounds();
        Rectangle p1 = player1.getBounds();
        Rectangle p2 = player2.getBounds();
        Rectangle g1 = goal1.getBounds();
        Rectangle g2 = goal2.getBounds();

        if (ballBounds.intersects(g1)) {
            if (!pointScored) {
                score.SetPointsP2();
                ball.setXGrow(true);
                pointScored = true;
                int ballCenter = ballBounds.y + ballBounds.height / 2;
                int goalCenter = g1.y + g1.height / 2;
                int deltaY = ballCenter - goalCenter;
                ball.setYGrow(deltaY);
                Sound.play("point.wav");
            }
        } else if (ballBounds.intersects(g2)) {
            if (!pointScored) {
                score.SetPointsP1();
                ball.setXGrow(false);
                pointScored = true;
                int ballCenter = ballBounds.y + ballBounds.height / 2;
                int goalCenter = g2.y + g2.height / 2;
                int deltaY = ballCenter - goalCenter;
                ball.setYGrow(deltaY);
                Sound.play("point.wav"); 
            }
        } else {
            pointScored = false;
        }

        if (ballBounds.intersects(p1)) {
            ball.setXGrow(true); // Rebater para direita
            int ballCenter = ballBounds.y + ballBounds.height / 2;
            int playerCenter = p1.y + p1.height / 2;
            int deltaY = ballCenter - playerCenter;
            ball.setYGrow(deltaY);
            Sound.play("hit.wav");
        }

        if (ballBounds.intersects(p2)) {
            ball.setXGrow(false); // Rebater para esquerda
            int ballCenter = ballBounds.y + ballBounds.height / 2;
            int playerCenter = p2.y + p2.height / 2;
            int deltaY = ballCenter - playerCenter;
            ball.setYGrow(deltaY);
            Sound.play("hit.wav");
        }
    }
}
