package domain;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Anush
 *
 */

class CommercialTestCase {
	
	private Commercial commercial;
	
	@BeforeEach
	void initialize() {
		commercial = new Commercial();
		commercial.setCapitalImprovedValue(45000.00); //ensures that CIV is set before the Test case runs
	}

	 
	//tests the extra services charge
	@Test
	void testCalculateExtraServices() {
		commercial.setUpExtraServices();
		double output = commercial.calculateExtraServices();
		assertEquals(902.7, output, 0.001);
	}
	
	
	//tests the total cost depending on charitable status.
	//default charity value = false
	@Test
	void testCalculateRate() {
		commercial.setUpExtraServices();
		assertEquals(1168.20, commercial.calculateRates(), 0.001);
		commercial.getOwner().setCharity(true);
		assertEquals(934.56, commercial.calculateRates(), 0.001);
	}
	


}
