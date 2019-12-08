package filezip;

import javafx.concurrent.Task;
import sample.Controller3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil extends Task<List<File>> {

    String folder = Controller3.FILES.replaceAll(".zip(.*)","");
    File file = new File(folder);
    File[] files = file.listFiles();
    int count = countPro(file); /*files.length*/;
    int i = 0;
    private static List<File> copied = new ArrayList<>();

     public  int countPro(File dir){
         for (File file : dir.listFiles()) {
             i++;
             if (file.isDirectory()) {
                 countPro(file);
             }

         }
         return i;
    }

    @Override
    protected List<File> call() throws Exception {
//        String folder = OpenFileChooser.FILES.replaceAll(".zip(.*)","");
//        String folder2 = OpenFileChooser.FILES.replaceAll("(.*)patch","patch").replaceAll(".zip(.*)","");
//        String track = OpenFileChooser.FILES.replaceAll("patch(.*)","");
//        String newFile = OpenFileChooser.FILES.replaceAll("(.*)patch","patch");
        String folder2 = Controller3.FILES.replaceAll("(.*)patch","patch").replaceAll(".zip(.*)","");
        String track = Controller3.FILES.replaceAll("patch(.*)","");
        String newFile = Controller3.FILES.replaceAll("(.*)patch","patch");

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream( track+"new_"+newFile));

        doZip(file, out);

        out.close();
        return copied;
    }

    private  void doZip(File dir, ZipOutputStream out) throws Exception {



        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                this.copy(file);
                copied.add(file);
            }
            i++;
            System.out.println("i = "+i+" count = "+count+"  "+file.getName());
            this.updateProgress(i, count);
            if (file.isDirectory())
                doZip(file, out);
            else {
                out.putNextEntry(new ZipEntry(file.getPath()));
                write(new FileInputStream(file), out);
            }
        }
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        in.close();
    }
    private void copy(File file) throws Exception {
        this.updateMessage("Copying: " + file.getAbsolutePath());
       // Thread.sleep(500);
    }


}