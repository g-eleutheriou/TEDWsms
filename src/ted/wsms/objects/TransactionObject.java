package ted.wsms.objects;

public class TransactionObject {
	
    private ProductObject product;   
    private WarehouseObject source_warehouse;    
    private WarehouseObject target_warehouse;
    private String quantity;
    private String action;
    private String date;

    
    public TransactionObject() {
    	
		this.product = null; 
		this.source_warehouse = null;
		this.target_warehouse = null;    
		this.quantity = null;
		this.action = null;
		this.date = null;
    }

	public ProductObject getProduct() {
		return product;
	}

	public void setProduct(ProductObject product) {
		this.product = product;
	}

	public WarehouseObject getSourceWarehouse() {
		return source_warehouse;
	}

	public void setSourceWarehouse(WarehouseObject source_warehouse) {
		this.source_warehouse = source_warehouse;
	}

	public WarehouseObject getTargetWarehouse() {
		return target_warehouse;
	}

	public void setTargetWarehouse(WarehouseObject target_warehouse) {
		this.target_warehouse = target_warehouse;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    
}