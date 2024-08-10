package ctmilan.practice;
import java.util.*;

public class Main {
    
    // ====== Summary ======
    //
    // --Time Complexity--
    //   Overall: O(nlogn) // Whether or not the array is pre-sorted or in reverse, 
    //                         the algorithm will attempt to sort no matter what.
    //                          Therefore time complexity is independent of the initial
    //                          state of the given array.
    //
    // --Space Complexity--
    //   Overall: O(n) // Due to the temporary subarrays used
    //
    // -- Pros --
    // * Stable sorting (maintains order of equal elements)
    // * Simple divide & conquer strategy
    // * Merges subarrays in parallel processes
    // * Fast with large datasets relative to other algorithms (ie. quicksort)
    //
    // -- Cons --
    // * Requires space for temporary subarrays
    // * Slow with small datasets compared to other algorithms

    public static void main(String[] args)
    {
        Random random = new Random();

        // Initialize an integer array of random size (min=2, max=20)
        int[] array = new int[2+random.nextInt(20)];
        // Fill the array with integer values (min=0, max=60)
        for(int x=0; x<array.length; x++)
        {
            array[x]=random.nextInt(60);
        }

        System.out.println("The randomly generated unsorted array of size "+array.length+" is:");
        System.out.println(Arrays.toString(array)+"\n"); // print array before sort
        mergesort(array,0,array.length-1); // Call for mergesort function
        System.out.println("The array after the merge sort algorithm: ");
        System.out.println(Arrays.toString(array)); // print array after sort
    }

    // Mergesort Algorithm Function
    private static void mergesort(int[] array, int start, int end)
    {
        if(end<=start || array.length<2) // return if the given array can no longer be split
        {
            return;
        }
        // get the middle index position of the array
        int middle = (start+(end-start)/2);
        // initialize temporary left and right side arrays
        int[] left = new int[middle-start+1];
        int[] right = new int[end-middle];

        // copy the left and right sides of the given array onto their designated temporary arrays
        for(int x=0; x<array.length; x++)
        {
            if(x<left.length) // copy left side
            {
                left[x]=array[x];
            }
            else // copy right side
            {
                right[x-left.length]=array[x];
            }
        }
        
        mergesort(left,start,middle); // mergesort left side
        mergesort(right,middle+1,end); // mergesort right side
        mergeUtil(array,left,right,start,middle,end); // merge both sides together
    }

    // Mergesort Utility Function
    private static void mergeUtil(int[] array, int[] left, int[] right, int start, int middle, int end)
    {
        // initialize temporary index variables
        int a=0, b=0, c=0; // a->left b->right c->array

        // sorting temporary arrays as they are copied back to their parent array
        while(a<left.length && b<right.length)
        {
            if(left[a]<=right[b]) // if left element <= right element
            {
                array[c]=left[a]; // then copy down left element
                a++; // increment left array index
            }
            else // if left element > right element
            {
                array[c]=right[b]; // then copy down right element
                b++; // increment right array index
            }
            c++; // increment parent array index
        }

        // copy the rest of the left array onto the parent array
        while(a<left.length){
            array[c]=left[a];
            a++;
            c++;
        }
        // copy the rest of the right array onto the parent array
        while(b<right.length){
            array[c]=right[b];
            b++;
            c++;
        }
    }

}