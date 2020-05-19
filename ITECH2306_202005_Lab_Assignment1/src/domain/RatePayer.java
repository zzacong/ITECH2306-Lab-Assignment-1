package domain;

import java.io.Serializable;

import utility.Validator;

/**
 * @author Takeogh
 * @author Zac
 * @author Anush
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
	
	public RatePayer(String name, String address, String postcode, String phone, String type, String charity) {
		this.setName(name);
		this.setAddress(address);
		this.setPostcode(postcode);
		this.setPhone(phone);
		this.setType(type);
		
		if (Validator.validateBoolean("heavyVehicleStatus", charity) == true) {
			this.setCharity(Boolean.parseBoolean(charity));
		}
		else {
			throw new IllegalArgumentException("heavyVehicleStatus value for Industrial property type is either not boolean or  is null or empty.. Rejecting this record...\n");
		}
		
	
	}
	
	public RatePayer() {
		this(DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE );
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (Validator.validateString("Name", name)) {
			this.name = name;
		}
		else {
			throw new NullPointerException("Name for Rate Payer is null or empty. Rejecting this record...\n");
		}	
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (Validator.validateString("Address", address)) {
			this.address = address;
		}
		else {
			throw new NullPointerException("Address for Rate Payer is null or empty. Rejecting this record...\n");
		}	
		
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		if (Validator.validateString("Postcode", postcode)) {
			this.postcode = postcode;
		}
		else {
			throw new NullPointerException("Postcode for Rate Payer is null or empty. Rejecting this record...\n");
		}	
		
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
		if (Validator.validateString("Type", postcode)) {
			this.type = type;
		}
		else {
			throw new NullPointerException("Type for Rate Payer is null or empty. Rejecting this record...\n");
		}	
		
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
		if (Validator.checkDoubleWithinRange("Discount Percentage", charityDiscountPercentage, 0.0, 1.0) == true) {
			this.charityDiscountPercentage = charityDiscountPercentage;
		}
		else {
			throw new IllegalArgumentException("Invalid Discount Percentage value... \n");
		}
		
	}

	@Override
	public boolean equals(Object obj) {
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
		return getName().hashCode() + (int)getCharityDiscountPercentage();
	}
	
	@Override
	public String toString() {
		return "RatePayer [name=" + name + ", address=" + address + ", postcode=" + postcode + ", phone=" + phone
				+ ", type=" + type + ", charity=" + charity + ", charityDiscountPercentage=" + charityDiscountPercentage
				+ "]";
	}
}
