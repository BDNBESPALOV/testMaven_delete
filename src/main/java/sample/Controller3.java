package sample;

import filezip.UnzipUtil;

import filezip.ZipUtil;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Controller3 {
    public static  String FILES=null;
    final TextArea textArea = new TextArea();

    final  FileChooser fileChooser = new FileChooser();

    Button button1 = new Button("Open patch");
    Button button2 = new Button("Convert");
    @FXML
    Label statusLabel = new Label();
    @FXML
    ProgressBar progressBar = new ProgressBar(0);
    @FXML
    ProgressIndicator progressIndicator = new ProgressIndicator(0);


   ZipUtil zipUtil;
    UnzipUtil unzipUtil;


    public void buttonOpenFile(ActionEvent event) {
        textArea.clear();
        File file = fileChooser.showOpenDialog(new Stage());

        configuringFileChooser(new FileChooser());
        if (file != null) {
            FILES=file.getPath();
            System.out.println(file.getPath());

            List<File> files = Arrays.asList(file);
            printLog(textArea, files);
        }
    }

    public void setButton2Convert(ActionEvent event) throws InterruptedException, FileNotFoundException {

        progressBar.setProgress(0);
        progressIndicator.setProgress(0);
        statusLabel.setMinWidth(250);
        statusLabel.setTextFill(Color.BLUE);

        this.unzipTh(new UnzipUtil());
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
                                statusLabel.setText("Copied: " + copied.size());
                            }
                        });


        new Thread(uq).start();



    }
//    private void zipTh(){
//        progressBar.progressProperty().unbind();
//     //   progressBar.progressProperty().bind(zipUtil.progressProperty());
//        progressIndicator.progressProperty().unbind();
//     //   progressIndicator.progressProperty().bind(zipUtil.progressProperty());
//        statusLabel.textProperty().unbind();
//      //  statusLabel.textProperty().bind(zipUtil.messageProperty());
//        zipUtil.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
//                new EventHandler<WorkerStateEvent>() {
//
//                    @Override
//                    public void handle(WorkerStateEvent t) {
//                        List<File> copied = zipUtil.getValue();
//                        statusLabel.textProperty().unbind();
//                        statusLabel.setText("Copied: " + copied.size());
//                    }
//                });
//    //      new Thread(zipUtil).start();
//
//    }

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


    private void configuringFileChooser(FileChooser fileChooser) {
        // Set title for FileChooser
        fileChooser.setTitle("Choose a fresh patch");

        // Set Initial Directory
         fileChooser.setInitialDirectory(new File("D:\\E\\ff\\ttttt"));

        // Add Extension Filters
        fileChooser.getExtensionFilters().addAll(//
                new FileChooser.ExtensionFilter("All Files", "*.*"), //
                new FileChooser.ExtensionFilter("ZIP", "*.zip"));
    }


/////////////////////////////////////////////////////////////////////










}
