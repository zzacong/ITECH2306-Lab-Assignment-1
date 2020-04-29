package domain;

/**
 * @author Takeogh
 * @author Arun
 */
public class VacantLand extends Property{

	private static final double CIV_RATE = 0.002;
	private static final double FIRE_SERVICES_BASE = 110;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private ServiceType fireServicesLevy;
	private String[] overlays;

	public VacantLand() {
		super();
		setCapitalImprovedRate(CIV_RATE);
	}
	
	public VacantLand(String[] overlayArray) {
		super();
		setCapitalImprovedRate(CIV_RATE);
		setOverlays(overlayArray);
		
	}
	
	public String[] getOverlays() {
		return overlays;
	}
	
	public void setOverlays(String[] overlays) {
		this.overlays = overlays;
	}
	
	@Override
	public double calculateExtraServices() {

		return fireServicesLevy.calculateChargeForServiceType();
	}
	
	@Override
	public void setUpExtraServices() {

		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
				FIRE_SERVICES_BASE,
				FIRE_SERVICES_PERCENT,
				getCapitalImprovedValue());
	}
	
	public ServiceType getFireServicesLevy() {
		return fireServicesLevy;
	}
	
	public void setFireServicesLevy(ServiceType fireServicesLevy) {
		this.fireServicesLevy = fireServicesLevy;
	}
	
	public static double getCivRate() {
		return CIV_RATE;
	}
	
	@Override
	public String toString() {
		return super.toString() + "VacantLand [\n" +
								  fireServicesLevy.toString() + "]\n ";	
	}
	
	public static double getFireServicesBase() {
		return FIRE_SERVICES_BASE;
	}
	
	public static double getFireServicesPercent() {
		return FIRE_SERVICES_PERCENT;
	}

}
