import Controller.Controller;
import view.initializeBoard;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Controller controller = new Controller();
        initializeBoard game = new initializeBoard(controller);
    }
}
