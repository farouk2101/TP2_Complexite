package MiniProjet_1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader_DIMACS_CNF {

    File dimacsCNF;
    int nbVarPrev;
    int nbClausePrev;
    int nbVarReal = 0;
    int nbClauseReal = 0;
    ArrayList<Integer> literalList = new ArrayList<>();
    ArrayList<ArrayList<String>> variableList = new ArrayList<>();
    ArrayList<String> listOfLines = new ArrayList<>();

    public Reader_DIMACS_CNF(String path){
        dimacsCNF = new File(path);
        try {
            Scanner dimacScanner = new Scanner(dimacsCNF);
            while (dimacScanner.hasNextLine()){
                String currentLine = dimacScanner.nextLine();
                countnbClauseRealFromFile(currentLine);
                listOfLines.add(currentLine);
            }
            dimacScanner.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        addVarInListFromlistOfLines();
        countnbVarRealFromFile();
        //Recupere le nombre de variable et le nombre de clause d'apres le fichier
        getnbVarPrevFromFile();
        getnbClausePrevFromFile();
        //Affiche le nombre de variable et le nombre de clauses d'après le fichier
//        System.out.println("nbVarPrev = " + nbVarPrev + " nbClausePrev = " + nbClausePrev);
//        System.out.println("nbVarReal = " + nbVarReal + " nbClauseReal = " + nbClauseReal);
        //Permet d'être sur que le doc est correcte
//        System.out.println(isTheDocCorrect());
        /*for(String line : listOfLines){
            System.out.println(line);
        }*/
        initVariableListFromFile();
    }

    public void getnbVarPrevFromFile() {
        nbVarPrev = Character.getNumericValue(listOfLines.get(0).charAt(6));
    }

    public void getnbClausePrevFromFile(){
        nbClausePrev = Character.getNumericValue(listOfLines.get(0).charAt(8));
    }

    public void addVarInListFromlistOfLines(){
        for(int i = 1; i < listOfLines.size(); i++){
            addVarInList(listOfLines.get(i));
        }
    }

    public void addVarInList(String currentLine){
        int i = 0;
        String currentVar = "";
        while (i < currentLine.length()){
            if(currentLine.charAt(i) == ' '){
                int varReco = wichVarIsIt(currentVar);
                if(!isAlreadyAdded(varReco)){
                    literalList.add(varReco);
                }
                currentVar = "";
                i++;
            }
            if(currentLine.charAt(i) == '0'){
                i++;
            }
            else {
                currentVar = currentVar + currentLine.charAt(i);
                i++;
            }
        }
    }

    public int wichVarIsIt(String currentVar){
        String recoVarStr = "";
        for (int i = 0; i < currentVar.length() ; i++){
            if(currentVar.charAt(i) == '-') {
                i++;
            }
            recoVarStr = recoVarStr + currentVar.charAt(i);
        }
        return Integer.parseInt(recoVarStr);
    }

    public boolean isAlreadyAdded(int varToTest){
        for(Integer integer : literalList){
            if(varToTest == integer){
                return true;
            }
        }
        return false;
    }

    public void countnbVarRealFromFile(){
        nbVarReal = literalList.size();
    }

    public void countnbClauseRealFromFile(String currentLine){
        for(int i = 0; i < currentLine.length(); i++){
            if(currentLine.charAt(i) == '0'){
                nbClauseReal += 1;
            }
        }
    }

    public void initVariableListFromFile(){
        try {
            int nbLine = 0;
            Scanner scannerGetVar = new Scanner(dimacsCNF);
            String lineToGet = scannerGetVar.nextLine();
            while (scannerGetVar.hasNextLine()){
                lineToGet = scannerGetVar.nextLine();
                addToVariableList(lineToGet);
                nbLine++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addToVariableList(String currentLine){
        ArrayList<String> arrTempo = new ArrayList<>();
        Scanner sc = new Scanner(currentLine);
        String var;
        while (!(var = sc.next()).equals("0")){
            arrTempo.add(var);
        }
        variableList.add(arrTempo);
    }

    public void afficherListVar(){
        for(int i =0; i < variableList.size(); i++){
            for(int j = 0; j < variableList.get(i).size(); j++){
                System.out.println("Variable n°" + j + " ligne " + i + " = " + variableList.get(i).get(j));
            }
        }
    }

    public ArrayList<ArrayList<String>> getVariableList() {
        return variableList;
    }

    public boolean isTheDocCorrect(){
        if((nbVarPrev == nbVarReal) && (nbClausePrev == nbClauseReal)){
            return true;
        }
        return false;
    }

    public int getNbVarPrev() {
        return nbVarPrev;
    }

    public int getNbClausePrev() {
        return nbClausePrev;
    }

    public int getNbVarReal() {
        return nbVarReal;
    }

    public int getNbClauseReal() {
        return nbClauseReal;
    }

    public ArrayList<Integer> getLiteralList() {
        return literalList;
    }

    public ArrayList<String> getListOfLines() {
        return listOfLines;
    }
}
