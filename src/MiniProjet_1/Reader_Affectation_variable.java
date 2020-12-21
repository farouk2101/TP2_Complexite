package MiniProjet_1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader_Affectation_variable {

    File affectationVariable;
    ArrayList<VarNValue> variablesNValues = new ArrayList<>();

    public Reader_Affectation_variable(String path){
        this.affectationVariable = new File(path);
        try {
            Scanner affectationVariableScanner = new Scanner(affectationVariable);
            String onlyLine = affectationVariableScanner.nextLine();
            String currentVar = "";
            for(int i = 0; i < onlyLine.length(); i++){
                if(onlyLine.charAt(i) == ' '){
                    int variable = getPositifFromVar(currentVar);
                    boolean value = getValueFromVar(currentVar);
                    variablesNValues.add(new VarNValue(variable,value));
                    currentVar = "";
                }
                else {
                    currentVar = currentVar + onlyLine.charAt(i);
                    if(i == onlyLine.length()-1){
                        int variable = getPositifFromVar(currentVar);
                        boolean value = getValueFromVar(currentVar);
                        variablesNValues.add(new VarNValue(variable,value));
                    }
                }
            }
            affectationVariableScanner.close();
        }catch(Exception e){
            e.printStackTrace();
        }
//        afficherListVariableNValues();
    }

    public Reader_Affectation_variable(String path, int numLigne){
        this.affectationVariable = new File(path);
        try {
            Scanner affectationVariableScanner = new Scanner(affectationVariable);
            String choosenLine = "";
            for (int nbLine = 0; nbLine < numLigne; nbLine++){
                choosenLine = affectationVariableScanner.nextLine();
            }
                String currentVar = "";
                for (int i = 0; i < choosenLine.length(); i++) {
                    if (choosenLine.charAt(i) == ' ') {
                        int variable = getPositifFromVar(currentVar);
                        boolean value = getValueFromVar(currentVar);
                        variablesNValues.add(new VarNValue(variable, value));
                        currentVar = "";
                    } else {
                        currentVar = currentVar + choosenLine.charAt(i);
                        if (i == choosenLine.length() - 1) {
                            int variable = getPositifFromVar(currentVar);
                            boolean value = getValueFromVar(currentVar);
                            variablesNValues.add(new VarNValue(variable, value));
                        }
                    }
                }
            affectationVariableScanner.close();
        }catch(Exception e){
            e.printStackTrace();
        }
//        afficherListVariableNValues();
    }

    public int getPositifFromVar(String currentVar){
        int i = 0;
        String strOfVar = "";
        while(currentVar.charAt(i) == '-'){
            i++;
        }
        while(i < currentVar.length()){
            strOfVar = strOfVar + currentVar.charAt(i);
            i++;
        }
        return Integer.parseInt(strOfVar);
    }

    public boolean getValueFromVar(String currentVar){
        int i = 0;
        int compteur = 0;
        while(i < currentVar.length()){
            if(currentVar.charAt(i) == '-'){
                compteur ++;
            }
            i++;
        }
        if(compteur%2 == 0){
            return true;
        }
        else {
            return false;
        }
    }

    public void afficherListVariableNValues(){
        for(VarNValue varNValue : variablesNValues){
            System.out.println("Variable : " + varNValue.getVariable() + " Value : " + varNValue.getValue());
        }
    }

    public ArrayList<VarNValue> getVariablesNValues() {
        return variablesNValues;
    }
}
