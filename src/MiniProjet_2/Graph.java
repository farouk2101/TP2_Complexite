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

    public Graph() { }


    private void set(int n, boolean directed, int k) {
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

    public void parse(String path,  boolean directed, int k) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            int n = -1;
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
        try {
            writer = new FileWriter("./ressources/temp_affect_zone.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=0; i<Math.pow(2, n*k); i++) {
            String rep = Integer.toBinaryString(i);
            int[] combi = new int[n*k];
            Arrays.fill(combi, 0);
            for (int j = 0; j<rep.length(); j++) {
                combi[combi.length - rep.length() + j] = Integer.parseInt("" + rep.charAt(j));;
            }

            // traduction
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < combi.length; j++) {
                int aff = combi[j] == 1 ? j + 1 : -(j + 1);
                line.append(aff + " ");
            }

            try {
                writer.write(line + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void bruteForce(String pathClauses){
        for (int i=0; i<Math.pow(2, n*k); i++) {
             if(Main.verifMultiple(pathClauses, i)){
                 System.out.println("Solution trouvée !");
                 return;
             }
        }

        System.out.println("Aucune solution.");
    }


    private int getVarIndex(int i, int v) {
        if (i+1 > k || v+1 > n ) {
            System.out.println("i = " + i + " v = " + v);
        }
        return n*i + v + 1;
    }

    public void genClause(String outPath){
        StringBuilder clauses = new StringBuilder();
        int clauseCount = 0;
        for (int i = 0; i<k; i++) {
            String clause = "";
            for (int v=0; v<n; v++) {
                clause += getVarIndex(i, v) +" ";
            }

            clause = clause.substring(0, clause.length()-1);
            clauses.append(clause + " 0\n");
            clauseCount++;
        }

        for (int v=0; v<n; v++) {
            for (int i=0; i<k; i++) {
                for (int j=i+1; j<k; j++) {
                    clauses.append("-"+ getVarIndex(i, v) +" -"+getVarIndex(j, v)+" 0\n");
                    clauseCount++;
                }
            }
        }

        for (int i=0; i<k; i++) {
            for (int v=0; v<n; v++) {
                for (int u=v+1; u<n; u++) {
                    clauses.append("-"+getVarIndex(i, v)+" "+"-"+getVarIndex(i, u)+" 0\n");
                    clauseCount++;
                }
            }
        }


        for (int v=0; v<n; v++) {
            for (int u = v; u < n; u++) {
                if (hasEdge(u, v)) {

                    for (int i = 0; i < k; i++) {
                        for (int j = 0; j < k; j++) {
                            clauses.append("-" + getVarIndex(i, v) + " -" + getVarIndex(j, u)+" 0\n");
                            clauseCount++;
                        }
                    }
                }
            }
        }
        clauses.deleteCharAt(clauses.length() - 1);

        try {
            FileWriter writer = new FileWriter(outPath);
            writer.write("p cnf " + n*k + " " + clauseCount + "\n");
            writer.write(clauses.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
