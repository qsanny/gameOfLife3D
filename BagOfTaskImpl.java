import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class BagOfTaskImpl extends UnicastRemoteObject implements BagOfTask {

    Grid grid;

    int currentI = 0,currentJ = 0, currentK = 0;
    boolean endOfGen = false;

    Vector<Cell> newGenCells = new Vector<Cell>();


    public BagOfTaskImpl() throws RemoteException{

        Vector<Cell> cells = new Vector<Cell>();
        cells.add(new Cell(5, 5, 0, true));
        cells.add(new Cell(5, 6,0, true));
        cells.add(new Cell(5, 7,0, true));
        cells.add(new Cell(5, 8,0, true));
        cells.add(new Cell(5, 9,0, true));

        Grid g = new Grid(15, 15, 15, cells);
        System.out.println("Welcome to game of life 3D");

        this.grid = g;

        g.print();

        
    }

    public BagOfTaskImpl(Grid grid) throws RemoteException{
        this.grid = grid;

        grid.print();

    }  
    
    public Task getTask() throws RemoteException {

        if(endOfGen) {
            return null;
        }
        
        // System.out.printf("%d, %d, %d \n", currentI, currentJ, currentK);
        Cell currentCell = this.grid.get(currentI, currentJ, currentK);
        Task t = new TaskImpl(currentCell, this.grid);

        currentI++;
        if (currentI == this.grid.getRows() ){
            currentI = 0;
            currentJ++;

            if (currentJ == this.grid.getCols() ){
                currentJ = 0;
                currentK++;

                if (currentK == this.grid.getDepth() ){
                    currentK = 0;
                    // this board is complete
                    endOfGen = true;

                    System.out.println("END OF GEN" + endOfGen);
                }
            }
        }
        return t;
    }

    public void sendResult(Task task) throws RemoteException {
        TaskImpl t = (TaskImpl)task;
        Cell newCell = new Cell(t.c.x, t.c.y, t.c.z, t.nextState);
        this.newGenCells.add(newCell);
        // System.out.printf("got result %s \n", newGenCells.size());


        if (newGenCells.size() == grid.getCols() * grid.getRows() * grid.getDepth() ){
            grid.updateNewCells(newGenCells);
            System.out.println("One gen done");
            grid.print();
            endOfGen = true;
        }
    }
    
}
