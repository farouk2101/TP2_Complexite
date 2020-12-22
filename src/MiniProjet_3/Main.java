package MiniProjet_3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        // Choose a path
        String path = "./ressources/sudoku.txt";
        System.out.print("Fichier du sodoku (" + path + ") : ");
        String inputPath = sc.nextLine();
        if (!"".equals(inputPath)) path = inputPath;

        // Choose output file
        String outPath = "./ressources/DIMACS_CNF.txt";
        System.out.print("Sauvegarder sous (" + outPath + ") : ");
        String inputOutPath = sc.nextLine();
        if (!"".equals(inputOutPath)) outPath = inputOutPath;

        Long start = start = System.currentTimeMillis();
        SudokuToSat sudokuToSat = new SudokuToSat(path, outPath);
        System.out.println("Terminé en " + (System.currentTimeMillis() - start) + "ms.");
        System.out.println("Sauvegardé sous " + outPath);
    }

}
