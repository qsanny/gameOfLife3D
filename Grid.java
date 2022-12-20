import java.io.Serializable;
import java.util.Vector;

public class Grid implements Serializable {
    private int rows = 10, cols = 10, depth = 10;
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

        // for (int c = 0; c < initCells.size(); c++) {
        //     if (initCells.get(c) == this.cells[i][j][k]){
        //         this.cells[i][j][k] = initCells.get(c);
        //     }
        // }

    }

    public boolean isInside(int x, int y, int z){
        if (x <0 || x >= this.cols || y < 0 || y >= this.rows ||  z< 0 || z >= this.depth){
            return false;
        }
        return true;
    }

    public Vector<Cell> getCellNeighbour(Cell c){
        Vector<Cell> n = new Vector<Cell>();
        for (int i = c.x -1; i <= c.x+1; i++) {
            for (int j = c.y -1; j <= c.y+1; j++) {
                for (int k = c.z -1; k <= c.z+1; k++) {
                    if (isInside(i, j, k) && c != this.cells[i][j][k]){
                        n.add(this.cells[i][j][k]);
                    }
                }
            }
        }
        return n;
    }

    public int getCellNeighbourCount(Cell c){
        Vector<Cell> n = getCellNeighbour(c);

        int count =0;
        for (Cell cell : n) {
            if (cell.isAlive){
                count++;
            }
        }

        return count; // remove the cell itself from the count
    }

    private boolean willSurvive(Cell c){
        int neighbourCount = getCellNeighbourCount(c);
        return neighbourCount ==  2 || neighbourCount == 3;
    }

    private boolean willBorn(Cell c){
        return getCellNeighbourCount(c) == 3 || getCellNeighbourCount(c) == 4;
    }

    public boolean getNextCellState(Cell c){
        if (c.isAlive) return willSurvive(c);
        else return willBorn(c);
    }

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

    public void updateNewCells(Vector<Cell> newCells) {
        for (int c = 0; c < newCells.size(); c++) {
            this.cells[newCells.get(c).x][newCells.get(c).y][newCells.get(c).z].setStatus(newCells.get(c).isAlive);
        }
    }

    public Cell get(int x, int y, int z){
        return cells[x][y][z];
    }

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

