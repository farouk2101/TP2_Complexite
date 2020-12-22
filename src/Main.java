import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choix mini-projet :");
        System.out.println("  1 : Vérificateur affectation variables.");
        System.out.println("  2 : Réduction du problème Zone Vide à SAT et brute force.");
        System.out.println("  3 : Réduction du jeu Sudoku à SAT.");
        System.out.print("Choix (1/2/3) : ");
        String input = sc.nextLine();
        if ("1".equals(input)) {
            MiniProjet_1.Main.main(args);
            return;
        }
        if ("2".equals(input)) {
            MiniProjet_2.Main.main(args);
            return;
        }
        if ("3".equals(input)) {
            MiniProjet_3.Main.main(args);
            return;
        }

        System.out.println("Choix invalide.");
    }
}
