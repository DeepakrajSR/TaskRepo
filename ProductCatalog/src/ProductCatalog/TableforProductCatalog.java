package ProductCatalog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class TableforProductCatalog {
	public static void main( String args[] )

	{
		
	Connection c = null;
	Statement stmt = null;

	try {
	// Create or open already created database ProductCatalog.db
	Class.forName("org.sqlite.JDBC");
	c = DriverManager.getConnection("jdbc:sqlite:ProductCatalog.db");
	System.out.println("Database Opened...\n");

	stmt = c.createStatement();

	// SQL statement for creating a new table  
	String sql = "CREATE TABLE ProductCatalogs " +
	"(p_id INTEGER PRIMARY KEY AUTOINCREMENT," +
	" p_name TEXT NOT NULL, " +
	" p_category TEXT NOT NULL) ";

	stmt.executeUpdate(sql);
	stmt.close();
	c.close();

	}

	catch ( Exception e ) {

	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	System.exit(0);
	}
	System.out.println("Successfully Created ProductCatalogs Table ");
}
}
