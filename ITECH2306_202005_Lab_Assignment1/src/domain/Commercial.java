package domain;

/**
 * @author Anush
 * 
 */
public class Commercial extends Property {
	private String businessName;
	private long abn;
	private static final double CIV_RATE = 0.0059;
	private static final int WASTE_MANAGEMENT_UNITS = 2;
	private static final double WASTE_MANAGEMENT_FEES = 350.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private ServiceType wasteManagement;
	private ServiceType fireServicesLevy;

	public Commercial() {
		super();
		// Explicit assignment of property attributes
		this.setBusinessName("Zac and Anush Pty. Ltd");
		this.setAbn(123456);
		setCapitalImprovedRate(CIV_RATE);
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public long getAbn() {
		return abn;
	}

	public void setAbn(long abn) {
		this.abn = abn;
	}

	// Set up the extra services of Commercial property type
	@Override
	public void setUpExtraServices() {
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
	
	@Override
	public String toString() {
		return  super.toString() + "Property type: Commercial [\n" + 
									wasteManagement.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n ";
	}

}
