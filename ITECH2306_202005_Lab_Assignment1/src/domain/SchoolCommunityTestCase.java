package domain;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.*;
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
	public void setUp() throws Exception {
		schoolCommunity = new SchoolCommunity();
		schoolCommunity.setCapitalImprovedValue(350000.00); // Set up CIV value for the property type before running test case
		schoolCommunity.getOwner().setCharity(false); // Set default charitable status = false 
	}

	// Boundary testing for categoryIndex
	@Test
	public void testCategoryNull() {
		assertNull(schoolCommunity.getCategory());
		schoolCommunity.setCategory(0);
		assertNull(schoolCommunity.getCategory());
		schoolCommunity.setCategory(4);
		assertNull(schoolCommunity.getCategory());
	}
	
	// Boundary testing for categoryIndex
	@Test
	public void testCategoryNotNull() {
		schoolCommunity.setCategory(1);
		assertNotNull(schoolCommunity.getCategory());
		schoolCommunity.setCategory(3);
		assertNotNull(schoolCommunity.getCategory());
	}
	
	// Test the category name
	@Test
	public void testCategory() {
		schoolCommunity.setCategory(1);
		assertEquals("Small", schoolCommunity.getCategory());
		schoolCommunity.setCategory(2);
		assertEquals("Medium", schoolCommunity.getCategory());
		schoolCommunity.setCategory(3);
		assertEquals("Large", schoolCommunity.getCategory());
	}
	
	// Test the Traffic Management Levy value 
	@Test
	public void testTrafficManagementLevy() {
		schoolCommunity.setCategory(1);
		assertEquals(60.00, schoolCommunity.getTrafficManagementExtra(), 0.0001);
		schoolCommunity.setCategory(2);
		assertEquals(80.00, schoolCommunity.getTrafficManagementExtra(), 0.0001);
		schoolCommunity.setCategory(3);
		assertEquals(100.00, schoolCommunity.getTrafficManagementExtra(), 0.0001);
	}

	// Test extra services charge for Small category 
	@Test
	public void testSmallSchoolCommunityExtraServices() {
		schoolCommunity.setCategory(1);
		schoolCommunity.setUpExtraServices();
		assertEquals(1481.00, schoolCommunity.calculateExtraServices(), 0.0001);
	}
	
	// Test extra services charge for Medium category
	@Test
	public void testMediumSchoolCommunityExtraServices() {
		schoolCommunity.setCategory(2);
		schoolCommunity.setUpExtraServices();
		assertEquals(1501.00, schoolCommunity.calculateExtraServices(), 0.0001);
	}
	
	// Test extra services charge for Large category
	@Test
	public void testLargeSchoolCommunityExtraServices() {
		schoolCommunity.setCategory(3);
		schoolCommunity.setUpExtraServices();
		assertEquals(1521.00, schoolCommunity.calculateExtraServices(), 0.0001);
	}
	
	// Test total rate for Small category depending on charitable status
	// default charity value = false
	@Test
	public void testSmallSchoolCommunityRate() {
		schoolCommunity.setCategory(1);
		schoolCommunity.setUpExtraServices();
		assertEquals(2356.00, schoolCommunity.calculateRates(), 0.0001);
		schoolCommunity.getOwner().setCharity(true);
		assertEquals(1884.80, schoolCommunity.calculateRates(), 0.0001);
	}
	
	// Test total rate for Medium category depending on charitable status
	// default charity value = false	
	@Test
	public void testMediumSchoolCommunityRate() {
		schoolCommunity.setCategory(2);
		schoolCommunity.setUpExtraServices();
		assertEquals(2376.00, schoolCommunity.calculateRates(), 0.0001);
		schoolCommunity.getOwner().setCharity(true);
		assertEquals(1900.80, schoolCommunity.calculateRates(), 0.0001);
	}
	
	// Test total rate for Large category depending on charitable status
	// default charity value = false
	@Test
	public void testLargeSchoolCommunityRate() {
		schoolCommunity.setCategory(3);
		schoolCommunity.setUpExtraServices();
		assertEquals(2396.00, schoolCommunity.calculateRates(), 0.0001);
		schoolCommunity.getOwner().setCharity(true);
		assertEquals(1916.80, schoolCommunity.calculateRates(), 0.0001);
	}
	
}
