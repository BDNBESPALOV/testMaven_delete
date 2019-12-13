package sample;

import filezip.UnzipUtil;


import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Controller3  {
    public static  String FILES=null;
    final TextArea textArea = new TextArea();

    final  FileChooser fileChooser = new FileChooser();
    @FXML
    Button button1 = new Button();
    @FXML
    Button button2 = new Button();
    @FXML
    Label statusLabel = new Label();
    @FXML
    ProgressBar progressBar = new ProgressBar(0);
    @FXML
    ProgressIndicator progressIndicator = new ProgressIndicator(0);

    final  DropShadow shadow = new DropShadow();
    final MotionBlur motionBlur = new MotionBlur();




    public void buttonOpenFile(ActionEvent event) {
      // textArea.clear();
        configuringFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            FILES=file.getPath();
            System.out.println(file.getPath());

//            List<File> files = Arrays.asList(file);
//            printLog(textArea, files);
        }
        if(FILES!=null){
            button2.setDisable(false);
        }


    }

    public void setButton2Convert(ActionEvent event) throws InterruptedException, FileNotFoundException {

        progressBar.setProgress(0);
        progressIndicator.setProgress(0);
        statusLabel.setMinWidth(250);
        statusLabel.setTextFill(Color.BLUE);

        this.unzipTh(new UnzipUtil());

        button2.setDisable(true);

    }



    private void unzipTh(final UnzipUtil uq) {
                progressBar.progressProperty().unbind();
                progressBar.progressProperty().bind(uq.progressProperty());
                progressIndicator.progressProperty().unbind();
                progressIndicator.progressProperty().bind(uq.progressProperty());
                statusLabel.textProperty().unbind();
                statusLabel.textProperty().bind(uq.messageProperty());
                uq.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
                        new EventHandler<WorkerStateEvent>() {
                            @Override
                            public void handle(WorkerStateEvent t) {
                                List<File> copied = uq.getValue();
                                statusLabel.textProperty().unbind();
                                statusLabel.setText("");
                            }
                        });
        new Thread(uq).start();
    }
    private void configuringFileChooser(FileChooser fileChooser) {
        // Set title for FileChooser
        fileChooser.setTitle("Choose a fresh patch");

        // Set Initial Directory
        fileChooser.setInitialDirectory(new File("./"));

        // Add Extension Filters
        fileChooser.getExtensionFilters().addAll(//
              //  new FileChooser.ExtensionFilter("All Files", "*.*"), //
                new FileChooser.ExtensionFilter("ZIP", "patch_*"));
    }


    ///////////******************////////////////

    public  void printLog(String str) {
        textArea.appendText(str + "\n" +"--- ");
    }

    private void printLog(TextArea textArea, List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(file.getAbsolutePath() + "\n");
        }
    }







/////////////////////////////////////////////////////////////////////

}
