package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class QueryServlet
 */
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Creating data source
		DataSource datasource;
		// Lookup the JNDI data source
		Properties p = new Properties();
		// configure the service provider url: "t3://localhost:7001"
		p.put(Context.PROVIDER_URL, "t3://localhost:7001");

		// configure the initial context factory.
		// we use WebLogic context factory
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		try {
			InitialContext ctx = new InitialContext(p);
			datasource = (DataSource) ctx.lookup("localhost/mimdw");
			// invoke the object method
			Connection c = datasource.getConnection();
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM test";
			ResultSet rs = stmt.executeQuery(sql);

			// Loop and extract received data
			String test = "";
			while (rs.next()) {
				test += rs.getString("test") + "\n";
			}
			out.println(test);

			// Release the connections
			rs.close();
			stmt.close();
			c.close();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
