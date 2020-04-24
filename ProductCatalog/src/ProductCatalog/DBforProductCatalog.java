package ProductCatalog;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBforProductCatalog {

	public static void main(String[] args) {
		Connection c = null;

		try {
		// register the driver 
		Class.forName("org.sqlite.JDBC");
		// create a connection to the database 
		c = DriverManager.getConnection("jdbc:sqlite:ProductCatalog.db");
		}

		catch ( Exception e ) {
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		System.exit(0);
		}

		System.out.println("database successfully created");
		}

}
