package sample;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class ChangeXML {

    ChangeXML(){
        try{

            InputStream skolkovo = new FileInputStream("E:/skolkovo.xml");
            InputStream funcat = new FileInputStream("E:/funcat.xml");
            InputStream addskolkovoX = new FileInputStream("E:/addskolkovo.xml");

            String theString = IOUtils.toString(skolkovo, "UTF-8");
            String addskolkovo = IOUtils.toString(addskolkovoX, "UTF-8");
            String theStringfuncat = IOUtils.toString(funcat, "Cp1251");

            System.out.println(theStringfuncat.contains(theString));
            System.out.println(theStringfuncat.indexOf("skolkovoPurchInfo"));
            System.out.println(theString);

            String a= theStringfuncat.replace(theString,addskolkovo);

            OutputStream os = new FileOutputStream("E:/ff/funcat.xml");
            os.write(a.getBytes("Cp1251"));

            skolkovo.close();
            funcat.close();
            os.close();

        } catch (IOException e){

        }
    }
}
