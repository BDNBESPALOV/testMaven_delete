package sample;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class ChangeXML   {

    String folder = OpenFileChooser.FILES.replaceAll(".zip(.*)","");
    String track = OpenFileChooser.FILES.replaceAll("patch(.*)","");
    String newFile = OpenFileChooser.FILES.replaceAll("(.*)patch","patch");
    String strFunc = /*track+*/"\\"+folder+"\\XML\\funcat.xml";




    ChangeXML() throws IOException{


            InputStream skolkovo = new FileInputStream(".\\target\\classes\\skolkovo.xml"); //текст перед которым будет втавка
            InputStream funcat = new FileInputStream(strFunc); //исходный файл
            InputStream addskolkovoX = new FileInputStream(".\\target\\classes\\addskolkovo.xml"); //блок для вставки

            String theString = IOUtils.toString(skolkovo, "UTF-8");
            String addskolkovo = IOUtils.toString(addskolkovoX, "UTF-8");
            String theStringfuncat = IOUtils.toString(funcat, "Cp1251");

            System.out.println(theStringfuncat.contains(theString));
            System.out.println(theStringfuncat.indexOf("skolkovoPurchInfo"));
            System.out.println(theString);

            String a= theStringfuncat.replace(theString,addskolkovo);

            File file = new File(strFunc);
            file.delete();

            OutputStream os = new FileOutputStream(strFunc);
            os.write(a.getBytes("Cp1251"));

            skolkovo.close();
            funcat.close();
            os.close();


    }
}
