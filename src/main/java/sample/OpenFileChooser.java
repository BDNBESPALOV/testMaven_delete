package sample;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import filezip.UnzipUtil;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class OpenFileChooser extends Application {
    public static  String FILES=null;

    private Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(final Stage primaryStage) throws Exception {

        final FileChooser fileChooser = new FileChooser();
        configuringFileChooser(fileChooser);

        final TextArea textArea = new TextArea();
        textArea.setMinHeight(70);

        Button button1 = new Button("Открыть свеженький патч");
        Button button2 = new Button("Выполнить");



        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
                File file = fileChooser.showOpenDialog(primaryStage);

                if (file != null) {
                    FILES=file.getPath();
                    System.out.println(file.getPath());
                    //openFile(file);
                    List<File> files = Arrays.asList(file);
                    printLog(textArea, files);
                }
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               new UnzipUtil().start();
            }
        });


 //       Button buttonM = new Button("Select Multi Files");
//        buttonM.setOnAction(new EventHandler<ActionEvent>() {
////
////            @Override
////            public void handle(ActionEvent event) {
////                textArea.clear();
////                List<File> files = fileChooser.showOpenMultipleDialog(primaryStage);
////
////                printLog(textArea, files);
////            }
////        });

        VBox root = new VBox();

        root.setPadding(new Insets(10));
        root.setSpacing(5);

        root.getChildren().addAll(textArea, button1,button2/*, buttonM*/);

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Преобразование патча под особенности БО");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void printLog(TextArea textArea, List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(file.getAbsolutePath() + "\n");
        }
    }

//    private void openFile(File file) {
//        try {
//            this.desktop.open(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void configuringFileChooser(FileChooser fileChooser) {
        // Set title for FileChooser
        fileChooser.setTitle("Выбери свеженький патч");

        // Set Initial Directory
        fileChooser.setInitialDirectory(new File("D:\\E\\ff\\ttttt"));

        // Add Extension Filters
        fileChooser.getExtensionFilters().addAll(//
                new FileChooser.ExtensionFilter("All Files", "*.*"), //
                new FileChooser.ExtensionFilter("ZIP", "*.zip"));
    }



}
