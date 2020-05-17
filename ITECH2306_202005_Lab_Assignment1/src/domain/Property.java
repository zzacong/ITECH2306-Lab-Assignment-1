package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import utility.Validator;

/**
 * @author Takeogh
 * @author Zac
 *
 */
public abstract class Property implements Serializable {
	private String description;
	private String location;
	private double area;
	private double siteValue;
	private double capitalImprovedValue;
	private double capitalImprovedRate;
	private double netAnnualValue;
	private String valuationDate;
	private RatePayer owner;
	
	private boolean hasExtraServices = false;

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
	protected static final String NOT_AVAILABLE = "Not Available";
//	protected Validator v = new Validator();
	
	public Property(double capitalImprovedRate) {
		this(NOT_AVAILABLE, NOT_AVAILABLE, 0.0, 0.0, 0.0, capitalImprovedRate, 0.0, dateToString(LocalDate.now()), new RatePayer());
		// We are explicit about our String and date defaults but leave the numbers to be filled with Java default values
//		this.setDescription(NOT_AVAILABLE);
//		this.setLocation(NOT_AVAILABLE);
//		this.setValuationDate(dateToString(LocalDate.now()));
		// Provide a default owner 
//		this.setOwner(new RatePayer());	
	}
	
	public Property(String description, String location, double area, double siteValue, 
			double capitalImprovedValue, double capitalImprovedRate, double netAnnualValue, String valuationDate, RatePayer owner) {
		this.setDescription(description);
		this.setLocation(location);
		this.setArea(area);
		this.setSiteValue(siteValue);
		this.setCapitalImprovedValue(capitalImprovedValue);
		this.setCapitalImprovedRate(capitalImprovedRate);
		this.setNetAnnualValue(netAnnualValue);
		this.setValuationDate(valuationDate);
		this.setOwner(owner);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (Validator.validateString("description", description)) {
			this.description = description;
		}
		else {
			this.description = NOT_AVAILABLE;
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		if (Validator.validateString("location", location)) {
			this.location = location;
		}
		else {
			this.location = NOT_AVAILABLE;
		}
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		if (Validator.checkDoubleWithinRange("Area", area, 100.0,1000000000.0)) {
			this.area = area;
		}
		else {
			this.area = 100.0;
		}
	}

	public double getSiteValue() {
		return siteValue;
	}

	public void setSiteValue(double siteValue) {
		if (Validator.checkDoubleWithinRange("Site value", siteValue, 100.0, 49999999.0)) {
			this.siteValue = siteValue;
		}
		else {
			this.siteValue = 100.0;
		}
	}

	public double getCapitalImprovedValue() {
		return capitalImprovedValue;
	}

	public void setCapitalImprovedValue(double capitalImprovedValue) {
		if (Validator.checkDoubleWithinRange("CIV", capitalImprovedValue, 100.0, 50000000.0)) {
			if(capitalImprovedValue > getSiteValue()) {
				this.capitalImprovedValue = capitalImprovedValue;
			}
			else {
				System.out.println("CIV must be greater than site value. Assigning siteValue + $100 to CIV.");
				this.capitalImprovedValue = getSiteValue() + 100.0;
			}
		}
		else {
			this.capitalImprovedValue = 100.0;
		}
	}

	public double getNetAnnualValue() {
		return netAnnualValue;
	}

	public void setNetAnnualValue(double netAnnualValue) {
		if (Validator.checkDoubleWithinRange("Net annual value", netAnnualValue, 100.0, 50000000.0)) {
			this.netAnnualValue = netAnnualValue;
		}
		else {
			this.capitalImprovedValue = 100.0;
		}
	}

	public String getValuationDate() {
		return valuationDate;
	}

	public void setValuationDate(String date) {
		if (Validator.validateStringToDate("Valuation Date", date)) {
			this.valuationDate = date;
		}
		else {
			setValuationDate(dateToString(LocalDate.now()));
		}
	}
	
	private static String dateToString(LocalDate date) {
		return date.format(FORMATTER);
	}
	
	public double getCapitalImprovedRate() {
		return capitalImprovedRate;
	}

	public void setCapitalImprovedRate(double capitalImprovedRate) {
		if (Validator.checkDoubleWithinRange("Capital Improved Rate", capitalImprovedRate, 0.0020, 1.0)) {
			this.capitalImprovedRate = capitalImprovedRate;
		}
		else {
			this.capitalImprovedRate = 0.0020;
		}
	}

	public RatePayer getOwner() {
		return owner;
	}

	public void setOwner(RatePayer owner) {
		this.owner = owner;
	}
	
	public boolean getHasExtraServices() {
		return hasExtraServices;
	}

	public void setHasExtraServices(boolean hasExtraServices) {
		this.hasExtraServices = hasExtraServices;
	}

	public double calculateRates() {
		// So, we return the CIV multiplied by the CIV rate + the total of extra services all multiplied by
		// a charity discount (if applicable)
		return (((getCapitalImprovedValue() * getCapitalImprovedRate()) 
				+ calculateExtraServices()) *
				(getOwner().isCharity() ? 1 - getOwner().getCharityDiscountPercentage() : 1));
	}
	
	public abstract void setUpExtraServices();
	
	public abstract double calculateExtraServices();

	@Override
	public String toString() {
		return "Property [description=" + description + ", location=" + location + ", area=" + area 
				+ ", siteValue=" + siteValue + ", capitalImprovedValue=" + capitalImprovedValue
				+ ", capitalImprovedRate=" + capitalImprovedRate + ", netAnnualValue=" + netAnnualValue 
				+ ", valuationDate=" + valuationDate + ", owner=" + owner.getName() + "] \n";
	}
	
}
