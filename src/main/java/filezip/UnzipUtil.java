package filezip;

import javafx.concurrent.Task;
import sample.Controller3;


import java.io.File;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipUtil  extends Task<List<File>> {
    String folder = Controller3.FILES.replaceAll("(.*)patch","patch").replaceAll(".zip(.*)","");
    String cutZip = Controller3.FILES.replaceAll(".zip(.*)","");
    String newFolder = cutZip+"\\"+folder;

    File file = new File(newFolder);
    File[] files = file.listFiles();
    int  count =  countPro(file); /*files.length*/;
    int i = 0;
    private static List<File> copied = new ArrayList<>();

    public  int countPro(File dir)  {
        int count=0;
        try {
            ZipFile zip = new ZipFile(dir);
            Enumeration entries = zip.entries();

            while (entries.hasMoreElements()) {
                count++;
            }
            zip.close();
        }catch (IOException e){

        }

            return count;
    }

   protected List<File> call() throws Exception {
 //   public void start(){

          try {

            ZipFile zip = new ZipFile(Controller3.FILES);

            Enumeration entries = zip.entries();
              count =  zip.size()*2;
              System.out.println("zip.size() = " + zip.size());
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                       this.copy(new File(entry.getName()));
                       copied.add(new File(entry.getName()));
                   this.updateProgress(i, count);
                i++;
                System.out.println(/*"i = " + i + " count = " + count +*/ "UnzipUtil  " + entry.getName());

                if (entry.isDirectory()) {
                    new File(file.getParent(), entry.getName()).mkdirs();
                } else {
                    write(zip.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(
                                    new File(file.getParent(), entry.getName()))));
                }
            }

            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

return  copied;
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        out.close();
        in.close();
    }
        private void copy(File file) throws Exception {
            this.updateMessage("Copying: " + file.getAbsolutePath());
            // Thread.sleep(500);

        }
}