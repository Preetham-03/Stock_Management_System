package _StockManagement;
import java.util.*;

public class StockManagement 
{	
	
	
	public static void main(String[] args) 
	{
		StockMethod sm = new StockMethod();
		
		while(true) 
		{
		try 
		{
		Scanner s = new Scanner(System.in);
			
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
	    
	    System.out.println(" ");
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
			System.out.println("Please enter any one choice 1 or 2 or 3 or 4 or 5 !! \n");
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
}

class StockMethod
{
	int product_id[]=new int[50];
	int no_of_product = 0;
	
	String product_name[] = new String[50];
	String desc_name[] = new String[50];
	int quantity_nos[] = new int[50];
	int buyer_price[] = new int[50];
	int selling_price[] = new int[50];
	int mrp_price[] = new int[50];
	String status[] = new String[50];
	
	
	int productid;

	void createMethod()
	{
		Scanner s_create = new Scanner(System.in);
		
		System.out.print("Enter the Number of Products\t: ");
		no_of_product = s_create.nextInt();
		
		int index=100;
				
		for(int i=0; i<no_of_product;i++)
		{
			
		index++; 
		product_id[i]=index;
			
		System.out.print("Enter the Product Name\t: "  );
		s_create.nextLine();
		product_name[i]= s_create.nextLine();
		
		System.out.print("Enter the Description of the Product\t: ");
		desc_name[i] = s_create.nextLine();

		System.out.print("Enter Total Quantity\t: ");
		quantity_nos[i] = s_create.nextInt();
		
		System.out.print("Enter the Buyer Price\t: ");
		buyer_price[i] = s_create.nextInt();
		
		System.out.print("Enter the Selling Price\t: ");
		selling_price[i] = s_create.nextInt();
		
		do {
		System.out.print("Enter the M.R.P Price\t: ");
		mrp_price[i] = s_create.nextInt();
		}while(mrp_price[i]<buyer_price[i] && mrp_price[i]<selling_price[i]);
		
		
		status[i]="ACTIVE";
		
		System.out.println(" ");
		
		}
		//    s_create.close();
		}
	
	
	
	
	void updateMethod()
   	{
		Scanner s_update = new Scanner(System.in);

		try {
		System.out.print("Enter the Product Id you want to Update : ");
		productid=s_update.nextInt();
		System.out.println(" ");
	
		System.out.println("Choose any one to Update : \n");
		System.out.println("1. To Update Product Name \n");
		System.out.println("2. To Update Description \n");
		System.out.println("3. To Update M.R.P Price \n");	
		System.out.println("4. To Update Status \n");	
		
		System.out.print("Enter your Choice to Update : ");
		int choice2=s_update.nextInt();
		System.out.println(" ");	
		
		switch (choice2)
		{
		case 1:
			
			for(int i=0; i<no_of_product;i++)
			{
				if(productid==product_id[i])
				{
					System.out.print("Enter the Product Name to Update : ");
					String updated_name= s_update.next();
					product_name[i]=updated_name;
					System.out.println("Product Name has been Updated !!");
					System.out.println(" ");
				}
			}
			break;
			
		case 2:
			for(int i=0; i<no_of_product;i++)
			{
				if(productid==product_id[i])
				{
					System.out.print("Enter the Product Description to Update : ");
					String updated_description= s_update.next();
					desc_name[i]=updated_description;
					System.out.println("Product Description has been Updated !!");
					System.out.println(" ");
				}
			}
			break;
			
		case 3:
			for(int i=0; i<no_of_product;i++)
			{
				if(productid==product_id[i])
				{
					System.out.print("Enter the M.R.P Price to Update : ");
					int updated_mrp= s_update.nextInt();
					mrp_price[i]=updated_mrp;
					System.out.println("Product M.R.P Price has been Updated !!");
					System.out.println(" ");
				}
			}
			break;
		case 4:
			for(int i=0; i<no_of_product;i++)
			{
				if(productid==product_id[i])
				{
					status[i]="Out of Stock";
					System.out.println("Product Status has been Updated !!");
					System.out.println(" ");
				}
			}
			break;
		default:
			System.out.println(" No Data has been Updated !!");
			System.out.println(" ");
			break;
			}
		}
		catch(InputMismatchException ime)
		{
			System.out.println(" InputMismatchException Handled in UPDATE Method !!");
			
		}
		System.out.println(" ");
		//s_update.close();
	}
	
	
	void displayMethod()
	{
		
		System.out.println("Product ID \t Product Name \t Description \t Total Quantity "
				+ "\t Buying Price \t Selling Price \t M.R.P Price \t Status \n");
		
		boolean active_id=false;
		
		for(int i=0; i<no_of_product; i++)
		{
			if(status[i]=="ACTIVE") 
			{
			System.out.println(product_id[i]+"\t"+product_name[i]+"\t"+desc_name[i]+"\t"+quantity_nos[i]+"\t"
					+"₹"+buyer_price[i]+"\t"+"₹"+selling_price[i]+"\t"+"₹"+mrp_price[i]+"\t"+status[i]+"\n");
			active_id=true;
			}
		}
		if(!active_id) //if no active products were found during the loop
		{
		System.out.println("NO ACTIVE ID FOUND");	
		}
		System.out.println(" ");
	}
	
	void restockMethod()
	{
		Scanner s_restock = new Scanner(System.in);
		
		System.out.print("Enter the Product ID : ");
		int prod_id = s_restock.nextInt();
		int choice2;
		boolean active_id=false;
		
		try {
		for(int i=0; i<product_id.length; i++)
		{
			if(prod_id == product_id[i])
			{
				System.out.println("Choose whether the product has been purchased (1) or sold (2) :");
				choice2=s_restock.nextInt();  
				
				switch(choice2) 
				{
					case 1:
						System.out.print("Enter the number of products you purchased :  ");
						int new_stock = s_restock.nextInt();
						quantity_nos[i] = quantity_nos[i] + new_stock;
						active_id=true;
						break;
						
					case 2:
						System.out.print("Enter the number of products you sold :  ");
						int sold_stock = s_restock.nextInt();
						quantity_nos[i] = quantity_nos[i] -	sold_stock;
						active_id=true;
						break;
						
					default :
						System.out.println(" PLease enter number 1 or 2 !!");
					
				}	
			}
		}
		if(!active_id)
		{
			System.out.println(" Product ID has been not found !! ");
		}
		System.out.println(" ");
		}
		catch(InputMismatchException ime) 
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
		
		int profit;
		try {
		System.out.println("Enter the Product ID : ");
		int prod_id = s_client.nextInt();
		
		System.out.println("Product ID \t Product Name \t Description \t Total Quantity "
				+ "\t Buying Price \t Selling Price \t M.R.P Price \t Status \t Profit \n");
		
		boolean active_id=false;
		
		for(int i=0; i<product_id.length; i++)
		{
			if(prod_id == product_id[i])
			{
				profit = selling_price[i] - buyer_price[i];
				System.out.println(product_id[i]+"\t"+product_name[i]+"\t"+desc_name[i]+"\t"+quantity_nos[i]+"\t"
						+"₹"+buyer_price[i]+"\t"+"₹"+selling_price[i]+"\t"+"₹"+mrp_price[i]+"₹"+profit+"\t"+status[i]+"\t"+"\n");
				active_id=true;
			}
		}
		if(!active_id)
		{
			System.out.println(" Product ID has been not found !! ");
		}
		System.out.println(" ");
		}catch(InputMismatchException ime)
		{
			System.out.println("Input Mismatch Exception is found in ClientTable Method");
		}
	}
	
	void individualMethod()
	{
		Scanner s_individual = new Scanner(System.in);

		try {
		System.out.print("Enter the Product ID to see \t: ");
		productid=s_individual.nextInt();
		System.out.println(" ");

		for(int i=0; i<product_id.length; i++)
		{  
			if(productid==product_id[i]) 
			{
				System.out.println("Product ID \t Product Name \t Description \t Total Quantity "
						+ "\t Buying Price \t Selling Price \t M.R.P Price \t Status \n");
				
				System.out.println(product_id[i]+"\t"+product_name[i]+"\t"+desc_name[i]+"\t"+quantity_nos[i]+"\t"
						+"₹"+buyer_price[i]+"\t"+"₹"+selling_price[i]+"\t"+"₹"+mrp_price[i]+"\t"+status[i]+"\n");
			}
		} 
		}catch(InputMismatchException ime) 
		{
			System.out.println("Input Mismatch Exception is found in Individual Method");
		}
		//s_individual.close();
	}
	void deleteMethod()
	{
		Scanner s_delete= new Scanner(System.in);

		try {
		System.out.print("Enter the Product ID to Deactivate\t : ");
		productid = s_delete.nextInt();
		System.out.println(" ");

		for(int i=0; i<product_id.length; i++)
		{
			if(productid==product_id[i])
			{
				status[i]="INACTIVE";
				
				System.out.println(" The given Product Id "+productid+" has been Deactivated !!");
			}	
		}
		
		System.out.println(" ");
		//s_delete.close();	
		}catch(InputMismatchException ime)
		{
			System.out.println("Input Mismatch Exception is found in Delete Method");
		}
	}
	
	

}
