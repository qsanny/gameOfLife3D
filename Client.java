import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;

public class Client {

    public static void main(String[] args) throws UnknownHostException {

        String nom="mybagoftask";
        String serveur = InetAddress.getLocalHost().getHostAddress();

        if(args.length == 1){
            serveur = args[0];
        }
        String url = "rmi://" + serveur + "/"+ nom;
        System.out.println("Obtention de l'objet distant de l'url : " + url);

        try{
            BagOfTask bot=(BagOfTask) Naming.lookup(url);
            Task t = bot.getTask();
            while(!bot.isCompleted()){
                while (t != null){
                    t.execute();
                    bot.sendResult(t);
                    t = bot.getTask();
                }
            }
        }
            catch (Exception e){
            System.err.println("Erreur :"+e);
        }
    }
    
}
