package ted.wsms.objects;

public class RoleObject {

    private String name;
    private String description;
    private String warehouses_permissions;
    private String products_permissions;
    private String suppliers_permissions;
    private String roles_permissions;
    private String messages_permissions;
    private String users_permissions;

    public RoleObject() {
        this.name = null;
        this.description = null;
        this.warehouses_permissions = null;
        this.products_permissions = null;
        this.suppliers_permissions = null;
        this.roles_permissions = null;
        this.messages_permissions = null;
        this.users_permissions = null;
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

	public String getWarehousesPermissions() {
		return warehouses_permissions;
	}

	public void setWarehousesPermissions(String warehouses_permissions) {
		this.warehouses_permissions = warehouses_permissions;
	}

	public String getProductsPermissions() {
		return products_permissions;
	}

	public void setProductsPermissions(String products_permissions) {
		this.products_permissions = products_permissions;
	}

	public String getSuppliersPermissions() {
		return suppliers_permissions;
	}

	public void setSuppliersPermissions(String suppliers_permissions) {
		this.suppliers_permissions = suppliers_permissions;
	}

	public String getRolesPermissions() {
		return roles_permissions;
	}

	public void setRolesPermissions(String roles_permissions) {
		this.roles_permissions = roles_permissions;
	}

	public String getMessagesPermissions() {
		return messages_permissions;
	}

	public void setMessagesPermissions(String messages_permissions) {
		this.messages_permissions = messages_permissions;
	}

	public String getUsersPermissions() {
		return users_permissions;
	}

	public void setUsersPermissions(String users_permissions) {
		this.users_permissions = users_permissions;
	}
}
