import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;

public class Server {

    static int MAX_GEN = 2;
    static Vector<Cell> cells = new Vector<Cell>();
    static Grid grid = new Grid();

    static int rows = 15, cols = 15, depths = 15;
    public static void main(String arg[]){
        
    try{
        Registry registry = LocateRegistry.createRegistry (1099);

        if (arg.length == 1 ){
            String inputfile = arg[0];
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(inputfile));
                // get number of generation
                String line = reader.readLine();
                if (line != null){
                    MAX_GEN = Integer.parseInt(line);
                }

                // get the size on the grid in the format widht height depth
                line = reader.readLine();
                if (line != null){
                    String[] sizes = line.split(" ");
                    if (sizes.length!= 3){
                        System.err.println("Il faut trois valeurs pour chaque axes");
                        reader.close();
                        return;
                    }
                    rows = Integer.parseInt(sizes[0]);
                    cols = Integer.parseInt(sizes[1]);
                    depths = Integer.parseInt(sizes[2]);

                }

                // get and set initial alive cells in the format x y z
                line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    String[] coordinate = line.split(" ");
                    if (coordinate.length != 3){
                        System.err.println("Il faut trois valeurs pour les coordonées de la cellule vivante");
                        reader.close();
                        return;
                    }
                    cells.add(new Cell(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]), Integer.parseInt(coordinate[2]), true));
                    // read next line
                    line = reader.readLine();
                }
    
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            cells.add(new Cell(5, 5, 5, true));
            cells.add(new Cell(4, 5, 5, true));
            cells.add(new Cell(6, 5, 5, true));
            cells.add(new Cell(5, 4, 5, true));
            cells.add(new Cell(5, 6, 5, true));
            cells.add(new Cell(5, 5, 4, true));
            cells.add(new Cell(5, 5, 6, true));

        }

        Grid grid = new Grid(rows, cols, depths, cells);

        BagOfTaskImpl bot = new BagOfTaskImpl(grid, MAX_GEN);
        
        String nom="mybagoftask";

        String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/"+ nom;


        registry.bind(nom, bot);

        System.out.println("Enregistrement de l'objet avec l'url : " + url);
    }
        catch (Exception e){System.err.println("Erreur :"+e);}
    }
}
