package MiniProjet_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // Choose a graph file
        String graphPath = "./ressources/graph.txt";
        System.out.print("Fichier du graphe (" + graphPath + ") : ");
        String inputGaphPath = sc.nextLine();
        if (!"".equals(inputGaphPath)) graphPath = inputGaphPath;

        // Choose output file
        String outPath = "./ressources/DIMACS_CNF.txt";
        System.out.print("Sauvegarder sous (" + outPath + ") : ");
        String inputOutPath = sc.nextLine();
        if (!"".equals(inputOutPath)) outPath = inputOutPath;

        // Choose k
        int k = 2;
        System.out.print("Taille de la zone vide (" + k + ") : ");
        String inputkStr = sc.nextLine();
        if (!"".equals(inputkStr)) k = Integer.parseInt(inputkStr);

        Long start;

        Graph graph = new Graph();
        System.out.println("Construction du graphe...");
        start = System.currentTimeMillis();
        graph.parse(graphPath, false, k);
        System.out.println("Terminé en " + (System.currentTimeMillis() - start) + "ms.");
        System.out.println("Génération des clauses...");
        start = System.currentTimeMillis();
        graph.genClause(outPath);
        System.out.println("Terminé en " + (System.currentTimeMillis() - start) + "ms.");
        System.out.println("Sauvegardé sous " + outPath);

        // Brute forcer ?
        boolean brute = false;
        System.out.print("Résoudre par brute force ? (Oui/Non) : ");
        String inputBrute = sc.nextLine();
        if (!"".equals(inputBrute) && (inputBrute.toLowerCase().equals("o") || inputBrute.toLowerCase().equals("oui") || inputBrute.equals("1")))
            brute = true;

        if (brute) {
            System.out.println("Génération des affectations possibles...");
            start = System.currentTimeMillis();
            graph.genAffect();
            System.out.println("Terminé en " + (System.currentTimeMillis() - start) + "ms.");
            System.out.println("Résolution par brute force...");
            start = System.currentTimeMillis();
            graph.bruteForce(outPath);
            System.out.println("Terminé en " + (System.currentTimeMillis() - start) + "ms.");

            return;
        }
    }
}
