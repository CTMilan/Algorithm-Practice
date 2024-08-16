package ctmilan.practice;
import java.util.Arrays;
import java.util.Random;

public class Main {

    // ====== Summary ======
    //
    // --Time Complexity--
    // Overall: O(nlogn)
    //
    // --Space Complexity--
    // Recursive Implementation: O(logn)
    // Iterative Implementation: O(1)
    //
    // -- Pros --
    // * Fairly Simple
    // * Does well with larger datasets
    // * Low memory cost (if implemented through iteration)
    //
    // -- Cons --
    // * Produces an unstable sort
    // * Struggles with more complicated data

    public static void main(String[] args) {
        Random random = new Random();

        // Initialize an integer array of random size (min=2, max=20)
        int[] array = new int[2+random.nextInt(19)];
        // Fill the array with integer values (min=0, max=60)
        for(int x=0; x<array.length; x++)
        {
            array[x]=random.nextInt(60);
        }

        System.out.println("The randomly generated unsorted array of size "+array.length+" is:");
        System.out.println(Arrays.toString(array)+"\n"); // print array before sort
        heapSort(array); // Call for heap sort function
        System.out.println("The array after the heap sort algorithm: ");
        System.out.println(Arrays.toString(array)); // print array after sort

    }

    private static void heapSort(int[] array)
    {
        int end = array.length-1; // Take the index of the last element in the array
        int start = (array.length/2)-1; // Get the index of the middle element

        for(int x=start; x>=0; x--) // Iterate down the array, starting from the middle
        {
            heapify(array,array.length,x); // Turn the array into a heap by rearranging the first half of the array
        }

        for(int y=end; y>=0; y--) // Iterate down the array, starting from the last index position
        {
            swapUtil(array, 0, y); // Swap the root (largest value) with the index position (y)
            heapify(array,y,0); // Rearrange the heap again, placing the next largest value as the root
        }
    }

    // Recursively sorts the array into a binary tree with the largest element as the root
    private static void heapify(int[] array, int size, int root)
    {
        int max=root; // Temporarily set the max index value as the root index
        int left = 2*root+1; // Get the left index of the root (binary tree)    Example: array[]={9,8,7}   left=1    (root=0)
        int right = 2*root+2; // Get the right index of the root (binary tree)  Example: array[]={9,8,7}   right=2   (root=0)

        if(left<size && array[left]>array[max]) // If left is inbounds and it's corresponding array element is larger than the max...
        {
            max=left; // Make left the new max value
        }

        if(right<size && array[right]>array[max]) // If right is inbounds and it's corresponding array element is larger than the max...
        {
            max=right; // Make right the new max value
        }

        if(max!=root) // If the max value is no longer the root...
        {
            swapUtil(array, root, max); // Swap the root element with the max element
            heapify(array,size,max); // Then rearrange the array again
        }
    }

    // Utility function used to swap two array elements
    private static void swapUtil(int[] arr, int x, int y)
    {
        int n = arr[x];
        arr[x]=arr[y];
        arr[y]=n;
    }

}