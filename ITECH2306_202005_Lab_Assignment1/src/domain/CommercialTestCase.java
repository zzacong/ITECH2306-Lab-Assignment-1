package domain;

import static org.junit.Assert.assertEquals;

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
	public void setUp() {
		commercial = new Commercial();
	}

	// Boundary testing for CIV with a highest value that should be rejected
	@Test
	public void testMaxRejectedCIV() {
		commercial.setCapitalImprovedValue(50000001);
		assertEquals(0, commercial.getCapitalImprovedValue(), 0.001);
	}

	// Boundary testing for CIV with a lowest value that should be rejected
	@Test
	public void testMinRejectedCIV() {
		commercial.setCapitalImprovedValue(99);
		assertEquals(0, commercial.getCapitalImprovedValue(), 0.001);
	}
	
	// Boundary testing for CIV with a Minimum value allowed within the range
	@Test
	public void testMinAllowedCIV() {
		commercial.setCapitalImprovedValue(100);
		assertEquals(100, commercial.getCapitalImprovedValue(), 0.001);
	}
	
	// Boundary testing for CIV with a Maximum value allowed within the range
	@Test
	public void testMaxAllowedCIV() {
		commercial.setCapitalImprovedValue(50000000);
		assertEquals(50000000, commercial.getCapitalImprovedValue(), 0.001);
	}

	// Test discount percentage
	@Test
	public void testDiscountPercentage() {
		commercial.getOwner().setCharity(true);
		assertEquals(0.8,(commercial.getOwner().isCharity() ? 1 - commercial.getOwner().getCharityDiscountPercentage() : 1),0.001);
	}
	
	// Test non-discount percentage
	@Test
	public void testNonDiscountPercentage() {
		commercial.getOwner().setCharity(false);
		assertEquals(1,(commercial.getOwner().isCharity() ? 1 - commercial.getOwner().getCharityDiscountPercentage() : 1), 0.001);
	}
		
	// Test multiplying CIV with CIV rate, CIV = $45,000
	@Test
	public void testCIVRateCalculation() {
		commercial.setCapitalImprovedValue(45000.00);
		assertEquals(265.50, (commercial.getCapitalImprovedValue() * commercial.getCapitalImprovedRate()), 0.001);
	}
	 
	// Tests the extra services charge, CIV = $45,000
	@Test
	public void testCalculateExtraServices() {
		commercial.setCapitalImprovedValue(45000.00);
		commercial.setUpExtraServices();
		double output = commercial.calculateExtraServices();
		assertEquals(902.7, output, 0.001);
	}
	
	// Test total yearly rate with NO DISCOUNT, CIV = $45,000
	@Test
	public void testNoDiscountTotalRate() {
		commercial.setCapitalImprovedValue(45000.00);
		commercial.getOwner().setCharity(false);
		commercial.setUpExtraServices();
		assertEquals(1168.20, commercial.calculateRates(), 0.001);
	}
	
	// Test total yearly rate with DISCOUNT, CIV = $45,000
	@Test
	public void testDiscountTotalRate() {
		commercial.setCapitalImprovedValue(45000.00);
		commercial.getOwner().setCharity(true);
		commercial.setUpExtraServices();
		assertEquals(934.56, commercial.calculateRates(), 0.001);
	}

}
