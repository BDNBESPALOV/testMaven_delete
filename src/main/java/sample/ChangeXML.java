package sample;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

public class ChangeXML   {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ChangeXML.class);

//    String folder = OpenFileChooser.FILES.replaceAll(".zip(.*)","");
//    String track = OpenFileChooser.FILES.replaceAll("patch(.*)","");
//    String newFile = OpenFileChooser.FILES.replaceAll("(.*)patch","patch");
//    String strFunc = /*track+*/"\\"+folder+"\\XML\\funcat.xml";
//    String appobj = /*track+*/"\\"+folder+"\\XML\\module\\bft.gz\\appobj.xml";



    String folder = Controller3.FILES.replaceAll(".zip(.*)","");
    String track = Controller3.FILES.replaceAll("patch(.*)","");
    String newFile = Controller3.FILES.replaceAll("(.*)patch","patch");
    String strFunc = /*track+*/"\\"+folder+"\\XML\\funcat.xml";
    String appobj = /*track+*/"\\"+folder+"\\XML\\module\\bft.gz\\appobj.xml";

    public ChangeXML() throws IOException{


            InputStream skolkovo  = getClass().getResourceAsStream("/skolkovo.xml"); /*new FileInputStream("skolkovo.xml"); *///текст перед которым будет втавка
            InputStream funcat = new FileInputStream(strFunc); //исходный файл
            InputStream addskolkovoX = getClass().getResourceAsStream("/addskolkovo.xml");  /*new FileInputStream("addskolkovo.xml");*/ //блок для вставки

            String theString = IOUtils.toString(skolkovo, "UTF-8");
            String addskolkovo = IOUtils.toString(addskolkovoX, "UTF-8");
            String theStringfuncat = IOUtils.toString(funcat, "Cp1251");

            log.info(String.valueOf(theStringfuncat.contains(theString)));
            log.info(String.valueOf(theStringfuncat.indexOf("skolkovoPurchInfo")));
            log.info(theString);

            String a= theStringfuncat.replace(theString,addskolkovo);

            File file = new File(strFunc);
            file.delete();

            OutputStream os = new FileOutputStream(strFunc);
            os.write(a.getBytes("Cp1251"));

           OutputStream test = new FileOutputStream("\\"+folder+"\\XML\\TESTSSSSSSSSSSSSSS.xml");
           test.write(a.getBytes("Cp1251"));
           test.close();

            skolkovo.close();
            funcat.close();
            addskolkovoX.close();
            os.close();



  /////////////////////////////////////////////////////////////

        InputStream addReportsBO  = getClass().getResourceAsStream("/addReportsBO.xml"); /*new FileInputStream("skolkovo.xml"); *///текст перед которым будет втавка
        InputStream appobjInput = new FileInputStream(appobj); //исходный файл
        InputStream firstReportAppobj = getClass().getResourceAsStream("/firstReportAppobj.xml");  /*new FileInputStream("addskolkovo.xml");*/ //блок для вставки

        String addReportsBOString = IOUtils.toString(addReportsBO, "UTF-8");
        String firstReportAppobjString = IOUtils.toString(firstReportAppobj, "UTF-8");
        String appobjString = IOUtils.toString(appobjInput, "Cp1251");

        log.info(String.valueOf(appobjString.contains(firstReportAppobjString)));
        log.info(String.valueOf(appobjString.contains("belContractsRep")));
        log.info(firstReportAppobjString);

        String a2= appobjString.replace(firstReportAppobjString,addReportsBOString);

        File file2 = new File(appobj);
        file2.delete();

        OutputStream os2 = new FileOutputStream(appobj);
        os2.write(a2.getBytes("Cp1251"));



        addReportsBO.close();
        appobjInput.close();
        firstReportAppobj.close();
        os2.close();

        try {
            log.info("ChangeXML start ImportRepo!!!!!!!!!");
            new ImportRepo().startImportFile();
        } catch (IOException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }

    }
}
