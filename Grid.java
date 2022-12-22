import java.io.Serializable;
import java.util.Vector;

public class Grid implements Serializable {
    private int rows = 10, cols = 10, depth = 10; // les dimensions de la grille sur chaque axe
    private Cell[][][] cells;

    public Grid() {
        this.initGrid(new Vector<Cell>());
    }
    
    public Grid(int nr, int nc, int nd, Vector<Cell> initCells) {
        this.rows = nr;
        this.cols = nc;
        this.depth = nd;
        this.initGrid(initCells);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getDepth() {
        return depth;
    }

    // initialiser la grille ave lesc ellules vivantes de départ
    public void initGrid(Vector<Cell> initCells){
        this.cells = new Cell[this.rows][this.cols][this.depth];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j< this.cols; j++){
                for (int k = 0; k< this.depth; k++){
                    this.cells[i][j][k] = new Cell(i, j, k);
                }
            }
        }

        initCells.forEach((c) ->{
            this.cells[c.x][c.y][c.z].setStatus(c.isAlive);
        } );

    }

    // determiner si une coordonnée est bien incluse dans notre environnement
    public boolean isInside(int x, int y, int z){
        if (x <0 || x >= this.cols || y < 0 || y >= this.rows ||  z< 0 || z >= this.depth){
            return false;
        }
        return true;
    }

    // obtenir les voisins d'une cellule
    public Vector<Cell> getCellNeighbor(Cell c){
        Vector<Cell> neighbor = new Vector<Cell>();
        for (int i = c.x -1; i <= c.x+1; i++) {
            for (int j = c.y -1; j <= c.y+1; j++) {
                for (int k = c.z -1; k <= c.z+1; k++) {
                    if (isInside(i, j, k) && c != this.cells[i][j][k]){
                        neighbor.add(this.cells[i][j][k]);
                    }
                }
            }
        }
        return neighbor;
    }

    // obtenir le nombre de voisin vivant d'une cellule
    public int getCellNeighborCount(Cell c){
        Vector<Cell> n = getCellNeighbor(c);

        int count =0;
        for (Cell cell : n) {
            if (cell.isAlive){
                count++;
            }
        }

        return count;
    }

    // retourne si une cellull survivra
    private boolean willSurvive(Cell c){
        int neighbourCount = getCellNeighborCount(c);
        return neighbourCount ==  2 || neighbourCount == 3;
    }

    // retourne si une cellull naitra
    private boolean willBorn(Cell c){
        return getCellNeighborCount(c) == 3 || getCellNeighborCount(c) == 4;
    }

    // retorune l'etat suivant d'une cellule
    public boolean getNextCellState(Cell c){
        if (c.isAlive) return willSurvive(c);
        else return willBorn(c);
    }

    // utiliser pour iterer de generation en génértaion
    public void nextGeneration() {

        Vector<Cell> newCells = new Vector<Cell>();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                for (int k = 0; k< this.depth; j++){
                    Cell c = this.cells[i][j][k];
                    newCells.add(new Cell(i, j, k, getNextCellState(c)));
                }
            }
        }

        for (int c = 0; c < newCells.size(); c++) {
            this.cells[newCells.get(c).x][newCells.get(c).y][newCells.get(c).z].setStatus(newCells.get(c).isAlive);
        }
    }

    // mettre a jour la grille avec les cellulles d'une prochiane generation
    public void updateNewCells(Vector<Cell> newCells) {
        for (int c = 0; c < newCells.size(); c++) {
            this.cells[newCells.get(c).x][newCells.get(c).y][newCells.get(c).z].setStatus(newCells.get(c).isAlive);
        }
    }

    // obtenir une cellull a partir de ces coordonnees
    public Cell get(int x, int y, int z){
        return cells[x][y][z];
    }

    // affichier la grille
    public void print(){
        System.out.println("Liste des cellules vivantes : ");
        boolean isEmpty = true;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j< this.cols; j++){
                for (int k = 0; k< this.depth; k++){
                    Cell c = get(i, j, k);
                    if (c.isAlive){
                        isEmpty = false;
                        System.out.printf("%s\n", c);
                    }
                }
            }
        }

        if(isEmpty){
            System.out.printf("Aucune cellule vivante\n");
        }
    }
}

