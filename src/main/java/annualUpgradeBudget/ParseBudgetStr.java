package annualUpgradeBudget;


import java.util.ArrayList;

public class ParseBudgetStr {


    public ArrayList<String> parseBudgetStr(String budget){
        ArrayList<String> budgetAr = new ArrayList<>();
        String newBudgetStr = budget.replaceAll(" ","");
        newBudgetStr = newBudgetStr.replaceAll("\n","");
        newBudgetStr = newBudgetStr.replaceAll("\r","");
        newBudgetStr = newBudgetStr.replaceAll("\t","");

        String strTemp="";
        for (int i = 0;i < newBudgetStr.length();i++){
            if(newBudgetStr.charAt(i) == ',' && !strTemp.equals("")){
                budgetAr.add(strTemp);
                strTemp="";
            }else
            {
                strTemp+=newBudgetStr.charAt(i);
            }
        }
        return budgetAr;
    }
}
