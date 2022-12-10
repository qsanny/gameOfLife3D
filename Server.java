import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;

public class Server {
    public static void main(String arg[]){
    try{
        Registry registry = LocateRegistry.createRegistry (1099);


        Vector<Cell> cells = new Vector<Cell>();
        cells.add(new Cell(5, 5, 5, true));
        cells.add(new Cell(4, 5, 5, true));
        cells.add(new Cell(6, 5, 5, true));

        cells.add(new Cell(5, 4, 5, true));
        cells.add(new Cell(5, 6, 5, true));

        cells.add(new Cell(5, 5, 4, true));
        cells.add(new Cell(5, 5, 6, true));

        Grid g = new Grid(15, 15, 15, cells);

        BagOfTaskImpl bot = new BagOfTaskImpl(g);
        
        
        
        String nom="mybagoftask";
        registry.bind(nom, bot); //enregistrement

        System.out.println("Serveur enregistré");
    }
        catch (Exception e){System.err.println("Erreur :"+e);}
    }
}
