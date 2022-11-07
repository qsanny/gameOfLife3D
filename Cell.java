public class Cell {

    public boolean isAlive;
    public int x, y = 0;


    public Cell(int x, int y){
        this.isAlive = false;
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, boolean isAlive){
        this.isAlive = isAlive;
        this.x = x;
        this.y = y;
    }

    public void  setAlive() {
        this.isAlive = true;
    }

    public void  setDeade() {
        this.isAlive = false;
    }

    public String toString() {
        return this.isAlive ? "X":" ";
    }
    
    
}
