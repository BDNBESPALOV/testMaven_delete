package annualUpgradeBudget.old;
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.PrintWriter;
        import java.io.StringWriter;
        import java.util.ArrayList;
        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.ParserConfigurationException;
        import javax.xml.transform.OutputKeys;
        import javax.xml.transform.Transformer;
        import javax.xml.transform.TransformerConfigurationException;
        import javax.xml.transform.TransformerException;
        import javax.xml.transform.TransformerFactory;
        import javax.xml.transform.TransformerFactoryConfigurationError;
        import javax.xml.transform.dom.DOMSource;
        import javax.xml.transform.stream.StreamResult;
        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.Node;


public class RplobjectForGZ {
    static  ArrayList<DOMSource> s = new ArrayList<DOMSource>();
    private static final String CLIENT_ID = "0";
    private static final String MASTER_ID = "600";
    private static final String action = "synchronize_update";
    private static String BUDGET_ID = "";

    public static void main(String[] args) throws ParserConfigurationException, TransformerConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        //id бюджетов
//              String[] budget = new String[] {  "600000002858", "600000002859", "600000002860", "600000002861", "600000002862",
//                      "600000002863", "600000002864", "600000002865", "600000002866", "600000002867", "600000002868", "600000002869",
//                      "600000002870", "600000002871", "600000002872", "600000002873", "600000002874", "600000002875", "600000002876",
//                      "600000002877", "600000002878", "600000002879", "600000002880", "600000002881", "600000002851", "600000002852",
//                      "600000002853", "600000002854", "600000002855", "600000002856", "600000002882", "600000002883", "600000002884",
//                      "600000002888", "600000002889", "600000002890", "600000002891", "600000002892", "600000002893", "600000002894",
//                      "600000002895", "600000002896", "600000002897", "600000002898", "600000002899", "600000002900", "600000002901",
//                      "600000002902", "600000002908", "600000002831", "600000003079", "600000003080", "600000003081", "600000003082",
//                      "600000003083", "600000003085", "600000003086", "600000003087", "600000003088", "600000003089", "600000003090",
//                      "600000003091", "600000003092", "600000003093", "600000003094", "600000003095", "600000003096", "600000003097",
//                      "600000003098", "600000003099", "600000003100", "600000003101", "600000003102", "600000003103", "600000003105",
//                      "600000003106", "600000003107", "600000003108", "600000003109", "600000003110", "600000003111", "600000003112",
//                      "600000003113", "600000003134", "600000003104", "600000003132", "600000003133", "600000003135", "600000003136",
//                      "600000003137", "600000003139", "600000003141", "600000003143", "600000003146", "600000003148", "600000003149",
//                      "600000003151", "600000003152", "600000003153", "600000003154", "600000003155", "600000003156", "600000003157",
//                      "600000003158", "600000003131", "600000002914", "600000002915", "600000002918", "600000002919", "600000002920",
//                      "600000002921", "600000002922", "600000002923", "600000002924", "600000002925", "600000002931", "600000002932",
//                      "600000002933", "600000002910", "600000002942", "600000002943", "600000002945", "600000002946", "600000002947",
//                      "600000002948", "600000002949", "600000002950", "600000002951", "600000002952", "600000002953", "600000002954",
//                      "600000002955", "600000002956", "600000002957", "600000002958", "600000002959", "600000002960", "600000002961",
//                      "600000002962", "600000002963", "600000002964", "600000002937", "600000002938", "600000002939", "600000002940",
//                      "600000002941", "600000002970", "600000002971", "600000002972", "600000002977", "600000002978", "600000002979",
//                      "600000002980", "600000002981", "600000002982", "600000002983", "600000002984", "600000002967", "600000002968",
//                      "600000002969", "600000003009", "600000003016", "600000003017", "600000003018", "600000003019", "600000003022",
//                      "600000003023", "600000003024", "600000003025", "600000003026", "600000003027", "600000003028", "600000003029",
//                      "600000003030", "600000003031", "600000003032", "600000003033", "600000003034", "600000003035", "600000003036",
//                      "600000003037", "600000003038", "600000003039", "600000003040", "600000003041", "600000003042", "600000003043",
//                      "600000003044", "600000003047", "600000003020", "600000003049", "600000003050", "600000003051", "600000003052",
//                      "600000003053", "600000003054", "600000003055", "600000003056", "600000003057", "600000003058", "600000003059",
//                      "600000003066", "600000003067", "600000003068", "600000003069", "600000003070", "600000003071", "600000003072",
//                      "600000003073", "600000003048", "600000003159", "600000003160"};
        //количество повторений


        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element rootElement = doc.createElementNS("", "RPLOBJECT");
        // добавляем корневой элемент в объект Document
        doc.appendChild(rootElement);

        rootElement.setAttribute("CLIENT_ID", CLIENT_ID);
        rootElement.setAttribute("MASTER_ID", MASTER_ID);
        rootElement.setAttribute("action", action);
        //  rootElement.setAttribute("BUDGET_ID", BUDGET_ID);


        for (int i = 0; i < Main.budget.length; i++) {
            try {
                BUDGET_ID = "" + Main.budget[i];
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
                System.out.println(xmlString);

                /*запись данных в файл============================================================================*/
                PrintWriter output = new PrintWriter("E:/testGenXML/RPLOBJECT_FOR_GZ/RPLOBJECT_FOR_GZ.xml");
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


