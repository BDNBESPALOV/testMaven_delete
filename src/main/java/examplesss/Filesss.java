package examplesss;

import java.io.*;
import java.util.ArrayList;

public class Filesss {
//    public   void copyFileUsingStream(File source, File dest) throws IOException {
//        InputStream is = null;
//        OutputStream os = null;
//        try {
//            is = new FileInputStream(source);
//            os = new FileOutputStream(dest);
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = is.read(buffer)) > 0) {
//                os.write(buffer, 0, length);
//            }
//        } finally {
//            is.close();
//            os.close();
//        }
//
//    }


    public static void main(String [] args) throws IOException {


//        ArrayList arrayList = new Filesss().arrayInputSt(Filesss.class.getResourceAsStream("/module/bft.gz/report/"));
//        for(Object s: arrayList){
//            new Filesss().copyFileUsingStreams(Filesss.class.getResourceAsStream(
//                    "/module/bft.gz/report/"+s),
//                    new File("E:\\test2\\"+s));
//        }

       new  Filesss().insertFile("/module/bft.gz/report/","E:/test2/");


    }

    public void insertFile(String path, String newPath) throws IOException{

        ArrayList arrayList = new Filesss().arrayInputSt(Filesss.class.getResourceAsStream(path));
        for(Object s: arrayList){
            new Filesss().copyFileUsingStreams(Filesss.class.getResourceAsStream(
                    path+s),
                    new File(newPath+s));
        }

    }


    public void copyFileUsingStreams(InputStream is, File dest) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
            os.close();
        }

    }

    public ArrayList<String> arrayInputSt (InputStream in) throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        String str="";
        String str_="";
        Reader inputStreamReader = new InputStreamReader( in, "UTF-8");
        int data = inputStreamReader.read();
        while(data != -1){
            char theChar = (char) data;
            data = inputStreamReader.read();
            if(!"\n".equals(""+theChar)){
                str_+=theChar;
            }
            if ("\n".equals(""+theChar )){
                arrayList.add(str_);
                str="";
                str_="";
            }
            str+=theChar;
        }
        inputStreamReader.close();
        return arrayList;
    }


}
