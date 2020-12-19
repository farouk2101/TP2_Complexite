package MiniProjet_3;

import java.util.ArrayList;

public class SudokuToSat {

    private final ArrayList<ArrayList<Integer>> grilleSudoku;
    private final ArrayList<String> listToPrint;
    public SudokuToSat(String path){
        ReadFile readFile = new ReadFile(path);
        readFile.read();
        grilleSudoku = readFile.getGrille();
        listToPrint = new ArrayList<>();
        //printGrilleSudoku();
        initListCell();
        printListToPrint();
    }

    private void initListCell(){
        for(int i=0; i<grilleSudoku.size();i++){
            for(int j = 0; j<grilleSudoku.get(i).size();j++){
                if(grilleSudoku.get(i).get(j)==0){
                    StringBuilder strBuilder = new StringBuilder("");
                    for(int indexNumber = 1 ; indexNumber<=grilleSudoku.get(i).size();indexNumber++) {
                        int index = getIndexForCell(i+1, j+1, indexNumber);
                        strBuilder.append(index);
                        if(indexNumber<grilleSudoku.get(i).size())
                            strBuilder.append(" ");
                        else
                            strBuilder.append("\n");
                    }
                    listToPrint.add(strBuilder.toString());
                    for(int d = 1; d<grilleSudoku.get(i).size();d++){
                        for(int d2 = d+1; d2<grilleSudoku.get(i).size();d2++){
                            StringBuilder strBuilderForNotTheSame = new StringBuilder("");
                            int index1 = getIndexForCell(i+1, j+1, d);
                            int index2 = getIndexForCell(i+1, j+1, d2);
                            strBuilderForNotTheSame.append("-").append(index1).append(" ").append("-").append(index2);
                            strBuilderForNotTheSame.append("\n");
                            listToPrint.add(strBuilderForNotTheSame.toString());
                        }
                    }

                }
            }
        }

        for(int i = 0; i<grilleSudoku.size();i++){
        }
    }

    private int getIndexForCell(int i, int j, int number){
        return Integer.parseInt(""+ i+j+number);
    }

    private void printListToPrint(){
        for(String str : listToPrint){
            System.out.println(str);
        }
    }

    private void printGrilleSudoku(){
        for(ArrayList<Integer> arrayList : grilleSudoku){
            for(Integer integer:arrayList)
                System.out.print(integer + " ");
        System.out.println("");
        }
    }

}
