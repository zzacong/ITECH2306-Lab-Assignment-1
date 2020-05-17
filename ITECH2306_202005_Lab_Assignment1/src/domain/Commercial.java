package domain;

/**
 * @author Anush
 * 
 */
public class Commercial extends Property {
	private String businessName;
	private String abn;
	private static final double CIV_RATE = 0.0059;
	private static final int WASTE_MANAGEMENT_UNITS = 2;
	private static final double WASTE_MANAGEMENT_FEES = 350.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private ServiceType wasteManagement;
	private ServiceType fireServicesLevy;

	public Commercial(String description, String location, double area, double siteValue, double capitalImprovedValue, 
			   double netAnnualValue, String valuationDate, RatePayer owner, String businessName, String abn) {
		super(description, location, area, siteValue, capitalImprovedValue, CIV_RATE, netAnnualValue, valuationDate, owner);
		this.setBusinessName(businessName);
		this.setAbn(abn);
	}
	
	public Commercial() {
		super(CIV_RATE);
		// Explicit assignment of property attributes
		this.setBusinessName("Zac and Anush Pty. Ltd");
		this.setAbn("123456");
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getAbn() {
		return abn;
	}

	public void setAbn(String abn) {
		this.abn = abn;
	}

	// Set up the extra services of Commercial property type
	@Override
	public void setUpExtraServices() {
		setHasExtraServices(true);
		wasteManagement = new UnitAndRateService("Waste Management",
												  WASTE_MANAGEMENT_UNITS,
												  WASTE_MANAGEMENT_FEES);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());		
	}

	// Add up all the extra services charges
	@Override
	public double calculateExtraServices() {
		return wasteManagement.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType();
	}
	
	public String extraServices() {
		if (getHasExtraServices()) {
			return  "Property extra services: [\n" + wasteManagement.toString() + "\n" + 
					fireServicesLevy.toString() + " ]\n";
		}
		else {
			return "";
		}
	}
	
	@Override 
	public String toString() {
		return  super.toString() + "Property type: Commercial [" + 
									"businessName=" + businessName + ", ABN=" + abn + "]\n" + 
									extraServices();
	}

}
