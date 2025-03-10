package stockmanagementusingcollection;
import java.util.*;


public class StockManagement {

	int search_id;
	int noofproduct=0;
	
	Map<Integer,Stockvariables> map_obj = new HashMap<Integer,Stockvariables>();
	
	Set<Map.Entry<Integer, Stockvariables>> set_obj= map_obj.entrySet();
	
	
	public static void main(String[] args) {
		
			StockManagement sm = new StockManagement();
			
			while(true) 
			{
			try 
			{
			Scanner s = new Scanner(System.in);
			
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
			catch(InputMismatchException ime)
			{
				System.out.println("InputMismatchException is found in Main method !! ");	
				System.out.println(" ");
			}
			
			}
		
	}
	void createMethod()
	{
		Scanner s_create = new Scanner(System.in);
		
		
		try {
		System.out.print("Enter the Number of Products\t: ");
		noofproduct = s_create.nextInt();
		
		int index=100;
		
		for(int i=0; i<noofproduct;i++)
		{
			Stockvariables sv= new Stockvariables();
		
		index++; 
		sv.setProduct_id(index);
			
		System.out.print("Enter the Product Name\t: "  );
		s_create.nextLine();
		sv.setProduct_name(s_create.nextLine());;
		
		System.out.print("Enter the Description of the Product\t: ");
		sv.setDesc_name(s_create.nextLine());;

		System.out.print("Enter Total Quantity\t: ");
		sv.setQuantity_nos(s_create.nextInt());;
		
		System.out.print("Enter the Buyer Price\t: ");
		sv.setBuyer_price(s_create.nextInt());;
		
		System.out.print("Enter the Selling Price\t: ");
		sv.setSelling_price(s_create.nextInt());;
		
		do {
		System.out.print("Enter the M.R.P Price\t: ");
		sv.setMrp_price(s_create.nextInt());;
		}while(sv.getMrp_price()<sv.getBuyer_price() && sv.getMrp_price()<sv.getSelling_price());
		
		
		sv.setStatus("ACTIVE");
		
		System.out.println(" ");
		
		map_obj.put(sv.getProduct_id(), sv);
		
		}
		}
		catch(InputMismatchException ime) {
			System.out.println(ime+" Input Mis-match Exception found in createMethod !!");
			System.out.println(" ");
		}
		//    s_create.close();
		}
	
	void updateMethod() {
	    
		Scanner s_update = new Scanner(System.in);

		boolean active_id=false;
	   
	    try {
	    if(!map_obj.isEmpty()) 
		{
	        System.out.print("Enter the Product Id you want to Update : ");
	        search_id = s_update.nextInt();
	        System.out.println(" ");

	        Iterator<Map.Entry<Integer, Stockvariables>> itr = set_obj.iterator();

	        while (itr.hasNext()) {
	            Map.Entry<Integer, Stockvariables> entry = itr.next();
	            Stockvariables sv = entry.getValue();
	            if (search_id == sv.getProduct_id()) {

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
	                sv.setProduct_name(s_update.next());
	                System.out.println("Product Name has been Updated!!");
	                break;

	            case 2:
	                System.out.print("Enter the Product Description to Update: ");
	                s_update.nextLine(); // consume the leftover newline
	                sv.setDesc_name(s_update.nextLine());
	                System.out.println("Product Description has been Updated!!");
	                break;

	            case 3:
	                System.out.print("Enter the M.R.P Price to Update: ");
	                sv.setMrp_price(s_update.nextInt());
	                System.out.println("Product M.R.P Price has been Updated!!");
	                break;

	            case 4:
	                sv.setStatus("ACTIVE");
	                System.out.println("Product Status has been Updated!!");
	                break;

	            default:
	                System.out.println("No Data has been Updated!!");
	                break;
	        }
	      }
	     }
		}
	    if(map_obj.isEmpty()) {
			System.out.println("No Product Found");
		}
		else if(!active_id) {
			System.out.println("\n"+"No ACTIVE ID Found / Incorrect");
		}
	    } catch (InputMismatchException ime) {
	        System.out.println("InputMismatchException Handled in UPDATE Method!!");
	    }
	}

	
	void displayMethod()
	{
		boolean active_id=false;
		
		if(!map_obj.isEmpty()) 
		{
			System.out.println("Product ID \t Product Name \t Description \t Total Quantity "
					+ "\t Buying Price \t Selling Price \t M.R.P Price \t Status \n");
			Iterator<Map.Entry<Integer, Stockvariables>> itr = set_obj.iterator();
			while(itr.hasNext()) 
			{
			Map.Entry<Integer, Stockvariables> disitr_obj= itr.next();
			Stockvariables sv = disitr_obj.getValue();
				if((sv.getStatus()).equals("ACTIVE"))
				{
					System.out.println(sv.getProduct_id()+"\t"+sv.getProduct_name()+"\t"+sv.getDesc_name()
					+"\t"+sv.getQuantity_nos()+"\t"+sv.getBuyer_price()+"\t"
							+sv.getSelling_price()+"\t"+sv.getMrp_price()+"\t"+sv.getStatus());
				active_id=true;
				}
			}
		}
		if(map_obj.isEmpty()) {
			System.out.println("No Product Found");
		}
		else if(!active_id) {
			System.out.println("\n"+"No ACTIVE ID Found");
		}
	}
	
	void restockMethod()
	{
		Scanner s_restock = new Scanner(System.in);		
		
		boolean active_id=false;
		int choice;

		try {
		if(!map_obj.isEmpty()) 
		{
			System.out.print("Enter the Product ID to Restock : ");
			search_id = s_restock.nextInt();
			
			Iterator<Map.Entry<Integer, Stockvariables>> itr = set_obj.iterator();
			
			
			while(itr.hasNext()) 
			{
				Map.Entry<Integer, Stockvariables> restock_obj = itr.next();
				Stockvariables sv = restock_obj.getValue();
			if(search_id==sv.getProduct_id()) 
			{
			System.out.println("Choose whether the product has been purchased (1) or sold (2) :");
			choice=s_restock.nextInt();  
			
			switch(choice) 
			{
			case 1:
				System.out.print("Enter the number of products you purchased :  ");
				int purchase_stock=s_restock.nextInt();
				sv.setQuantity_nos(sv.getQuantity_nos()+purchase_stock);
				System.out.println(purchase_stock+" stock has been purchased");
				active_id=true;
				break;
						
			case 2:
				System.out.print("Enter the number of products you sold :  ");
				int sold_stock = s_restock.nextInt();
				sv.setQuantity_nos(sv.getQuantity_nos()-sold_stock);
				System.out.println(sold_stock+" stock has been sold");
				active_id=true;
				break;
						
			default :
				System.out.println(" PLease enter number 1 or 2 !!");
					
			}	
		System.out.println(" ");
			}
			}
			}
		if(map_obj.isEmpty()) 
		{
			System.out.println("No Product Found");
		}
		else if(!active_id) 
		{
			System.out.println("\n"+"Product ID is incorrect/ Not Found");
		}
		}catch(InputMismatchException ime) 
		{
			System.out.println(" Input Mismatch Exception is found in restock method");
		}
		catch(ArrayIndexOutOfBoundsException abe)
		{
			System.out.println(" Array Index Out Of Bounds Exception is found in restock method");
		}
	}
	
	void clienttableMethod() 
	{
		Scanner s_client = new Scanner(System.in);
		
		boolean active_id=false;
		double profit;
		
		try {
		if(!map_obj.isEmpty()) 
		{
			System.out.print("Enter the Product ID : ");
			search_id = s_client.nextInt();
			System.out.println(" ");
			System.out.println("Product ID \t Product Name \t Description \t Total Quantity "
					+ "\t Buying Price \t Selling Price \t M.R.P Price \t Profit \t Status  \n");
			Iterator<Map.Entry<Integer, Stockvariables>> itr = set_obj.iterator();
			while(itr.hasNext()) 
			{
			Map.Entry<Integer, Stockvariables> cliitr_obj= itr.next();
			Stockvariables sv = cliitr_obj.getValue();
			if(search_id==sv.getProduct_id()) {
				profit = sv.getSelling_price() - sv.getBuyer_price();
				System.out.println(sv.getProduct_id()+"\t"+sv.getProduct_name()+"\t"+sv.getDesc_name()
				+"\t"+sv.getQuantity_nos()+"\t"+sv.getBuyer_price()+"\t"
						+sv.getSelling_price()+"\t"+sv.getMrp_price()+"\t"+profit+"\t"+sv.getStatus()+"\t");
			active_id=true;
			}
		}
		}
		if(map_obj.isEmpty()) {
		System.out.println("No Product Found");
		}
		else if(!active_id) {
			System.out.println("\n"+"Product ID is incorrect/ Not Found");
		}
		}catch(InputMismatchException ime)
		{
			System.out.println("Input Mismatch Exception is found in ClientTable Method");
		}
	}
	
	void individualMethod()
	{
		Scanner s_individual = new Scanner(System.in);
		boolean active_id=false;
		
		try {
		if(!map_obj.isEmpty()) {
		System.out.print("Enter the Product ID to see \t: ");
		search_id=s_individual.nextInt();
		System.out.println(" ");
		
		Iterator<Map.Entry<Integer, Stockvariables>> itr = set_obj.iterator();
			while(itr.hasNext()) 
			{
				Map.Entry<Integer, Stockvariables> inditr_obj= itr.next();
				Stockvariables sv = inditr_obj.getValue();
				if(search_id==sv.getProduct_id()) {
					System.out.println("Product ID \t Product Name \t Description \t Total Quantity "
						+ "\t Buying Price \t Selling Price \t M.R.P Price \t Status \n");
					System.out.println(sv.getProduct_id()+"\t"+sv.getProduct_name()+"\t"+sv.getDesc_name()
						+"\t"+sv.getQuantity_nos()+"\t"+sv.getBuyer_price()+"\t"
						+sv.getSelling_price()+"\t"+sv.getMrp_price()+"\t"+sv.getStatus());
					active_id=true;
				}
			}
		}
		if(map_obj.isEmpty())
		{
			System.out.println(" No Product is Found");
		}
		else if(!active_id) {
			System.out.println("\n"+"PRODUCT ID not Found/ Incorrect");
		}
		}
		catch(InputMismatchException ime) 
		{
			System.out.println("Input Mismatch Exception is found in Individual Method");
		}

	}
	
	void deleteMethod()
	{
		boolean active_id=false;
		Scanner s_delete = new Scanner(System.in);
		try {
		if(!map_obj.isEmpty()) {
		System.out.print("Enter the Product ID to Delete : ");
		search_id=s_delete.nextInt();
		
		Iterator<Map.Entry< Integer, Stockvariables>> itr= set_obj.iterator();
		
		while(itr.hasNext()) {
			Map.Entry<Integer, Stockvariables> delit_obj = itr.next();
			Stockvariables sv=delit_obj.getValue();
			if(search_id==sv.getProduct_id())
			{
				sv.setStatus("INACTIVE");
				System.out.println("The given Product ID "+search_id+" has been deactivated.");
				active_id=true;
			}
		}
		}
		if(map_obj.isEmpty())
		{
			System.out.println(" No Product is Found");
		}
		else if(!active_id) {
			System.out.println("\n"+"PRODUCT ID not Found/ Incorrect");
		}
		}
		catch(InputMismatchException ime)
		{
			System.out.println(ime+" Input Mismatch Exception Found in Delete Method");
		}
		
	}
	
	
}
