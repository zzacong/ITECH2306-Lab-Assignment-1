package domain;

/**
 * @author Takeogh
 * @author Zac
 *
 */
public class Hospital extends Property {

	private String propertyStatus;
	private String facilities;
	private int numberOfFloors; 
	private static final double CIV_RATE = 0.0035;
	private static final int INDUSTRIAL_WASTE_DISPOSAL_UNITS = 4;
	private static final double INDUSTRIAL_WASTE_DISPOSAL_FEES = 500.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private ServiceType industrialWasteDisposal;
	private ServiceType fireServicesLevy;
	
	public Hospital() {
		super();
		// We are explicit about our defaults for Strings
		this.setPropertyStatus("Private");
		this.setFacilities("Clinics");
		this.setNumberOfFloors(1);
		setCapitalImprovedRate(CIV_RATE);
	}

	public String getPropertyStatus() {
		return propertyStatus;
	}

	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	@Override
	public void setUpExtraServices() {
		industrialWasteDisposal = new UnitAndRateService("Industrial Waste Disposal", 
														  INDUSTRIAL_WASTE_DISPOSAL_UNITS, 
														  INDUSTRIAL_WASTE_DISPOSAL_FEES);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy", FIRE_SERVICES_BASE, 
															   FIRE_SERVICES_PERCENT, 
															   getCapitalImprovedValue());
	}

	@Override
	public double calculateExtraServices() {
		return industrialWasteDisposal.calculateChargeForServiceType() + 
				fireServicesLevy.calculateChargeForServiceType();
	}

	@Override
	public String toString() {
		return super.toString() + "Hospital [\n" + 
								   industrialWasteDisposal.toString() + "\n" +
								   fireServicesLevy.toString() + "]\n";
	}
	
}
