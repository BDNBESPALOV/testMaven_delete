package examplesss;

import java.io.*;

public class Filesss {
    private  void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }

    }

    private String reportDir(String str){
    //    String skolkovo  = getClass().getResource();
        return str;
    }
    public static void main(String [] args) throws IOException {
        File file1 = new File("");
        File file2 = new File("E:/test2/");

        //file2.getPath();
        for (File f : file1.listFiles()) {
           // System.out.println(f+"   "+file2.getPath()+f.getName());
           new Filesss().copyFileUsingStream(f,new File(file2.getPath()+"/"+f.getName()));
        }




    }
}
