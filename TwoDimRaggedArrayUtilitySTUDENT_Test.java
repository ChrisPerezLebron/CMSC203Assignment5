

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * this JUnit test the functionality of the TwoDimRaggedArrayUtility class
 * JUnit template was provided by Professor Grinberg
 * @author Christopher Perez Lebron
 * 
 *
 */
public class TwoDimRaggedArrayUtilitySTUDENT_Test {
	//STUDENT fill in dataSetSTUDENT with values, it must be a ragged array
	private double[][] dataSetSTUDENT = { {71.08, 281.56, 871.39, 498.94}, 
										  {477.54, 466.81, 652.42, 506.05, 335.51},
										  {453.05, 379.72, 816.43, 541.16, 846.50, 110.55, 270.97, 321.74},
										  {503.00, 486.80, 750.57, 2.85, 845.86, 832.87, 488.11} };
	
	private File inputFile,outputFile;

	/**
	 * link the inputFile and outputFile objects with their respective file in the file system
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		outputFile = new File("TestOut.txt");
		inputFile = new File("TestIn.txt");
	}

	/**
	 * tear everything down: set File objects to null and set array to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataSetSTUDENT = null;
		inputFile = outputFile = null;
	}

	/**
	 * Student Test getTotal method
	 * Return the total of all the elements in the two dimensional array
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetTotal() throws FileNotFoundException {
		assertEquals(13682.18, TwoDimRaggedArrayUtility.getTotal(TwoDimRaggedArrayUtility.readFile(inputFile)), .0001);
		assertEquals(11811.48, TwoDimRaggedArrayUtility.getTotal(dataSetSTUDENT), .0001); 
	}

	/**
	 * Student Test getAverage method
	 * Return the average of all the elements in the two dimensional array
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetAverage() throws FileNotFoundException {
		
		assertEquals(547.2872, TwoDimRaggedArrayUtility.getAverage(TwoDimRaggedArrayUtility.readFile(inputFile)), .00001);
		assertEquals(492.145, TwoDimRaggedArrayUtility.getAverage(dataSetSTUDENT), 0.0001); 
	}

	/**
	 * Student Test getRowTotal method
	 * Return the total of all the elements of the row.
	 * Row 0 refers to the first row in the two dimensional array
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetRowTotal() throws FileNotFoundException {
		assertEquals(4196.32, TwoDimRaggedArrayUtility.getRowTotal(TwoDimRaggedArrayUtility.readFile(inputFile), 1), .001);
		assertEquals(3910.06, TwoDimRaggedArrayUtility.getRowTotal(dataSetSTUDENT, 3), .001);
	}


	/**
	 * Student Test getColumnTotal method
	 * Return the total of all the elements in the column. If a row in the two dimensional array
	 * doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * Column 0 refers to the first column in the two dimensional array
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetColumnTotal() throws FileNotFoundException {
		assertEquals(728.25, TwoDimRaggedArrayUtility.getColumnTotal(TwoDimRaggedArrayUtility.readFile(inputFile), 8), .001);
		assertEquals(1504.67, TwoDimRaggedArrayUtility.getColumnTotal(dataSetSTUDENT, 0), .001);
		
	}


	/**
	 * Student Test getHighestInArray method
	 * Return the largest of all the elements in the two dimensional array.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetHighestInArray() throws FileNotFoundException {
		assertEquals(974.60, TwoDimRaggedArrayUtility.getHighestInArray(TwoDimRaggedArrayUtility.readFile(inputFile)), .001);
		assertEquals(871.39, TwoDimRaggedArrayUtility.getHighestInArray(dataSetSTUDENT), .001);
	}
	

	/**
	 * Test the writeToFile method
	 * write the array to the outputFile File
	 * then read it back to make sure formatted correctly to read
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void testWriteToFile() throws FileNotFoundException {
		//write dataSetSTUDENT to file
		TwoDimRaggedArrayUtility.writeToFile(dataSetSTUDENT, outputFile); 
		
		//read the data back from the file
		double[][] array = TwoDimRaggedArrayUtility.readFile(outputFile);
		
		//run the previous tests on this read data
		assertEquals(871.39, TwoDimRaggedArrayUtility.getHighestInArray(array), .001);
		assertEquals(1504.67, TwoDimRaggedArrayUtility.getColumnTotal(array, 0), .001);
		assertEquals(3910.06, TwoDimRaggedArrayUtility.getRowTotal(array, 3), .001);
		assertEquals(492.145, TwoDimRaggedArrayUtility.getAverage(array), .00001);
		assertEquals(11811.48, TwoDimRaggedArrayUtility.getTotal(array), .0001);
		
	}

}
