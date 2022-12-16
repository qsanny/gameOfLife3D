import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BagOfTask extends Remote {
   public Task getTask() throws RemoteException;
   public void sendResult(Task t) throws RemoteException;
   public boolean isCompleted() throws RemoteException;

}
