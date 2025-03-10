package stockmanagementusingdbms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;



public class StockManagement 
{
	StockManagement(){
	try {
		Class.forName("com.mysql.jdbc.Driver"); //To connect with mysql database..
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}
	int search_id;
	int noofproduct=0;
	
	// To store the values in map object..
	Map<Integer,StockDto> map_obj = new HashMap<Integer,StockDto>();   
	
	// Set<Map.Entry<Integer, StockDto>> set_obj= map_obj.entrySet();	   
	
	
	public static void main(String[] args) {
					
			try 
			{
				StockManagement sm = new StockManagement();
				Scanner s = new Scanner(System.in);
				
			while(true) 
			{

			System.out.println("\n");
			 
			System.out.println("STOCK MANAGEMENT SYSTEM \n");
			System.out.println("1. Create\n");
			System.out.println("2. Update\n");
			System.out.println("3. Display\n");
			System.out.println("4. Restock\n");
			System.out.println("5. Client Table\n");
			System.out.println("6. Individual\n");
			System.out.println("7. Delete\n");
			System.out.println("8. Exit\n");
			
			System.out.print("Enter your Choice : ");
		    int choice1 = s.nextInt();
			System.out.println("\n");
		   
		
			switch (choice1)
			{
			case 1:
				sm.createMethod();
				
				break;
				
			case 2:
				sm.updateMethod();
				break;
				
			case 3:
				sm.displayMethod();
				
				break;
			
			case 4:
				sm.restockMethod();
				break;
				
			case 5:
				sm.clienttableMethod();
				break;
				
			case 6:
				sm.individualMethod();
				break;
				
			case 7:
				sm.deleteMethod();
				
				break;
				
			case 8:
				System.out.println("Thank you for using our Application\n");
				System.exit(0);
				break;
			
			default:
				System.out.println("Please enter any one choice 1 or 2 or 3 or 4 or 5 or 6 or 7!! \n");
				break;
			}	
			}
			}
			catch(InputMismatchException ime)
			{
				System.out.println("InputMismatchException is found in Main method !! ");	
				System.out.println(" ");
			}catch(Exception e)
			{
				System.out.println("An Exception Occured at Main Method "+e.getMessage());
			}
			
			
		
	}
	
	/* To get input of product_name, description, quantity_nos, buyer_price, selling_price, mrp_price, status
	   and store it in map_obj and database using connection (insert)   */
	void createMethod()
	{
	
		try {
			Scanner s_create = new Scanner(System.in);
			
		System.out.print("Enter the Number of Products\t: ");
		noofproduct = s_create.nextInt();
		
		int index=101;
		
		for(int i=0; i<noofproduct;i++)
		{
			StockDto sd= new StockDto();
		
			index++; 
			sd.setProduct_id(index);
			sd.setProduct_code();
				
			System.out.print("Enter the Product Name\t: "  );
			s_create.nextLine();
			sd.setProduct_name(s_create.nextLine());;
			
			System.out.print("Enter the Description of the Product\t: ");
			sd.setDesc_name(s_create.nextLine());;
	
			System.out.print("Enter Total Quantity\t: ");
			sd.setQuantity_nos(s_create.nextInt());;
			
			System.out.print("Enter the Buyer Price\t: ");
			sd.setBuyer_price(s_create.nextInt());;
			
			System.out.print("Enter the Selling Price\t: ");
			sd.setSelling_price(s_create.nextInt());;
			
			do {
			System.out.print("Enter the M.R.P Price\t: ");
			sd.setMrp_price(s_create.nextInt());;
			}while(sd.getMrp_price()<sd.getBuyer_price() && sd.getMrp_price()<sd.getSelling_price());
			
			
			sd.setStatus("ACTIVE");
			
			System.out.println(" ");
			
			map_obj.put(sd.getProduct_id(), sd);	
	
		}

		}
		catch(InputMismatchException ime) {
			System.out.println(ime+" Input Mis-match Exception found in createMethod !!");
			System.out.println(" ");
		}
		try {
			for(StockDto dto_obj:map_obj.values()) 
			{
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_db",
						"root","admin2104");
				
				Statement  ps = con.createStatement();
				ResultSet rs=ps.executeQuery(" SELECT ifnull(MAX(product_id)+1, 100) AS MAX_VAL FROM stock_db.stocks");
				rs.next();
				int p_id = rs.getInt(1);
				
					PreparedStatement pt = con.prepareStatement("  INSERT INTO stocks( product_code, product_name,  "
							+ "desc_name, quantity_nos,\r\n"
							+ " buyer_price, selling_price, mrp_price, status) \r\n"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?)");
					pt.setString(1, "PT-"+p_id);
					pt.setString(2, dto_obj.getProduct_name());
					pt.setString(3, dto_obj.getDesc_name());
					pt.setInt(4, dto_obj.getQuantity_nos());
					pt.setDouble(5, dto_obj.getBuyer_price());
					pt.setDouble(6, dto_obj.getSelling_price());
					pt.setDouble(7, dto_obj.getMrp_price());
					pt.setString(8, dto_obj.getStatus());	
					
					int i = pt.executeUpdate();
					System.out.println("Record Inserted: "+i);
					con.close();
			}
			map_obj.clear();
		}catch(Exception e) 
		{
			System.out.println(e+"Exception caught in  inside create method");
		}
		//    s_create.close();
		}
	
	void updateMethod() {
	    
		Scanner s_update = new Scanner(System.in);

		boolean active_id=false;
	   
	    try {
	  
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_db",
	    			"root","admin2104");
	    	
	    	
	    	System.out.print("Enter the Product Id you want to Update : ");
	        search_id = s_update.nextInt();
	        System.out.println(" ");

	        PreparedStatement pt_obj = con.prepareStatement("SELECT product_id, product_code, product_name, desc_name, "
        	+ "quantity_nos, buyer_price, selling_price, mrp_price, status FROM stocks WHERE product_id="+search_id);
	        
	        ResultSet rs= pt_obj.executeQuery();
        	while(rs.next()) {

	        System.out.println("Choose any one to Update:");
	        System.out.println("1. To Update Product Name");
	        System.out.println("2. To Update Description");
	        System.out.println("3. To Update M.R.P Price");
	        System.out.println("4. To Update Status");

	        System.out.print("Enter your Choice to Update: ");
	        int choice = s_update.nextInt();

	        switch (choice) {
	           
	        	case 1:
	                System.out.print("Enter the Product Name to Update: ");
	                s_update.nextLine();
	                String update_prod_name=s_update.nextLine();
	                
	                PreparedStatement ps1_obj = con.prepareStatement("UPDATE stocks SET product_name= ?  "
	            			+ "WHERE product_id=?");
	            	ps1_obj.setString(1, update_prod_name);
	            	ps1_obj.setInt(2, search_id);
	            	ps1_obj.executeUpdate();
	            	
	                System.out.println("Product Name has been Updated!!");
	                active_id=true;
	                
	                break;

	            case 2:
	                System.out.print("Enter the Product Description to Update: ");
	                s_update.nextLine();                                // consume the leftover newline
	                String update_desc=s_update.nextLine();
	                PreparedStatement ps2_obj = con.prepareStatement("UPDATE stocks SET desc_name= ?  "
	            			+ "WHERE product_id=?");
	            	ps2_obj.setString(1, update_desc);
	            	ps2_obj.setInt(2, search_id);
	            	ps2_obj.executeUpdate();
	            	
	                System.out.println("Product Description has been Updated!!");
	                active_id=true;
	                
	                break;

	            case 3:
	                System.out.print("Enter the M.R.P Price to Update: ");
	                float update_mrp = s_update.nextInt();
	                PreparedStatement ps3_obj = con.prepareStatement("UPDATE stocks SET mrp_price= ?  "
	            			+ "WHERE product_id=?");
	            	ps3_obj.setDouble(1, update_mrp);
	            	ps3_obj.setInt(2, search_id);
	            	ps3_obj.executeUpdate();
	      
	                System.out.println("Product M.R.P Price has been Updated!!");
	                active_id=true;
	                
	                break;

	            case 4:
	            	
	            	PreparedStatement ps4_obj = con.prepareStatement("UPDATE stocks SET status= ?  "
	            			+ "WHERE product_id=?");
	            	ps4_obj.setString(1, "ACTIVE");
	            	ps4_obj.setInt(2, search_id);
	            	ps4_obj.executeUpdate();
	      
	                System.out.println("Product Status has been Updated!!");
	                active_id=true;
	                
	                break;

	            default:
	                System.out.println("No Data has been Updated!!");
	                active_id=true;
	                break;
	        }
        	}
        	con.close();
		}
	    catch (Exception e) {
	        System.out.println(e+" Exception Handled in UPDATE Method!!");
	    }
	   
		if(!active_id) {
			System.out.println("\n"+"No Product ID Found / Incorrect");
		}
	    
	}

	
	void displayMethod()
	{
		boolean active_id=false;
		try {
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_db",
					"root","admin2104");
			
			System.out.println("Product ID \t Product Code \t Product Name \t Description \t Total Quantity "
					+ "\t Buying Price \t Selling Price \t M.R.P Price \t Status \n");
			
			Statement stat_obj = con.createStatement();
			ResultSet rs = stat_obj.executeQuery("(SELECT product_id, product_code, product_name, desc_name, quantity_nos, buyer_price, "
					+ "selling_price, mrp_price, status FROM stocks)");	
			
			while(rs.next())
			{
				if(rs.getString(9).equals("ACTIVE")) 
				{
				System.out.println(""+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getInt(5)+"\t\t"+
							rs.getDouble(6)+"\t\t"+rs.getDouble(7)+"\t\t"+rs.getDouble(8)+"\t\t"+rs.getString(9));
				active_id=true;
				}
			}
			con.close();
			}catch(Exception e)
			{
				System.out.println(e+"Exception caught in second case inside main method");
			}
			if(!active_id) {
			System.out.println("\n"+"No ACTIVE ID Found");
			}
	}
	
	void restockMethod()
	{
		Scanner s_restock = new Scanner(System.in);		
		
		boolean active_id=false;
		int choice;
		int update_stock;
		
		try {
			System.out.print("Enter the Product ID to Restock : ");
			search_id = s_restock.nextInt();
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_db",
					"root","admin2104");
			
			PreparedStatement pt_obj = con.prepareStatement("SELECT product_id, product_code, product_name, desc_name, "
			        	+ "quantity_nos, buyer_price, selling_price, mrp_price, status FROM stocks WHERE product_id="+search_id);
				        
	        ResultSet rs= pt_obj.executeQuery();
			while(rs.next()) 
			{
			
			System.out.println("Choose whether the product has been purchased (1) or sold (2) :");
			choice=s_restock.nextInt();  
			
			switch(choice) 
			{
			case 1:
				System.out.print("Enter the number of products you purchased :  ");
				int purchase_stock=s_restock.nextInt();
				
				PreparedStatement ps1_obj = con.prepareStatement("UPDATE stocks SET quantity_nos= ?  "
            			+ "WHERE product_id=?");
							
				update_stock=purchase_stock+rs.getInt(5);
	
				ps1_obj.setInt(1,update_stock);
				ps1_obj.setInt(2, search_id);
				
				int i=ps1_obj.executeUpdate();
				
				System.out.println(purchase_stock+" stock has been purchased and "+i+" row has been updated");
				active_id=true;
				break;
						
			case 2:
				System.out.print("Enter the number of products you sold :  ");
				int sold_stock = s_restock.nextInt();
				
				PreparedStatement ps2_obj = con.prepareStatement("UPDATE stocks SET quantity_nos= ?  "
            			+ "WHERE product_id=?");
				
				update_stock = rs.getInt(5)-sold_stock;
				
				ps2_obj.setInt(1,update_stock);
				ps2_obj.setInt(2, search_id);
				
				int j=ps2_obj.executeUpdate();
				
				System.out.println(sold_stock+" stock has been sold and "+j+" has been updated");
				active_id=true;
				break;
						
			default :
				System.out.println(" PLease enter number 1 or 2 !!");
					
			}	
			}
		con.close();
		}
		catch(Exception e)
		{
			System.out.println(e+"Exception Found in Restock Method !!");
		}
		
		
	}
	
	void clienttableMethod() 
	{
		Scanner s_client = new Scanner(System.in);
		
		boolean active_id=false;
		double profit;

		try 
		{
			System.out.print("Enter the Product ID to see the Client Table : ");
			search_id = s_client.nextInt();
			System.out.println(" ");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_db",
					"root","admin2104");
			
			System.out.println("Product ID \t\t Product Name \t\t Description \t\t Total Quantity "
					+ "\t\t Buying Price \t\t Selling Price \t\t M.R.P Price \t\t Profit \t\t Status  \n");
			
			Statement indi_obj = con.createStatement();
			ResultSet rs= indi_obj.executeQuery("SELECT product_id, product_code, product_name, desc_name, quantity_nos, buyer_price,\r\n"
					+ "		selling_price, mrp_price, status FROM stocks WHERE product_id="+search_id);
			while(rs.next()) {
				profit=rs.getDouble(7)-rs.getDouble(6);
				System.out.println(""+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getInt(5)+"\t\t"+
						rs.getDouble(6)+"\t\t"+rs.getDouble(7)+"\t\t"+rs.getDouble(8)+"\t\t"+profit+"\t\t"+rs.getString(9));
				active_id=true;
			}
			con.close();
		}catch(Exception e)
		{
			System.out.println(e+"Exception is found in Client Table Method !!");
		}
		if(!active_id) {
			System.out.println("\n"+"No Product ID Found/ Incorrect");
		}

		
	}
	
	void individualMethod()
	{
		Scanner s_individual = new Scanner(System.in);
		boolean active_id=false;
		try {
		
		System.out.print("Enter the Product ID to Search : ");
		search_id = s_individual.nextInt();
	
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_db",
			"root","admin2104");
		
		System.out.println("Product ID \t Product Code \t Product Name \t Description \t Total Quantity "
				+ "\t Buying Price \t Selling Price \t M.R.P Price \t Status \n");	
		
		Statement indi_obj = con.createStatement();
		ResultSet rs= indi_obj.executeQuery("SELECT product_id, product_code, product_name, desc_name, quantity_nos, buyer_price,\r\n"
				+ "		selling_price, mrp_price, status FROM stocks WHERE product_id="+search_id);
		while(rs.next()) {
			System.out.println(""+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getInt(5)+"\t\t"+
					rs.getDouble(6)+"\t\t"+rs.getDouble(7)+"\t\t"+rs.getDouble(8)+"\t\t"+rs.getString(9));
			active_id=true;
		}
		con.close();
		}
		catch(Exception e) 
		{
			System.out.println(e+" Exception is found in Individual Method");
		}
		if(!active_id) {
			System.out.println("\n"+"No Product ID Found/ Incorrect");
		}

	}
//	
	void deleteMethod()
	{
		boolean active_id=false;
		Scanner s_delete = new Scanner(System.in);
		try {
			System.out.print("Enter the Product ID to De-Activate : ");
			search_id = s_delete.nextInt();
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_db",
					"root","admin2104");
			
			 PreparedStatement pt_obj = con.prepareStatement("SELECT product_id, product_code, product_name, desc_name, "
			        	+ "quantity_nos, buyer_price, selling_price, mrp_price, status FROM stocks WHERE product_id="+search_id);
				        
			ResultSet rs= pt_obj.executeQuery();
			        	
			while(rs.next()) {
			
			PreparedStatement pt_del = con.prepareStatement("UPDATE stocks SET status = 'INACTIVE' WHERE  product_id=?");
			pt_del.setInt(1, search_id);
			
			int i=pt_del.executeUpdate();
			System.out.println("Deleted product : "+i);

			System.out.println("The given Product ID "+search_id +" has been De-Activated!!");
			active_id=true;
	     	}
	    con.close();
		}
		catch(Exception e)
		{
			System.out.println(e+" Exception Found in Delete Method");
		}
		if(!active_id) {
			System.out.println("\n"+"PRODUCT ID not Found/ Incorrect");
		}
		
	}
//	
//	

}
