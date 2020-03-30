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
	
	public static void main(String[] args) {
	}
	
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
	
	/** Uses selection search to find the first index of a given value in a list if such an index
	 * exists 
	 * 
	 * @param values The list to search through (List)
	 * @param searchFor The value to be searched for (Integer)
	 * @return The index of the value searchFor, or -1 if no such index exists
	 */
	public static Integer selecSearch(List<Integer> values, int searchFor) {
		for (int index = 0; index < values.size(); index++) {
			if (values.get(index) == searchFor) {
				return index;
			}
		}
		return -1;
	}
	
	/** Uses binary search to find the first index of a given value in a list if such an index
	 * exists 
	 * 
	 * @param values The list to search through (List)
	 * @param searchFor The value to be searched for (Integer)
	 * @return The index of the value searchFor, or -1 if no such index exists
	 */
	public static Integer binSearch(List<Integer> values, int searchFor) {
		List<Integer> funcValues = new ArrayList<Integer>();
		Integer index = 0;
		copyList(values, funcValues);
		while (funcValues.size() > 1) {
			Integer midVal = funcValues.size()/2;
			if (funcValues.get(midVal) == searchFor) {
				return midVal + index;
			} else if (funcValues.get(midVal) < searchFor) {
				index = index + midVal;
				funcValues = funcValues.subList(midVal, funcValues.size());
			} else if (funcValues.get(midVal) > searchFor) {
				funcValues = funcValues.subList(0, midVal);
				
			}
			System.out.println(funcValues);
		}
		if (funcValues.get(0) == searchFor) {
			return index;
		} else {
			return -1;
		}
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
	
	/** Combines two sorted lists into a third sorted list containing all values from each
	 * 
	 * @param values1 The first sorted list (List)
	 * @param values2 The second sorted list (List)
	 * @return A combined sorted list (List)
	 */
	public static List<Integer> merge(List<Integer> values1, List<Integer> values2) {
		List<Integer> result = new ArrayList<Integer>();
		int index1 = 0;
		int index2 = 0;
		while (index1 < values1.size() && index2 < values2.size()) {
			if (values1.get(index1) < values2.get(index2)) {
				result.add(values1.get(index1));
				index1++;
			} else {
				result.add(values2.get(index2));
				index2++;
			}
		} 
		if (index1 < values1.size()) {
			result.addAll(values1.subList(index1, values1.size()));
		} else {
			result.addAll(values2.subList(index2, values2.size()));
		}
		return result;
	}
	
	/** Sorts a list of integers using merge sort
	 * 
	 * @param values The list of integers to be sorted (List)
	 * @return A sorted list of integers (List)
	 */
	public static List<Integer> mergeSort(List<Integer> values){
		if (values.size() <= 1) {
			return values;
		} 
		List<Integer> first = new ArrayList<Integer>();
		List<Integer> last = new ArrayList<Integer>();
		Integer midVal = values.size()/2;
		first = values.subList(0, midVal);
		last = values.subList(midVal, values.size());
		first = mergeSort(first);
		last = mergeSort(last);
		return merge(first, last);
	}
		
}
