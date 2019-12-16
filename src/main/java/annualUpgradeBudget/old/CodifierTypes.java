package annualUpgradeBudget.old;

import java.io.File;
        import java.util.ArrayList;
        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.transform.OutputKeys;
        import javax.xml.transform.Transformer;
        import javax.xml.transform.TransformerFactory;
        import javax.xml.transform.dom.DOMSource;
        import javax.xml.transform.stream.StreamResult;

        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.Node;



        /*Типы кодификаторов*/
        public class CodifierTypes {
    private static final String REF_NAME = "CODETYPE";
    private static final String ACTION = "perform";
    private static final String SUBSYSTEM = "0";
    private static String BUDGET_ID = "718";

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        //id бюджетов
        int[] budget = new int[] {728,729,738,739,740};
        //количество повторений
        for (int i = 0; i < 9; i++) {
            try {
                builder = factory.newDocumentBuilder();

                // создаем пустой объект Document, в котором будем
                // создавать наш xml-файл
                Document doc = builder.newDocument();
                // создаем корневой элемент

                Element rootElement = doc.createElementNS("", "REFERENCE");
                // добавляем корневой элемент в объект Document
                doc.appendChild(rootElement);

                rootElement.setAttribute("ref_name", REF_NAME);
                rootElement.setAttribute("action", ACTION);
                rootElement.setAttribute("SUBSYSTEM", SUBSYSTEM);
                rootElement.setAttribute("BUDGET_ID", BUDGET_ID);
                // добавляем первый дочерний элемент к корневому
                rootElement.appendChild(getLanguage(doc, "1", "1","KFSR","КФСР","Функциональный классификатор расходов","0000","00.00","22"));
                rootElement.appendChild(getLanguage(doc, "2", "2","KCSR","КЦСР","Классификатор целевой статьи расходов","0000000","000.00.00","322"));
                rootElement.appendChild(getLanguage(doc, "3", "3","KVR","КВР","Классификатор вида расходов","000","000","3"));
                rootElement.appendChild(getLanguage(doc, "4", "4","KESR","КОСГУ","Справочник операций сектора гос. управления","000","0.0.0","111"));
                rootElement.appendChild(getLanguage(doc, "5", "5","KVSR","КВСР","Ведомственный классификатор расходов","000","000","3"));
                rootElement.appendChild(getLanguage(doc, "6", "6","KDF","Доп. ФК","Дополнительный функциональный код","00000","000.00","32"));
                rootElement.appendChild(getLanguage(doc, "7", "7","KDE","Доп. ЭК","Дополнительный экономический код","00000","00.00.0","221"));
                rootElement.appendChild(getLanguage(doc, "8", "8","KDR","Доп. КР","Дополнительный код расхода","000","000","3"));
                rootElement.appendChild(getLanguage(doc, "9", "10","KIF","КВФО","Код вида финансового обеспечения","0","0","1"));
                rootElement.appendChild(getLanguage(doc, "10", "9","PURPOSEFULGRANT","Код цели","Целевые назначения","0000000000000000","9999999999999999","116"));





                //добавляем второй дочерний элемент к корневому
                // rootElement.appendChild(getLanguage(doc, "2", "C", "44","qqq"));

                //создаем объект TransformerFactory для печати в консоль
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                // для красивого вывода в консоль
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "windows-1251");
                DOMSource source = new DOMSource(doc);

                //печатаем в консоль или файл
                // StreamResult console = new StreamResult(System.out);

                StreamResult file = new StreamResult(new File("/work/CODETYPE_2019/CODETYPE" + i + ".xml"));

                //записываем данные
                //  transformer.transform(source, console);
                transformer.transform(source, file);
                System.out.println("CODETYPE" + i + ".xml");

            } catch (Exception e) {
                e.printStackTrace();
            }
            // for (int j = 0; j < 629; j++) {
            BUDGET_ID = "" + budget[i];
            //  }


        }
    }

    // метод для создания нового узла XML-файла
    private static Node getLanguage(Document doc, String SEQORDER, String DISPLAY_ORDER,String NAME,
                                    String CAPTION, String DESCRIPTION, String DEFVALUE, String MASK, String GROUPMASK) {
        Element language = doc.createElement("CODETYPE");

        // устанавливаем атрибут id
        language.setAttribute("SEQORDER", SEQORDER);
        language.setAttribute("DISPLAY_ORDER", DISPLAY_ORDER);
        language.setAttribute("NAME", NAME);
        language.setAttribute("CAPTION", CAPTION);
        language.setAttribute("DESCRIPTION", DESCRIPTION);
        language.setAttribute("DEFVALUE", DEFVALUE);
        language.setAttribute("MASK", MASK);
        language.setAttribute("GROUPMASK", GROUPMASK);



        // создаем элемент name
        //* language.appendChild(getLanguageElements(doc, language, "name","xxxxxx"));

        // создаем элемент age
        //*   language.appendChild(getLanguageElements(doc, language, "age", age));
        return language;
    }


    // утилитный метод для создание нового узла XML-файла
    private static Node getLanguageElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}

