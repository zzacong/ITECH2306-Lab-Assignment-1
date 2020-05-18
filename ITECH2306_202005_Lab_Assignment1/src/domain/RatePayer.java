package domain;

import java.io.Serializable;

/**
 * @author Takeogh
 * @author Zac
 *
 */
public class RatePayer implements Serializable {

	private String name;
	private String address;
	private String postcode;
	private String phone;
	private String type;
	private boolean charity;
	//Discount might not necessarily be on RatePayer but for convenience at the moment we place it here.
	private double charityDiscountPercentage = 0.20;
	private static final String DUMMY_VALUE = "Dummy Value";
	
	public RatePayer(String name, String address, String postcode, String phone, String type, boolean charity) {
		this.setName(name);
		this.setAddress(address);
		this.setPostcode(postcode);
		this.setPhone(phone);
		this.setType(type);
		this.setCharity(charity);
	}
	
	public RatePayer() {
		this(DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (phone.isEmpty())
			this.phone = DUMMY_VALUE;
		else
			this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isCharity() {
		return charity;
	}

	public void setCharity(boolean charity) {
		this.charity = charity;
	}

	
	public double getCharityDiscountPercentage() {
		return charityDiscountPercentage;
	}

	public void setCharityDiscountPercentage(double charityDiscountPercentage) {
		this.charityDiscountPercentage = charityDiscountPercentage;
	}

	@Override
	public boolean equals(Object obj) {
//		System.out.println(hashCode());
//		System.out.println(obj.hashCode());
		if (obj == null || !(obj.getClass().equals(this.getClass()))) {
			return false;
		}
		RatePayer other = (RatePayer) obj;		
		if (this.getName().equals(other.getName()) &&
			this.getAddress().equals(other.getAddress()) &&
			this.getPostcode().equals(other.getPostcode()) &&
			this.getPhone().equals(other.getPhone()) &&
			this.getType().equals(other.getType()) &&
			this.isCharity() == other.isCharity() &&
			this.getCharityDiscountPercentage() == other.getCharityDiscountPercentage()) {
			return true;
		}
		else {
			return false;	
		}
	}
	
	@Override
	public int hashCode() {
		return getName().hashCode() + (int)getCharityDiscountPercentage();// + Integer.parseInt(getPostcode());
	}
	
	@Override
	public String toString() {
		return "RatePayer [name=" + name + ", address=" + address + ", postcode=" + postcode + ", phone=" + phone
				+ ", type=" + type + ", charity=" + charity + ", charityDiscountPercentage=" + charityDiscountPercentage
				+ "]";
	}
}
