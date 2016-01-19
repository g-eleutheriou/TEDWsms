
package ted.wsms.objects;

public class SupplyObject {

	private float price;
    private ProductObject product;
    private SupplierObject supplier;

    public SupplyObject() {
        this.price = 0;
        this.product = null;
        this.supplier = null;
    }

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public ProductObject getProduct() {
		return product;
	}

	public void setProduct(ProductObject product) {
		this.product = product;
	}

	public SupplierObject getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierObject supplier) {
		this.supplier = supplier;
	}

}