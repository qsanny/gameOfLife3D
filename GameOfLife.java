import java.util.Scanner;
import java.util.Vector;

/**
 * GameOfLife
 */
public class GameOfLife {

    public static void main(String[] args) {
        Vector<Cell> cells = new Vector<Cell>();
        cells.add(new Cell(5, 5, true));
        cells.add(new Cell(5, 6, true));
        cells.add(new Cell(5, 7, true));
        cells.add(new Cell(5, 8, true));
        cells.add(new Cell(5, 9, true));

        Scanner sc = new Scanner(System.in);
        Grid g = new Grid(15, 15, cells);
        System.out.println("Welcome to game of life 2D");
        g.print();

        for (int i = 0; i < 100; i++) {
            sc.nextLine();
            g.nextGeneration();
            g.print();
        }
    }
}
