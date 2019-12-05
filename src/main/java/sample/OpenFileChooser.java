//package sample;
//
//import java.awt.Desktop;
//import java.io.*;
//import java.util.Arrays;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//
//import filezip.UnzipUtil;
//import filezip.ZipUtil;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.VBox;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
//public class OpenFileChooser extends Application {
//    public static  String FILES=null;
//
//    private Desktop desktop = Desktop.getDesktop();
//
//    final TextArea textArea = new TextArea();
//
//    @Override
//    public void start(final Stage primaryStage) throws Exception {
//
//        final FileChooser fileChooser = new FileChooser();
//        configuringFileChooser(fileChooser);
//
//
//        textArea.setMinHeight(70);
//
//        Button button1 = new Button("Open patch");
//        Button button2 = new Button("unzip");
////        Button button3 = new Button("magick");
////        Button button4 = new Button("zip");
//
//
//
//        button1.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                textArea.clear();
//                File file = fileChooser.showOpenDialog(primaryStage);
//
//                if (file != null) {
//                    FILES=file.getPath();
//                    System.out.println(file.getPath());
//                    //openFile(file);
//                    List<File> files = Arrays.asList(file);
//                    printLog(textArea, files);
//                }
//            }
//        });
//
//        button2.setOnAction(new EventHandler<ActionEvent>()  {
//
//            @Override
//            public void handle(ActionEvent event) {
//               start();
//
//                try {
//                    new ChangeXML();
//                    new ZipUtil().start();
//                } catch (IOException e) {
//                    textArea.appendText(e.toString());
//
//                }
//            }
//        });
//
////        button3.setOnAction(new EventHandler<ActionEvent>() {
////            @Override
////            public void handle(ActionEvent event) {
////                try {
////                    new ChangeXML();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        });
////        button4.setOnAction(new EventHandler<ActionEvent>() {
////
////            @Override
////            public void handle(ActionEvent event) {
////                try {
////                    new ZipUtil().start();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        });
//
//
//        VBox root = new VBox();
//
//        root.setPadding(new Insets(10));
//        root.setSpacing(5);
//
//        root.getChildren().addAll(textArea, button1,button2/*,button3,button4, buttonM*/);
//
//        Scene scene = new Scene(root, 400, 200);
//
//        primaryStage.setTitle("Convert the patch to the features of BO");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public  void printLog(String str) {
//            textArea.appendText(str + "\n" +"--- ");
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
//      //  fileChooser.setInitialDirectory(new File("D:\\E\\ff\\ttttt"));
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
//
//        File file = new File(newFolder);
//
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
//               // System.out.println(entry.getName());
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
//}
