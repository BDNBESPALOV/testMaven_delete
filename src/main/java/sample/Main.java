package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
       // DirectoryChoosers directoryChoosers = new DirectoryChoosers();
//        OpenFileChooser openFileChooser = new OpenFileChooser();
//        openFileChooser.start(primaryStage);
        new ZipI();
       // new ZipO();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
