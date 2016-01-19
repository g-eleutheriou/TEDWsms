package ted.wsms.objects;

public class SupplierObject {
	
    private String name;
    private String surname;    
    private String address;    
    private String ssn;
    private String phone;
    private String email;

    
    public SupplierObject() {
    	
		this.name = null;
		this.surname = null;    
		this.address = null;    
		this.ssn = null;
		this.phone = null;
		this.email = null;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAdress(String address) {
		this.address = address;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}