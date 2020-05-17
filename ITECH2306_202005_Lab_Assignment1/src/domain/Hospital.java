package domain;

/**
 * @author Zac
 *
 */
public class Hospital extends Property {

	private boolean isPublic;
	private String facilities;
	private int numberOfFloors; 
	private static final double CIV_RATE = 0.0035;
	private static final int INDUSTRIAL_WASTE_DISPOSAL_UNITS = 4;
	private static final double INDUSTRIAL_WASTE_DISPOSAL_FEES = 500.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private ServiceType industrialWasteDisposal;
	private ServiceType fireServicesLevy;
	
	public Hospital(String description, String location, double area, double siteValue, double capitalImprovedValue, 
			   double netAnnualValue, String valuationDate, RatePayer owner, boolean isPublic, String facilities, int numberOfFloors) {
		super(description, location, area, siteValue, capitalImprovedValue, CIV_RATE, netAnnualValue, valuationDate, owner);
		this.setIsPublic(isPublic);
		this.setFacilities(facilities);
		this.setNumberOfFloors(numberOfFloors);
	}

	public Hospital() {
		super(CIV_RATE);
		// We are explicit about our defaults for Strings
		this.setIsPublic(true);
		this.setFacilities("Clinics");
		this.setNumberOfFloors(1);
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
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

	// Set up the extra services of Hospital property type
	@Override
	public void setUpExtraServices() {
		industrialWasteDisposal = new UnitAndRateService("Industrial Waste Disposal", 
														  INDUSTRIAL_WASTE_DISPOSAL_UNITS, 
														  INDUSTRIAL_WASTE_DISPOSAL_FEES);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy", FIRE_SERVICES_BASE, 
															   FIRE_SERVICES_PERCENT, 
															   getCapitalImprovedValue());
	}

	// Add up all the extra services charges
	@Override
	public double calculateExtraServices() {
		return industrialWasteDisposal.calculateChargeForServiceType() + 
				fireServicesLevy.calculateChargeForServiceType();
	}

	@Override
	public String toString() {
		return super.toString() + "Property type: Hospital [isPublic=" + isPublic + ", facilities=" + facilities + ", numberOfFloors="
				+ numberOfFloors + "]\n";
	}

//	@Override
//	public String toString() {
//		return super.toString() + "Property type: Hospital [\n" + 
//								   industrialWasteDisposal.toString() + "\n" +
//								   fireServicesLevy.toString() + "]\n";
//	}
	

	
}
