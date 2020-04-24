package ProductCatalog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ProductCatalogs {

	public static void main(String[] args) {
String flag = "Y";
		
		do {
			System.out.println("Select DML Operation For ProductCatalogs Table...");
			System.out.println("1. Add a product");
			System.out.println("2. Delete a product");
			System.out.println("3. View products");
			System.out.println("4. Exit");
			
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter a choice: ");
			int n = reader.nextInt();
			
			Connection c= null;
			Statement stmt = null;
		
			try {
				// register the driver 
				Class.forName("org.sqlite.JDBC");
				
				// create a connection to the database
				c = DriverManager.getConnection("jdbc:sqlite:ProductCatalog.db");
				c.setAutoCommit(false);
				
				//Create a Statement object for sending SQL statements to the database.
				stmt = c.createStatement();
				
				String name="",sql="", category="";
				int id;
				Scanner scanCat,scanName;
				
				switch(n) {
				
				case 1:
					
					System.out.println("Select a category");
					System.out.println("1. Laptop");
					System.out.println("2. Mobile");
					scanCat = new Scanner(System.in);
					System.out.println("Enter a choice: ");
					int n1 = scanCat.nextInt();
					
					
					scanName = new Scanner(System.in);
					
					
					if (n1 == 1) {
						category = "Laptop";
						System.out.println("Enter Product Name:");
						name =scanName.nextLine();
						sql = "INSERT INTO ProductCatalogs (p_name,p_category) " + 
								   "VALUES ('" +name+ "', '"+category+ "')";
					}
					else if(n1 == 2) {
						category = "Mobile";
						System.out.println("Enter Product Name:");
						name =scanName.nextLine();
						sql = "INSERT INTO ProductCatalogs (p_name,p_category) " + 
						   "VALUES ('" +name+ "', '"+category+ "')";
					}
					else {
						System.out.println(" Wrong Choice....");
						break;
					}
					stmt.executeUpdate(sql);
					System.out.println("Inserted Successfully");

					break;
					
				case 2:
					
					System.out.println("Enter Product id");
					scanName = new Scanner(System.in);
					id = scanName.nextInt();
					
					sql = "DELETE FROM ProductCatalogs where p_id ="+ id+";";
					stmt.executeUpdate(sql);
					System.out.println("Deleted successfully");
					
					break;
					
				case 3:
					System.out.println("Select any of the below option");
					System.out.println("1. View all");
					System.out.println("2. View Laptops");
					System.out.println("3. View mobiles");
					Scanner scanOption = new Scanner(System.in);
					System.out.println("Enter a choice: ");
					int n2 = scanOption.nextInt();
					
					if (n2 == 1) {
					ResultSet rs = stmt.executeQuery("SELECT * FROM ProductCatalogs;");
					System.out.println("ID\t Name\t Categoty ");
					
					while(rs.next()) {
						id = rs.getInt("p_id");
						name = rs.getString("p_name");
						category = rs.getString("p_category");
						System.out.println(id+"\t "+name+" \t "+category);
					}
					rs.close();
					break;
					}
					
					else if (n2 == 2) {
						
					ResultSet rs1 = stmt.executeQuery("SELECT * FROM ProductCatalogs WHERE p_category LIKE 'Laptop';");
					System.out.println("ID\t Name\t Categoty ");
					
					while(rs1.next()) {
						id = rs1.getInt("p_id");
						name = rs1.getString("p_name");
						category = rs1.getString("p_category");
						System.out.println(id+"\t "+name+" \t "+category);
					}
					rs1.close();
					break;
					}
					
					else if (n2 == 3) {
					
					ResultSet rs2 = stmt.executeQuery("SELECT * FROM ProductCatalogs WHERE p_category LIKE 'Mobile';");
					System.out.println("ID\t Name\t Categoty ");
					
					while(rs2.next()) {
						id = rs2.getInt("p_id");
						name = rs2.getString("p_name");
						category = rs2.getString("p_category");
						System.out.println(id+"\t "+name+"\t "+category);
					}
					rs2.close();
					break;
					}
					else {
						System.out.println(" Wrong Choice...");
						break;	
					}
				case 4:

					System.exit(0);
					break;
					
				default:

					System.out.println(" Wrong Choice...");
					break;
				}
				stmt.close();
				c.commit();
				c.close();
			}
			catch ( Exception e) {

				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			
			System.out.println("Continue Y OR N?");
			reader=new Scanner(System.in);
			flag=reader.nextLine();
		
		}while (flag.equalsIgnoreCase("Y"));
		System.exit(0);

	}

}
