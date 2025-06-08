import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Score {
    private int pointsPlayer1 = 0;
    private int pointsPlayer2 = 0;

    public void SetPointsP1(){
        pointsPlayer1++;
    }

    public void SetPointsP2(){
        pointsPlayer2++;
    }

    public void draw(Graphics g, int panelWidth){
        g.setFont(new Font("SansSerif", Font.BOLD, 40));
        g.setColor(Color. WHITE);

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
