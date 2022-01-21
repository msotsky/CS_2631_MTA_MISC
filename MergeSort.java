// MergeSort.java
//
// Original author:  Liam Keliher, 2019
// 
// Implements the Merge Sort algorithm.  The numbers to be sorted
// are read from a file, and the output is written to different file.


import java.io.*;
import java.util.*;

public class MergeSort {
	//--------------------------------------------------------------------
	public static void main(String[] args) {

		int[] arr = new int[]{18, 10, 5, 99, 18, 72, 46, 13};


		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the full path name of the input file: ");
		String file = sc.nextLine();
		//File fileName = new File(file);
		//int[] arr = readArrayFromFile(fileName);
		sc.close();
		mergeSort(arr, 0, arr.length-1);
		printArray(arr);
		testSorting(arr);

		// writeArrayToFile(fileName + ".sort.txt", arr);

	} // main(String[])
	//--------------------------------------------------------------------
	// Read in an array of integer values from a file whose name is given
	// as an argument.  The file is assumed to have the following format:
	// there is one integer per line, and the integer on the first line
	// is the number of integers that follow.  NOTE: THE FIRST INTEGER
	// IN THE FILE SHOULD NOT BE PLACED INTO THE ARRAY.
	public static int[] readArrayFromFile(String inName) {
		System.out.println("\nInside readArrayFromFile().");
		System.out.println("About to read values from file: " + inName + "\n");
		try{
			File file = new File(inName);
			Scanner sc = new Scanner(file);
			int size = sc.nextInt();
			int[] arr = new int[size];
			for(int index = 0; index < arr.length;i++){
				arr[index] = sc.nextInt();
			}
			sc.close();
			return arr;
		}
		catch(FileNotFoundException x){
			System.out.println("invalid file");
			return null;
		}
		// Fill in here
	} // readArrayFromFile(String)
	//--------------------------------------------------------------------
	// Writes the contents of the array passed as the second argument
	// (one value per line) to the file given by the first argument.
	public static void writeArrayToFile(String outName, int[] theArray) {
		System.out.println("\nInside writeArrayToFile().");
		System.out.println("Writing sorted values to the file: " + outName + "\n");
		//File file = new File(outName);
		
		//ran out of time

		}
		// Fill in here

	} // writeArrayToFile(String,int[])
	//--------------------------------------------------------------------
	// Tests whether or not the array of integers passed as an
	// argument is sorted.  If not, produces an error message
	// and terminates the program.
	public static void testSorting(int[] inArr) {
		if (inArr == null) {
			System.out.print("Inside testSorting()");
			System.out.println(" -- input array is null.");
			System.exit(0);			
		} // if
		int length = inArr.length;
		for (int i = 0; i < length-1; i++) {
			if (inArr[i] > inArr[i+1]) {
				System.out.println("\nInside testSorting().\n");
				System.out.println("      *** A TRAGEDY HAS OCCURRED!!!" +
						"  THE ARRAY IS NOT SORTED!!! ***\n\n");
				System.exit(0);
			} // if
		} // for
	} // testSorting(int[])
	//--------------------------------------------------------------------
	// Simply prints out the contents of the array passed as
	// an argument (10 values per line).	
	public static void printArray(int[] inArr) {
		final int NUM_PER_LINE = 10;
		if (inArr == null) {
			System.out.print("Inside printArray()");
			System.out.println(" -- input array is null.");
			System.exit(0);
		} // if

		System.out.println("------------------------------------------------");
		System.out.println("Contents of array:\n");
		int length = inArr.length;
		for (int i = 0; i < length; i++) {
			System.out.print(inArr[i] + " ");
			if ((i<length-1) && ((i+1) % NUM_PER_LINE == 0)) {
				System.out.println();
			} // if
		} // for
		System.out.println();
		System.out.println("------------------------------------------------");
	} // printArray(int[])
	//--------------------------------------------------------------------
	// Implements the Merge Sort algorithm using recursion.  The
	// MergeSort algorithm recursively sorts the first half of the
	// array and the second half of the array.  It then merges these
	// two halves, storing the merged array in a temporary array,
	// which is then copied back into the original array once
	// the merging is finished.
	public static void mergeSort(int[] inArr, int first, int last) {
		if (inArr == null) {
			System.out.println("Inside mergeSort() -- input array is null.");
			System.exit(0);
		} // if
		else if (first < 0 || last >= inArr.length) {
			System.out.println("Inside mergeSort() -- problem with 'first' or 'last'");
			System.out.println("    first = " + first + ", last = " + last);
			System.exit(0);
		} // else if
		else if (first == last) {
			return;    // base case -- a subarray of length 1 is sorted
		} // else if
		
		if (first < last) {
			// Divide the subarray inArr[first..last] into halves
			//first <----> last
			int middle = (first + last)/ 2;
			mergeSort(inArr, first, middle);
			mergeSort(inArr,middle+1,last);
			mergeSortHelper(inArr,first,last,middle);

		} // else if
	} // mergeSort(int[],int,int)
	public static void mergeSortHelper(int[]inArr,int first,int last, int middle){
		//temp arrays
		//int bf = middle - first;
		int bf = middle - first+1;
		int bl = last - middle;
		int[] tempf = new int[bf];
		int[] templ = new int[bl];

		for(int i = 0; i < bf;i++){
			tempf[i] = inArr[first + i];
		}
		for(int i = 0; i < bl; i++){
			templ[i] = inArr[middle+1+i];
		}
		//==========================================
		int indexF = 0;
		int indexL = 0;
		int index = first;

		while( (indexF < bf) && (indexL < bl) ){
			if(tempf[indexF] < templ[indexL]){
				inArr[index] = tempf[indexF];
				indexF++;
			}
			else{
				inArr[index] = templ[indexL];
				indexL++;
			}
			index++;
		}
		//back into OG arr
		while(indexF < bf){
			inArr[index] = tempf[indexF];
			indexF++;
			index++;
		}
		while(indexL < bl){
			inArr[index] = templ[indexL];
			indexL++;
			index++;
		}

	}
	//--------------------------------------------------------------------
} // class MergeSort

