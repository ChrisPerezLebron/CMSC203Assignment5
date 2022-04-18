import java.io.*; 
import java.util.*; 
/**
 * this class provides static methods for handling 2d ragged arrays
 * @author Christopher Perez Lebron
 *
 */
public class TwoDimRaggedArrayUtility {
	
	/**
	 * reads the data contained in the File object passed to it and return it as a 2D double array
	 * @param file a File object that is linked to the file that holds data
	 * @return a 2D double array containing the data
	 * @throws FileNotFoundException
	 */
	public static double[][] readFile(File file) throws FileNotFoundException  {
		Scanner inputFile = new Scanner(file);
		
		String str;
		String[] tokens; 
		double[] rowArray;
		
		//determine the number of lines in the file and thus the number of rows in the array
		int lines = 0;
		while(inputFile.hasNext()) {
			inputFile.nextLine();
			lines++;
		}
		
		//create a 2D array that can accommodate the number of lines in the file.
		double[][] finalArray = new double[lines][]; //number of columns is purposefully left out because each row in the array will be passed individually
		
		//close input object so that the nextLine or read position can be reset by creating a new object
		inputFile.close();
		
		Scanner inputFile2 = new Scanner(file);
		
		
		int index = 0;
		while(inputFile2.hasNext() && index < 10) {
			str = inputFile2.nextLine();
			str.trim();
			tokens = str.split(" ");
			
			rowArray = new double[tokens.length]; 
			for (int count = 0; count < tokens.length; count++) {
				rowArray[count] = Double.parseDouble(tokens[count]); 
			}
			
			finalArray[index] = rowArray; 
			index++;
		}
		inputFile2.close();
		return finalArray;
	}
	
	/**
	 * writes the 2D double array passed to it into the file object passed to it
	 * @param data a 2D double array containing the data that should be written to the output file
	 * @param file a File object which has been linked with the outputFile
	 * @throws FileNotFoundException
	 */
	public static void writeToFile(double[][] data, File file) throws FileNotFoundException {
		//FileWriter fwriter = new FileWriter(file, true); 
		//PrintWriter outputFile = new PrintWriter(fwriter); 
		PrintWriter outputFile = new PrintWriter(file);
		int row, col;
		
		for(row = 0; row < data.length; row++) {
			for( col = 0; col < data[row].length -1; col++) 
				outputFile.print(data[row][col] + " ");
			
			outputFile.println(data[row][col]);
		}
		
		outputFile.close();
	}
	
	/**
	 * gets average by obtaining total across all items in the data set and dividing by the number items in the set
	 * @param data a 2D array containing data that can be averaged
	 * @return the average value of all the data
	 */
	public static double getAverage(double[][] data) {
		double totalValue = 0;
		double totalIndex = 0; 
		
		for(int row = 0; row < data.length; row++) {
			if (data[row] != null) {
				for(int col = 0; col < data[row].length; col++) {
					totalValue += data[row][col];
					totalIndex++; 
				}
			}
		}
		return (totalValue / totalIndex); 
	}
	
	/**
	 * iterate and accumulate all values in a specified column
	 * @param data a 2D array containing data
	 * @param col the column to be checked
	 * @return total value in that column
	 */
	public static double getColumnTotal(double[][] data, int col) {
		double totalValue = 0; 
		
		for(int row = 0; row < data.length; row++) {
			if (data[row] != null) {
				if( col <  data[row].length) { //check if this row has a column at #col 
					   //(for instance if col is 3 this outer if will check if column 3 exists in this row if it does not no comparison is made) 
					totalValue += data[row][col]; 
				}
			}
		}
		return totalValue;
	}
	
	/**
	 * find the highest value in the whole array
	 * @param data a 2D array containing data
	 * @return the highest value in the array as a double
	 */
	public static double getHighestInArray(double[][] data) {
		double max = data[0][0]; 
		
		for(int row = 0; row < data.length; row++) {
			if (data[row] != null) {
				for(int col = 0; col < data[row].length; col++) {
					if (data[row][col] > max) 
						max = data[row][col];
				}
			}
		}
		return max;
	}
	
	/**
	 * find and return the highest value in a specified column
	 * @param data a 2D array containing data
	 * @param col the column to be checked
	 * @return the highest value in the specified column
	 */
	public static double getHighestInColumn(double[][] data, int col) {
		
		/*find initial value: 
		 	loop through each row to find a row that has a length greater than specified col
		 	set max to the data at the index that passes above condition column number col 
		 	comparison will start at indexRow 
		*/
		boolean found = false;
		double max = -.001; //set to a number that is not $ so it cannot be confused within the data set incase a initial value is not found
		int indexRow= 0;
		while(indexRow < data.length && found == false) {
			if (col < data[indexRow].length) {
				 max = data[indexRow][col];
				found = true;
			}
			indexRow++; 
		}

		
		if (indexRow == data.length) //this will only be true when the inital position is the last index (thus no comparison needed) or if no intial value was found. Either way return max.
			return max; //return max's initial value
		
		
		//start comparisons to max at indexRow
		for(int row = indexRow; row < data.length; row++) {
			if( col <  data[row].length) { //check if this row has a column at #col 
				   //(for instance if col is 3 this outer if will check if column 3 exists in this row if it does not no comparison is made) 
				if (data[row][col] > max) 
					max = data[row][col]; 
			}
		}
		return max;
	}
	
	/**
	 * find the highest value in a column and return its row index
	 * @param data a 2D array containing data
	 * @param col the column to be checked
	 * @return the index of the highest value in the specified column
	 */
	public static int getHighestInColumnIndex(double[][] data, int col) {
		/*find initial value: 
	 	loop through each row to find a row that has a length greater than specified col
	 	set max to the data at the index that passes above condition column number col 
	 	set indexMax to the row number where max is located
	 	comparison will start at indexRow 
		 */
		boolean found = false;
		double max = -.001; //set to a number that is not $ so it cannot be confused within the data set incase a initial value is not found
		int indexMax = -1; //set to a error number ie a number that cannot be within the normal data set [0, inf] 
		int indexRow= 0;
		while(indexRow < data.length && found == false) {
			if (col < data[indexRow].length) {
				 max = data[indexRow][col];
				 indexMax = indexRow; 
				 found = true;
			}
			indexRow++; 
		}
	
		//if the above loop iterated through all rows and did not find one with specified column length return a error value
		if (indexRow == data.length) //this will only be true when the inital position is the last index (thus no comparison needed) or if no intial value was found. Either way return max.
			return indexMax; //return indexMax's intial value
		
		
		//start comparisons to max at indexRow
		for(int row = indexRow; row < data.length; row++) {
			if( col <  data[row].length) { //check if this row has a column at #col 
				   //(for instance if col is 3 this outer if will check if column 3 exists in this row if it does not no comparison is made) 
				if (data[row][col] > max) {
					max = data[row][col]; 
					indexMax = row;
				}
			}
		}
		return indexMax;
	}
	
	/**
	 * find and return the highest value in a specified row 
	 * @param data a 2D array containing data
	 * @param row the row to be checked
	 * @return the highest value in the given row
	 */
	public static double getHighestInRow(double[][] data, int row) {
		//if row is greater than the number of rows within data OR data at row is null set return a false value
		if (row >= data.length || data[row] == null)
			return -.001;
		
		double max = data[row][0];
		
		for(int col = 0; col < data[row].length; col++) {
			if (data[row][col] > max) 
				max = data[row][col];
		}
		
		return max;
	}
	
	/**
	 * find the highest value in a given row and return its column index
	 * @param data a 2D array containing data
	 * @param row the row to be checked
	 * @return the column index of the highest value in the row
	 */
	public static int getHighestInRowIndex(double[][] data, int row) {
		//if row is greater than the number of rows within data OR data at row is null set return a false value
		if (row >= data.length || data[row] == null)
			return -1;
		
		double max = data[row][0];
		int index = 0; 
		
		for(int col = 0; col < data[row].length; col++) {
			if (data[row][col] > max) { 
				max = data[row][col];
				index = col; 
			}
		}
		
		return index;
	}
	
	/**
	 * find the absolute lowest value in the given array
	 * @param data a 2D array containing data
	 * @return the lowest value in the array as a double
	 */
	public static double getLowestInArray(double[][] data) { 
		double min = data[0][0]; 
		
		for(int row = 0; row < data.length; row++) {
			for(int col = 0; col < data[row].length; col++) {
				if (data[row][col] < min) 
					min = data[row][col];
			}
		}
		return min;
	}
	
	/**
	 * get the lowest value in a given column
	 * @param data a 2D array containing data
	 * @param col the column to be checked
	 * @return the lowest value in the column as a double
	 */
	public static double getLowestInColumn(double[][] data, int col) {
		/*find initial value: 
	 	loop through each row to find a row that has a length greater than specified col
	 	set min to the data at the index that passes above condition column number col 
	 	comparison will start at indexRow 
		*/
		boolean found = false;
		double min = -.001; //set to a number that is not $ so it cannot be confused within the data set in case a initial value is not found
		int indexRow= 0;
		while(indexRow < data.length && found == false) {
			if (col < data[indexRow].length) {
				 min = data[indexRow][col];
				found = true;
			}
			indexRow++; 
		}
	
		
		if (indexRow == data.length) //this will only be true when the initial position is the last index (thus no comparison needed) or if no initial value was found. Either way return min.
			return min; //return min's initial value
		
		
		//start comparisons to max at indexRow
		for(int row = indexRow; row < data.length; row++) {
			if( col <  data[row].length) { //check if this row has a column at #col 
				   						   //(for instance if col is 3 this outer if will check if column 3 exists in this row if it does not no comparison is made) 
				if (data[row][col] < min) 
					min = data[row][col]; 
			}
		}
		return min;
	}
	
	/**
	 * find the lowest value in a given column and return its row index
	 * @param data a 2D array containing data
	 * @param col the column to be checked
	 * @return the row index of the lowest value in the given column
	 */
	public static int getLowestInColumnIndex(double[][] data, int col) {
		
		/*find initial value: 
	 	loop through each row to find a row that has a length greater than specified col
	 	set min to the data at the index that passes above condition column number col 
	 	set indexMin to the row number where min is located
	 	comparison will start at indexRow 
		 */
		boolean found = false;
		double min = -.001; //set to a number that is not $ so it cannot be confused within the data set incase a initial value is not found
		int indexMin = -1; //set to a error number ie a number that cannot be within the normal data set [0, inf] 
		int indexRow= 0;
		while(indexRow < data.length && found == false) {
			if (col < data[indexRow].length) {
				 min = data[indexRow][col];
				 indexMin = indexRow; 
				 found = true;
			}
			indexRow++; 
		}
	
		//if the above loop iterated through all rows and did not find one with specified column length return a error value
		if (indexRow == data.length) //this will only be true when the inital position is the last index (thus no comparison needed) or if no intial value was found. Either way return max.
			return indexMin; //return indexMax's intial value
		
		
		//start comparisons to max at indexRow
		for(int row = indexRow; row < data.length; row++) {
			if( col <  data[row].length) { //check if this row has a column at #col 
										   //(for instance if col is 3 this outer if will check if column 3 exists in this row if it does not no comparison is made) 
				if (data[row][col] < min) {
					min = data[row][col]; 
					indexMin = row;
				}
			}
		}
		return indexMin;
	}
	
	/**
	 * find and return the lowest value in a given row 
	 * @param data a 2D array containing data
	 * @param row the row to be checked
	 * @return the lowest value in the row
	 */
	public static double getLowestInRow(double[][] data, int row) {
		//if row is greater than the number of rows within data OR data at row is null set return a false value
		if (row >= data.length || data[row] == null)
			return -.001;
		
		double min = data[row][0];
		
		for(int col = 0; col < data[row].length; col++) {
			if (data[row][col] < min) 
				min = data[row][col];
		}
		
		return min;
	}
	
	/**
	 * find the lowest value in a given row and return its index
	 * @param data a 2D array containing data
	 * @param row the row to be checked
	 * @return the column index of the lowest value in the given row
	 */
	public static int getLowestInRowIndex(double[][] data, int row) {
		//if row is greater than the number of rows within data OR data at row is null set return a false value
		if (row >= data.length || data[row] == null)
			return -1;
		
		double min = data[row][0];
		int index = 0; 
		
		for(int col = 0; col < data[row].length; col++) {
			if (data[row][col] < min) { 
				min = data[row][col];
				index = col; 
			}
		}
		
		return index;
	}
	
	/**
	 * find the total value contained within a given row
	 * @param data a 2D array containing data
	 * @param row the row to be checked
	 * @return the total value of the row as a double
	 */
	public static double getRowTotal(double[][] data, int row) {
		double totalValue = 0; 
		
		for(int col = 0; col < data[row].length; col++) {
			totalValue += data[row][col]; 
		}
		return totalValue;
	}
	/**
	 * get the total value contained within the entire array
	 * @param data a 2D array containing data
	 * @return the total value of the array
	 */
	public static double getTotal(double[][] data) {
		double total = 0;
		
		for(int row = 0; row < data.length; row++) {
			if (data[row] != null) {
				for(int col = 0; col < data[row].length; col++) {
					total += data[row][col];
				}
			}
		}
		return total;
	}
	
	
}