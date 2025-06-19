import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Random;

public class Score {
    private int pointsPlayer1 = 0;
    private int pointsPlayer2 = 0;
    private Color textColor = Color.WHITE;

    public void SetPointsP1(){
        pointsPlayer1++;
        setColor();
    }

    public void SetPointsP2(){
        pointsPlayer2++;
        setColor();
    }

    private void setColor(){
        Random rand = new Random();
        textColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    public void draw(Graphics g, int panelWidth){
        g.setFont(new Font("SansSerif", Font.BOLD, 40));
        g.setColor(textColor);

        String scoreP1 = String.format("%02d", pointsPlayer1);
        String scoreP2 = String.format("%02d", pointsPlayer2);
        String scoreText = scoreP1 + "  " + scoreP2;

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(scoreText);
        int x = (panelWidth - textWidth) / 2;
        int y = 50;

        g.drawString(scoreText, x, y);
    }
}
