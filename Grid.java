import java.util.Vector;

public class Grid {
    private int row = 10, col = 10;
    private Cell[][] cells;

    public Grid() {
        this.initGrid(new Vector<Cell>());
    }
    
    public Grid(int nr, int nc, Vector<Cell> initCells) {
        this.row = nr;
        this.col = nc;
        this.initGrid(initCells);
    }

    public void initGrid(Vector<Cell> initCells){
        this.cells = new Cell[this.row][this.col];

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j< this.col; j++){
                this.cells[i][j] = new Cell(i, j);
                for (int c = 0; c < initCells.size(); c++) {
                    if (initCells.get(c).x == i && initCells.get(c).y == j){
                        this.cells[i][j] = initCells.get(c);
                    }
                }
            }
        }

    }

    public boolean isInside(int x, int y){
        if (x <0 || x >= this.col || y < 0 || y >= this.row){
            return false;
        }
        return true;
    }

    private int getCellNeighbourCount(Cell c){
        int count = 0;
        for (int i = c.x -1; i <= c.x+1; i++) {
            for (int j = c.y -1; j <= c.y+1; j++) {
                if (isInside(i, j) && c != this.cells[i][j]){
                    if (this.cells[i][j].isAlive) count ++;
                }
            }
        }

        return count; // remove the cell itself from the count
    }

    private boolean willSurvive(Cell c){
        int neighbourCount = getCellNeighbourCount(c);
        return neighbourCount ==  2 || neighbourCount == 3;
    }

    private boolean willBorn(Cell c){
        return getCellNeighbourCount(c) == 3;
    }

    private boolean getNextCellState(Cell c){
        if (c.isAlive) return willSurvive(c);
        else return willBorn(c);
    }

    public void nextGeneration() {

        Vector<Cell> newCells = new Vector<Cell>();

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                Cell c = this.cells[i][j];
                newCells.add(new Cell(i, j, getNextCellState(c)));
            }
        }

        for (int c = 0; c < newCells.size(); c++) {
            this.cells[newCells.get(c).x][newCells.get(c).y].isAlive = newCells.get(c).isAlive;
        }
    }

    public void print(){
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j< this.col; j++){
                System.out.printf("%s", this.cells[i][j]);
            }
            System.out.println();
        }
    }
}

