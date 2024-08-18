import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
/* Name: Valerie Dawson
 * Overview: A sorting program that allows users to select from various sorting algorithm to sort different-sized arrays 
 * of randomly generated integers. The program presents a menu to the user, accepts their choice of sorting algorithm 
 * and executes the selected algorithm on arrays of sizes 20, 100, 500 and 1000. The program measures the execution 
 * time for each sorting operation and displays the sorted arrays along with corresponding time in nanoseconds. The code
 * includes helper methods for array generation, printing, and partitioning and follows a user-controlled loop until 
 * the user decides to exit the program.
 * Input: The user is prompted to choose a sorting algorithm by enetering the corresponding number. 
 * The user can input 0 to exit the program
 * Output: The program displays a menu with sorting algorithm options. For each chosen sorting algorithm 
 * and array size, the program displays the sorted array and the time taken to sort the array in nanoseconds 
 * Variables: sizes , running, choice, array, startTime, endTime
 */

	public class SortingProgram {
		// Generate an array of random integers
	    private static int[] generateArray(int size) {
	        Random random = new Random();
	        int[] array = new int[size];
	        for (int i = 0; i < size; i++) {
	            array[i] = random.nextInt(1000);
	        }
	        return array;
	    }
	    // print the elements of an array 
	    private static void printArray(int[] array) {
	        System.out.println(Arrays.toString(array));
	    }
	    // Perform the Selection Sort algorithm 
	    // Time Complexity: O(n^2)
	    private static void selectionSort(int[] array) {
	        int n = array.length;
	        for (int i = 0; i < n - 1; i++) {
	            int minIndex = i;
	            for (int j = i + 1; j < n; j++) {
	                if (array[j] < array[minIndex]) {
	                    minIndex = j;
	                }
	            }
	            int temp = array[minIndex];
	            array[minIndex] = array[i];
	            array[i] = temp;
	        }
	    }
	    // Perform the Insertion Sort algorithm
	    // Time Complexity: O(n^2)
	    private static void insertionSort(int[] array) {
	        int n = array.length;
	        for (int i = 1; i < n; i++) {
	            int key = array[i];
	            int j = i - 1;

	            while (j >= 0 && array[j] > key) {
	                array[j + 1] = array[j];
	                j--;
	            }
	            array[j + 1] = key;
	        }
	    }
	    // Perform the Bubble Sort algorithm
	    // Time Complexity : O(n^2)
	    private static void bubbleSort(int[] array) {
	        int n = array.length;
	        for (int i = 0; i < n - 1; i++) {
	            for (int j = 0; j < n - i - 1; j++) {
	                if (array[j] > array[j + 1]) {
	                    int temp = array[j];
	                    array[j] = array[j + 1];
	                    array[j + 1] = temp;
	                }
	            }
	        }
	    }
	    	// Perform the Quick Sort algorithm
	    	// Time Complexity : O(n*logn) Average case, O(n^2) Worse case
	    private static void quickSort(int[] array, int low, int high) {
	        if (low < high) {
	            int partitionIndex = partition(array, low, high);

	            quickSort(array, low, partitionIndex - 1);
	            quickSort(array, partitionIndex + 1, high);
	        }
	    }
	     // Helper method for Quick Sort to perform partitioning
	    private static int partition(int[] array, int low, int high) {
	        int pivot = array[high];
	        int i = low - 1;

	        for (int j = low; j < high; j++) {
	            if (array[j] < pivot) {
	                i++;
	                int temp = array[i];
	                array[i] = array[j];
	                array[j] = temp;
	            }
	        }

	        int temp = array[i + 1];
	        array[i + 1] = array[high];
	        array[high] = temp;

	        return i + 1;
	    }
	     // Perform the Radix Sort algorithm 
	    // Time Complexity : O(d*(n+b)), where d is rhe maximum number of digits , n is the number of elements, and
	    // b is the base(10)
	    private static void radixSort(int[] array) {
	        int n = array.length;
	        int max = Arrays.stream(array).max().getAsInt();

	        for (int exp = 1; max / exp > 0; exp *= 10) {
	            countingSort(array, n, exp);
	        }
	    }
	    	 // Helper method for the Radix Sort to perform counting sort
	    private static void countingSort(int[] array, int n, int exp) {
	        int[] output = new int[n];
	        int[] count = new int[10];
	        Arrays.fill(count, 0);

	        for (int i = 0; i < n; i++) {
	            count[(array[i] / exp) % 10]++;
	        }

	        for (int i = 1; i < 10; i++) {
	            count[i] += count[i - 1];
	        }

	        for (int i = n - 1; i >= 0; i--) {
	            output[count[(array[i] / exp) % 10] - 1] = array[i];
	            count[(array[i] / exp) % 10]--;
	        }

	        System.arraycopy(output, 0, array, 0, n);
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        int[] sizes = {20, 100, 500, 1000};

	        boolean running = true;
	        while (running) {
	            System.out.println("***************************************");
	            System.out.println("Welcome to the Sorting Program!");
	            System.out.println("Please choose a sorting algorithm:");
	            System.out.println("1. Selection Sort");
	            System.out.println("2. Insertion Sort");
	            System.out.println("3. Bubble Sort");
	            System.out.println("4. Quick Sort");
	            System.out.println("5. Radix Sort");
	            System.out.println("0. Exit");

	            int choice = scanner.nextInt();

	           
	            switch (choice) {
	                case 0:
	                    running = false;
	                    System.out.println("Thank you for using the Sorting Program. Goodbye!");
	                    break;
	                case 1:
	                    System.out.println("Selection Sort:");
	                    for (int size : sizes) {
	                        int[] array = generateArray(size);

	                        long startTime = System.nanoTime();
	                        selectionSort(array);
	                        long endTime = System.nanoTime();

	                        System.out.println("Array size: " + size);
	                        System.out.println("Sorted array: ");
	                        printArray(array);
	                        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
	                        System.out.println();
	                    }
	                    break;
	                case 2:
	                    System.out.println("Insertion Sort:");
	                    for (int size : sizes) {
	                        int[] array = generateArray(size);

	                        long startTime = System.nanoTime();
	                        insertionSort(array);
	                        long endTime = System.nanoTime();

	                        System.out.println("Array size: " + size);
	                        System.out.println("Sorted array: ");
	                        printArray(array);
	                        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
	                        System.out.println();
	                    }
	                    break;
	                case 3:
	                    System.out.println("Bubble Sort:");
	                    for (int size : sizes) {
	                        int[] array = generateArray(size);

	                        long startTime = System.nanoTime();
	                        bubbleSort(array);
	                        long endTime = System.nanoTime();

	                        System.out.println("Array size: " + size);
	                        System.out.println("Sorted array: ");
	                        printArray(array);
	                        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
	                        System.out.println();
	                    }
	                    break;
	                case 4:
	                    System.out.println("Quick Sort:");
	                    for (int size : sizes) {
	                        int[] array = generateArray(size);

	                        long startTime = System.nanoTime();
	                        quickSort(array, 0, array.length - 1);
	                        long endTime = System.nanoTime();

	                        System.out.println("Array size: " + size);
	                        System.out.println("Sorted array: ");
	                        printArray(array);
	                        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
	                        System.out.println();
	                    }
	                    break;
	                case 5:
	                    System.out.println("Radix Sort:");
	                    for (int size : sizes) {
	                        int[] array = generateArray(size);

	                        long startTime = System.nanoTime();
	                        radixSort(array);
	                        long endTime = System.nanoTime();

	                        System.out.println("Array size: " + size);
	                        System.out.println("Sorted array: ");
	                        printArray(array);
	                        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
	                        System.out.println();
	                    }
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	                    break;
	            }
	        }
	        scanner.close();
	    }
	}
			
				
			
			
