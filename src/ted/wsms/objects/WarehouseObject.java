package ted.wsms.objects;

public class WarehouseObject {
	
    private String name;
    private String description;    
    private String location;    
    private String state;
    
    
    public WarehouseObject() {
    	
		this.name = null;
		this.description = null;    
		this.location = null;    
		this.state = null;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
    
}