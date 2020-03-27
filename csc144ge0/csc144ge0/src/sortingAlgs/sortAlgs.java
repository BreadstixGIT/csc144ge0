package sortingAlgs;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/** This program has methods to generate a random list and sort it using two basic sorting algorithms,
 *  selection sort and insertion sort. 
 * 
 * @author Jack Castiglione
 * @version 3/27/2020
 */

public class sortAlgs {
	
	private static final Random RNG = new Random();
	private static final int Maximum = 100;
	
	/** Creates a random list of numbers with a specified length.
	 * 
	 * @param length The length of the generated list (Integer)
	 * @return A list of random numbers with a length equal to the length parameter (List)
	 */
	public static List<Integer> makeList(Integer length){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i< length; i++ ) {
			int next = RNG.nextInt(Maximum);
			result.add(next);
		}
		return result;
	}
	
	/** Copies a list into a separate specified list location
	 * 
	 * @param initList The initial list to be copied (List)
	 * @param finalList The destination for the initial list to be copied in to (List)
	 *  
	 */
	public static void copyList(List<Integer> initList, List<Integer> finalList){
		for (int n : initList) {
			finalList.add(n);
		}
	}
	
	/** Finds the minimum of a list of integers
	 * 
	 * @param values The list of integers of which the method will find the minimum (List)
	 * @return The minimum value within the list (Integer)
	 */
	public static Integer findMin(List<Integer> values) {
		int minVal = values.get(0);
		for (int n : values) {
			if (n < minVal) {
				minVal = n;
			}
		}
		return minVal;
	}
	
	/** Finds the position of the minimum of a list of integers
	 * 
	 * @param values The list of integers of which the method will find the minimum (List)
	 * @return The position of the first occurrence of the minimum value within the list (Integer)
	 */
	public static Integer findPosMin(List<Integer> values) {
		int minPos = 0;
		int currentPos = 0;
		int minVal = findMin(values);
		boolean isFound = false;
		while (currentPos < values.size() &&  !isFound) {
			if (values.get(currentPos) == minVal) {
				minPos = currentPos;
				isFound = true;
			}
			currentPos++;
		}
		return minPos;
	}
	
	/** Sorts a list of integers using selection sort
	 * 
	 * @param values The list of integers to be sorted (List)
	 * @return A sorted list of integers (List)
	 */
	public static List<Integer> selecSort(List<Integer> values){
		List<Integer> funcValues = new ArrayList<Integer>();
		copyList(values, funcValues);
		List<Integer> result = new ArrayList<Integer>();
		int cPosMin;
		int cMinVal;
		while (funcValues.size() > 0) {
			cMinVal = findMin(funcValues);
			cPosMin = findPosMin(funcValues);
			result.add(cMinVal);
			funcValues.remove(cPosMin);
		}
		return result;
	}
	
	/** Sorts a list of integers using insertion sort
	 * 
	 * @param values The list of integers to be sorted (List)
	 * @return A sorted list of integers (List)
	 */
	public static List<Integer> insertSort(List<Integer> values){
		List<Integer> funcValues = new ArrayList<Integer>();
		copyList(values, funcValues);
		funcValues.remove(0);
		List<Integer> result = new ArrayList<Integer>();
		result.add(values.get(0));
		for (int n : funcValues) {
			boolean isFound = false;
			for (int m = 0; m < result.size(); m++) {
				if (n < result.get(m) && ! isFound) {
					result.add(m, n);
					isFound = true;
				}
			}
			if (! isFound) {
				result.add(n);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		
	}
}
