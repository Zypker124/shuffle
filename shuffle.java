
/*
ID: angusjy1
LANG: JAVA
TASK: 
*/
import java.io.*;
import java.util.*;

class shuffle {
	
	
	//For comparing doubles (floating point error)
	public double marginOfError = 0.0001;
	
	/*
	 st = new StringTokenizer(reader.readLine());
	 Integer.parseInt(st.nextToken());
	 Double.parseDouble(st.nextToken());
	 ArrayList<String> name = new ArrayList<String>();
	 ArrayList<Double> name = new ArrayList<Double>();
	 ArrayList<Integer> name = new ArrayList<Integer>();
	 System.out.println();
	 * */
	
  public static void main (String [] args) throws IOException {
	  shuffle obj = new shuffle();
	  obj.run();
  }
  
  public void run() throws IOException {
	//Use BufferedReader rather than RandomAccessFile; it's much faster
	    BufferedReader reader = new BufferedReader(new FileReader("shuffle.in"));
	                                                  // input file name goes above
	  PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
	    StringTokenizer st = new StringTokenizer(reader.readLine());
	  
	    /**int numOfCows = Integer.parseInt(st.nextToken());
	    st = new StringTokenizer(reader.readLine());
	    int[] shuffle = new int[numOfCows];
	    for(int ind = 0; ind < shuffle.length; ind++){
	    	shuffle[ind]=Integer.parseInt(st.nextToken());
	    }
	    st = new StringTokenizer(reader.readLine());
	    int[] finalOrder = new int[numOfCows];
	    for(int ind = 0; ind < finalOrder.length; ind++){
	    	finalOrder[ind]=Integer.parseInt(st.nextToken());
	    } 
	  
	   int[] initialOrder = new int[numOfCows];
	   
	   for(int ind = 0; ind < finalOrder.length; ind++){
		   int ID = finalOrder[ind];
		   int index = ind+1;
		   for(int time = 1; time <= 3; time++){
			   for(int ind2 = 0; ind2 < shuffle.length; ind2++){
				   if(shuffle[ind2]==index){
					   index=ind2+1;
					   break;
				   }
			   }
		   }
		   initialOrder[index-1]=ID;
	   }
	   
	   
	   for(int ind = 0; ind < initialOrder.length; ind++){
		   writer.println(initialOrder[ind]);
	   }*/
	    
	    int numOfCows = Integer.parseInt(st.nextToken());
	    st = new StringTokenizer(reader.readLine());
	    int[] shuffle = new int[numOfCows+1];
	    for(int ind = 1; ind < numOfCows+1; ind++){
	    	shuffle[ind]=Integer.parseInt(st.nextToken());
	    }
	    int positionsConstant = 0;
	    ArrayList<Integer> positionsInCycle = new ArrayList<Integer>();
	    for(int pos = 1; pos <= numOfCows; pos++){
	    	ArrayList<Integer> positionsTravelled = new ArrayList<Integer>();
	    	if(posExists(positionsInCycle, pos)){
	    		continue;
	    	}
	    	int changePos = pos;
	    	int newPos = shuffle[changePos];
	    	positionsTravelled.add(changePos);
	    	while(!posExists(positionsTravelled, newPos)){
	    		changePos = newPos;
	    		newPos = shuffle[changePos];
	    		if(posExists(positionsInCycle, changePos)||posExists(positionsInCycle, newPos)){
	    			break;
	    		}
	    		positionsTravelled.add(changePos);
	    	}
	    	if(posExists(positionsInCycle, changePos)||posExists(positionsInCycle, newPos)){
    			continue;
    		}
	    	
	    	changePos = newPos;
	    	newPos = shuffle[changePos];
	    	positionsInCycle.add(changePos);
	    	while(!posExists(positionsInCycle, newPos)){
	    		changePos = newPos;
	    		newPos = shuffle[changePos];
	    		positionsInCycle.add(changePos);
	    	}
	    	//positionsInCycle.add(changePos);
	    }
	    writer.println(positionsInCycle.size());
	   reader.close();
	    writer.close();
  }
  
  public boolean posExists(ArrayList<Integer> positionsTravelled, int pos){
	  if(positionsTravelled==null||positionsTravelled.size()==0){
		  return false;
	  }
	  ArrayList<Integer> positionsTravelled2 = sortIL(positionsTravelled);
	  if(binarySearchIL(positionsTravelled2, 0, positionsTravelled2.size()-1, pos)!=-1){
		  return true;
	  }
	  return false;
  }
  
  /**
   
   //Prime and Composite (With help from BeginnersBook.com)
   public boolean isPrime(int n)
   public boolean isComposite(int n)
   
   
   
   //Printing 1-D Arrays
   public void printIA(int[] arr)
   public void printBA(boolean[] arr)
   public void printDA(double[] arr)
   public void printCA(char[] arr)
   public void printSA(String[] arr)
   
   //Printing 2-D Arrays
   public void printIA2(int[][] arr)
   public void printBA2(boolean[][] arr)
   public void printDA2(double[][] arr)
   public void printCA2(char[][] arr)
   public void printSA2(String[][] arr)
   
   //Printing 1-D Lists
   public void printIL(ArrayList<Integer> arr)
   public void printDL(ArrayList<Double> arr)
   public void printSL(ArrayList<String> arr)
   
   //Printing 2-D Lists
   public void printIL2(ArrayList<int[]> arr)
   public void printDL2(ArrayList<double[]> arr)
   public void printBL2(ArrayList<boolean[]> arr)
   public void printSL2(ArrayList<String[]> arr)
   public void printCL2(ArrayList<char[]> arr)
   
   
   
   //Binary Search - 1-D Arrays
   public int binarySearchIA(int arr[], int lowerIndex, int higherIndex, int find)
   public int binarySearchDA(double arr[], int lowerIndex, int higherIndex, double find)
   public int binarySearchCA(char arr[], int lowerIndex, int higherIndex, char find)
   public int binarySearchSA(String arr[], int lowerIndex, int higherIndex, String find)
   
   //Binary Search - 1-D Lists
   public int binarySearchIL(ArrayList<Integer> arr, int lowerIndex, int higherIndex, int find)
   public int binarySearchDL(ArrayList<Double> arr, int lowerIndex, int higherIndex, double find)
   public int binarySearchSL(ArrayList<String> arr, int lowerIndex, int higherIndex, String find)
   
   //Binary Search - 2-D Arrays
   public int binarySearchIA2(int arr[][], int lowerIndex, int higherIndex, int[] find)
   public int binarySearchDA2(double arr[][], int lowerIndex, int higherIndex, double[] find)
   public int binarySearchCA2(char arr[][], int lowerIndex, int higherIndex, char[] find)
   public int binarySearchSA2(String arr[][], int lowerIndex, int higherIndex, String[] find)
   
   //Binary Search - 2-D Lists
   public int binarySearchIL2(ArrayList<int[]> arr, int lowerIndex, int higherIndex, int[] find)
   public int binarySearchDL2(ArrayList<double[]> arr, int lowerIndex, int higherIndex, double[] find)
   public int binarySearchCL2(ArrayList<char[]> arr, int lowerIndex, int higherIndex, char[] find)
   public int binarySearchSL2(ArrayList<String[]> arr, int lowerIndex, int higherIndex, String[] find)
   
   
   
   //Duplicate - 1-D Arrays
   public int[] duplicateIA(int[] inputArr)
   public double[] duplicateDA(double[] inputArr)
   public boolean[] duplicateBA(boolean[] inputArr)
   public char[] duplicateCA(char[] inputArr)
   public String[] duplicateSA(String[] inputArr)
    
   //Duplicate - 1-D Lists
   public ArrayList<Integer> duplicateIL(ArrayList<Integer> inputArr)
   public ArrayList<Double> duplicateDL(ArrayList<Double> inputArr)
   public ArrayList<String> duplicateSL(ArrayList<String> inputArr)
   
   //Duplicate - 2-D Arrays
    public int[][] duplicateIA2(int[][] inputArr)
   public double[][] duplicateDA2(double[][] inputArr)
   public boolean[][] duplicateBA2(boolean[][] inputArr)
   public char[][] duplicateCA2(char[][] inputArr)
   public String[][] duplicateSA2(String[][] inputArr)
   
   //Duplicate - 2-D Lists
   public ArrayList<int[]> duplicateIL2(ArrayList<int[]> inputArr)
   public ArrayList<double[]> duplicateDL2(ArrayList<double[]> inputArr)
   public ArrayList<String[]> duplicateSL2(ArrayList<String[]> inputArr)
   public ArrayList<boolean[]> duplicateBL2(ArrayList<boolean[]> inputArr)
   public ArrayList<char[]> duplicateCL2(ArrayList<char[]> inputArr)
   
   
   
   //GreaterThan - 1-D Arrays
   public boolean greaterThanIA(int[] arr1, int[] arr2)
   public boolean greaterThanDA(double[] arr1, double[] arr2)
   public boolean greaterThanSA(String[] arr1, String[] arr2)
   public boolean greaterThanCA(char[] arr1, char[] arr2)
   
   //EqualsThan - 1-D Arrays
   public boolean equalsThanIA(int[] arr1, int[] arr2)
   public boolean equalsThanDA(double[] arr1, double[] arr2)
   public boolean equalsThanSA(String[] arr1, String[] arr2)
   public boolean equalsThanCA(char[] arr1, char[] arr2)
   
   //LessThan - 1-D Arrays
   public boolean lessThanIA(int[] arr1, int[] arr2)
   public boolean lessThanDA(double[] arr1, double[] arr2)
   public boolean lessThanSA(String[] arr1, String[] arr2)
   public boolean lessThanCA(char[] arr1, char[] arr2)
   
   //GreaterThan - 1-D Lists
   public boolean greaterThanIL(ArrayList<Integer> arr1, ArrayList<Integer> arr2)
   public boolean greaterThanDL(ArrayList<Double> arr1, ArrayList<Double> arr2)
   public boolean greaterThanSL(ArrayList<String> arr1, ArrayList<String> arr2)
   
   //EqualsThan - 1-D Lists
   public boolean equalsThanIL(ArrayList<Integer> arr1, ArrayList<Integer> arr2)
   public boolean equalsThanDL(ArrayList<Double> arr1, ArrayList<Double> arr2)
   public boolean equalsThanSL(ArrayList<String> arr1, ArrayList<String> arr2)
   
   //LessThan - 1-D Lists
   public boolean lessThanIL(ArrayList<Integer> arr1, ArrayList<Integer> arr2)
   public boolean lessThanDL(ArrayList<Double> arr1, ArrayList<Double> arr2)
   public boolean lessThanSL(ArrayList<String> arr1, ArrayList<String> arr2)
      
   
   
   //QuickSort - 1-D Arrays (With help from Geeksforgeeks.org)
   public int[] sortIA(int[] inputArr)
   public String[] sortSA(String[] inputArr)
   public double[] sortDA(double[] inputArr)
   public char[] sortCA(char[] inputArr)
   
   //QuickSort - 1-D Lists
   public ArrayList<Integer> sortIL(ArrayList<Integer> inputArr)
   public ArrayList<Double> sortDL(ArrayList<Double> inputArr)
   public ArrayList<String> sortSL(ArrayList<String> inputArr)
   
   //QuickSort - 2-D Arrays (With help from Geeksforgeeks.org)
   public int[][] sortIA2(int[][] inputArr)
   public String[][] sortSA2(String[][] inputArr)
   public double[][] sortDA2(double[][] inputArr)
   public char[][] sortCA2(char[][] inputArr)
   
   //QuickSort - 2-D Lists
   public ArrayList<int[]> sortIL2(ArrayList<int[]> inputArr)
   public ArrayList<double[]> sortDL2(ArrayList<double[]> inputArr)
   public ArrayList<String[]> sortSL2(ArrayList<String[]> inputArr)
   
   
   
   
   * */
  
  
  //Prime and Composite (With help from BeginnersBook.com)
  
  public boolean isPrime(int n) {
	  if(n < 2)
		  return false;
	    if(n > 2 && (n % 2) == 0)
	       return false;
	    for(int i = 3; i * i <= n; i += 2)
	        if (n % i == 0) 
	            return false;
	    return true;
  }
  
  public boolean isComposite(int n) {
	  if(n > 2 && isPrime(n)==false)
		  return true;
	  return false;
  }
  
  
  //Printing 1-D Arrays
  
  public void printIA(int[] arr){
	  for(int i = 0; i < arr.length; i++){
		  System.out.println(i+": "+arr[i]);
	  }
  }
  
  public void printBA(boolean[] arr){
	  for(int i = 0; i < arr.length; i++){
		  System.out.println(i+": "+arr[i]);
	  }
  }
  
  public void printDA(double[] arr){
	  for(int i = 0; i < arr.length; i++){
		  System.out.println(i+": "+arr[i]);
	  }
  }
  
  public void printCA(char[] arr){
	  for(int i = 0; i < arr.length; i++){
		  System.out.println(i+": "+arr[i]);
	  }
  }
  
  public void printSA(String[] arr){
	  for(int i = 0; i < arr.length; i++){
		  System.out.println(i+": "+arr[i]);
	  }
  }

  
  //Printing 2-D Arrays
  
  public void printIA2(int[][] arr){
	  for(int r = 0; r < arr.length; r++){
		  System.out.print(r+": ");
		  for(int c = 0; c < arr[0].length; c++){
			  System.out.print(arr[r][c]+" ");
		  }
		  System.out.println();
	  }
  }
  
  public void printBA2(boolean[][] arr){
	  for(int r = 0; r < arr.length; r++){
		  System.out.print(r+": ");
		  for(int c = 0; c < arr[0].length; c++){
			  System.out.print(arr[r][c]+" ");
		  }
		  System.out.println();
	  }
  }
  
  public void printDA2(double[][] arr){
	  for(int r = 0; r < arr.length; r++){
		  System.out.print(r+": ");
		  for(int c = 0; c < arr[0].length; c++){
			  System.out.print(arr[r][c]+" ");
		  }
		  System.out.println();
	  }
  }
  
  public void printCA2(char[][] arr){
	  for(int r = 0; r < arr.length; r++){
		  System.out.print(r+": ");
		  for(int c = 0; c < arr[0].length; c++){
			  System.out.print(arr[r][c]+" ");
		  }
		  System.out.println();
	  }
  }
  
  public void printSA2(String[][] arr){
	  for(int r = 0; r < arr.length; r++){
		  System.out.print(r+": ");
		  for(int c = 0; c < arr[0].length; c++){
			  System.out.print(arr[r][c]+" ");
		  }
		  System.out.println();
	  }
  }
  
  
  //Printing 1-D Lists
  
  public void printIL(ArrayList<Integer> arr){
	  for(int i = 0; i < arr.size(); i++){
		  System.out.println(i+": "+arr.get(i));
	  }
  }
  
  public void printDL(ArrayList<Double> arr){
	  for(int i = 0; i < arr.size(); i++){
		  System.out.println(i+": "+arr.get(i));
	  }
  }
  
  public void printSL(ArrayList<String> arr){
	  for(int i = 0; i < arr.size(); i++){
		  System.out.println(i+": "+arr.get(i));
	  }
  }
  
  
  //Printing 2-D Lists
  
  public void printIL2(ArrayList<int[]> arr){
	  for(int i = 0; i < arr.size(); i++){
		  System.out.print(i+": ");
		  int[] arr2 = arr.get(i);
		  for(int j = 0; j < arr2.length; j++){
			  System.out.print(arr2[j]+" ");
		  }
		  System.out.println();
	  }
  }
  
  public void printDL2(ArrayList<double[]> arr){
	  for(int i = 0; i < arr.size(); i++){
		  System.out.print(i+": ");
		  double[] arr2 = arr.get(i);
		  for(int j = 0; j < arr2.length; j++){
			  System.out.print(arr2[j]+" ");
		  }
		  System.out.println();
	  }
  }
  
  public void printBL2(ArrayList<boolean[]> arr){
	  for(int i = 0; i < arr.size(); i++){
		  System.out.print(i+": ");
		  boolean[] arr2 = arr.get(i);
		  for(int j = 0; j < arr2.length; j++){
			  System.out.print(arr2[j]+" ");
		  }
		  System.out.println();
	  }
  }
  
  public void printSL2(ArrayList<String[]> arr){
	  for(int i = 0; i < arr.size(); i++){
		  System.out.print(i+": ");
		  String[] arr2 = arr.get(i);
		  for(int j = 0; j < arr2.length; j++){
			  System.out.print(arr2[j]+" ");
		  }
		  System.out.println();
	  }
  }
  
  public void printCL2(ArrayList<char[]> arr){
	  for(int i = 0; i < arr.size(); i++){
		  System.out.print(i+": ");
		  char[] arr2 = arr.get(i);
		  for(int j = 0; j < arr2.length; j++){
			  System.out.print(arr2[j]+" ");
		  }
		  System.out.println();
	  }
  }
  
  
  //Binary Search - 1-D Arrays
  
  public int binarySearchIA(int arr[], int lowerIndex, int higherIndex, int find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (arr[mid] == find)
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray
          if (arr[mid] > find)
             return binarySearchIA(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchIA(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchDA(double arr[], int lowerIndex, int higherIndex, double find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself arr[mid] == find
          if((Math.abs(arr[mid]-find))<=marginOfError)
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray
          if (arr[mid] > find)
             return binarySearchDA(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchDA(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchCA(char arr[], int lowerIndex, int higherIndex, char find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (arr[mid] == find)
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray
          if (arr[mid] > find)
             return binarySearchCA(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchCA(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchSA(String arr[], int lowerIndex, int higherIndex, String find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (arr[mid].equals(find))
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray arr[mid] > find
          if (arr[mid].compareTo(find)>0)
             return binarySearchSA(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchSA(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  
  //Binary Search - 1-D Lists
  
  public int binarySearchIL(ArrayList<Integer> arr, int lowerIndex, int higherIndex, int find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (arr.get(mid) == find)
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray
          if (arr.get(mid) > find)
             return binarySearchIL(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchIL(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchDL(ArrayList<Double> arr, int lowerIndex, int higherIndex, double find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself arr[mid] == find
          if((Math.abs(arr.get(mid)-find))<=marginOfError)
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray
          if (arr.get(mid) > find)
             return binarySearchDL(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchDL(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchSL(ArrayList<String> arr, int lowerIndex, int higherIndex, String find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (arr.get(mid).equals(find))
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray arr[mid] > find
          if (arr.get(mid).compareTo(find)>0)
             return binarySearchSL(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchSL(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  
  //Binary Search - 2-D Arrays
  
  public int binarySearchIA2(int arr[][], int lowerIndex, int higherIndex, int[] find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (equalsThanIA(arr[mid], find))
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray arr[mid] > find
          if (greaterThanIA(arr[mid], find))
             return binarySearchIA2(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchIA2(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchDA2(double arr[][], int lowerIndex, int higherIndex, double[] find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself arr[mid] == find
          if(equalsThanDA(arr[mid], find))
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray
          if (greaterThanDA(arr[mid], find))
             return binarySearchDA2(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchDA2(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchCA2(char arr[][], int lowerIndex, int higherIndex, char[] find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (equalsThanCA(arr[mid], find))
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray
          if (greaterThanCA(arr[mid], find))
             return binarySearchCA2(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchCA2(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchSA2(String arr[][], int lowerIndex, int higherIndex, String[] find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (equalsThanSA(arr[mid], find))
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray arr[mid] > find
          if (greaterThanSA(arr[mid], find))
             return binarySearchSA2(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchSA2(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  
  //Binary Search - 2-D Lists
  
  public int binarySearchIL2(ArrayList<int[]> arr, int lowerIndex, int higherIndex, int[] find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (equalsThanIA(arr.get(mid), find))
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray arr[mid] > find
          if (greaterThanIA(arr.get(mid), find))
             return binarySearchIL2(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchIL2(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchDL2(ArrayList<double[]> arr, int lowerIndex, int higherIndex, double[] find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself arr[mid] == find
          if(equalsThanDA(arr.get(mid), find))
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray
          if (greaterThanDA(arr.get(mid), find))
             return binarySearchDL2(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchDL2(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchCL2(ArrayList<char[]> arr, int lowerIndex, int higherIndex, char[] find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (equalsThanCA(arr.get(mid), find))
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray
          if (greaterThanCA(arr.get(mid), find))
             return binarySearchCL2(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchCL2(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  public int binarySearchSL2(ArrayList<String[]> arr, int lowerIndex, int higherIndex, String[] find)
  {
      if (higherIndex>=lowerIndex)
      {
          int mid = lowerIndex + (higherIndex - lowerIndex)/2;

          // If the element is present at the middle itself
          if (equalsThanSA(arr.get(mid), find))
             return mid;

          // If element is smaller than mid, then it can only
          // be present in left subarray arr[mid] > find
          if (greaterThanSA(arr.get(mid), find))
             return binarySearchSL2(arr, lowerIndex, mid-1, find);

          // Else the element can only be present in right
          // subarray
          return binarySearchSL2(arr, mid+1, higherIndex, find);
      }

      // We reach here when element is not present in array
      return -1;
  }
  
  
  //Duplicate - 1-D Arrays
  
  public int[] duplicateIA(int[] inputArr){
	  int[] inputArr2 = new int[inputArr.length];
	  for(int ind = 0; ind < inputArr.length; ind++){
		  inputArr2[ind]=inputArr[ind];
	  }
	  return inputArr2;
  }
  
  public boolean[] duplicateBA(boolean[] inputArr){
	  boolean[] inputArr2 = new boolean[inputArr.length];
	  for(int ind = 0; ind < inputArr.length; ind++){
		  inputArr2[ind]=inputArr[ind];
	  }
	  return inputArr2;
  }
  
  public double[] duplicateDA(double[] inputArr){
	  double[] inputArr2 = new double[inputArr.length];
	  for(int ind = 0; ind < inputArr.length; ind++){
		  inputArr2[ind]=inputArr[ind];
	  }
	  return inputArr2;
  }
  
  public char[] duplicateCA(char[] inputArr){
	  char[] inputArr2 = new char[inputArr.length];
	  for(int ind = 0; ind < inputArr.length; ind++){
		  inputArr2[ind]=inputArr[ind];
	  }
	  return inputArr2;
  }
  
  public String[] duplicateSA(String[] inputArr){
	  String[] inputArr2 = new String[inputArr.length];
	  for(int ind = 0; ind < inputArr.length; ind++){
		  inputArr2[ind]=inputArr[ind];
	  }
	  return inputArr2;
  }
  
  
  //Duplicate - 1-D Lists
  
  public ArrayList<Integer> duplicateIL(ArrayList<Integer> inputArr){
	  ArrayList<Integer> inputArr2 = new ArrayList<Integer>();
	  for(int ind = 0; ind < inputArr.size(); ind++){
		  inputArr2.add(inputArr.get(ind));
	  }
	  return inputArr2;
  }
  
  public ArrayList<Double> duplicateDL(ArrayList<Double> inputArr){
	  ArrayList<Double> inputArr2 = new ArrayList<Double>();
	  for(int ind = 0; ind < inputArr.size(); ind++){
		  inputArr2.add(inputArr.get(ind));
	  }
	  return inputArr2;
  }
  
  public ArrayList<String> duplicateSL(ArrayList<String> inputArr){
	  ArrayList<String> inputArr2 = new ArrayList<String>();
	  for(int ind = 0; ind < inputArr.size(); ind++){
		  inputArr2.add(inputArr.get(ind));
	  }
	  return inputArr2;
  }
  
  
  //Duplicate - 2-D Arrays
  
  public int[][] duplicateIA2(int[][] inputArr){
	  int[][] inputArr2 = new int[inputArr.length][inputArr[0].length];
	  for(int ind = 0; ind < inputArr.length; ind++){
		  for(int ind2 = 0; ind2 < inputArr[0].length; ind2++){
			  inputArr2[ind][ind2]=inputArr[ind][ind2];
		  }
	  }
	  return inputArr2;
  }
  
  public boolean[][] duplicateBA2(boolean[][] inputArr){
	  boolean[][] inputArr2 = new boolean[inputArr.length][inputArr[0].length];
	  for(int ind = 0; ind < inputArr.length; ind++){
		  for(int ind2 = 0; ind2 < inputArr[0].length; ind2++){
			  inputArr2[ind][ind2]=inputArr[ind][ind2];
		  }
	  }
	  return inputArr2;
  }
  
  public double[][] duplicateDA2(double[][] inputArr){
	  double[][] inputArr2 = new double[inputArr.length][inputArr[0].length];
	  for(int ind = 0; ind < inputArr.length; ind++){
		  for(int ind2 = 0; ind2 < inputArr[0].length; ind2++){
			  inputArr2[ind][ind2]=inputArr[ind][ind2];
		  }
	  }
	  return inputArr2;
  }
  
  public char[][] duplicateCA2(char[][] inputArr){
	  char[][] inputArr2 = new char[inputArr.length][inputArr[0].length];
	  for(int ind = 0; ind < inputArr.length; ind++){
		  for(int ind2 = 0; ind2 < inputArr[0].length; ind2++){
			  inputArr2[ind][ind2]=inputArr[ind][ind2];
		  }
	  }
	  return inputArr2;
  }
  
  public String[][] duplicateSA2(String[][] inputArr){
	  String[] []inputArr2 = new String[inputArr.length][inputArr[0].length];
	  for(int ind = 0; ind < inputArr.length; ind++){
		  for(int ind2 = 0; ind2 < inputArr[0].length; ind2++){
			  inputArr2[ind][ind2]=inputArr[ind][ind2];
		  }
	  }
	  return inputArr2;
  }
  
  
  //Duplicate - 2-D Lists
  
  public ArrayList<int[]> duplicateIL2(ArrayList<int[]> inputArr){
	  ArrayList<int[]> inputArr2 = new ArrayList<int[]>();
	  for(int ind = 0; ind < inputArr.size(); ind++){
		  inputArr2.add(inputArr.get(ind));
	  }
	  return inputArr2;
  }
  
  public ArrayList<double[]> duplicateDL2(ArrayList<double[]> inputArr){
	  ArrayList<double[]> inputArr2 = new ArrayList<double[]>();
	  for(int ind = 0; ind < inputArr.size(); ind++){
		  inputArr2.add(inputArr.get(ind));
	  }
	  return inputArr2;
  }
  
  public ArrayList<String[]> duplicateSL2(ArrayList<String[]> inputArr){
	  ArrayList<String[]> inputArr2 = new ArrayList<String[]>();
	  for(int ind = 0; ind < inputArr.size(); ind++){
		  inputArr2.add(inputArr.get(ind));
	  }
	  return inputArr2;
  }
  
  public ArrayList<boolean[]> duplicateBL2(ArrayList<boolean[]> inputArr){
	  ArrayList<boolean[]> inputArr2 = new ArrayList<boolean[]>();
	  for(int ind = 0; ind < inputArr.size(); ind++){
		  inputArr2.add(inputArr.get(ind));
	  }
	  return inputArr2;
  }
  
  public ArrayList<char[]> duplicateCL2(ArrayList<char[]> inputArr){
	  ArrayList<char[]> inputArr2 = new ArrayList<char[]>();
	  for(int ind = 0; ind < inputArr.size(); ind++){
		  inputArr2.add(inputArr.get(ind));
	  }
	  return inputArr2;
  }
  
  
  
  //QuickSort - 1-D Arrays (With help from Geeksforgeeks.org)
  
  public int[] sortIA(int[] inputArr) { 
      if (inputArr == null || inputArr.length == 0) {
          return null;
      }
      int[] inputArr2 = duplicateIA(inputArr);
      quickSortIA(0, inputArr2.length - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortIA(int lowerIndex, int higherIndex, int[] inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      int pivot = inputArr[lowerIndex+(higherIndex-lowerIndex)/2];
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
          while (inputArr[i] < pivot) {
              i++;
          }
          while (inputArr[j] > pivot) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersIA(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortIA(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortIA(i, higherIndex, inputArr);
  }

  public void exchangeNumbersIA(int i, int j, int[] inputArr) {
      int temp = inputArr[i];
      inputArr[i] = inputArr[j];
      inputArr[j] = temp;
  }
  
  public String[] sortSA(String[] inputArr) { 
      if (inputArr == null || inputArr.length == 0) {
          return null;
      }
      String[] inputArr2 = duplicateSA(inputArr);
      quickSortSA(0, inputArr2.length - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortSA(int lowerIndex, int higherIndex, String[] inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      String pivot = inputArr[lowerIndex+(higherIndex-lowerIndex)/2];
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
    	  //inputArr[i] < pivot
          while (inputArr[i].compareTo(pivot) < 0) {
              i++;
          }
          while (inputArr[j].compareTo(pivot) > 0) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersSA(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortSA(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortSA(i, higherIndex, inputArr);
  }

  public void exchangeNumbersSA(int i, int j, String[] inputArr) {
      String temp = inputArr[i];
      inputArr[i] = inputArr[j];
      inputArr[j] = temp;
  }
  
  public double[] sortDA(double[] inputArr) { 
      if (inputArr == null || inputArr.length == 0) {
          return null;
      }
      double[] inputArr2 = duplicateDA(inputArr);
      quickSortDA(0, inputArr2.length - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortDA(int lowerIndex, int higherIndex, double[] inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      double pivot = inputArr[lowerIndex+(higherIndex-lowerIndex)/2];
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
          while (inputArr[i] < pivot) {
              i++;
          }
          while (inputArr[j] > pivot) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersDA(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortDA(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortDA(i, higherIndex, inputArr);
  }

  public void exchangeNumbersDA(int i, int j, double[] inputArr) {
	  double temp = inputArr[i];
      inputArr[i] = inputArr[j];
      inputArr[j] = temp;
  }
  
  public char[] sortCA(char[] inputArr) { 
      if (inputArr == null || inputArr.length == 0) {
          return null;
      }
      char[] inputArr2 = duplicateCA(inputArr);
      quickSortCA(0, inputArr2.length - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortCA(int lowerIndex, int higherIndex, char[] inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      char pivot = inputArr[lowerIndex+(higherIndex-lowerIndex)/2];
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
          while (inputArr[i] < pivot) {
              i++;
          }
          while (inputArr[j] > pivot) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersCA(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortCA(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortCA(i, higherIndex, inputArr);
  }

  public void exchangeNumbersCA(int i, int j, char[] inputArr) {
	  char temp = inputArr[i];
      inputArr[i] = inputArr[j];
      inputArr[j] = temp;
  }
  
  
  //QuickSort - 1-D Lists
  
  public ArrayList<Integer> sortIL(ArrayList<Integer> inputArr) {
      if (inputArr == null || inputArr.size() == 0) {
          return null;
      }
      ArrayList<Integer> inputArr2 = duplicateIL(inputArr);
      quickSortIL(0, inputArr2.size() - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortIL(int lowerIndex, int higherIndex, ArrayList<Integer> inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      int pivot = inputArr.get(lowerIndex+(higherIndex-lowerIndex)/2);
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
          while (inputArr.get(i) < pivot) {
              i++;
          }
          while (inputArr.get(j) > pivot) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersIL(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortIL(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortIL(i, higherIndex, inputArr);
  }

  public void exchangeNumbersIL(int i, int j, ArrayList<Integer> inputArr) {
      int temp = inputArr.get(i);
      inputArr.set(i, inputArr.get(j));
      inputArr.set(j, temp);
  }
  
  public ArrayList<Double> sortDL(ArrayList<Double> inputArr) { 
      if (inputArr == null || inputArr.size() == 0) {
          return null;
      }
      ArrayList<Double> inputArr2 = duplicateDL(inputArr);
      quickSortDL(0, inputArr2.size() - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortDL(int lowerIndex, int higherIndex, ArrayList<Double> inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      double pivot = inputArr.get(lowerIndex+(higherIndex-lowerIndex)/2);
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
          while (inputArr.get(i) < pivot) {
              i++;
          }
          while (inputArr.get(j) > pivot) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersDL(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortDL(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortDL(i, higherIndex, inputArr);
  }

  public void exchangeNumbersDL(int i, int j, ArrayList<Double> inputArr) {
	  double temp = inputArr.get(i);
      inputArr.set(i, inputArr.get(j));
      inputArr.set(j, temp);
  }
  
  public ArrayList<String> sortSL(ArrayList<String> inputArr) { 
      if (inputArr == null || inputArr.size() == 0) {
          return null;
      }
      ArrayList<String> inputArr2 = duplicateSL(inputArr);
      quickSortSL(0, inputArr2.size() - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortSL(int lowerIndex, int higherIndex, ArrayList<String> inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      String pivot = inputArr.get(lowerIndex+(higherIndex-lowerIndex)/2);
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
    	  //inputArr[i] < pivot
          while (inputArr.get(i).compareTo(pivot) < 0) {
              i++;
          }
          while (inputArr.get(j).compareTo(pivot) > 0) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersSL(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortSL(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortSL(i, higherIndex, inputArr);
  }

  public void exchangeNumbersSL(int i, int j, ArrayList<String> inputArr) {
      String temp = inputArr.get(i);
      inputArr.set(i, inputArr.get(j));
      inputArr.set(j, temp);
  }
 
  
  //GreaterThan - 1-D Arrays
  
  public boolean greaterThanIA(int[] arr1, int[] arr2){
	  if(arr1.length > arr2.length){
		  for(int ind = 0; ind < arr2.length; ind++){
			  if(arr1[ind]!=arr2[ind]){
				  if(arr1[ind]>arr2[ind]){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
		  return true;
	  }else{
		  for(int ind = 0; ind < arr1.length; ind++){
			  if(arr1[ind]!=arr2[ind]){
				  if(arr1[ind]>arr2[ind]){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
		  return false;
	  }
  }
  
  public boolean greaterThanDA(double[] arr1, double[] arr2){
	  if(arr1.length > arr2.length){
		  for(int ind = 0; ind < arr2.length; ind++){
			  //arr1[ind]!=arr2[ind]
			  if(Math.abs(arr1[ind]-arr2[ind])>=marginOfError){
				  if(arr1[ind]>arr2[ind]){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
		  return true;
	  }else{
		  for(int ind = 0; ind < arr1.length; ind++){
			  if(Math.abs(arr1[ind]-arr2[ind])>=marginOfError){
				  if(arr1[ind]>arr2[ind]){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
		  return false;
	  }
  }
  
  public boolean greaterThanSA(String[] arr1, String[] arr2){
	  if(arr1.length > arr2.length){
		  for(int ind = 0; ind < arr2.length; ind++){
			  //arr1[ind]!=arr2[ind]
			  if(arr1[ind].compareTo(arr2[ind])!=0){
				  if(arr1[ind].compareTo(arr2[ind])>0){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
	  }else{
		  for(int ind = 0; ind < arr1.length; ind++){
			  if(arr1[ind].compareTo(arr2[ind])!=0){
				  if(arr1[ind].compareTo(arr2[ind])>0){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
	  }
	  return false;
  }
  
  public boolean greaterThanCA(char[] arr1, char[] arr2){
	  if(arr1.length > arr2.length){
		  for(int ind = 0; ind < arr2.length; ind++){
			  if(arr1[ind]!=arr2[ind]){
				  if(arr1[ind]>arr2[ind]){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
	  }else{
		  for(int ind = 0; ind < arr1.length; ind++){
			  if(arr1[ind]!=arr2[ind]){
				  if(arr1[ind]>arr2[ind]){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
	  }
	  return false;
  }
  
  
  //EqualsThan - 1-D Arrays
  
  public boolean equalsThanIA(int[] arr1, int[] arr2){
	  if(arr1.length==arr2.length){
		  for(int ind = 0; ind < arr2.length; ind++){
			  if(arr1[ind]!=arr2[ind]){
				  return false;
			  }
		  }
		  return true;
	  }
	  return false;
  }
  
  public boolean equalsThanDA(double[] arr1, double[] arr2){
	  if(arr1.length==arr2.length){
		  for(int ind = 0; ind < arr2.length; ind++){
			  if(Math.abs(arr1[ind]-arr2[ind])>=marginOfError){
				  return false;
			  }
		  }
		  return true;
	  }
	  return false;
  }
  
  public boolean equalsThanSA(String[] arr1, String[] arr2){
	  if(arr1.length==arr2.length){
		  for(int ind = 0; ind < arr2.length; ind++){
			  if(!arr1[ind].equals(arr2[ind])){
				  return false;
			  }
		  }
		  return true;
	  }
	  return false;
  }

  public boolean equalsThanCA(char[] arr1, char[] arr2){
	  if(arr1.length==arr2.length){
		  for(int ind = 0; ind < arr2.length; ind++){
			  if(arr1[ind]!=arr2[ind]){
				  return false;
			  }
		  }
		  return true;
	  }
	  return false;
  }
  
  
  //LessThan - 1-D Arrays
  
  public boolean lessThanIA(int[] arr1, int[] arr2){
	 if(!greaterThanIA(arr1, arr2) && !equalsThanIA(arr1, arr2)){
		 return true;
	 }
	 return false;
  }
  
  public boolean lessThanDA(double[] arr1, double[] arr2){
		 if(!greaterThanDA(arr1, arr2) && !equalsThanDA(arr1, arr2)){
			 return true;
		 }
		 return false;
  }
  
  public boolean lessThanSA(String[] arr1, String[] arr2){
		 if(!greaterThanSA(arr1, arr2) && !equalsThanSA(arr1, arr2)){
			 return true;
		 }
		 return false;
  }
  
  public boolean lessThanCA(char[] arr1, char[] arr2){
		 if(!greaterThanCA(arr1, arr2) && !equalsThanCA(arr1, arr2)){
			 return true;
		 }
		 return false;
}
  
  
  //GreaterThan - 1-D Lists
  
  public boolean greaterThanIL(ArrayList<Integer> arr1, ArrayList<Integer> arr2){
	  if(arr1.size() > arr2.size()){
		  for(int ind = 0; ind < arr2.size(); ind++){
			  if(arr1.get(ind)!=arr2.get(ind)){
				  if(arr1.get(ind)>arr2.get(ind)){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
		  return true;
	  }else{
		  for(int ind = 0; ind < arr1.size(); ind++){
			  if(arr1.get(ind)!=arr2.get(ind)){
				  if(arr1.get(ind)>arr2.get(ind)){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
		  return false;
	  }
  }
  
  public boolean greaterThanDL(ArrayList<Double> arr1, ArrayList<Double> arr2){
	  if(arr1.size() > arr2.size()){
		  for(int ind = 0; ind < arr2.size(); ind++){
			  if(Math.abs(arr1.get(ind)-arr2.get(ind))>=marginOfError){
				  if(arr1.get(ind)>arr2.get(ind)){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
		  return true;
	  }else{
		  for(int ind = 0; ind < arr1.size(); ind++){
			  if(Math.abs(arr1.get(ind)-arr2.get(ind))>=marginOfError){
				  if(arr1.get(ind)>arr2.get(ind)){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
		  return false;
	  }
  }
  
  public boolean greaterThanSL(ArrayList<String> arr1, ArrayList<String> arr2){
	  if(arr1.size() > arr2.size()){
		  for(int ind = 0; ind < arr2.size(); ind++){
			  if(!arr1.get(ind).equals(arr2.get(ind))){
				  if(arr1.get(ind).compareTo(arr2.get(ind))>0){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
		  return true;
	  }else{
		  for(int ind = 0; ind < arr1.size(); ind++){
			  if(!arr1.get(ind).equals(arr2.get(ind))){
				  if(arr1.get(ind).compareTo(arr2.get(ind))>0){
					  return true;
				  }else{
					  return false;
				  }
			  }
		  }
		  return false;
	  }
  }

  
  //EqualThan - 1-D Lists
  
  public boolean equalsThanIL(ArrayList<Integer> arr1, ArrayList<Integer> arr2){
	  if(arr1.size()==arr2.size()){
		  for(int ind = 0; ind < arr2.size(); ind++){
			  if(arr1.get(ind)!=arr2.get(ind)){
				  return false;
			  }
		  }
		  return true;
	  }
	  return false;
  }
  
  public boolean equalsThanDL(ArrayList<Double> arr1, ArrayList<Double> arr2){
	  if(arr1.size()==arr2.size()){
		  for(int ind = 0; ind < arr2.size(); ind++){
			  if(Math.abs(arr1.get(ind)-arr2.get(ind))>=marginOfError){
				  return false;
			  }
		  }
		  return true;
	  }
	  return false;
  }
  
  public boolean equalsThanSL(ArrayList<String> arr1, ArrayList<String> arr2){
	  if(arr1.size()==arr2.size()){
		  for(int ind = 0; ind < arr2.size(); ind++){
			  if(!arr1.get(ind).equals(arr2.get(ind))){
				  return false;
			  }
		  }
		  return true;
	  }
	  return false;
  }


  //LessThan - 1-D Lists
  
  public boolean lessThanIL(ArrayList<Integer> arr1, ArrayList<Integer> arr2){
		 if(!greaterThanIL(arr1, arr2) && !equalsThanIL(arr1, arr2)){
			 return true;
		 }
		 return false;
  }
	  
  public boolean lessThanDL(ArrayList<Double> arr1, ArrayList<Double> arr2){
	if(!greaterThanDL(arr1, arr2) && !equalsThanDL(arr1, arr2)){
		return true;
	}
	return false;
  }
	  
  public boolean lessThanSL(ArrayList<String> arr1, ArrayList<String> arr2){
	  if(!greaterThanSL(arr1, arr2) && !equalsThanSL(arr1, arr2)){
		  return true;
	  }
	return false;
  }
  
  
  //QuickSort - 2-D Arrays
  
  public int[][] sortIA2(int[][] inputArr) { 
      if (inputArr == null || inputArr.length == 0) {
          return null;
      }
      int[][] inputArr2 = duplicateIA2(inputArr);
      quickSortIA2(0, inputArr2.length - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortIA2(int lowerIndex, int higherIndex, int[][] inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      int[] pivot = inputArr[lowerIndex+(higherIndex-lowerIndex)/2];
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
    	  //inputArr[i] < pivot
          while (lessThanIA(inputArr[i], pivot)) {
              i++;
          }
          while (greaterThanIA(inputArr[j], pivot)) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersIA2(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortIA2(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortIA2(i, higherIndex, inputArr);
  }

  public void exchangeNumbersIA2(int i, int j, int[][] inputArr) {
      int[] temp = inputArr[i];
      inputArr[i] = inputArr[j];
      inputArr[j] = temp;
  }
  
  public double[][] sortDA2(double[][] inputArr) { 
      if (inputArr == null || inputArr.length == 0) {
          return null;
      }
      double[][] inputArr2 = duplicateDA2(inputArr);
      quickSortDA2(0, inputArr2.length - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortDA2(int lowerIndex, int higherIndex, double[][] inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      double[] pivot = inputArr[lowerIndex+(higherIndex-lowerIndex)/2];
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
    	  //inputArr[i] < pivot
          while (lessThanDA(inputArr[i], pivot)) {
              i++;
          }
          while (greaterThanDA(inputArr[j], pivot)) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersDA2(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortDA2(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortDA2(i, higherIndex, inputArr);
  }

  public void exchangeNumbersDA2(int i, int j, double[][] inputArr) {
	  double[] temp = inputArr[i];
      inputArr[i] = inputArr[j];
      inputArr[j] = temp;
  }
  
  public String[][] sortSA2(String[][] inputArr) { 
      if (inputArr == null || inputArr.length == 0) {
          return null;
      }
      String[][] inputArr2 = duplicateSA2(inputArr);
      quickSortSA2(0, inputArr2.length - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortSA2(int lowerIndex, int higherIndex, String[][] inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      String[] pivot = inputArr[lowerIndex+(higherIndex-lowerIndex)/2];
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
    	  //inputArr[i] < pivot
          while (lessThanSA(inputArr[i], pivot)) {
              i++;
          }
          while (greaterThanSA(inputArr[j], pivot)) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersSA2(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortSA2(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortSA2(i, higherIndex, inputArr);
  }

  public void exchangeNumbersSA2(int i, int j, String[][] inputArr) {
	  String[] temp = inputArr[i];
      inputArr[i] = inputArr[j];
      inputArr[j] = temp;
  }
  
  public char[][] sortCA2(char[][] inputArr) {
      if (inputArr == null || inputArr.length == 0) {
          return null;
      }
      char[][] inputArr2 = duplicateCA2(inputArr);
      quickSortCA2(0, inputArr2.length - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortCA2(int lowerIndex, int higherIndex, char[][] inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      char[] pivot = inputArr[lowerIndex+(higherIndex-lowerIndex)/2];
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
    	  //inputArr[i] < pivot
          while (lessThanCA(inputArr[i], pivot)) {
              i++;
          }
          while (greaterThanCA(inputArr[j], pivot)) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersCA2(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortCA2(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortCA2(i, higherIndex, inputArr);
  }

  public void exchangeNumbersCA2(int i, int j, char[][] inputArr) {
	  char[] temp = inputArr[i];
      inputArr[i] = inputArr[j];
      inputArr[j] = temp;
  }
  
  
  //QuickSort - 2-D Lists
  
  public ArrayList<int[]> sortIL2(ArrayList<int[]> inputArr) { 
      if (inputArr == null || inputArr.size() == 0) {
          return null;
      }
      ArrayList<int[]> inputArr2 = duplicateIL2(inputArr);
      quickSortIL2(0, inputArr2.size() - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortIL2(int lowerIndex, int higherIndex, ArrayList<int[]> inputArr) {
      
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      int[] pivot = inputArr.get(lowerIndex+(higherIndex-lowerIndex)/2);
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers. inputArr.get(i) < pivot
           */
          while (lessThanIA(inputArr.get(i), pivot)) {
              i++;
          }
          //inputArr.get(j) > pivot
          while (greaterThanIA(inputArr.get(j), pivot)) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersIL2(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortIL2(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortIL2(i, higherIndex, inputArr);
  }

  public void exchangeNumbersIL2(int i, int j, ArrayList<int[]> inputArr) {
      int[] temp = inputArr.get(i);
      inputArr.set(i, inputArr.get(j));
      inputArr.set(j, temp);
  }
  
  public ArrayList<double[]> sortDL2(ArrayList<double[]> inputArr) { 
      if (inputArr == null || inputArr.size() == 0) {
          return null;
      }
      ArrayList<double[]> inputArr2 = duplicateDL2(inputArr);
      quickSortDL2(0, inputArr2.size() - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortDL2(int lowerIndex, int higherIndex, ArrayList<double[]> inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      double[] pivot = inputArr.get(lowerIndex+(higherIndex-lowerIndex)/2);
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
    	  //inputArr[i] < pivot
          while (lessThanDA(inputArr.get(i), pivot)) {
              i++;
          }
          while (greaterThanDA(inputArr.get(j), pivot)) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersDL2(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortDL2(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortDL2(i, higherIndex, inputArr);
  }

  public void exchangeNumbersDL2(int i, int j, ArrayList<double[]> inputArr) {
	  double[] temp = inputArr.get(i);
      inputArr.set(i, inputArr.get(j));
      inputArr.set(j, temp);
  }
  
  public ArrayList<String[]> sortSL2(ArrayList<String[]> inputArr) { 
      if (inputArr == null || inputArr.size() == 0) {
          return null;
      }
      ArrayList<String[]> inputArr2 = duplicateSL2(inputArr);
      quickSortSL2(0, inputArr2.size() - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortSL2(int lowerIndex, int higherIndex, ArrayList<String[]> inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      String[] pivot = inputArr.get(lowerIndex+(higherIndex-lowerIndex)/2);
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
    	  //inputArr[i] < pivot
          while (lessThanSA(inputArr.get(i), pivot)) {
              i++;
          }
          while (greaterThanSA(inputArr.get(j), pivot)) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersSL2(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortSL2(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortSL2(i, higherIndex, inputArr);
  }

  public void exchangeNumbersSL2(int i, int j, ArrayList<String[]> inputArr) {
      String[] temp = inputArr.get(i);
      inputArr.set(i, inputArr.get(j));
      inputArr.set(j, temp);
  }
  
  public ArrayList<char[]> sortCL2(ArrayList<char[]> inputArr) { 
      if (inputArr == null || inputArr.size() == 0) {
          return null;
      }
      ArrayList<char[]> inputArr2 = duplicateCL2(inputArr);
      quickSortCL2(0, inputArr2.size() - 1, inputArr2);
      return inputArr2;
  }

  public void quickSortCL2(int lowerIndex, int higherIndex, ArrayList<char[]> inputArr) {
       
      int i = lowerIndex;
      int j = higherIndex;
      // calculate pivot number, I am taking pivot as middle index number
      char[] pivot = inputArr.get(lowerIndex+(higherIndex-lowerIndex)/2);
      // Divide into two arrays
      while (i <= j) {
          /**
           * In each iteration, we will identify a number from left side which 
           * is greater then the pivot value, and also we will identify a number 
           * from right side which is less then the pivot value. Once the search 
           * is done, then we exchange both numbers.
           */
    	  //inputArr[i] < pivot
          while (lessThanCA(inputArr.get(i), pivot)) {
              i++;
          }
          while (greaterThanCA(inputArr.get(j), pivot)) {
              j--;
          }
          if (i <= j) {
              exchangeNumbersCL2(i, j, inputArr);
              //move index to next position on both sides
              i++;
              j--;
          }
      }
      // call quickSort() method recursively
      if (lowerIndex < j)
          quickSortCL2(lowerIndex, j, inputArr);
      if (i < higherIndex)
          quickSortCL2(i, higherIndex, inputArr);
  }

  public void exchangeNumbersCL2(int i, int j, ArrayList<char[]> inputArr) {
	  char[] temp = inputArr.get(i);
      inputArr.set(i, inputArr.get(j));
      inputArr.set(j, temp);
  }
  
  
  
}