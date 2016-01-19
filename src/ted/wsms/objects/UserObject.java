package ted.wsms.objects;

public class UserObject {
	
    private String username;
    private String password;    
    private String name;    
    private String surname;
    private String email;
    private String state;
    private RoleObject role;
    
    public UserObject() {
    	
        this.username = null;
        this.password = null;
        this.name = null;
        this.surname = null;
        this.email = null;
        this.role = null;
        this.state = null;
    }

    public String getState() {
        return this.state;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSurname() {
        return this.surname;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }

    public RoleObject getRole() {
        return this.role;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(RoleObject roleObj) {
       this.role = roleObj;
    }
  
}
