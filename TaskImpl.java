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
        System.out.printf("%s -> %s \n", c,  nextState);
    }
    
}
