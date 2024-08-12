package ctmilan.practice;
import java.util.*;

public class Main {

    // ====== Summary ======
    //
    // --Time Complexity--
    //   Overall:   O(n^2)
    //
    // --Space Complexity--
    //   Overall: O(1)
    //
    // -- Pros --
    // * Super Simple
    // * Fine with smaller datasets
    //
    // -- Cons --
    // * Has O(n^2) time complexity
    // * Unstable sorting (does not maintain relative order of equal elements)
    // * Bad with large datasets

    public static void main(String[] args)
    {
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
        selectionSort(array); // Call for selection sort function
        System.out.println("The array after the selection sort algorithm: ");
        System.out.println(Arrays.toString(array)); // print array after sort
    }

    // Selection Sort Function
    private static void selectionSort(int[] array)
    {
        for(int x=0; x<array.length; x++) // Loop through each element in the array
        {
            int key = array[x]; // Initially set the 'key' to current element selected
            int k_index = x; // copy that element's position on the array
            for(int y=x+1; y<array.length; y++) // loop through each element beyond the selected element
            {
                if(array[y]<key) // if an element is less than the 'key'
                {
                    key=array[y]; // update the 'key' to that element's value
                    k_index=y; // and update the position of the 'key'
                }
            }
            // at the end of the loop the smallest (unsorted) element should selected as the 'key'
            swapUtil(array, x, k_index); // swap the 'x' element with the 'key' element, thus sorting them
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