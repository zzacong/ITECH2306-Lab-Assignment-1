package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
	protected static final String NOT_AVAILABLE = "Not Available";
	
	public Property(double capitalImprovedRate) {
		// We are explicit about our String and date defaults but leave the numbers to be filled with Java default values
		this(NOT_AVAILABLE, NOT_AVAILABLE, 0.0, 0.0, 0.0, capitalImprovedRate, 0.0, dateToString(LocalDate.now()), new RatePayer());
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
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getSiteValue() {
		return siteValue;
	}

	public void setSiteValue(double siteValue) {
		this.siteValue = siteValue;
	}

	public double getCapitalImprovedValue() {
		return capitalImprovedValue;
	}

	public void setCapitalImprovedValue(double capitalImprovedValue) {
		if (capitalImprovedValue >= 100.00 && capitalImprovedValue <= 50000000.00) { // Make sure the CIV is within range
			this.capitalImprovedValue = capitalImprovedValue;
		}
		
		else {
			this.capitalImprovedValue = 0;
		}

	}

	public double getNetAnnualValue() {
		return netAnnualValue;
	}

	public void setNetAnnualValue(double netAnnualValue) {
		this.netAnnualValue = netAnnualValue;
	}

	public String getValuationDate() {
		return valuationDate;
	}

	public void setValuationDate(String date) {
		this.valuationDate = date;
	}
	
	private static String dateToString(LocalDate date) {
		return date.format(FORMATTER);
	}
	
	public double getCapitalImprovedRate() {
		return capitalImprovedRate;
	}

	public void setCapitalImprovedRate(double capitalImprovedRate) {
		this.capitalImprovedRate = capitalImprovedRate;
	}

	public RatePayer getOwner() {
		return owner;
	}

	public void setOwner(RatePayer owner) {
		this.owner = owner;
	}
	
	public double calculateRates() {
		// So, we return the CIV multiplied by the CIV rate + the total of extra services all multiplied by
		// a charity discount (if applicable)
		return(((getCapitalImprovedValue() * getCapitalImprovedRate()) 
				+ calculateExtraServices()) *
				(getOwner().isCharity() ? 1 - getOwner().getCharityDiscountPercentage() : 1));
	}
	
	public abstract void setUpExtraServices();
	
	public abstract double calculateExtraServices();

//	@Override
//	public String toString() {
//		return "Property [description=" + description + ", capitalImprovedValue=" + capitalImprovedValue
//				+ ", capitalImprovedRate=" + capitalImprovedRate + "] \n";
//	}
	
	@Override
	public String toString() {
		return "Property [description=" + description + ", location=" + location + ", area=" + area 
				+ ", siteValue=" + siteValue + ", capitalImprovedValue=" + capitalImprovedValue
				+ ", capitalImprovedRate=" + capitalImprovedRate + ", netAnnualValue=" + netAnnualValue 
				+ ", valuationDate=" + valuationDate + ", owner=" + owner + "] \n";
	}
	
}
