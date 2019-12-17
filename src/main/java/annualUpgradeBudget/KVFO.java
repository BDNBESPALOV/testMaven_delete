package annualUpgradeBudget;

import annualUpgradeBudget.old.Main;
import examplesss.Filesss;
import org.slf4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;

public class KVFO {
    private  final Logger log = org.slf4j.LoggerFactory.getLogger(RplObjectForGZ.class);
    private static final String REF_NAME = "BUDGCODE";
    private static final String ACTION = "synchronize";
    private String FINYEAR = "null";
    private static final String SUBSYSTEM = "0";
    private static final String SEQORDER = "9";

    public  void start(String budgetId) {
        ArrayList<String> budgetK = new ParseBudgetStr().parseBudgetStr(budgetId);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;



        int i=0;
        for (String s: budgetK) {
            try {
                builder = factory.newDocumentBuilder();
                // создаем пустой объект Document, в котором будем
                // создавать наш xml-файл
                Document doc = builder.newDocument();
                // создаем корневой элемент
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                Element rootElement = doc.createElementNS("", "REFERENCE");
                // добавляем корневой элемент в объект Document
                doc.appendChild(rootElement);

                rootElement.setAttribute("ref_name", REF_NAME);
                rootElement.setAttribute("action", ACTION);
                rootElement.setAttribute("SEQORDER", SEQORDER);
                rootElement.setAttribute("FINYEAR", FINYEAR);
                rootElement.setAttribute("SUBSYSTEM", SUBSYSTEM);
                rootElement.setAttribute("BUDGET_ID", s+"");
                // добавляем первый дочерний элемент к корневому

                rootElement.appendChild(getLanguage(doc, "0", "Неклассифицированный источник"));
                rootElement.appendChild(getLanguage(doc, "1", "Деятельность, осуществляемая за счет средств соответствующего бюджета бюджетной системы Российской Федерации (бюджетная деятельность)"));
                rootElement.appendChild(getLanguage(doc, "2", "Приносящая доход деятельность (собственные доходы учреждения)"));
                rootElement.appendChild(getLanguage(doc, "3", "Средства во временном распоряжении"));
                rootElement.appendChild(getLanguage(doc, "4", "Субсидии на выполнение государственного (муниципального) задания"));
                rootElement.appendChild(getLanguage(doc, "5", "Субсидии на иные цели"));
                rootElement.appendChild(getLanguage(doc, "6", "Субсидии на цели осуществления капитальных вложений"));
                rootElement.appendChild(getLanguage(doc, "7", "Средства по обязательному медицинскому страхованию"));
                rootElement.appendChild(getLanguage(doc, "8", "Средства некоммерческих организаций на лицевых счетах"));
                rootElement.appendChild(getLanguage(doc, "9", "Средства некоммерческих организаций на отдельных лицевых счетах"));




                //создаем объект TransformerFactory для печати в консоль
//                TransformerFactory transformerFactory = TransformerFactory.newInstance();
//                Transformer transformer = transformerFactory.newTransformer();
                // для красивого вывода в консоль

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "windows-1251");
               // transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-16");



                //печатаем в консоль или файл
                // StreamResult console = new StreamResult(System.out);
                if(!new File("./KVFO").isDirectory()){
                    new File("./KVFO").mkdir();
                }
                StreamResult file = new StreamResult(new File("./KVFO/KVFO" + i + ".xml"));
              //  StreamResult file2 = new StreamResult(new File("./KVFO/AllKVFO.xml"));

                //записываем данные
               // transformer.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(new FileOutputStream("./KVFO/KVFO" + i + ".xml"), "windows-1251")));
               transformer.transform(new DOMSource(doc), file);



                System.out.println("KVFO" + i + ".xml");

                // AllKVFO
                try(FileWriter writer = new FileWriter("./KVFO/@AllKVFO.lst", true))
                {
                    // запись всей строки

                    writer.write("KVFO" + i + ".xml"+"\n");
                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }






            } catch (Exception e) {
                e.printStackTrace();
            }



i++;
        }
    }

    // метод для создания нового узла XML-файла
    private static Node getLanguage(Document doc, String id, String DESCRIPTION) {
        Element language = doc.createElement("BUDGCODE");
        // устанавливаем атрибут id
        language.setAttribute("CODE", id);
        language.setAttribute("DESCRIPTION", DESCRIPTION);
        return language;
    }




    // утилитный метод для создание нового узла XML-файла
    private static Node getLanguageElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    public void setFinYear(String str){
        FINYEAR=str.replaceAll("-(.*)", "");
    }

}

