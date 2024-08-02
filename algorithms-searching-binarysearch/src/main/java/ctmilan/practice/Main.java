package ctmilan.practice;
import java.util.Arrays;

public class Main {
    // ====== Summary ======
    // --Time Complexity--
    // Best: O(1)
    // Avg: O(logn)
    // Worst: O(logn)
    // --Space Complexity--
    // Recursive Implementation: O(logn) due to recursive stack calls
    // Iterative Implementation: O(1)
    // -- Pros --
    // * Good for searching large datasets
    // -- Cons --
    // * Given array has to be sorted to be efficient
    // * Elements have to be comparable
    public static void main(String[] args) {

        // The given *sorted* array
        int[] array = {4,5,8,17,20,21,25,36};

        // Index position of the end of the array
        int end = array.length-1; 
        // Index position of the start of the array
        int start = 0;
        // Target element to be searched for
        int target = 21;
        // Index position of the target element (equals -1 if target is not found in the array)
        int index = binarySearch_iterative(array,start,end,target);

        System.out.println("\nThe array is: "+Arrays.toString(array));

        if(index==-1) // Check if target was found
        {
            System.out.println("\nThe target element: ("+target+") does not exist in the array");
        }
        else // Else, print the target and it's index location in the array
        {
            System.out.println("\nThe target element: "+target+" is in the index position: "+index+"\n");
        }
    }

    // Recursive implementation of binary search (slightly simpler to implement)
    public static int binarySearch_recursive(int[] arr, int start, int end, int target)
    {
        // First, check if the end is greater than the start
        if(end>=start)
        {
            // Then find the middle element of the given range
            int middle = (end+start)/2;

            if(arr[middle]==target) // Check if the element is the target
            {
                return middle; // Return that index position if it is
            }
            else if(arr[middle]>target) // Check if the element is larger than the target
            {
                return binarySearch_recursive(arr,start,middle-1,target); // If it is then search the lower half of the rest of the array
            }
            else if(arr[middle]<target) // If the element is smaller than the target...
            {
                return binarySearch_recursive(arr,middle+1,end,target); // Then search the upper half of the array
            }
        }
        return -1; // If the element cannot be found, return -1
    }

    // Iterative implementation of binary search (slightly less space complexity)
    public static int binarySearch_iterative(int[] arr, int start, int end, int target)
    {
        // Loop while the end is greater than the start
        while(end>=start)
        {
            // Find the middle element of the given range
            int middle = (end+start)/2;

            if(arr[middle]==target) // Check if the element is the target
            {
                return middle; // Return that index position if it is
            }
            else if(arr[middle]>target) // If the element is larger than the target...
            {
                end=middle-1;; // Move the search range down just below the middle
            }
            else if(arr[middle]<target) // If the element is smaller than the target...
            {
                start=middle+1; // Move the search range up past the middle
            }
        }
        return -1; // Since the element cannot be found, return -1
    }
}