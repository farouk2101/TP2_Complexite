package MiniProjet_3;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]){

        ArrayList<Integer> test = new ArrayList<>();
        ArrayList<ArrayList<Integer>> test2 = new ArrayList<>();
        test.add(0);
        test.add(1);
        test.add(2);
        for (int i = 0; i < test.size();i++){
            test2.add(test);
        }
        String path = "./ressources/sudoku.txt";
        //ReadFile readFile = new ReadFile(path);
        //readFile.write(test2);
        //readFile.read();
        SudokuToSat sudokuToSat = new SudokuToSat(path);

    }

}
