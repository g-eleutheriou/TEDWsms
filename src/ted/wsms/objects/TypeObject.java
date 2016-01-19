package ted.wsms.objects;

public class TypeObject {
	
    private String name;
    private String description;    
    
    public TypeObject() {
    	
        this.name = null;
        this.description = null;
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
    
    
}
