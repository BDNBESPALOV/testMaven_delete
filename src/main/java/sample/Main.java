package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//       // DirectoryChoosers directoryChoosers = new DirectoryChoosers();
//        OpenFileChooser openFileChooser = new OpenFileChooser();
//        openFileChooser.start(primaryStage);
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{

      //  Parent root = FXMLLoader.load(Main.class.getResource("/UI/gridPaneXml.fxml"));
        Parent root = FXMLLoader.load(Main.class.getResource("/UI/TabPanelGZ.fxml"));

        primaryStage.setTitle("Converter patch GZ BO");
        primaryStage.setScene(new Scene(root, 300, 275));

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
