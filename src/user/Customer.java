package user;

import java.util.Objects;

public class Customer extends User {
	private String name;
    private String address;
    private String phoneNum;
    
    
    public Customer(String id, String password) {
        super(id,password);
        this.name = null;
        this.address = null;
        this.phoneNum = null;
    }
	
    public Customer(String id, String password,String name, String address, String phoneNum) {
        super(id,password);
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    

        
    @Override
    public boolean equals(Object obj) {
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
				+ "이름=" + name + "\n"
				+ "주소=" + address + "\n"
				+ "스마트폰 번호= " + phoneNum;
				
	}





}
