package MiniProjet_1;

import java.util.ArrayList;

public class Main {

    static public boolean areAllVariablesInit(Reader_DIMACS_CNF dimacs, Reader_Affectation_variable affect){
        boolean exist;
        if(dimacs.getLiteralList().size() == affect.getVariablesNValues().size()) {
            for (int var : dimacs.getLiteralList()) {
                exist = false;
                for (VarNValue varNValue : affect.getVariablesNValues()) {
                    if (var == varNValue.getVariable()) {
                        exist = true;
                    }
                }
                if(!exist){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }



    public static void main(String args[]){
        ArrayList<VarNValue> listVarNValue = new ArrayList<>();
        Reader_DIMACS_CNF reader_dimacs_cnf = new Reader_DIMACS_CNF("src/MiniProjet_1/DIMACS_CNF.txt");
        Reader_Affectation_variable readerAffectationVariable = new Reader_Affectation_variable("src/MiniProjet_1/Affectation_variable.txt");
        //Test si toutes les variables ont été initialisées
//        System.out.println(areAllVariablesInit(reader_dimacs_cnf,readerAffectationVariable));
//        readerAffectationVariable.afficherListVariableNValues();
//        reader_dimacs_cnf.afficherListVar();

    }

}
