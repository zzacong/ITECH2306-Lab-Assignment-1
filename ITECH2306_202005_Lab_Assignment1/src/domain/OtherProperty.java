package domain;

/**
 * @author Takeogh
 * @author Zac
 *
 */
public class OtherProperty extends Property {

	private String specialDescription;
	private static final double CIV_RATE = 0.0030;
	private static final double FIRE_SERVICES_BASE = 150;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private ServiceType fireServicesLevy;
	
	public OtherProperty(String description, String location, double area, double siteValue, double capitalImprovedValue, double capitalImprovedRate, 
							double netAnnualValue, String valuationDate, RatePayer owner, String specialDescription) {
		super(description, location, area, siteValue, capitalImprovedValue, capitalImprovedRate, netAnnualValue, valuationDate, owner);
		this.setSpecialDescription(specialDescription);
	}
	
	public OtherProperty() {
		super();
		// Explicitly assign defaults for String
		this.setSpecialDescription("None");
		setCapitalImprovedRate(CIV_RATE);
	}

	public String getSpecialDescription() {
		return specialDescription;
	}

	public void setSpecialDescription(String specialDescription) {
		this.specialDescription = specialDescription;
	}

	// Set up the extra services of OtherProperty property type
	@Override
	public void setUpExtraServices() {
		setHasExtraServices(true);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
				FIRE_SERVICES_BASE,
				FIRE_SERVICES_PERCENT,
				getCapitalImprovedValue());
	}

	// Add up all the extra services charges
	@Override
	public double calculateExtraServices() {
		return fireServicesLevy.calculateChargeForServiceType();
	}

	public String extraServices() {
		if (getHasExtraServices()) {
			return  "Property extra services: [\n" + fireServicesLevy.toString() + " ]\n";
		}
		else {
			return "";
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "Property Type: OtherProperty [specialDescription=" + specialDescription + "]\n" + 
									extraServices();
	}
	
//	@Override
//	public String toString() {
//		return super.toString() + "Property type: OtherProperty [\n" +
//								  fireServicesLevy.toString() + "]\n ";	
//	}

}
