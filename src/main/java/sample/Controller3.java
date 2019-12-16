package sample;

import annualUpgradeBudget.KVFO;
import annualUpgradeBudget.RplObjectForGZ;
import annualUpgradeBudget.RplobjectForFin;
import filezip.UnzipUtil;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Controller3  {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ChangeXML.class);
    public static  String FILES=null;


    final  FileChooser fileChooser = new FileChooser();
    //Преобразовать патч
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

    //Генерация RPLOBJECT
    @FXML
    TextArea textArea = new TextArea();
    @FXML
    Button bGenRplGZ = new Button();
    @FXML
    Button bGenRplFIN = new Button();
    @FXML
    Button bKVFO = new Button();



    public void buttonOpenFile(ActionEvent event) {
        configuringFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            FILES=file.getPath();
        }
        if(FILES!=null){
            button2.setDisable(false);
        }
    }
    public void setButton2Convert(ActionEvent event) throws  FileNotFoundException {

        progressBar.setProgress(0);
        progressIndicator.setProgress(0);
        statusLabel.setMinWidth(250);
        statusLabel.setTextFill(Color.BLUE);

        this.unzipTh(new UnzipUtil());

        button2.setDisable(true);

    }

    //Генерация RPLOBJECT
    public void buttonGenRplGZ(){
        new RplObjectForGZ().start(textArea.getText());
        bGenRplGZ.setDisable(true);
    }
    public void buttonGenRplFin(){
        new RplobjectForFin().start(textArea.getText());
        bGenRplFIN.setDisable(true);
    }
    public void buttonKVFO(){
        new KVFO().start(textArea.getText());
        bKVFO.setDisable(true);
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




}
