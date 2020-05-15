package domain;

/**
 * @author Takeogh
 * @author Zac
 *
 */

public class VacantLand extends Property{

	private String[] overlays;
	private static final double CIV_RATE = 0.0020;

	public VacantLand() {
		super(CIV_RATE);
		System.out.println("Not implemented yet");
	}
	
	public String[] getOverlays() {
		return overlays;
	}
	
	public void setOverlays(String[] overlays) {
		this.overlays = overlays;
	}
	
	@Override
	public double calculateExtraServices() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setUpExtraServices() {
		// TODO Auto-generated method stub
	}

}