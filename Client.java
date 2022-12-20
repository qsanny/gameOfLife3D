import java.rmi.Naming;

public class Client {

    public static void main(String[] args) {
        try{
            BagOfTask bot=(BagOfTask) Naming.lookup("mybagoftask");
            Task t = bot.getTask();

            while(true){
                while (t != null){
                    t.execute();
                    bot.sendResult(t);
                    t = bot.getTask();
                }
            }

            // System.out.println("BOT IS DONE");
        }
            catch (Exception e){
            System.err.println("Erreur :"+e);
        }
    }
    
}
