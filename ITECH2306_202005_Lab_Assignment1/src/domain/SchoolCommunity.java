package domain;

/**
 * @author Zac
 * @author Anush
 *
 */
public class SchoolCommunity extends Property {

	private String classification;
	private String category;
	private static final String SMALL = "Small", 
								MEDIUM = "Medium",
								LARGE = "Large";
	private static final double CIV_RATE = 0.0025;
	private static final int INDUSTRIAL_WASTE_DISPOSAL_UNITS = 2;
	private static final double INDUSTRIAL_WASTE_DISPOSAL_FEES = 500.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private static final double TRAFFIC_MANAGEMENT_BASE = 200.00;
	private static final double TRAFFIC_MANAGEMENT_EXTRA_SMALL = 60.00;
	private static final double TRAFFIC_MANAGEMENT_EXTRA_MEDIUM = 80.00;
	private static final double TRAFFIC_MANAGEMENT_EXTRA_LARGE = 100.00;
	private double trafficManagementExtra;
	private ServiceType industrialWasteDisposal;
	private ServiceType fireServicesLevy;
	private ServiceType trafficManagementLevy;
	
	public SchoolCommunity(int categoryIndex) {
		// We are explicit about our defaults for Strings
		this();
		this.setCategory(categoryIndex);
	}
	
	public SchoolCommunity() {
		super();
		// We are explicit about our defaults for Strings
		this.setClassification("Private");
		setCapitalImprovedRate(CIV_RATE);
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(int categoryIndex) {
		if (categoryIndex >= 1 && categoryIndex <= 3) { // Make sure the categoryIndex is within range
			switch (categoryIndex)
			{
			case 1:
				this.category = SMALL;
				trafficManagementExtra = TRAFFIC_MANAGEMENT_EXTRA_SMALL;
				break;
			case 2:
				this.category = MEDIUM;
				trafficManagementExtra = TRAFFIC_MANAGEMENT_EXTRA_MEDIUM;
				break;
			case 3:
				this.category = LARGE;
				trafficManagementExtra = TRAFFIC_MANAGEMENT_EXTRA_LARGE;
				break;
			}
		}
		else {
			this.category = null;
			trafficManagementExtra = 0;
		}
	}
	
	public double getTrafficManagementExtra() {
		return trafficManagementExtra;
	}

	// Set up the extra services of School Community property type
	@Override
	public void setUpExtraServices() {
		industrialWasteDisposal = new UnitAndRateService("Industrial Waste Disposal", 
														  INDUSTRIAL_WASTE_DISPOSAL_UNITS, 
														  INDUSTRIAL_WASTE_DISPOSAL_FEES);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy", FIRE_SERVICES_BASE, 
																FIRE_SERVICES_PERCENT, 
																getCapitalImprovedValue());
		trafficManagementLevy = new BaseAndExtraService("Traffic Management Levy", 
														 TRAFFIC_MANAGEMENT_BASE, 
														 trafficManagementExtra);
	}

	// Add up all the extra services charges
	@Override
	public double calculateExtraServices() {
		return industrialWasteDisposal.calculateChargeForServiceType() + 
			   fireServicesLevy.calculateChargeForServiceType() + 
			   trafficManagementLevy.calculateChargeForServiceType();
	}

	@Override
	public String toString() {
		return super.toString() + "Property type: SchoolCommunity [\n" +
								   industrialWasteDisposal.toString() + "\n" + 
								   fireServicesLevy.toString() + "\n" + 
								   trafficManagementLevy.toString() + "] \n";
	}
	
}
