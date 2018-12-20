
import DaGame.GameLauncher;
import DaGame.Window;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application  {


        public void start(Stage primaryStage) {
            String path = Main.class.getResource("music/background_music.mp3").toString();
            Media media = new Media(path);
            MediaPlayer mp = new MediaPlayer(media);
            mp.play();
            mp.play();

            System.out.println("Playing...");
        }

        public Main(){
            new Window();
        }

        public static void main(String[] args) {
            launch(args);
            new GameLauncher();
        }

    }

