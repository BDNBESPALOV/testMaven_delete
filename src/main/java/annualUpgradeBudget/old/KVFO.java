package annualUpgradeBudget.old;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class KVFO {
    private static final String REF_NAME = "BUDGCODE";
    private static final String ACTION = "synchronize";
    private static final String FINYEAR = "2020";
    private static final String SUBSYSTEM = "0";
    private static final String SEQORDER = "9";

    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;


        //int[] budget = new int[] { 718,728,729,738,739,740 };
        int[] budget = Main.budgetK ;
        for (int i = 0; i < budget.length; i++) {
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
                rootElement.setAttribute("BUDGET_ID", budget[i]+"");
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


                //печатаем в консоль или файл
                // StreamResult console = new StreamResult(System.out);
                StreamResult file = new StreamResult(new File("/work/ideaGZ/KVFO_2019_okrug/KVFO" + i + ".xml"));
                StreamResult file2 = new StreamResult(new File("/work/ideaGZ/KVFO_2019_okrug/AllKVFO.xml"));

                //записываем данные
                //  transformer.transform(source, console);

                transformer.transform(new DOMSource(doc), file);

                System.out.println("KVFO" + i + ".xml");

                // AllKVFO
                try(FileWriter writer = new FileWriter("/work/ideaGZ/KVFO_2019_okrug/@AllKVFO.lst", true))
                {
                    // запись всей строки
                    writer.write("KVFO" + i + ".xml"+'\n');
                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }


            } catch (Exception e) {
                e.printStackTrace();
            }




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

}

