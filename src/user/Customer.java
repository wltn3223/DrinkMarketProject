package user;

import java.util.Objects;

public class Customer extends User {
	private String name;
    private String address;

    
    
    public Customer(String id, String password) {
        super(id,password);
        this.name = null;
        this.address = null;
    }
	
    public Customer(String id, String password,String name, String address) {
        super(id,password);
        this.name = name;
        this.address = address;

    }


        
    @Override
    public boolean equals(Object obj) {  // 비밀번호와 id가 같아야 같은 인스턴스로 판단
    	Customer customer;
    	if(!(obj instanceof Customer)){
    		return false;
    	}
    	customer = (Customer)obj;
    	
    	return customer.getId().equals(this.getId()) && customer.getPassword().equals(this.getPassword());
    	
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getPassword());
    }



	@Override
	public String toString() {
		return "ID= " + this.getId() + "\n"
				+"비밀번호= " + this.getPassword() + "\n"
				+ "이름= " + name + "\n"
				+ "주소= " + address;
				
	}





}
