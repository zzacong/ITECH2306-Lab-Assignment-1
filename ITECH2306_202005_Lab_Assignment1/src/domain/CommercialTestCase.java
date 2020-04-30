package domain;

import static org.junit.Assert.assertEquals;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Anush
 * @author Zac
 *
 */

public class CommercialTestCase {
	
	private Commercial commercial;
	
	// Create a Commercial object before running each test
	@Before
	public void initialize() {
		commercial = new Commercial();
		commercial.setCapitalImprovedValue(45000.00); // ensures that CIV value is set before the Test case runs
		commercial.getOwner().setCharity(false); // Set default charitable status = false 
	}

	 
	// tests the extra services charge
	@Test
	public void testCalculateExtraServices() {
		commercial.setUpExtraServices();
		double output = commercial.calculateExtraServices();
		assertEquals(902.7, output, 0.001);
	}
	
	
	// tests the total rate depending on charitable status.
	// default charity value = false
	@Test
	public void testCalculateRate() {
		commercial.setUpExtraServices();
		assertEquals(1168.20, commercial.calculateRates(), 0.001);
		commercial.getOwner().setCharity(true);
		assertEquals(934.56, commercial.calculateRates(), 0.001);
	}

	
}
