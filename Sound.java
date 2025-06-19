import javax.sound.sampled.*;
import java.io.InputStream;

public class Sound {
    public static void play(String fileName) {
        try {
            // Carrega o arquivo de som como recurso
            InputStream audioSrc = Sound.class.getResourceAsStream("resources/" + fileName);
            if (audioSrc == null) {
                System.err.println("Arquivo de som não encontrado: " + fileName);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
