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

	// Boundary testing for categoryIndex with an highest value that should be rejected
	@Test
	public void testMaxRejectedCategoryIndex() {
		schoolCommunity.setCategory(0);
		assertNull(schoolCommunity.getCategory());
	}
	
	// Boundary testing for categoryIndex with an lowest value that should be rejected
	@Test
	public void testMinRejectedCategoryIndex() {	
	schoolCommunity.setCategory(4);
	assertNull(schoolCommunity.getCategory());
	}
	
	// Boundary testing for categoryIndex with an Minimum value allowed within the range
	@Test
	public void testMinAllowedCategoryIndex() {
		schoolCommunity.setCategory(1);
		assertNotNull(schoolCommunity.getCategory());
	}
	
	// Boundary testing for categoryIndex with an Maximum value allowed within the range
	@Test
	public void testMaxAllowedCategoryIndex() {
		schoolCommunity.setCategory(3);
		assertNotNull(schoolCommunity.getCategory());
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
		assertEquals(60.00, schoolCommunity.getTrafficManagementExtra(), 0.0001);
	}
	
	// Test the Traffic Management Levy value for SMALL category
	@Test
	public void testMediumTrafficManagementLevy() {
		schoolCommunity.setCategory(2);
		assertEquals(80.00, schoolCommunity.getTrafficManagementExtra(), 0.0001);
	}
	
	// Test the Traffic Management Levy value for SMALL category
	@Test
	public void testLargeTrafficManagementLevy() {
		schoolCommunity.setCategory(3);
		assertEquals(100.00, schoolCommunity.getTrafficManagementExtra(), 0.0001);
	}
	
	// Test extra services charge for SMALL category, CIV = $350,000 
	@Test
	public void testSmallSchoolCommunityExtraServices() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.setCategory(1);
		schoolCommunity.setUpExtraServices();
		assertEquals(1481.00, schoolCommunity.calculateExtraServices(), 0.0001);
	}
	
	// Test extra services charge for MEDIUM category, CIV = $350,000
	@Test
	public void testMediumSchoolCommunityExtraServices() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.setCategory(2);
		schoolCommunity.setUpExtraServices();
		assertEquals(1501.00, schoolCommunity.calculateExtraServices(), 0.0001);
	}
	
	// Test extra services charge for LARGE category, CIV = $350,000
	@Test
	public void testLargeSchoolCommunityExtraServices() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.setCategory(3);
		schoolCommunity.setUpExtraServices();
		assertEquals(1521.00, schoolCommunity.calculateExtraServices(), 0.0001);
	}
	
	// Test total rate for SMALL category with NO DISCOUNT, CIV = $350,000
	@Test
	public void testNoDiscountSmallCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(false);
		schoolCommunity.setCategory(1);
		schoolCommunity.setUpExtraServices();
		assertEquals(2356.00, schoolCommunity.calculateRates(), 0.0001);
	}
	
	// Test total rate for SMALL category with DISCOUNT, CIV = $350,000
	@Test
	public void testDiscountSmallCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(true);
		schoolCommunity.setCategory(1);
		schoolCommunity.setUpExtraServices();
		assertEquals(1884.80, schoolCommunity.calculateRates(), 0.0001);
	}
	
	// Test total rate for MEDIUM category with NO DISCOUNT, CIV = $350,000
	@Test
	public void testNoDiscountMediumCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(false);
		schoolCommunity.setCategory(2);
		schoolCommunity.setUpExtraServices();
		assertEquals(2376.00, schoolCommunity.calculateRates(), 0.0001);
	}

	// Test total rate for MEDIUM category with DISCOUNT, CIV = $350,000
	@Test
	public void testDiscountMediumCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(true);
		schoolCommunity.setCategory(2);
		schoolCommunity.setUpExtraServices();
		assertEquals(1900.80, schoolCommunity.calculateRates(), 0.0001);
	}
	
	// Test total rate for LARGE category with NO DISCOUNT, CIV = $350,000
	@Test
	public void testNoDiscountLargeCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(false);
		schoolCommunity.setCategory(3);
		schoolCommunity.setUpExtraServices();
		assertEquals(2396.00, schoolCommunity.calculateRates(), 0.0001);
	}

	// Test total rate for LARGE category with DISCOUNT, CIV = $350,000
	@Test
	public void testDiscountLargeCategoryTotalRate() {
		schoolCommunity.setCapitalImprovedValue(350000.00);
		schoolCommunity.getOwner().setCharity(true);
		schoolCommunity.setCategory(3);
		schoolCommunity.setUpExtraServices();
		assertEquals(1916.80, schoolCommunity.calculateRates(), 0.0001);
	}
	
	
}
