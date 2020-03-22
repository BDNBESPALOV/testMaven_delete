package tempdir;

import annualUpgradeBudget.ParseBudgetStr;
import annualUpgradeBudget.RplObjectForGZ;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class Tempxml {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RplObjectForGZ.class);
    static  ArrayList<DOMSource> s = new ArrayList<DOMSource>();
    private static final String CLIENT_ID = "0";
    private static final String MASTER_ID = "600";
    private static final String action = "synchronize";
    private static String KODE="";
    private static String BUDGET_ID = "";

    public Tempxml() throws TransformerException, FileNotFoundException {
        ArrayList<String> budgetAr = new ParseBudgetStr().parseBudgetStr("budget");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= null;
        //id бюджетов
        String[] ko = new String[] {"KCSR","KADM","KFSR","KVR","KES","KDE","KDF","KDR" };
        String[] ko1 = new String[] {"PURPOSEFULGRANT","PL_DIRECTION","INDUSTRYCODE","GRANTINVESTMENT" };
        //количество повторений

        //  StreamResult file = new StreamResult(new File("/work/real/CODETYPE/RPL_2018/RPL_33333_"+".xml"));
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
       // Element rootElement = doc.createElementNS("", "RPLOBJECT");
        Element rootElement = doc.createElementNS("", "TASK");
        // добавляем корневой элемент в объект Document
        doc.appendChild(rootElement);
//        rootElement.setAttribute("CLIENT_ID", CLIENT_ID);
//        rootElement.setAttribute("MASTER_ID", MASTER_ID);
//        rootElement.setAttribute("action", action);

        Element rpl = (Element) rootElement.appendChild(getHeadNodeRPLOBJECT(doc));
        rpl.appendChild(getLanguage(doc, "KCSR", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));
        rpl.appendChild(getLanguage(doc, "KADM", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));



        //  rootElement.appendChild(getLanguage(doc, "KCSR", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));



        // для красивого вывода в консоль
        {
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "windows-1251");
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            String xmlString = result.getWriter().toString();
            log.info(xmlString);

            if (!new File("./RPLOBJECT_FOR_FIN_temp").isDirectory()) {
                new File("./RPLOBJECT_FOR_FIN_temp").mkdir();
            }

            PrintWriter output = new PrintWriter("./RPLOBJECT_FOR_FIN_temp/RPLOBJECT_FOR_FIN.xml");
            output.println(xmlString);
            output.close();
        }

    }
    private static Node getHeadNodeRPLOBJECT(Document doc){
        Element language = doc.createElement("RPLOBJECT");
        language.setAttribute("CLIENT_ID", CLIENT_ID);
        language.setAttribute("MASTER_ID", MASTER_ID);
        language.setAttribute("action", action);
        return language;
    }
    private static Node getLanguage(Document doc, String SEQORDER, String DISPLAY_ORDER, String NAME,
                                    String CAPTION, String DESCRIPTION, String DEFVALUE ) {
        Element language = doc.createElement("PRL");

        String e=CAPTION+"_"+DESCRIPTION+"_GZ";

        // устанавливаем атрибут id
        language.setAttribute("TABLE_NAME", SEQORDER);
        language.setAttribute("EXEC_ORDER", DISPLAY_ORDER);
        language.setAttribute("RPLTYPE", NAME);
        language.setAttribute("NAME", e);
        language.setAttribute("FIELD1_VALUE", DESCRIPTION);
        language.setAttribute("SERVERPROCESSOR_NAME", DEFVALUE);
        // создаем элемент name
        //* language.appendChild(getLanguageElements(doc, language, "name","xxxxxx"));

        // создаем элемент age
        //*   language.appendChild(getLanguageElements(doc, language, "age", age));
        return language;
    }

    public static void main(String [] args) throws TransformerException, FileNotFoundException {
         new Tempxml();

    }

}
