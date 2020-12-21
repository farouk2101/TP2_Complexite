package MiniProjet_3;

import java.util.ArrayList;

public class SudokuToSat {

    private final ArrayList<ArrayList<Integer>> grilleSudoku;
    private final ArrayList<String> listToPrint;
    private final ArrayList<String> listVar;
    public SudokuToSat(String path){
        listVar = new ArrayList<>();
        ReadFile readFile = new ReadFile(path);
        readFile.read();
        grilleSudoku = readFile.getGrille();
        listToPrint = new ArrayList<>();
        initListCell();
        readFile.writeFile(listToPrint, "./ressources/sat.txt", listVar.size());
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
                    }
                    listToPrint.add(strBuilder.append(" ").append(0).toString());
                    for(int d = 1; d<=grilleSudoku.get(i).size();d++){
                        for(int d2 = d+1; d2<=grilleSudoku.get(i).size();d2++){
                            StringBuilder strBuilderForNotTheSame = new StringBuilder("");
                            int index1 = getIndexForCell(i+1, j+1, d);
                            int index2 = getIndexForCell(i+1, j+1, d2);
                            strBuilderForNotTheSame.append("-").append(index1).append(" ").append("-").append(index2);
                            listToPrint.add(strBuilderForNotTheSame.append(" ").append(0).toString());
                        }
                    }

                }
            }
        }

        for(int i = 0; i<grilleSudoku.size();i++){
            for(int j=0;j<grilleSudoku.get(i).size();j++){
                for(int d=1; d<=grilleSudoku.size();d++) {
                    for (int j2 = j + 1; j2 < grilleSudoku.get(i).size(); j2++) {
                        if(grilleSudoku.get(i).get(j)==d && grilleSudoku.get(i).get(j2)==d) {
                            listToPrint.clear();
                            listToPrint.add("IMPOSSIBLE");
                            return;
                        }
                        boolean doWrite = true;
                        if((grilleSudoku.get(i).get(j)>0 && grilleSudoku.get(i).get(j)!=d)||(grilleSudoku.get(i).get(j2)>0&&
                                grilleSudoku.get(i).get(j2)!=d))
                            doWrite = false;
                        String strBuilder = "" + "-" + getIndexForCell(i + 1, j + 1, d) + " " +
                                "-" + getIndexForCell(i + 1, j2 + 1, d) + " 0";
                        if(doWrite)
                            listToPrint.add(strBuilder);
                    }
                }
            }
        }

        for(int i=0; i<grilleSudoku.size();i++){
            for(int j=0; j<grilleSudoku.get(i).size();j++){
                for(int d=1; d<=grilleSudoku.size();d++){
                    for(int i2 = i+1; i2<grilleSudoku.size();i2++){
                        if(grilleSudoku.get(i).get(j)==d && grilleSudoku.get(i2).get(j)==d){
                            listToPrint.clear();
                            listToPrint.add("IMPOSSIBLE");
                            return;
                        }
                        boolean doWrite = true;
                        if((grilleSudoku.get(i).get(j)>0&&grilleSudoku.get(i).get(j)!=d)||
                                (grilleSudoku.get(i2).get(j)>0&&grilleSudoku.get(i2).get(j)!=d))
                            doWrite = false;
                        String strBuilder = "" + "-" + getIndexForCell(i + 1, j + 1, d) + " " +
                                "-" + getIndexForCell(i2 + 1, j + 1, d)+" 0";
                        if(doWrite)
                            listToPrint.add(strBuilder);
                    }
                }
            }
        }

        for(int i=0;i<grilleSudoku.size();i++){
            for(int j=0;j<grilleSudoku.get(i).size();j++){
                int value = (int)((int)((i+1)/Math.sqrt(grilleSudoku.size()))*Math.sqrt(grilleSudoku.size()));
                if(value==0)
                    value=(int)Math.sqrt(grilleSudoku.size());
                for(int i2=i+1;i2<value;i2++){
                    for(int j2=j+1;j2<value;j2++){
                        for(int d = 1; d<grilleSudoku.size();d++){
                            if(grilleSudoku.get(i).get(j)==d && grilleSudoku.get(i2).get(j2)==d){
                                listToPrint.clear();
                                listToPrint.add("IMPOSSIBLE");
                                return;
                            }
                            boolean doWrite = true;
                            if((grilleSudoku.get(i).get(j)>0&&grilleSudoku.get(i).get(j)!=d)||
                                    (grilleSudoku.get(i2).get(j2)>0&&grilleSudoku.get(i2).get(j2)!=d)) {
                                doWrite = false;
                                System.out.println("test");
                            }
                            String strBuilder = "" + "-" + getIndexForCell(i + 1, j + 1, d) + " " +
                                    "-" + getIndexForCell(i2 + 1, j2 + 1, d) + " 0";
                            if(doWrite)
                                listToPrint.add(strBuilder);
                        }
                    }
                }
            }
        }
    }

    private int getIndexForCell(int i, int j, int number){
        final String o = String.valueOf(Integer.parseInt("" + i + j + number));
        if(!listVar.contains(o))
            listVar.add(o);
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
