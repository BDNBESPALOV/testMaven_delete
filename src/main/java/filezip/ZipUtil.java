package filezip;

import sample.OpenFileChooser;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public void start() throws IOException {
        String folder = OpenFileChooser.FILES.replaceAll(".zip(.*)","");
        String folder2 = OpenFileChooser.FILES.replaceAll("(.*)patch","patch").replaceAll(".zip(.*)","");
        String track = OpenFileChooser.FILES.replaceAll("patch(.*)","");
        String newFile = OpenFileChooser.FILES.replaceAll("(.*)patch","patch");

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream( track+"new_"+newFile));


        File file = new File(folder2);

        doZip(file, out);

        out.close();
    }

    private static void doZip(File dir, ZipOutputStream out) throws IOException {
        for (File f : dir.listFiles()) {
            System.out.println(f.getPath()+" path "+f.getName());
            if (f.isDirectory())
                doZip(f, out);
            else {
                out.putNextEntry(new ZipEntry(f.getPath()));
                write(new FileInputStream(f), out);
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
}