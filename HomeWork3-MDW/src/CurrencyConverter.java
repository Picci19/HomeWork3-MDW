import java.rmi.Remote;
import java.rmi.RemoteException;


public interface CurrencyConverter extends Remote{
	public double convert(String from, String to, double amount) throws RemoteException;
}
