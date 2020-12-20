package MiniProjet_2;

import java.io.*;
import java.util.Scanner;

public class Graph {
    private int[][] nodes;
    private int n;
    private int m;
    private boolean directed;

    public Graph(String path, boolean directed) {

        parse(path, directed);
    }

    public Graph(int n, boolean directed) {
        set(n, directed);
    }

    private void set(int n, boolean directed) {
        this.directed = directed;
        this.n = n;
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

    public Graph complementaire(){
        Graph graph = new Graph(n, directed);
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n;j++){
                if(!hasEdge(i, j)){
                    graph.addEdge(i, j);
                }
            }
        }
        return graph;
    }

    private void parse(String path, boolean directed) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            int n = -1;
            while((line = reader.readLine()) != null) {
                if (line.startsWith("#")) continue;
                Scanner sr = new Scanner(line);
                if (n < 0) {
                    n = sr.nextInt();
                    set(n, directed);
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
                '}';
    }
}
