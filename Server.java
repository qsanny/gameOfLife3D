import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
// import java.util.Vector;

public class Server {
    public static void main(String arg[]){
    try{
        Registry registry = LocateRegistry.createRegistry (1099);

        BagOfTaskImpl bot = new BagOfTaskImpl();
        
        
        
        String nom="mybagoftask";
        registry.bind(nom, bot); //enregistrement

        System.out.println("Serveur enregistré");
    }
        catch (Exception e){System.err.println("Erreur :"+e);}
    }
}
