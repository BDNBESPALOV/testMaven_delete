package filezip;

import javafx.concurrent.Task;
import org.slf4j.Logger;
import main.ChangeXML;
import main.Controller3;
//import sample.ImportRepo;


import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class UnzipUtil  extends Task<List<File>> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UnzipUtil.class);
    //упаковка полным путем
    private String folderZ = Controller3.FILES.replaceAll(".zip(.*)","");
    //упаковка отнасительным путем
    private String folder2 = Controller3.FILES.replaceAll("(.*)patch","patch").replaceAll(".zip(.*)","");

    private File fileZ = new File(folder2);
    private String track = Controller3.FILES.replaceAll("patch(.*)","");
    private String newFile = Controller3.FILES.replaceAll("(.*)patch","patch");
    private ZipOutputStream outs = new ZipOutputStream(new FileOutputStream( track+"new_"+newFile));

    private String folder = Controller3.FILES.replaceAll("(.*)patch","patch").replaceAll(".zip(.*)","");
    private String cutZip = Controller3.FILES.replaceAll(".zip(.*)","");
    private String newFolder = cutZip+"\\"+folder;

    private File file = new File(newFolder);
    private  int count = 0;
    volatile private int i = 0;
    private  List<File> copied = new ArrayList<>();

    public UnzipUtil() throws FileNotFoundException {
    }


    protected List<File> call() throws Exception {

         try {
             log.info("folderZ "+folderZ+"\n"+"folder2 "+folder2);
          ZipFile zip = new ZipFile(Controller3.FILES);
            Enumeration entries = zip.entries();
             count =  zip.size()*2;
              log.info("zip.size() = " + zip.size());
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                log.info(/*"i = " + i + " count = " + count +*/ i+"UnzipUtil  " + entry.getName());
                if (entry.isDirectory()) {
                    new File(file.getParent(), entry.getName()).mkdirs();
                } else {
                    write2(zip.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(
                                    new File(file.getParent(), entry.getName()))));
                }

                this.copy(new File(entry.getName()));
                copied.add(new File(entry.getName()));
                i++;
                this.updateProgress(i, count);


            }
             zip.close();
             new ChangeXML();

        } catch (IOException e) {
            e.printStackTrace();
        }

       doZip(fileZ, outs);
       outs.close();



return  copied;

    }

    private  void doZip(File dir, ZipOutputStream outv) throws Exception {
        log.info("dir.listFiles().length = "+dir.listFiles().length);
        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                this.copy(f);
                copied.add(f);
            }
            i++;
            log.info("i = "+i+" count = "+count+"  "+f.getName());
            this.updateProgress(i, count);
            if (f.isDirectory())
                doZip(f, outv);
            else {
                outv.putNextEntry(new ZipEntry(f.getPath()));
                write(new FileInputStream(f), outv);
            }
        }
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
       // out.close();
        in.close();
    }
    private static void write2(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
         out.close();
        in.close();
    }
        private  void copy(File file) throws Exception {
            this.updateMessage( file.getAbsolutePath());
               }

}