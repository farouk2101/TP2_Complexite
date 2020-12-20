package MiniProjet_3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class ReadFile {

    private ArrayList<ArrayList<Integer>> grille;
    private String pathName;
    private String file[];

    public ReadFile(String pathName) {
        this.grille = new ArrayList<>();
        this.pathName = pathName;

        File file = new File(pathName);


    }


    public void write(ArrayList<ArrayList<Integer>> grille) {
        try {
            Writer wr = new FileWriter(pathName);
            for (int i = 0; i < grille.size(); i++) {
                for (int j = 0; j < grille.get(i).size(); j++) {

                    wr.write(String.valueOf(grille.get(j).get(i)));

                }
                wr.write("\n");
            }
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(ArrayList<String> satList, String newPath, int nbVar){
        File file = new File(newPath);
        file.delete();
        try {
            file.createNewFile();
        }catch(IOException exception){
            exception.printStackTrace();
        }
        Path path = Paths.get(newPath);
        try{
            Files.write(path, ("p cnf "+nbVar+" "+satList.size()+"\n").getBytes(), StandardOpenOption.APPEND);
            for(int i=0; i<satList.size();i++){
                Files.write(path, satList.get(i).getBytes(), StandardOpenOption.APPEND);
                if(i<satList.size()-1)
                    Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void read() {

        Path path = Paths.get(pathName);
        try {
            this.file = Files.readString(path).split("\n");
            for (int i = 0; i < file.length; i++) {
                String line[] = file[i].split("");
                ArrayList<Integer> ligne = new ArrayList<>();
                for (int j = 0; j < line.length; j++) {

                    ligne.add(Integer.parseInt(line[j]));

                }
                grille.add(ligne);
                //System.out.println(line[]);
            }


        } catch (IOException e) {

        }

    }

    public ArrayList<ArrayList<Integer>> getGrille() {
        return grille;
    }
}


