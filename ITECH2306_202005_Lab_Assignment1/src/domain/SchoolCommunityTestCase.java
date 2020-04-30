package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Zac
 * @author Anush
 *
 */

public class SchoolCommunityTestCase {
	
	private SchoolCommunity schoolCommunity;

	// Create a SchoolCommunity object before running each test
	@Before
	public void setUp() {
		schoolCommunity = new SchoolCommunity();
	}

	// Boundary testing for categoryIndex with a highest value that should be rejected
	@Test
	public void testMaxRejectedCategoryIndex() {
		schoolCommunity.setCategory(0);
		assertNull(schoolCommunity.getCategory());
	}
	
	// Boundary testing for categoryIndex with a lowest value that should be rejected
	@Test
	public void testMinRejectedCategoryIndex() {	
		schoolCommunity.setCategory(4);
		assertNull(schoolCommunity.getCategory());
	}
	
	// Boundary testing for categoryIndex with a Minimum value allowed within the range
	@Test
	public void testMinAllowedCategoryIndex() {
		schoolCommunity.setCategory(1);
		assertNotNull(schoolCommunity.getCategory());
	}
	
	// Boundary testing for categoryIndex with a Maximum value allowed within the range
	@Test
	public void testMaxAllowedCategoryIndex() {
		schoolCommunity.setCategory(3);
		assertNotNull(schoolCommunity.getCategory());
	}
	
	// Boundary testing for CIV with a highest value that should be rejected
	@Test
	public void testMaxRejectedCIV() {
		schoolCommunity.setCapitalImprovedValue(50000001);
		assertEquals(0, schoolCommunity.getCapitalImprovedValue(), 0.001);
	}

	// Boundary testing for CIV with a lowest value that should be rejected
	@Test
	public void testMinRejectedCIV() {
		schoolCommunity.setCapitalImprovedValue(99);
		assertEquals(0, schoolCommunity.getCapitalImprovedValue(), 0.001);
	}
	
	// Boundary testing for CIV with a Minimum value allowed within the range
	@Test
	public void testMinAllowedCIV() {
		schoolCommunity.setCapitalImprovedValue(100);
		assertEquals(100, schoolCommunity.getCapitalImprovedValue(), 0.001);
	}
	
	// Boundary testing for CIV with a Maximum value allowed within the range
	@Test
	public void testMaxAllowedCIV() {
		schoolCommunity.setCapitalImprovedValue(50000000);
		assertEquals(50000000, schoolCommunity.getCapitalImprovedValue(), 0.001);
	}
		
	// Test setting up SMALL category
	@Test
	public void testSmallCategoryName() {
		schoolCommunity.setCategory(1);
		assertEquals("Small", schoolCommunity.getCategory());
	}
	
	// Test setting up MEDIUM category
	@Test
	public void testMediumCategoryName() {
		schoolCommunity.setCategory(2);
		assertEquals("Medium", schoolCommunity.getCategory());
	}
	
	// Test setting up LARGE category
	@Test
	public void testLargeCategoryName() {
		schoolCommunity.setCategory(3);
		assertEquals("Large", schoolCommunity.getCategory());
	}

	// Test the Traffic Management Levy value for SMALL category
	@Test
	public void testSmallTrafficManagementLevy() {
		schoolCommunity.setCategory(1);
		assertEquals(60.00, schoolCommunity.getTrafficManagementExtra(), 0.001);
	}
	
	// Test the Traffic Management Levy value for SMALL category
	@Test
	public void testMediumTrafficManagementLevy() {
		schoolCommunity.setCategory(2);
		assertEquals(80.00, schoolCommunity.getTrafficManagementExtra(), 0.001);
	}
	
	// Test the Traffic Management Levy value for SMALL category
	@Test
	public void testLargeTrafficManagementLevy() {
		schoolCommunity.setCategory(3);
		assertEquals(100.00, schoolCommunity.getTrafficManagementExtra(), 0.001);
	}
	
	// Test discount percentage
	@Test
	public void testDiscountPercentage() {
		schoolCommunity.getOwner().setCharity(true);
		assertEquals(0.8,(schoolCommunity.getOwner().isCharity() ? 1 - schoolCommunity.getOwner().getCharityDiscountPercentage() : 1),0.001);
	}
	
	// Test non-discount percentage
	@Test
	public void testNonDiscountPercentage() {
		schoolCommunity.getOwner().setCharity(false);
		assertEquals(1,(schoolCommunity.getOwner().isCharity() ? 1 - schoolCommunity.getOwner().getCharityDiscountPercentage() : 1), 0.001);
	}
	
	// Test multiplying CIV with CIV rate, CIV = $350,000
	@Test
	public void testCIVRateCalculation() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		assertEquals(875, (schoolCommunity.getCapitalImprovedValue() * schoolCommunity.getCapitalImprovedRate()), 0.001);
	}
	
	// Test extra services charge for SMALL category, CIV = $350,000 
	@Test
	public void testSmallCategoryExtraServices() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.setCategory(1);
		schoolCommunity.setUpExtraServices();
		assertEquals(1481.00, schoolCommunity.calculateExtraServices(), 0.001);
	}
	
	// Test extra services charge for MEDIUM category, CIV = $350,000
	@Test
	public void testMediumCategoryExtraServices() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.setCategory(2);
		schoolCommunity.setUpExtraServices();
		assertEquals(1501.00, schoolCommunity.calculateExtraServices(), 0.001);
	}
	
	// Test extra services charge for LARGE category, CIV = $350,000
	@Test
	public void testLargeCategoryExtraServices() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.setCategory(3);
		schoolCommunity.setUpExtraServices();
		assertEquals(1521.00, schoolCommunity.calculateExtraServices(), 0.001);
	}
	
	// Test total yearly rate for SMALL category with NO DISCOUNT, CIV = $350,000
	@Test
	public void testNoDiscountSmallCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(false);
		schoolCommunity.setCategory(1);
		schoolCommunity.setUpExtraServices();
		assertEquals(2356.00, schoolCommunity.calculateRates(), 0.001);
	}
	
	// Test total yearly rate for SMALL category with DISCOUNT, CIV = $350,000
	@Test
	public void testDiscountSmallCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(true);
		schoolCommunity.setCategory(1);
		schoolCommunity.setUpExtraServices();
		assertEquals(1884.80, schoolCommunity.calculateRates(), 0.001);
	}
	
	// Test total yearly rate for MEDIUM category with NO DISCOUNT, CIV = $350,000
	@Test
	public void testNoDiscountMediumCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(false);
		schoolCommunity.setCategory(2);
		schoolCommunity.setUpExtraServices();
		assertEquals(2376.00, schoolCommunity.calculateRates(), 0.001);
	}

	// Test total yearly rate for MEDIUM category with DISCOUNT, CIV = $350,000
	@Test
	public void testDiscountMediumCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(true);
		schoolCommunity.setCategory(2);
		schoolCommunity.setUpExtraServices();
		assertEquals(1900.80, schoolCommunity.calculateRates(), 0.001);
	}
	
	// Test total yearly rate for LARGE category with NO DISCOUNT, CIV = $350,000
	@Test
	public void testNoDiscountLargeCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(false);
		schoolCommunity.setCategory(3);
		schoolCommunity.setUpExtraServices();
		assertEquals(2396.00, schoolCommunity.calculateRates(), 0.001);
	}

	// Test total yearly rate for LARGE category with DISCOUNT, CIV = $350,000
	@Test
	public void testDiscountLargeCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(true);
		schoolCommunity.setCategory(3);
		schoolCommunity.setUpExtraServices();
		assertEquals(1916.80, schoolCommunity.calculateRates(), 0.001);
	}
		
}
