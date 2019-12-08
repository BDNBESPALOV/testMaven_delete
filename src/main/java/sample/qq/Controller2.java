//package sample.qq;
//import filezip.ZipUtil;
//import javafx.concurrent.Task;
//import javafx.concurrent.WorkerStateEvent;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.control.TextArea;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import sample.ChangeXML;
//
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//
//public class Controller2 extends Task<List<File>> {
//    List<File> copied = new ArrayList<>();
//
//    public static  String FILES=null;
//    final TextArea textArea = new TextArea();
//
//    final FileChooser fileChooser = new FileChooser();
//
//    Button button1 = new Button("Open patch");
//    Button button2 = new Button("Convert");
//
//    @FXML
//    ProgressBar progressBar = new ProgressBar(0);
//    @FXML
//    final Label label = new Label();
//
//
//
//
//
//    private void copy(File file) throws Exception {
//        this.updateMessage("Copying: " + file.getAbsolutePath());
//        Thread.sleep(500);
//    }
//
//    public void buttonOpenFile(ActionEvent event) throws Exception {
//
//        textArea.clear();
//        File file = fileChooser.showOpenDialog(new Stage());
//
//        if (file != null) {
//            FILES=file.getPath();
//            System.out.println(file.getPath());
//            List<File> files = Arrays.asList(file);
//            printLog(textArea, files);
//        }
//    }
//
//    public void setButton2Convert(ActionEvent event) throws Exception {
//
//
//        call();
//
//
//        //startButton.setDisable(true);
//        progressBar.setProgress(0);
//        //   progressIndicator.setProgress(0);
//        //cancelButton.setDisable(false);
//
//        // Create a Task.
//       // copyTask = new CopyTask();
//
//        // Unbind progress property
//        progressBar.progressProperty().unbind();
//
//        // Bind progress property
//        progressBar.progressProperty().bind(this.progressProperty());
//
//        // Hủy bỏ kết nối thuộc tính progress
//        //   progressIndicator.progressProperty().unbind();
//
//        // Bind progress property.
//        //   progressIndicator.progressProperty().bind(copyTask.progressProperty());
//
//        // Unbind text property for Label.
//       // statusLabel.textProperty().unbind();
//
//        // Bind the text property of Label
//        // with message property of Task
//       // statusLabel.textProperty().bind(copyTask.messageProperty());
//
//        // When completed tasks
//        this.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
//                new EventHandler<WorkerStateEvent>() {
//
//                    @Override
//                    public void handle(WorkerStateEvent t) {
//                        List<File> copieds = copied;
////                        statusLabel.textProperty().unbind();
////                        statusLabel.setText("Copied: " + copieds.size());
//                    }
//                });
//
//        // Start the Task.
//        new Thread(this).start();
//
//        try {
//            new ChangeXML();
//            new ZipUtil();
//        } catch (IOException e) {
//            textArea.appendText(e.toString());
//        }
//    }
//
//    ///////////******************////////////////
//
//    public  void printLog(String str) {
//        textArea.appendText(str + "\n" +"--- ");
//    }
//
//    private void printLog(TextArea textArea, List<File> files) throws Exception {
//      //  int count = files.size();
//
//        if (files == null || files.isEmpty()) {
//            return;
//        }
//
//   //     int i = 0;
//        for (File file : files) {
////            this.copy(file);
////            copied.add(file);
//            textArea.appendText(file.getAbsolutePath() + "\n");
//        }
//     //   this.updateProgress(i, count);
//    }
//
//    private void configuringFileChooser(FileChooser fileChooser) {
//        // Set title for FileChooser
//        fileChooser.setTitle("Choose a fresh patch");
//
//        // Set Initial Directory
//        //  fileChooser.setInitialDirectory(new File("D:\\E\\ff\\ttttt"));
//
//        // Add Extension Filters
//        fileChooser.getExtensionFilters().addAll(//
//                new FileChooser.ExtensionFilter("All Files", "*.*"), //
//                new FileChooser.ExtensionFilter("ZIP", "*.zip"));
//    }
//
//
///////////////////////////////////////////////////////////////////////
//@Override
//protected List<File> call() throws Exception {
//        String folder = FILES.replaceAll("(.*)patch","patch").replaceAll(".zip(.*)","");
//        String cutZip = FILES.replaceAll(".zip(.*)","");
//        String newFolder = cutZip+"\\"+folder;
//
//
//        File file = new File("C:\\Users\\userBDN\\Videos\\new_patch_1.39.0.282_p3_20191120");
//
//
//        try {
//
//            ZipFile zip = new ZipFile(FILES);
//
//            Enumeration entries = zip.entries();
//            /*CCCCCCCCCCCCCCCCCCCCCCCCCCCC*/
//            File[] files = file.listFiles();
//            int count = files.length;
//            int i = 0;
//            /*CCCCCCCCCCCCCCCCCCCCCCCCCCCC*/
//
//            while (entries.hasMoreElements()) {
//                if (file.isFile()) {
//                    this.copy(file);
//                    copied.add(file);
//                }
//                i++;
//                this.updateProgress(i, count);
//
//
//                ZipEntry entry = (ZipEntry) entries.nextElement();
//                textArea.appendText(entry.getName() + "\n");
//                if (entry.isDirectory()) {
//                    new File(file.getParent(), entry.getName()).mkdirs();
//                } else {
//                    write(zip.getInputStream(entry),
//                            new BufferedOutputStream(new FileOutputStream(
//                                    new File(file.getParent(), entry.getName()))));
//                }
//            }
//
//            zip.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return copied;
//    }
//
//    private static void write(InputStream in, OutputStream out) throws IOException {
//        byte[] buffer = new byte[1024];
//        int len;
//        while ((len = in.read(buffer)) >= 0)
//            out.write(buffer, 0, len);
//        out.close();
//        in.close();
//    }
//
//
//
//}
//
