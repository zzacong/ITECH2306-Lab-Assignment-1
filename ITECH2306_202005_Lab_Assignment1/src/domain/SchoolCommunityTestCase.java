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
	
	// Test setting up description with NULL
	@Test(expected=NullPointerException.class)
	public void testNullDescription() {
		schoolCommunity.setDescription(null);
	}
	
	// Test setting up description with EMPTY STRING
	@Test(expected=NullPointerException.class)
	public void testEmptyDescription() {
		schoolCommunity.setDescription("");
	}
		
	// Test setting up a VALID description 
	@Test
	public void testSetDescription() {
		schoolCommunity.setDescription("Lot 5 PS445631");
		assertEquals("Lot 5 PS445631", schoolCommunity.getDescription());
	}
	
	// Test setting up location with NULL
	@Test(expected=NullPointerException.class)
	public void testNullLocation() {
		schoolCommunity.setLocation(null);
	}
	
	// Test setting up location with EMPTY STRING
	@Test(expected=NullPointerException.class)
	public void testEmptyLocation() {
		schoolCommunity.setLocation("");
	}
		
	// Test setting up a VALID location 
	@Test
	public void testSetLocation() {
		schoolCommunity.setLocation("1 Residence Dr Mount Helen VIC");
		assertEquals("1 Residence Dr Mount Helen VIC", schoolCommunity.getLocation());
	}
	
	// Lower boundary testing for Area with a value should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMinRejectedArea() {
		schoolCommunity.setArea(99.99);
	}
	
	// Lower boundary testing for Area with the smallest value allowed within the range
	@Test
	public void testMinAllowedArea() {
		schoolCommunity.setArea(100.0);
		assertEquals(100.0, schoolCommunity.getArea(), 0.001);
	}
	
	// Upper boundary testing for Area with the largest value allowed within the range
	@Test
	public void testMaxAllowedArea() {
		schoolCommunity.setArea(1000000000.0);
		assertEquals(1000000000.0, schoolCommunity.getArea(), 0.001);
	}

	// Upper boundary testing for Area with a value should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMaxRejectedArea() {
		schoolCommunity.setArea(1000000001.0);
	}
	
	// Lower boundary testing for Site Value with a value should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMinRejectedSiteValue() {
		schoolCommunity.setSiteValue(99.98);
	}
	
	// Lower boundary testing for Site value with the smallest value allowed within the range
	@Test
	public void testMinAllowedSiteValue() {
		schoolCommunity.setSiteValue(99.99);
		assertEquals(99.99, schoolCommunity.getSiteValue(), 0.001);
	}
	
	// Upper boundary testing for Site value with the largest value allowed within the range
	@Test
	public void testMaxAllowedSiteValue() {
		schoolCommunity.setSiteValue(49999999.99);
		assertEquals(49999999.99, schoolCommunity.getSiteValue(), 0.001);
	}
	
	// Upper boundary testing for Site Value with a value should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMaxRejectedSiteValue() {
		schoolCommunity.setSiteValue(50000000.0);
	}
	
	// Lower boundary testing for CIV with a value that should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMinRejectedCIV() {
		schoolCommunity.setCapitalImprovedValue(99.99);
//		assertEquals(100, schoolCommunity.getCapitalImprovedValue(), 0.001);
	}
	
	// Lower boundary testing for CIV with the smallest value allowed within the range
	@Test
	public void testMinAllowedCIV() {
		schoolCommunity.setCapitalImprovedValue(100.0);
		assertEquals(100.0, schoolCommunity.getCapitalImprovedValue(), 0.001);
	}
	
	// Upper boundary testing for CIV with the largest value allowed within the range
	@Test
	public void testMaxAllowedCIV() {
		schoolCommunity.setCapitalImprovedValue(50000000.0);
		assertEquals(50000000.0, schoolCommunity.getCapitalImprovedValue(), 0.001);
	}

	// Upper boundary testing for CIV with a value that should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMaxRejectedCIV() {
		schoolCommunity.setCapitalImprovedValue(50000000.01);
//		assertEquals(100, schoolCommunity.getCapitalImprovedValue(), 0.001);
	}

	// Test setting a CIV which is smaller than Site Value
	@Test(expected=IllegalArgumentException.class)
	public void testCIVSmallerThanSiteValue() {
		schoolCommunity.setSiteValue(10000.00);
		schoolCommunity.setCapitalImprovedValue(9999.99);
	}
	
	// Test setting a CIV which is greater than Site Value
	@Test
	public void testCIVGreaterThanSiteValue() {
		schoolCommunity.setSiteValue(10000.00);
		schoolCommunity.setCapitalImprovedValue(10000.01);
		assertEquals(10000.01, schoolCommunity.getCapitalImprovedValue(), 0.001);
	}
	
	// Lower boundary testing for CIV rate with a value that should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMinRejectedCIVRate() {
		schoolCommunity.setCapitalImprovedRate(0.00009);
	}
	
	// Lower boundary testing for CIV rate with the smallest value allowed within the range
	@Test
	public void testMinAllowedCIVRate() {
		schoolCommunity.setCapitalImprovedRate(0.0001);
		assertEquals(0.0001, schoolCommunity.getCapitalImprovedRate(), 0.001);
	}
	
	// Upper boundary testing for CIV rate with the largest value allowed within the range
	@Test
	public void testMaxAllowedCIVRate() {
		schoolCommunity.setCapitalImprovedRate(1.0);
		assertEquals(1.0, schoolCommunity.getCapitalImprovedRate(), 0.001);
	}

	// Upper boundary testing for CIV rate with a value that should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMaxRejectedCIVRate() {
		schoolCommunity.setCapitalImprovedRate(1.1);
	}
	
	// Lower boundary testing for net annual value with a value that should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMinRejectedNetAnnualValue() {
		schoolCommunity.setNetAnnualValue(99.99);
	}
	
	// Lower boundary testing for net annual value with the smallest value allowed within the range
	@Test
	public void testMinAllowedNetAnnualValue() {
		schoolCommunity.setNetAnnualValue(100.0);
		assertEquals(100.0, schoolCommunity.getNetAnnualValue(), 0.001);
	}
	
	// Upper boundary testing for net annual value with the largest value allowed within the range
	@Test
	public void testMaxAllowedNetAnnualValue() {
		schoolCommunity.setNetAnnualValue(50000000.0);
		assertEquals(50000000.0, schoolCommunity.getNetAnnualValue(), 0.001);
	}

	// Upper boundary testing for net annual value with a value that should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMaxRejectedNetAnnualValue() {
		schoolCommunity.setNetAnnualValue(50000000.01);
	}
	
	// Test setting up valuation date with NULL
	@Test(expected=NullPointerException.class)
	public void testNullValuationDate() {
		schoolCommunity.setValuationDate(null);
	}
	
	// Test setting up valuation date with EMPTY STRING
	@Test(expected=NullPointerException.class)
	public void testEmptyValuationDate() {
		schoolCommunity.setValuationDate("");
	}
	
	// Test setting up valuation date with INVALID FORMAT of date in string
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidFormatValuationDate() {
		schoolCommunity.setValuationDate("02/05/2020");
	}
	
	// Test setting up a VALID valuation date 
	@Test
	public void testSetValuationDate() {
		schoolCommunity.setValuationDate("02 May 2020");
		assertEquals("02 May 2020", schoolCommunity.getValuationDate());
	}
	
	// Test setting up a NULL owner
	@Test(expected=NullPointerException.class)
	public void testNullRatePayer() {
		schoolCommunity.setOwner(null);
	}

	// Test setting up a VALID owner
	@Test
	public void testValidRatePayer() {
		schoolCommunity.setOwner(new RatePayer());
		assertEquals(new RatePayer(), schoolCommunity.getOwner());
	}
	
	// Test setting up classification with NULL
	@Test(expected=NullPointerException.class)
	public void testNullClassification() {
		schoolCommunity.setClassification(null);
//		assertEquals("Not Available", schoolCommunity.getClassification());
	}
	
	// Test setting up classification with EMPTY STRING
	@Test(expected=NullPointerException.class)
	public void testEmptyClassification() {
		schoolCommunity.setClassification("");
//		assertEquals("Not Available", schoolCommunity.getClassification());
	}
		
	// Test setting up a VALID classification 
	@Test
	public void testSetClassification() {
		schoolCommunity.setClassification("Public");
		assertEquals("Public", schoolCommunity.getClassification());
	}
	
	// Test setting up category with NULL
	@Test(expected=NullPointerException.class)
	public void testNullCategoryName() {
		schoolCommunity.setCategory(null);
//		assertEquals("Small", schoolCommunity.getCategory());
	}
	
	// Test setting up category with EMPTY STRING
	@Test(expected=NullPointerException.class)
	public void testEmptyCategoryName() {
		schoolCommunity.setCategory("");
//		assertEquals("Small", schoolCommunity.getCategory());
	}
	
	// Test setting up SMALL category
	@Test
	public void testSmallCategoryName() {
		schoolCommunity.setCategory("Small");
		assertEquals("Small", schoolCommunity.getCategory());
	}
	
	// Test setting up MEDIUM category
	@Test
	public void testMediumCategoryName() {
		schoolCommunity.setCategory("Medium");
		assertEquals("Medium", schoolCommunity.getCategory());
	}
	
	// Test setting up LARGE category
	@Test
	public void testLargeCategoryName() {
		schoolCommunity.setCategory("Large");
		assertEquals("Large", schoolCommunity.getCategory());
	}
	
	// Lower boundary testing for categoryIndex with a value that should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMinRejectedCategoryIndex() {	
		schoolCommunity.setCategory(4);
//		assertNull(schoolCommunity.getCategory());
	}
	
	// Lower boundary testing for categoryIndex with the smallest value allowed within the range
	@Test
	public void testMinAllowedCategoryIndex() {
		schoolCommunity.setCategory(1);
		assertNotNull(schoolCommunity.getCategory());
	}
	
	// Upper boundary testing for categoryIndex with the largest value allowed within the range
	@Test
	public void testMaxAllowedCategoryIndex() {
		schoolCommunity.setCategory(3);
		assertNotNull(schoolCommunity.getCategory());
	}
	
	// Upper boundary testing for categoryIndex with a value that should be rejected
	@Test(expected=IllegalArgumentException.class)
	public void testMaxRejectedCategoryIndex() {
		schoolCommunity.setCategory(0);
//		assertNull(schoolCommunity.getCategory());
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
