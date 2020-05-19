package domain;

import utility.Validator;

/**
 * @author Zac
 * @author Anush
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
	
	public Hospital(String description, String location, double area, double siteValue, double capitalImprovedValue, double capitalImprovedRate, 
			   double netAnnualValue, String valuationDate, RatePayer owner, String isPublic, String facilities, int numberOfFloors) throws NullPointerException, IllegalArgumentException {
		super(description, location, area, siteValue, capitalImprovedValue, capitalImprovedRate, netAnnualValue, valuationDate, owner);
		
		if (Validator.validateBoolean("isPublic", isPublic) == true) {
			this.setIsPublic(Boolean.parseBoolean(isPublic));
		}
		else {
			throw new IllegalArgumentException("isPublic value for Hospital property is either not boolean or  is null or empty. Rejecting this record...\n");
		}
		this.setFacilities(facilities);
		this.setNumberOfFloors(numberOfFloors);
	}

	public Hospital() {
		super();
		// Explicitly assign defaults for String
		this.setIsPublic(true);
		this.setFacilities("Clinics");
		this.setNumberOfFloors(1);
		setCapitalImprovedRate(CIV_RATE);
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

	public void setFacilities(String facilities) throws NullPointerException{
		if (Validator.validateString("Facilities ", facilities)) {
			this.facilities = facilities;
		}
		else {
			throw new NullPointerException("Facilities for Hospital is null or empty. Rejecting this record...\n");
		}		
		
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
		setHasExtraServices(true);
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

	public String extraServices() {
		if (getHasExtraServices()) {
			return  "Property extra services: [\n" + industrialWasteDisposal.toString() + "\n" + 
					fireServicesLevy.toString() + " ]\n";
		}
		else {
			return "";
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "Property type: Hospital [isPublic=" + isPublic + ", facilities=" + facilities + 
									", numberOfFloors=" + numberOfFloors + "]\n" + 
									extraServices();
	}

//	@Override
//	public String toString() {
//		return super.toString() + "Property type: Hospital [\n" + 
//								   industrialWasteDisposal.toString() + "\n" +
//								   fireServicesLevy.toString() + "]\n";
//	}
	

	
}
