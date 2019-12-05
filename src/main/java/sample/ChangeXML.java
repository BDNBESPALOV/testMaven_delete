package sample;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class ChangeXML   {

//    String folder = OpenFileChooser.FILES.replaceAll(".zip(.*)","");
//    String track = OpenFileChooser.FILES.replaceAll("patch(.*)","");
//    String newFile = OpenFileChooser.FILES.replaceAll("(.*)patch","patch");
//    String strFunc = /*track+*/"\\"+folder+"\\XML\\funcat.xml";
//    String appobj = /*track+*/"\\"+folder+"\\XML\\module\\bft.gz\\appobj.xml";

    String folder = Controller.FILES.replaceAll(".zip(.*)","");
    String track = Controller.FILES.replaceAll("patch(.*)","");
    String newFile = Controller.FILES.replaceAll("(.*)patch","patch");
    String strFunc = /*track+*/"\\"+folder+"\\XML\\funcat.xml";
    String appobj = /*track+*/"\\"+folder+"\\XML\\module\\bft.gz\\appobj.xml";





    ChangeXML() throws IOException{


            InputStream skolkovo  = getClass().getResourceAsStream("/skolkovo.xml"); /*new FileInputStream("skolkovo.xml"); *///текст перед которым будет втавка
            InputStream funcat = new FileInputStream(strFunc); //исходный файл
            InputStream addskolkovoX = getClass().getResourceAsStream("/addskolkovo.xml");  /*new FileInputStream("addskolkovo.xml");*/ //блок для вставки

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
            addskolkovoX.close();


  /////////////////////////////////////////////////////////////

        InputStream addReportsBO  = getClass().getResourceAsStream("/addReportsBO.xml"); /*new FileInputStream("skolkovo.xml"); *///текст перед которым будет втавка
        InputStream appobjInput = new FileInputStream(appobj); //исходный файл
        InputStream firstReportAppobj = getClass().getResourceAsStream("/firstReportAppobj.xml");  /*new FileInputStream("addskolkovo.xml");*/ //блок для вставки

        String addReportsBOString = IOUtils.toString(addReportsBO, "UTF-8");
        String firstReportAppobjString = IOUtils.toString(firstReportAppobj, "UTF-8");
        String appobjString = IOUtils.toString(appobjInput, "Cp1251");

        System.out.println(appobjString.contains(firstReportAppobjString));
        System.out.println(appobjString.contains("belContractsRep"));
        System.out.println(firstReportAppobjString);

        String a2= appobjString.replace(firstReportAppobjString,addReportsBOString);

        File file2 = new File(appobj);
        file2.delete();

        OutputStream os2 = new FileOutputStream(appobj);
        os2.write(a2.getBytes("Cp1251"));

        addReportsBO.close();
        appobjInput.close();
        firstReportAppobj.close();
        os2.close();





    }
}
