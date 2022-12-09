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
        // compute the next state of the cell
        nextState = g.getNextCellState(c);
    }
    
}
