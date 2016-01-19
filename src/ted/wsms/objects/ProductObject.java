package ted.wsms.objects;

public class ProductObject {
	
    private String name;
    private String serial_number;    
    private String description;    
    private String weight;
    private String volume;
    private String dimensions;
    private int quantity;
    private TypeObject typeObj;
    
    public ProductObject() {
    	
		this.name = null;
		this.serial_number = null;    
		this.description = null;    
		this.weight = null;
		this.volume = null;
		this.dimensions = null;
		this.quantity = 0;
		this.typeObj = null;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumber() {
		return serial_number;
	}

	public void setSerialNumber(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public TypeObject getType() {
		return typeObj;
	}

	public void setType(TypeObject typeObj) {
		this.typeObj = typeObj;
	}
    
}
