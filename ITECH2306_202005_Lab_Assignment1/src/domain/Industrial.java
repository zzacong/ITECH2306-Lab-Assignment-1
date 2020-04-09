package domain;

/**
 * @author Anush
 * @author Zac
 * 
 */
public class Industrial extends Property {

	private String hazardStatus;
	private String heavyVehicleStatus;
	private static final double CIV_RATE = 0.0059;
	private static final int INDUSTRIAL_WASTE_DISPOSAL_UNITS = 4;
	private static final double INDUSTRIAL_WASTE_DISPOSAL_FEES = 500.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private ServiceType industrialWasteDisposal;
	private ServiceType fireServicesLevy;
	
	public Industrial() {
		super();
		//Explicit assignment of property attributes
		this.setHazardStatus("Chemicals");
		this.setHeavyVehicleStatus("Approved");
		setCapitalImprovedRate(CIV_RATE);
	}
	
	public String getHazardStatus() {
		return hazardStatus;
	}

	public void setHazardStatus(String hazardStatus) {
		this.hazardStatus = hazardStatus;
	}

	public String getHeavyVehicleStatus() {
		return heavyVehicleStatus;
	}

	public void setHeavyVehicleStatus(String heavyVehicleStatus) {
		this.heavyVehicleStatus = heavyVehicleStatus;
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
		return super.toString() + "Industrial [\n" + 
								   industrialWasteDisposal.toString() + "\n" +
								   fireServicesLevy.toString() + "]\n";
	}
	
}
