package filezip;

import javafx.concurrent.Task;
import sample.ChangeXML;
import sample.Controller3;


import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class UnzipUtil  extends Task<List<File>> {
    String folderZ = Controller3.FILES.replaceAll(".zip(.*)","");
    File fileZ = new File(folderZ);
    String track = Controller3.FILES.replaceAll("patch(.*)","");
    String newFile = Controller3.FILES.replaceAll("(.*)patch","patch");
    ZipOutputStream outs = new ZipOutputStream(new FileOutputStream( track+"new_"+newFile));




    String folder = Controller3.FILES.replaceAll("(.*)patch","patch").replaceAll(".zip(.*)","");
    String cutZip = Controller3.FILES.replaceAll(".zip(.*)","");
    String newFolder = cutZip+"\\"+folder;

    File file = new File(newFolder);
    File[] files = file.listFiles();
    int count = 0; /*files.length*/;
   volatile int i = 1;
    private  List<File> copied = new ArrayList<>();

    public UnzipUtil() throws FileNotFoundException {
    }

//    public  int countPro(File dir)  {
//        int counts=0;
//        try {
//            ZipFile zip = new ZipFile(dir);
//            Enumeration entries = zip.entries();
//
//            while (entries.hasMoreElements()) {
//                count++;
//            }
//            zip.close();
//        }catch (IOException e){
//
//        }
//
//            return count;
//    }

   protected List<File> call() throws Exception {
       String threadName = Thread.currentThread().getName();
       System.out.println("Hello " + threadName);
         try {
          ZipFile zip = new ZipFile(Controller3.FILES);
            Enumeration entries = zip.entries();
             count =  zip.size()*2;
              System.out.println("zip.size() = " + zip.size());
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                System.out.println(/*"i = " + i + " count = " + count +*/ i+"UnzipUtil  " + entry.getName());
                if (entry.isDirectory()) {
                    new File(file.getParent(), entry.getName()).mkdirs();
                } else {
                    write(zip.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(
                                    new File(file.getParent(), entry.getName()))));
                }

                this.copy(new File(entry.getName()));
                copied.add(new File(entry.getName()));
                this.updateProgress(i, count);
                i++;

            }
             zip.close();
             new ChangeXML();



           //  outs.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


//       List<File> zipCop =  new ZipUtil().call();
//       for(File f:zipCop){
//           copied.add(f);
//           this.copy(f);
//           this.updateProgress(i, count);
//           i++;
//       }

       doZip(fileZ, outs);
       outs.close();
return  copied;

    }

    private  void doZip(File dir, ZipOutputStream outv) throws Exception {
        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                this.copy(f);
                copied.add(f);
            }
            i++;
            System.out.println("i = "+i+" count = "+count+"  "+f.getName());
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
        out.close();
        in.close();
    }
        private  void copy(File file) throws Exception {
            this.updateMessage("Copying: " + file.getAbsolutePath());
          //   Thread.sleep(500);
         //   System.out.println(Thread.activeCount());

        }
}