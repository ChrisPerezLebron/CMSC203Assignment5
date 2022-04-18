/**
 * this class leveraged the TwoDimRaggedArrayUtility class to calculate the bonuses for a group or district of stores
 * @author Christopher Perez Lebron
 *
 */
public class HolidayBonus {
	
	/**
	 * analyze a 2D array of values to find the bonuses for each store. For each category the highest earning store is given the high bonus if they're earnings are positive. 
	 * The lowest earning store in a category is given the low bonus if they're earnings are positive and they have not already been given the high bonus for this category. 
	 * for each category other is given to the stores that have positive earnings but did not receive a high or low bonus.
	 * this is repeated for all columns/categories in the data set
	 * @param data a 2D array containing the sales data for a group of stores. Each row aligns with a store and each column aligns with a category
	 * @param high the bonus for the highest earner in a category
	 * @param low the bonus for the lowest earner in a category
	 * @param other the bonus for those who did not receive a high or low bonus
	 * @return a 1D double array with each index corresponding to the bonus given to each store (or row in the original data set). Each index in the bonus array contains 
	 * 			the money earned by the store in question. 
	 */
	public static double[] calculateHolidayBonus(double[][] data, double high, double low, double other) {
		/* assuming: 
		row = store
		col = category 
		*/
		
		double[] bonus = new double[data.length]; 
		
		
		
		
		int highestIndex; 
		int lowestIndex; 
		
		//search each category(column) to find the highest and lowest earner in that category. Give bonus respectively. 
		//
		for (int col = 0; col < 10; col++) {
			highestIndex = TwoDimRaggedArrayUtility.getHighestInColumnIndex(data, col); 
			lowestIndex = TwoDimRaggedArrayUtility.getLowestInColumnIndex(data, col); 
			
		
			if (highestIndex != -1 && data[highestIndex][col] > 0)
				bonus[highestIndex] += high; 
			
			//only give lowest bonus if lowest is positive and if highest has not been applied to that store for this category
			if (lowestIndex != -1 && data[lowestIndex][col] > 0 && highestIndex != lowestIndex)
				bonus[lowestIndex] += low; 
			
			//assign other to the stores that did not recieve high or low for this category
			for (int count = 0; count < bonus.length; count++) {
				if(count != highestIndex && count != lowestIndex && col < data[count].length) {
					
					//this is written inside the other loop so it can be run after determining if this col exists for this row to avoid a error
					//only give other bonus if revenue for this store in this category is positive 
					if (data[count][col] > 0)
						bonus[count] += other; 
				}
			}
		}
		
		
		
		return bonus; 
		
	}
	
	/**
	 * call the calculateHolidayBonus and total the returned 1D array of doubles
	 * @param data a 2D array containing the sales data for a group of stores. Each row aligns with a store and each column aligns with a category
	 * @param high the bonus for the highest earner in a category
	 * @param low the bonus for the lowest earner in a category
	 * @param other the bonus for those who did not receive a high or low bonus
	 * @return a double corresponding to the total amount of money given to the group of stores
	 */
	public static double calculateTotalHolidayBonus(double[][] data, double high, double low, double other) {
		double[] bonus; 
		bonus = calculateHolidayBonus(data, high, low, other); 
		
		double total = 0; 
		for (int count = 0; count < bonus.length; count++) {
			total += bonus[count];
		}
		return total; 
	}
}