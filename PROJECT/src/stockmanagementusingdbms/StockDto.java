package stockmanagementusingdbms;

public class StockDto {
	

		private int product_id;
		
		
		private String product_name; 
		private String product_code;
		private String desc_name;
		private int quantity_nos;
		private double buyer_price;
		private double selling_price;
		private double mrp_price;
		private String status;
		
		StockDto(){
			
		}
		

		public int getProduct_id() {
			return product_id;
		}


		public void setProduct_id(int product_id) {
			this.product_id = product_id;
		}
		

		public String getProduct_name() {
			return product_name;
		}


		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}


		public String getDesc_name() {
			return desc_name;
		}


		public void setDesc_name(String desc_name) {
			this.desc_name = desc_name;
		}


		public int getQuantity_nos() {
			return quantity_nos;
		}


		public void setQuantity_nos(int quantity_nos) {
			this.quantity_nos = quantity_nos;
		}


		public double getBuyer_price() {
			return buyer_price;
		}


		public void setBuyer_price(int buyer_price) {
			this.buyer_price = buyer_price;
		}


		public double getSelling_price() {
			return selling_price;
		}


		public void setSelling_price(int selling_price) {
			this.selling_price = selling_price;
		}


		public double getMrp_price() {
			return mrp_price;
		}


		public void setMrp_price(int mrp_price) {
			this.mrp_price = mrp_price;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public String getProduct_code() {
			return product_code;
		}


		public void setProduct_code() {
			this.product_code = "PT -"+this.product_id;
		}


	

}
