//package sample;
//
//import filezip.ZipUtil;
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
//
//import java.io.*;
//import java.util.Arrays;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//
//public class Controller {
//    public static  String FILES=null;
//    final TextArea textArea = new TextArea();
//
//    final FileChooser fileChooser = new FileChooser();
//
//    Button button1 = new Button("Open patch");
//    Button button2 = new Button("Convert");
//
//
//    public void buttonOpenFile(ActionEvent event) {
//        textArea.clear();
//        File file = fileChooser.showOpenDialog(new Stage());
//
//        if (file != null) {
//            FILES=file.getPath();
//            System.out.println(file.getPath());
//
//            List<File> files = Arrays.asList(file);
//               printLog(textArea, files);
//        }
//    }
//
//    public void setButton2Convert(ActionEvent event) {
//            start();
//
//            try {
//                new ChangeXML();
//                new ZipUtil().start();
//            } catch (IOException e) {
//                textArea.appendText(e.toString());
//
//            }
//        }
//
//    ///////////******************////////////////
//
//    public  void printLog(String str) {
//        textArea.appendText(str + "\n" +"--- ");
//    }
//
//    private void printLog(TextArea textArea, List<File> files) {
//        if (files == null || files.isEmpty()) {
//            return;
//        }
//        for (File file : files) {
//            textArea.appendText(file.getAbsolutePath() + "\n");
//        }
//    }
//
////    private void openFile(File file) {
////        try {
////            this.desktop.open(file);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
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
//
//    public  void start() {
//        String folder = FILES.replaceAll("(.*)patch","patch").replaceAll(".zip(.*)","");
//        String cutZip = FILES.replaceAll(".zip(.*)","");
//        String newFolder = cutZip+"\\"+folder;
//
//        File file = new File(newFolder);
//
//        try {
//
//            ZipFile zip = new ZipFile(FILES);
//
//            Enumeration entries = zip.entries();
//
//            while (entries.hasMoreElements()) {
//                ZipEntry entry = (ZipEntry) entries.nextElement();
//                textArea.appendText(entry.getName() + "\n");
//                // System.out.println(entry.getName());
//
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
//    }
//
//    private static void write(InputStream in, OutputStream out) throws IOException {
//        byte[] buffer = new byte[102400];
//        int len;
//        while ((len = in.read(buffer)) >= 0)
//            out.write(buffer, 0, len);
//        out.close();
//        in.close();
//    }
//
//
//
//
//
//
//
//}
