package ctmilan.practice;
import java.util.*;

public class Main {

    // ====== Summary ======
    //
    // --Time Complexity--
    //   Overall:   O(n)    // Pre-Sorted
    //   Average:   O(n^2)  // Randomized
    //   Worst:     O(n^2)  // Sorted in reverse
    //
    // --Space Complexity--
    //   Overall: O(1)
    //
    // -- Pros --
    // * Stable sorting (maintains order of equal elements)
    // * Super Simple
    // * Great with smaller datasets that aren't too messy
    // * Little space required
    //
    // -- Cons --
    // * Slow with large datasets compared to other algorithms

    public static void main(String[] args) {
        Random random = new Random();

        // Initialize an integer array of random size (min=2, max=20)
        int[] array = new int[2+random.nextInt(18)];
        // Fill the array with integer values (min=0, max=60)
        for(int x=0; x<array.length; x++)
        {
            array[x]=random.nextInt(60);
        }

        System.out.println("The randomly generated unsorted array of size "+array.length+" is:");
        System.out.println(Arrays.toString(array)+"\n"); // print array before sort
        insertionSort(array); // Call for insertion sort function
        System.out.println("The array after the insertion sort algorithm: ");
        System.out.println(Arrays.toString(array)); // print array after sort
    }

    // Insertion Sort Function
    private static void insertionSort(int[] array)
    {
        int x=1; // Assuming the first array element is sorted, we start at index position x=1
        while(x<array.length) // loop until the end of the array
        {
            int y=x-1; // y = the index position before x
            int index=x; // copy x
            while(array[index]<array[y]) // Loop while there is an array element smaller than the current selected element
            {
                swapUtil(array,index,y); // swap the selected element with it's neighbor to the left
                y--; // find the next element to the left
                index--; // update the array position of the selected element
                if(y<0) // if y has reached the beginning of the array...
                {
                    break; // break the loop and select the next element to sort
                }
            }
            x++; // increment x to select the next element in the array
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