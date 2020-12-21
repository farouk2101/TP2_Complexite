package MiniProjet_2;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import MiniProjet_1.Main;

public class Graph {
    private int[][] nodes;
    private int n;
    private int m;
    private boolean directed;
    private int k;

    public Graph(String path, boolean directed) {

        parse(path, directed);
        genAffect();
        genClause();
        bruteForce();
    }


    private void set(int n, boolean directed,int k) {
        this.directed = directed;
        this.n = n;
        this.k = k;
        nodes = new int[n][n];
        m = 0;
        // initialiser la liste d'adjacence à 0;
        for (int i = 0; i < n ; i++)
            for (int j = 0; j < n ; j++)
                nodes[i][j] = 0;
    }

    public void addEdge(int i, int j) {
        if (hasEdge(i, j)) return;

        nodes[i][j] = 1;
        m++;
        if (directed) {
            nodes[j][i] = 1;
            m++;
        }
    }

    public void removeEdge(int i, int j) {
        if (!hasEdge(i, j)) return;

        nodes[i][j] = 0;
        m--;
        if (directed) {
            nodes[j][i] = 0;
            m--;
        }
    }

    public boolean hasEdge(int i, int j) {
        return nodes[i][j] == 1 || nodes[j][i] == 1;
    }

    public int vertexCount() {
        return n;
    }

    public int edgeCount() {
        return m;
    }



    private void parse(String path, boolean directed) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            int n = -1;
            Scanner sc = new Scanner(System.in);
            System.out.print("Quel k voulez vous choisir? : ");
            int k = sc.nextInt();
            while((line = reader.readLine()) != null) {
                if (line.startsWith("#")) continue;
                Scanner sr = new Scanner(line);
                if (n < 0) {
                    n = sr.nextInt();
                    set(n, directed,k);
                    continue;
                }

                addEdge(sr.nextInt(), sr.nextInt());
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String printNodes() {
        String s = "[ ";
        for (int i=0; i<n; i++) {
            for (int j=(directed ? 0 : i) ; j<n; j++) {
                if (hasEdge(i, j)) {
                    s += "(" + i + "," + j + ") ";
                }
            }
        }

        return s + "]";
    }


    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + printNodes() +
                ", n=" + n +
                ", m=" + m +
                ", directed=" + directed +
                ", k=" + k +
                '}';
    }

    public void genAffect(){
        FileWriter writer = null;
        FileWriter affectWriter = null;
        try {
            writer = new FileWriter("./ressources/affect_zone.txt");
            affectWriter = new FileWriter("./ressources/Affectation_variable.txt");
        } catch (IOException e) {
            e.printStackTrace();

        }
        for (int i=0; i<Math.pow(2, n*k); i++) {
            String rep = Integer.toBinaryString(i);
            String[] combi = new String[n*k];
            Arrays.fill(combi, "0");
            for (int j = 0; j<rep.length(); j++) {
                combi[combi.length - rep.length() + j] = String.valueOf(Integer.parseInt("" + rep.charAt(j)));;
            }

            // traduction

                int j = 0;
                int v = 0;
            for(int p = 0;p<combi.length;p++){
                combi[p] = combi[p].equals("1") ? (j+1)+""+(v+1) : -(j+1)+""+(v+1);
                j++;
                if(j == k)
                    j = 0;
                v++;
                if(v == n)
                    v=0;

                }

            String comb = Arrays.toString(combi).substring(1,Arrays.toString(combi).length()-1).replaceAll(",","");
            try {
                writer.write(comb);
                if(i<Math.pow(2, n*k)-1){
                    writer.write("\n");
                }
                if(i==0){
                    System.out.println(comb);
                    affectWriter.write(comb);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            affectWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void bruteForce(){
        for (int i=0; i<Math.pow(2, n*k); i++) {
            Main.verifMultiple(i);
        }
    }


    public void genClause(){
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("./ressources/DIMACS_CNF.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int nbclauses=(k + n*(k-1)+ n*2*k+(k-1)*k*3);
        try {
            myWriter.write("p cnf " + n*k + " " + nbclauses);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        for(int i = 0;i<k;i++){
            for(int v = 0;v<n;v++){

                System.out.println("x_" +i+"-v"+v);

            }
        } */

        try {
            myWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i<k; i++) {
            String clause = "";
            for (int v=0; v<n; v++) {
                clause += (i+1)+""+(v+1)+" ";
                //clause += "x_i"+i+"-v"+v+" v";
            }

            clause = clause.substring(0, clause.length()-1);
            try {
                myWriter.write(clause +" 0\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        for (int v=0; v<n; v++) {
            for (int i=0; i<k; i++) {
                for (int j=i; j<k; j++) {
                    if (i==j) continue;
                    try {
                        myWriter.write("-"+(i+1)+""+(v+1) +" -"+(j+1)+""+(v+1)+" 0\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //myWriter.write("¬x_i"+i+"-v"+v +" v ¬x_i"+j+"-v"+v);
                }
            }
        }

        for (int i=0; i<k; i++) {
            for (int v=0; v<n; v++) {
                for (int u=v; u<n; u++) {
                    if (u==v) continue;
                    try {
                        myWriter.write("-"+(i+1)+""+(v+1)+" "+"-"+(i+1)+""+(u+1)+" 0\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //myWriter.write("¬x_i${i}-v${v} v ¬x_i${i}-v${u} ");
                }
            }
        }

        for (int v=0; v<n; v++) {
            for (int u = v; u < n; u++) {
                if (hasEdge(u, v)) {

                    for (int i = 0; i < k; i++) {
                        for (int j = 0; j < k; j++) {
                            if (i == j) continue;
                            try {
                                myWriter.write("-" + (i+1) +""+ (v+1) + " -" + (j+1) +""+ (u+1)+" 0");
                                if(j<k-2){
                                    myWriter.write("\n");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        }

        try {
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
