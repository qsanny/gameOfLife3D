import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TaskImpl implements Task{

    Cell c;
    Grid g;

    boolean nextState;

    public TaskImpl(Cell c, Grid g){
        this.c = c;
        this.g = g;
    }

    @Override
    public void execute() {
        
        nextState = g.getNextCellState(c);

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("debug.log", true));


        writer.append(String.format("%s: [%d] -> %s \n", c, g.getCellNeighbourCount(c),   g.getNextCellState(c)));
        for (Cell cell : g.getCellNeighbour(c)) {
            writer.append(String.format("\t\t%s \n", cell));
        }

        System.out.printf("%s -> %s \n", c,  nextState);

        writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
