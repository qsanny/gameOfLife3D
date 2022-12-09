import java.io.Serializable;

public class Cell implements Serializable {

    public boolean isAlive;
    public int x, y, z = 0;


    public Cell(int x, int y, int z){
        this.isAlive = false;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Cell(int x, int y, int z, boolean isAlive){
        this.isAlive = isAlive;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        Cell c = (Cell)obj;
        return x == c.x && y == c.y && z == c.z;
    }

    public void  setAlive() {
        this.isAlive = true;
    }

    public void  setDeade() {
        this.isAlive = false;
    }

    public void  setStatus(boolean status) {
        this.isAlive = status;
    }

    public String toString() {
        String str = String.format("(%d, %d, %d, %b)", x, y, z, isAlive);
        return str;
    }
    
    
}
