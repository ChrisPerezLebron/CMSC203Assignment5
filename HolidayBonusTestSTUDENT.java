import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * this JUnit tests the functionality of the HolidayBonus class
 * @author Christopher Perez Lebron
 *
 */
public class HolidayBonusTestSTUDENT {
	
	private double[][] array1;
	
	/**
	 * set up an array of values that will be used for testing
	 * @throws Exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception {
		array1 = new double[][] { {-39.53, 278.00, 724.20, 94.27, -45.32, -185.34, -284.65, -108.46, 492.43, -319.03},
								  {862.95, 505.05, -232.19, -71.30, 483.33, 139.28, -149.42, 633.12, -79.17},
								  {860.28},
							   	  {446.59, 868.75, 33.75, -323.11, 544.69},
								  {657.38, 403.21, -211.79, -103.22, 601.15, 884.63, -233.81} };
		
		
	
	}

	/**
	 * break down the array by assigning null to the reference variable 
	 * @throws Exception
	 */
	@After
	public void tearDownAfterClass() throws Exception {
		array1 = null;
	}
	
	/**
	 * ensures that calculateHolidyBonus method is working properly by checking if the array returned by 
	 * the function holds the expected value which was obtained by doing the process by hand
	 */
	@Test
	public void testCalculateHolidayBonus() {
		//high is 5k low is 3k other is 4k
		double[] found = HolidayBonus.calculateHolidayBonus(array1, 5000, 3000, 4000);
		assertEquals(18000, found[0],.1);
		assertEquals(22000, found[1],.1);
		assertEquals(4000, found[2],.1);
		assertEquals(17000, found[3],.1);
		assertEquals(18000, found[4],.1);
	}
	
	/**
	 * ensures that calculateTotalHolidayPay works properly by checking if the total bonus is equal to the expected value found by hand
	 */
	@Test
	public void testCalculateTotalHolidayBonus() {
		
		//high is 1930 low is 1418 other is 1674
		double total = HolidayBonus.calculateTotalHolidayBonus(array1, 1930, 1418, 1674);
		assertEquals(31924, total, .1);
	}

}
