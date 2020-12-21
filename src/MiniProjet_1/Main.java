package MiniProjet_1;

import java.io.Reader;
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


    static public BooleanValue evaluation(Reader_DIMACS_CNF reader_dimacs_cnf, Reader_Affectation_variable reader_affectation_variable){
        BooleanValue eval = new BooleanValue(true);
        BooleanValue bool = new BooleanValue(false);
        for(ArrayList<String> strArray : reader_dimacs_cnf.getVariableList()){
            for (String str : strArray){
//                System.out.println(bool.getValue() + " OU " + evaluer(str, reader_affectation_variable).getValue());
                bool = BooleanValue.Ou(bool,evaluer(str, reader_affectation_variable));
//                System.out.println(bool.getValue());
            }
//            System.out.println(eval.getValue() + " ET " + bool.getValue());
            eval = BooleanValue.Et(eval,bool);
            bool.setValue(false);
//            System.out.println(eval.getValue());

        }
        return eval;
    }

    static public BooleanValue evaluer(String str, Reader_Affectation_variable reader_affectation_variable){
        BooleanValue bool = new BooleanValue(false);
        int i = 0;
        String varCorresp = "";
        int varCorrespInt;
        while (i < str.length()){
            if(str.charAt(i) == '-'){
                i++;
            }
            else{
                varCorresp = varCorresp + str.charAt(i);
                i++;
            }
        }
        varCorrespInt = Integer.parseInt(varCorresp);

        for(VarNValue varNValue : reader_affectation_variable.getVariablesNValues()){
            if(varNValue.getVariable() == varCorrespInt){
                bool.setValue(varNValue.getValue());
            }
        }

        for(int j = 0; j < str.length(); j++){
            if(str.charAt(j) == '-'){
                bool = BooleanValue.Non(bool);
            }
        }
        return bool;
    }

    public static void verif(){
        Reader_DIMACS_CNF reader_dimacs_cnf = new Reader_DIMACS_CNF("./ressources/DIMACS_CNF.txt");
        Reader_Affectation_variable readerAffectationVariable = new Reader_Affectation_variable("./ressources/Affectation_variable.txt");
        BooleanValue booleanValue;
        //Test si toutes les variables ont été initialisées
        /*System.out.println();
        System.out.println(areAllVariablesInit(reader_dimacs_cnf,readerAffectationVariable));*/
        System.out.println();
        readerAffectationVariable.afficherListVariableNValues();
        System.out.println();
        /*reader_dimacs_cnf.afficherListVar();
        System.out.println();*/
        booleanValue = evaluation(reader_dimacs_cnf,readerAffectationVariable);
        System.out.println(booleanValue.getValue());
    }

    public static void verifMultiple(int numLigne){
        Reader_DIMACS_CNF reader_dimacs_cnf = new Reader_DIMACS_CNF("./ressources/DIMACS_CNF.txt");
        for(int i=0; i<numLigne; i++) {
            System.out.println("\nAffectation N°" + i);
            Reader_Affectation_variable readerAffectationVariable = new Reader_Affectation_variable("./ressources/affect_zone.txt", numLigne);
            BooleanValue booleanValue;
            //Test si toutes les variables ont été initialisées
        /*System.out.println();
        System.out.println(areAllVariablesInit(reader_dimacs_cnf,readerAffectationVariable));*/
            System.out.println();
            readerAffectationVariable.afficherListVariableNValues();
            System.out.println();
        /*reader_dimacs_cnf.afficherListVar();
        System.out.println();*/
            booleanValue = evaluation(reader_dimacs_cnf, readerAffectationVariable);
            System.out.println("Resultat : " + booleanValue.getValue());
        }
    }



    public static void main(String args[]){
       verif();


    }

}
