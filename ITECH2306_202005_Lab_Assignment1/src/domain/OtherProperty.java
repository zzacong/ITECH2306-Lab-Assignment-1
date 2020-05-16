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
	
	public OtherProperty() {
		super(CIV_RATE);
		this.setSpecialDescription("None");
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

	@Override
	public String toString() {
		return super.toString() + "Property Type: OtherProperty [specialDescription=" + specialDescription + "]";
	}
	
	
	
//	@Override
//	public String toString() {
//		return super.toString() + "Property type: OtherProperty [\n" +
//								  fireServicesLevy.toString() + "]\n ";	
//	}

}
