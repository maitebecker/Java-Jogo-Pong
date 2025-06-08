import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PaintArea extends JPanel {
    private static final int TIMER_DELAY = 15;
    private static final int MOVE_AMOUNT = 30;
    private Goal goal1, goal2;
    private Ball ball;
    private Player player1, player2;

    int YPlayer1, YPlayer2;
    Score score;

    KeyListener kl = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent ke) {

        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_W && YPlayer1 > 0) {
                YPlayer1 -= MOVE_AMOUNT;
            }
            if (ke.getKeyCode() == KeyEvent.VK_S && YPlayer1 < (getHeight() - Player.HEIGHT)) {
                YPlayer1 += MOVE_AMOUNT;
            }

            if (ke.getKeyCode() == KeyEvent.VK_UP && YPlayer2 > 0) {
                YPlayer2 -= MOVE_AMOUNT;
            }
            if (ke.getKeyCode() == KeyEvent.VK_DOWN && YPlayer2 < (getHeight() - Player.HEIGHT)) {
                YPlayer2 += MOVE_AMOUNT;
            }

            player1.setPosition(40, YPlayer1);
            player2.setPosition((getWidth() - Player.WIDTH) - 40, YPlayer2);
        }

        @Override
        public void keyReleased(KeyEvent ke) {

        }
    };

    Timer t = new Timer(TIMER_DELAY, (ActionEvent e) -> {
        ball.move(getWidth(), getHeight());
        checkCollisions();// Verifica colisão
        repaint();
    });

    public PaintArea() {
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();

        ball = new Ball(80, 60, 30);
        goal1 = new Goal(0, 0);
        goal2 = new Goal(0, 0);
        player1 = new Player(0, 0);
        player2 = new Player(0, 0);
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

        // desenhar componentes
        ball.draw(g);
        goal1.draw(g);
        goal2.draw(g);
        player1.draw(g);
        player2.draw(g);
        score.draw(g, getWidth());

        Toolkit.getDefaultToolkit().sync();
    }

    private void checkCollisions() {
        if (ball.getBounds().intersects(goal1.getBounds())) {
            score.SetPointsP2();
            ball.setXGrow(true); // Rebater para a direita
            ball.increaseSpeed();
        }

        if (ball.getBounds().intersects(goal2.getBounds())) {
            score.SetPointsP1();
            ball.setXGrow(false); // Rebater para a esquerda
            ball.increaseSpeed();
        }

        if (ball.getBounds().intersects(player1.getBounds())) {
            ball.setXGrow(true); // Rebater para a direita
        }

        if (ball.getBounds().intersects(player2.getBounds())) {
            ball.setXGrow(false); // Rebater para a esquerda
        }
    }
}
