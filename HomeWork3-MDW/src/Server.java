import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements CurrencyConverter {

	private static final long serialVersionUID = 1L;

	public Server() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	public double convert(String from, String to, double amount)
			throws RemoteException {
		double converted = 0.0;
		if (from.equals("EUR") && to.equals("USD"))
			converted = amount * 1.34;
		else if (from.equals("USD") && to.equals("EUR"))
			converted = amount * 0.74;
		else if (from.equals("EUR") && to.equals("GBP"))
			converted = amount * 0.84;
		else if (from.equals("GBP") && to.equals("EUR"))
			converted = amount * 1.18;
		else if (from.equals("GBP") && to.equals("USD"))
			converted = amount * 1.59;
		else if (from.equals("USD") && to.equals("GBP"))
			converted = amount * 0.62;
		return converted;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// install a security manager (uses a security policy)
			if (System.getSecurityManager() == null) {
				RMISecurityManager sm = new RMISecurityManager();
				System.setSecurityManager(sm);
			}

			// create remote object
			Server obj = new Server();

			// create rmi registry
			Registry registry = LocateRegistry.createRegistry(1099);

			// Bind the object instance to the name "CurrencyConverter"
			registry.rebind("CurrencyConverter", obj);

			System.out.println("RMI	server	started, "
					+ "listening to	incoming requests...");
		} catch (Exception e) {
			System.out.println("Error occurred: " + e.getLocalizedMessage());
		}

	}

}
