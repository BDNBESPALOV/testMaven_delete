package annualUpgradeBudget;


import org.slf4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;


public class RplObjectForGZ {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RplObjectForGZ.class);
    static  ArrayList<DOMSource> s = new ArrayList<DOMSource>();
    private static final String CLIENT_ID = "0";
    private static final String MASTER_ID = "600";
    private static final String action = "synchronize_update";
    private static String BUDGET_ID = "";


    public  void start(String budget) {
        ArrayList<String> budgetAr = new ParseBudgetStr().parseBudgetStr(budget);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            log.error(String.valueOf(e.getStackTrace()));
        }
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.error(String.valueOf(e.getStackTrace()));
        }
        Document doc = builder.newDocument();
        Element rootElement = doc.createElementNS("", "RPLOBJECT");
        // добавляем корневой элемент в объект Document
        doc.appendChild(rootElement);

        rootElement.setAttribute("CLIENT_ID", CLIENT_ID);
        rootElement.setAttribute("MASTER_ID", MASTER_ID);
        rootElement.setAttribute("action", action);
        //  rootElement.setAttribute("BUDGET_ID", BUDGET_ID);


        for (String s: budgetAr) {
            try {
                BUDGET_ID = "" + s;
                rootElement.appendChild(getLanguageGZ(doc, "LIABILITYEXPENSE", "6","1","PL_DIRECTION","1","RPLLIABILITYEXPENSE",BUDGET_ID));
                rootElement.appendChild(getLanguageGZ(doc, "INDUSTRYCODE", "7","1","INDUSTRYCODE","1","RPLINDUSTRYCODE",BUDGET_ID));
                rootElement.appendChild(getLanguageGZ(doc, "GRANTINVESTMENT", "8","1","GRANTINVESTMENT","1","RPLGRANTINVESTMENT",BUDGET_ID));
                rootElement.appendChild(getLanguageGZ(doc, "INSTITUTIONLINE", "9","1","INSTITUTIONLINE","1","RPLINSTITUTIONLINE",BUDGET_ID));
                rootElement.appendChild( getLineTranslation(doc));

                // для красивого вывода
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "windows-1251");

                StreamResult result = new StreamResult(new StringWriter());
                DOMSource source = new DOMSource(doc);
                transformer.transform(source, result);
                String xmlString = result.getWriter().toString();
                log.info(xmlString);

                /*запись данных в файл============================================================================*/
                if(!new File("./RPLOBJECT_FOR_GZ").isDirectory()){
                    new File("./RPLOBJECT_FOR_GZ").mkdir();
                }
                PrintWriter output = new PrintWriter("./RPLOBJECT_FOR_GZ/RPLOBJECT_FOR_GZ.xml");
                output.println(xmlString);
                output.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            File  files = new File("d:/newfile.txt");
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(files);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    // метод для создания нового узла XML-файла
    ///для ГЗ устанавливаем атрибуты
    /*
    пример
    <PRL EXEC_ORDER="6" NAME="PL_DIRECTION_600000002857_GZ" RPLTYPE="1" RPL_ACTIVE="1"
     SERVERPROCESSOR_NAME="RPLLIABILITYEXPENSE" TABLE_NAME="PL_DIRECTION"/>
        * */
    private static Node getLanguageGZ(Document doc, String TABLE_NAME, String EXEC_ORDER,String RPLTYPE,String CAPTION,
                                      String RPL_ACTIVE, String SERVERPROCESSOR_NAME, String BUDGET_ID ) {
        Element language = doc.createElement("PRL");

        String e=CAPTION+"_"+BUDGET_ID+"_GZ";

        // устанавливаем атрибуты
        language.setAttribute("EXEC_ORDER", EXEC_ORDER); //
        language.setAttribute("NAME", e);//
        language.setAttribute("RPLTYPE", RPLTYPE);//
        language.setAttribute("RPL_ACTIVE", RPL_ACTIVE); //
        language.setAttribute("SERVERPROCESSOR_NAME", SERVERPROCESSOR_NAME); //
        language.setAttribute("TABLE_NAME", TABLE_NAME);//
        return language;
    }
    //add new line
    public static Node getLineTranslation(Document doc) {
        return doc.createTextNode("\n");
    }



}


