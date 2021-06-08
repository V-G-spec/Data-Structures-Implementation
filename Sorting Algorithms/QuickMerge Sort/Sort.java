import java.util.*;
public class Sort {
    
    public static void exchange(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    /**
     * Implementation of the sorting algorithm, an amalgation of quicksort and mergesort:
     * @param arr - array to be sorted
     * @param low - the start limit to be sorted
     * @param high - the end limit to be sorted
     */
    public static void merge(int arr[], int l, int m, int r) {
        if (l>=r || l> m) return;   //Change l>=m to l>m
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    
    static void qmSort(int[] arr, int low, int high) {
        if (low>=high) return;
        // write your code here
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = arr[low];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (i<=high && arr[i] <= pivot) {  //Change: if -> while and added high clause
                i++;
            } while (arr[j]>pivot) j--; //Change Added this line
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            if (i<j) { //Change this line
                exchange(arr, i, j);
              //j--;  //Change: Comment this out
            }
        }
        /*
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            if (arr[i] <= pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            else {
                exchange(arr, i, j);
                j--;
            }
        }
        */
        
        
        
        exchange(arr, low, j);   //Change exchange(arr, 0, j) to exchange(arr, low, j)
        int idx1=0;
        int idx2=0;
        // Recursion
        if (j-low>=high-i){
            int mid = (j+low)/2;
            if (mid>=0) idx1 = mid;
            if (j>=0) idx2 = j;
            printIdx(idx1, idx2);
        } else {
            int mid = (high+i)/2;
            if (j>=0) idx1 = j;
            if (mid>=0) idx2 = mid;
            printIdx(idx1, idx2);
        }
        qmSort(arr, low, idx1);
        qmSort(arr, idx1+1, idx2);
        qmSort(arr, idx2+1, high);
        if (j-low>=high-i){
            merge(arr, low, idx1, idx2);
        } else {
            merge(arr, idx1+1, idx2, high);
        }
        // if (low < j)
        //     quicksort(low, j);
        // if (i < high)
        //     quicksort(i, high);
    }

    /**
     * This is a helper method to print out the indexes in the desired format.
     * @param idx1 - First index
     * @param idx2 - second index
     */
    static void printIdx(int idx1, int idx2) {
        System.out.println(idx1+","+idx2);
    }

    /**
     * DO NOT CHANGE THIS FUNCTION - The main function to take input and call your method.
     * @param args
     */
    public static void main(String args[]) {
        // Scanner scanner = new Scanner(System.in);
        // int numOfBlocks = scanner.nextInt();
        // int[] nums = new int[numOfBlocks];
        // // for (int i = 0; i < numOfBlocks; i++) {
        // //     nums[i] = scanner.nextInt();
        // // }
        // Random rd = new Random(); 
        // for (int i = 0; i < nums.length; i++) {
        //          nums[i] = rd.nextInt(1001); //Create array with values between 0 and 100, inclusive
        //     }
        // scanner.close();
        // System.out.println();
        // for (int i = 0; i < nums.length; i++) {
        //     System.out.print(nums[i] + " ");
        // }
        // System.out.println();
        // qmSort(nums, 0, numOfBlocks-1);
        // for (int i = 0; i < nums.length; i++) {
        //     System.out.print(nums[i] + " ");
        // }

        Scanner scanner = new Scanner(System.in);
        int numOfBlocks = scanner.nextInt();
        int[] nums = new int[numOfBlocks];
        for (int i = 0; i < numOfBlocks; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();
        qmSort(nums, 0, numOfBlocks-1);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
