import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import weblogic.rmi.Naming;

public class Client {

	/**
	 * @param args
	 * @throws NotBoundException
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException,
			MalformedURLException, RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		Registry registry = LocateRegistry.getRegistry();
		CurrencyConverter c = (CurrencyConverter) registry.lookup("CurrencyConverter");
		try {
			System.out.println(c.convert("EUR", "USD", 100.6)); // Example currencies and amount
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
