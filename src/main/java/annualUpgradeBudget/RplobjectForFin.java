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


//        <TASK>
//
//<!-- MASTER_ID должен быть АЦК, CLIENT_ID должен быть ГЗ. Заменить в двух блоках -->
//<RPLOBJECT CLIENT_ID="0" MASTER_ID="600" action="synchronize">
//
// <RPL TABLE_NAME="BANK" EXEC_ORDER="2" RPLTYPE="1" NAME="BANK_GZ" />
//
// <!--для выгрузки организаций автономных учреждений необходимо добавить в фильтрацию условия 19/
// <RPL TABLE_NAME="ORG"  EXEC_ORDER="3" RPLTYPE="1" NAME="ORG_GZ" FIELD1_VALUE="0"
// FILTER_CONDITION=""/> -->
//
//  <!--для выгрузки счетов организаций автономных учреждений необходимо добавить в фильтрацию условия 19/ -->
// <RPL TABLE_NAME="ORGACCOUNT" EXEC_ORDER="5" RPLTYPE="1" NAME="ORGACCOUNT_GZ" FIELD1_VALUE="0"
//   FILTER_CONDITION="EXISTS (SELECT null FROM OrgRoles r WHERE r.Org_ID=t.Org_ID AND r.OrgRole_ID in (0,1,4,5,18,19)) AND t.OrgAccType_ID in (1,2,5,6,7,8,9)"/>
//
//
//  <RPL TABLE_NAME="PLAN_DIRECT_GROUP" EXEC_ORDER="9" NAME="PLAN_DIRECT_GROUP" RPLTYPE="1"/>
//  <RPL TABLE_NAME="PL_PERMISSION" SERVERPROCESSOR_NAME="PLPERMISSIONGZRPLPROCESSOR" EXEC_ORDER="10" NAME="PL_PERMISSION" RPLTYPE="1"/>
//
//*
//*
//
//*
//*
//*
//     </RPLOBJECT>
//</TASK>
//


public class RplobjectForFin {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RplObjectForGZ.class);
    static  ArrayList<DOMSource> s = new ArrayList<DOMSource>();
    private static final String CLIENT_ID = "0";
    private static final String MASTER_ID = "600";
    private static final String action = "synchronize";
    private static String KODE="";
    private static String BUDGET_ID = "";


        public  void start(String budget) {
        ArrayList<String> budgetAr = new ParseBudgetStr().parseBudgetStr(budget);
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
        Element rootElem = doc.createElementNS("", "TASK");
        // добавляем корневой элемент в объект Document
        doc.appendChild(rootElem);
        Element rpl = (Element) rootElem.appendChild(getHeadNodeRPLOBJECT(doc));
        rpl.appendChild(getNodeBANK(doc));
        rpl.appendChild(getNodeORGACCOUNT(doc));
        rpl.appendChild(getNodePLAN_DIRECT_GROUP(doc));
        rpl.appendChild(getNodePL_PERMISSION(doc));


        for (String s: budgetAr) {
            try {
                BUDGET_ID = "" + s;
                int iq=0;
                if(iq<7){
                    KODE=""+ko[iq];

                }
                // добавляем первый дочерний элемент к корневому
                rpl.appendChild(getLanguage(doc, "KCSR", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));
                if(iq<7){
                    iq++;
                    KODE=""+ko[iq];
                }
                rpl.appendChild(getLanguage(doc, "KADM", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));
                if(iq<7){
                    iq++;
                    KODE=""+ko[iq];
                }
                rpl.appendChild(getLanguage(doc, "KFSR", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));
                if(iq<7){
                    iq++;
                    KODE=""+ko[iq];
                }
                rpl.appendChild(getLanguage(doc, "KVR", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));
                if(iq<7){
                    iq++;
                    KODE=""+ko[iq];
                }
                rpl.appendChild(getLanguage(doc, "KES", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));
                if(iq<7){
                    iq++;
                    KODE=""+ko[iq];
                }
                rpl.appendChild(getLanguage(doc, "KDE", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));
                if(iq<7){
                    iq++;
                    KODE=""+ko[iq];
                }
                rpl.appendChild(getLanguage(doc, "KDF", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));
                if(iq<7){
                    iq++;
                    KODE=""+ko[iq];
                }
                rpl.appendChild(getLanguage(doc, "KDR", "1","1",KODE,BUDGET_ID,"PERIODIC_VS_NOT_PERIODIC_KBK_RPL"));

                ////////////////////////////////////////////////
                int iq1=0;
                if(iq1<4){

                    KODE=""+ko1[iq1];
                }
                rpl.appendChild(getLanguage1(doc, "PURPOSEFULGRANT", "1","1",KODE,BUDGET_ID));

                if(iq1<4){
                    iq1++;
                    KODE=""+ko1[iq1];
                }
                rpl.appendChild(getLanguage1(doc, "PL_DIRECTION", "11","1",KODE,BUDGET_ID));

                if(iq1<4){
                    iq1++;
                    KODE=""+ko1[iq1];
                }
                rpl.appendChild(getLanguage1(doc, "INDUSTRYCODE", "12","1",KODE,BUDGET_ID));

                if(iq1<4){
                    iq1++;
                    KODE=""+ko1[iq1];
                }
                rpl.appendChild(getLanguage1(doc, "GRANTINVESTMENT", "13","1",KODE,BUDGET_ID));

                //////////////для выгрузки бланков расходов
                rpl.appendChild(getLanguage2(doc, "ESTIMATE", "7","1","ESTIMATE",BUDGET_ID,"ESTIMATE_OFFLINE_RPL","ESTKIND_ID IN (1,5,7,8) AND Exists(SELECT null FROM OrgRoles r WHERE t.Org_ID=r.Org_ID AND r.OrgRole_ID in (0,1,4,5,18,19))   and Exists(select null from Org o where  not(o.taxcode is null or o.taxcode like '000%' )and t.org_id=o.id)"));

                ////////////для выгрузки бюджетных строк организаций
                rpl.appendChild(getLanguage3(doc, "BUDGETLINE", "8","1","BUDGETLINE",BUDGET_ID,"2","EXPBUDEGETLINERPL","ID IN (SELECT BudgetLine_ID FROM ExpBudget b, Estimate e WHERE b.Estimate_ID=e.ID AND e.EstKind_ID IN (1,5,7,8) AND b.Recipient_ID > 0)"));

                rpl.appendChild(getLanguage3(doc, "BUDGETLINE", "14","1","INSTITUTIONLINE",BUDGET_ID,"4","AUBUBUDEGETLINERPL","ID IN (SELECT BudgetLine_ID FROM AuBuBudget b, orgroles r WHERE b.Pay_ID = r.org_id and r.orgrole_id in (18,19))"));
                rpl.appendChild( getLineTranslation(doc));


                // для красивого вывода в консоль
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "windows-1251");
                StreamResult result = new StreamResult(new StringWriter());
                DOMSource source = new DOMSource(doc);
                transformer.transform(source, result);
                String xmlString = result.getWriter().toString();
                log.info(xmlString);

                if(!new File("./RPLOBJECT_FOR_FIN").isDirectory()){
                    new File("./RPLOBJECT_FOR_FIN").mkdir();
                }

                PrintWriter output = new PrintWriter("./RPLOBJECT_FOR_FIN/RPLOBJECT_FOR_FIN.xml");
                output.println(xmlString);
                output.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


//    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
//        try {
//            File  files = new File("d:/newfile.txt");
//            Transformer tr = TransformerFactory.newInstance().newTransformer();
//            DOMSource source = new DOMSource(document);
//            FileOutputStream fos = new FileOutputStream(files);
//            StreamResult result = new StreamResult(fos);
//            tr.transform(source, result);
//        } catch (TransformerException | IOException e) {
//            e.printStackTrace(System.out);
//        }
//    }

    // метод для создания нового узла XML-файла
    private static Node getLanguage(Document doc, String SEQORDER, String DISPLAY_ORDER,String NAME,
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


    private static Node getLanguage1(Document doc, String SEQORDER, String DISPLAY_ORDER,String NAME,
                                     String CAPTION, String DESCRIPTION ) {
        Element language = doc.createElement("PRL");

        String e=CAPTION+"_"+DESCRIPTION+"_GZ";

        // устанавливаем атрибут id
        language.setAttribute("TABLE_NAME", SEQORDER);
        language.setAttribute("EXEC_ORDER", DISPLAY_ORDER);
        language.setAttribute("RPLTYPE", NAME);
        language.setAttribute("NAME", e);
        language.setAttribute("FIELD1_VALUE", DESCRIPTION);
        // создаем элемент name
        //* language.appendChild(getLanguageElements(doc, language, "name","xxxxxx"));

        // создаем элемент age
        //*   language.appendChild(getLanguageElements(doc, language, "age", age));
        return language;
    }

    //для выгрузки бланков расходов
    private static Node getLanguage2(Document doc, String TABLE_NAME, String EXEC_ORDER,String RPLTYPE,
                                     String CAPTION, String DESCRIPTION, String SERVERPROCESSOR_NAME, String FILTER_CONDITION ) {
        Element language = doc.createElement("PRL");

        String e=CAPTION+"_"+DESCRIPTION+"_GZ";

        // устанавливаем атрибут id
        language.setAttribute("TABLE_NAME", TABLE_NAME);
        language.setAttribute("EXEC_ORDER", EXEC_ORDER);
        language.setAttribute("RPLTYPE", RPLTYPE);
        language.setAttribute("NAME", e);
        language.setAttribute("FIELD1_VALUE", DESCRIPTION);
        language.setAttribute("SERVERPROCESSOR_NAME", SERVERPROCESSOR_NAME);
        language.setAttribute("FILTER_CONDITION", FILTER_CONDITION);
        // создаем элемент name
        //* language.appendChild(getLanguageElements(doc, language, "name","xxxxxx"));

        // создаем элемент age
        //*   language.appendChild(getLanguageElements(doc, language, "age", age));
        return language;
    }

    //для выгрузки бюджетных строк организаций
    private static Node getLanguage3(Document doc, String TABLE_NAME, String EXEC_ORDER,String RPLTYPE,
                                     String CAPTION, String DESCRIPTION, String FIELD2_VALUE, String SERVERPROCESSOR_NAME, String FILTER_CONDITION ) {
        Element language = doc.createElement("PRL");

        String e=CAPTION+"_"+DESCRIPTION+"_GZ";

        // устанавливаем атрибут id
        language.setAttribute("TABLE_NAME", TABLE_NAME);
        language.setAttribute("EXEC_ORDER", EXEC_ORDER);
        language.setAttribute("RPLTYPE", RPLTYPE);
        language.setAttribute("NAME", e);
        language.setAttribute("FIELD1_VALUE", DESCRIPTION);
        language.setAttribute("FIELD2_VALUE", FIELD2_VALUE);
        language.setAttribute("SERVERPROCESSOR_NAME", SERVERPROCESSOR_NAME);
        language.setAttribute("FILTER_CONDITION", FILTER_CONDITION);
        // создаем элемент name
        //* language.appendChild(getLanguageElements(doc, language, "name","xxxxxx"));

        // создаем элемент age
        //*   language.appendChild(getLanguageElements(doc, language, "age", age));
        return language;
    }
    //add new line
    public static Node getLineTranslation(Document doc) {
        return doc.createTextNode("\n");
    }

    private static Node getHeadNodeRPLOBJECT(Document doc){
        Element language = doc.createElement("RPLOBJECT");
        language.setAttribute("CLIENT_ID", CLIENT_ID);
        language.setAttribute("MASTER_ID", MASTER_ID);
        language.setAttribute("action", action);
        return language;
    }

    private static Node getNodeBANK(Document doc){
        //для добавления  <RPL TABLE_NAME="BANK" EXEC_ORDER="2" RPLTYPE="1" NAME="BANK_GZ" />
        Element language = doc.createElement("RPL");
        language.setAttribute("TABLE_NAME", "BANK");
        language.setAttribute("EXEC_ORDER", "2");
        language.setAttribute("RPLTYPE", "1");
        language.setAttribute("NAME", "BANK_GZ");
        return language;
    }
    private static Node getNodeORGACCOUNT(Document doc){
        //для добавления  <RPL TABLE_NAME="ORGACCOUNT" EXEC_ORDER="5" RPLTYPE="1" NAME="ORGACCOUNT_GZ" FIELD1_VALUE="0"
        //   FILTER_CONDITION="EXISTS (SELECT null FROM OrgRoles r WHERE r.Org_ID=t.Org_ID AND r.OrgRole_ID in (0,1,4,5,18,19)) AND t.OrgAccType_ID in (1,2,5,6,7,8,9)"/>
        Element language = doc.createElement("RPL");
        language.setAttribute("TABLE_NAME","ORGACCOUNT" );
        language.setAttribute("EXEC_ORDER","5");
        language.setAttribute("RPLTYPE","1");
        language.setAttribute("NAME","ORGACCOUNT_GZ");
        language.setAttribute("FIELD1_VALUE","0");
        language.setAttribute("FILTER_CONDITION","EXISTS (SELECT null FROM OrgRoles r WHERE r.Org_ID=t.Org_ID AND r.OrgRole_ID in (0,1,4,5,18,19)) AND t.OrgAccType_ID in (1,2,5,6,7,8,9)");
        return language;
    }
    private static Node getNodePLAN_DIRECT_GROUP(Document doc){
        //для добавления    <RPL TABLE_NAME="PLAN_DIRECT_GROUP" EXEC_ORDER="9" NAME="PLAN_DIRECT_GROUP" RPLTYPE="1"/>
        Element language = doc.createElement("RPL");
        language.setAttribute("TABLE_NAME","PLAN_DIRECT_GROUP" );
        language.setAttribute("EXEC_ORDER","9" );
        language.setAttribute("NAME","PLAN_DIRECT_GROUP" );
        language.setAttribute("RPLTYPE","1" );
        return language;
    }
    private static Node getNodePL_PERMISSION(Document doc){
        //для добавления   <RPL TABLE_NAME="PL_PERMISSION" SERVERPROCESSOR_NAME="PLPERMISSIONGZRPLPROCESSOR" EXEC_ORDER="10" NAME="PL_PERMISSION" RPLTYPE="1"/>
        Element language = doc.createElement("RPL");
        language.setAttribute("TABLE_NAME","PL_PERMISSION" );
        language.setAttribute("SERVERPROCESSOR_NAME","PLPERMISSIONGZRPLPROCESSOR" );
        language.setAttribute("EXEC_ORDER","10" );
        language.setAttribute("NAME","PL_PERMISSION" );
        language.setAttribute("RPLTYPE","1" );
        return language;
    }


//
//    // утилитный метод для создание нового узла XML-файла
//    private static Node getLanguageElements(Document doc, Element element, String name, String value) {
//        Element node = doc.createElement(name);
//        node.appendChild(doc.createTextNode(value));
//        return node;
//    }

}


