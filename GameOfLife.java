import java.util.Scanner;
import java.util.Vector;

/**
 * GameOfLife
 */
public class GameOfLife {

    public static void main(String[] args) {
        Vector<Cell> cells = new Vector<Cell>();
        cells.add(new Cell(5, 5, 0, true));
        cells.add(new Cell(5, 6,0, true));
        cells.add(new Cell(5, 7,0, true));
        cells.add(new Cell(5, 8,0, true));
        cells.add(new Cell(5, 9,0, true));

        Scanner sc = new Scanner(System.in);
        Grid g = new Grid(15, 15, 0, cells);
        System.out.println("Welcome to game of life 3D");
        g.print();
        int NB_GEN = 100;
        for (int i = 0; i < NB_GEN; i++) {
            sc.nextLine();
            g.nextGeneration();
            g.print();
        }
    }
}
