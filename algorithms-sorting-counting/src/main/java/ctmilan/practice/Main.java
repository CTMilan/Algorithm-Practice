package ctmilan.practice;

import java.util.Arrays;
import java.util.Random;

public class Main {

    // ====== Summary ======
    //
    // --Time Complexity--
    //   Overall:   O(X+Y) (X = Given Array Size, Y = Count Array Size ie. max value+1)
    //
    // --Space Complexity--
    //   Overall: O(X+Y) (Due to auxiliary arrays being used)
    //
    // -- Pros --
    // * Super Simple
    // * Stable
    // * Can be significantly quicker than other algorithms when the range of possible values is small
    // * Linear Time Complexity
    //
    // -- Cons --
    // * Bad when the range of values is large (Example: array[]={0,1,2,3,10000})
    // * Uses temporary arrays and therefore more auxiliary space
    // * Incompatible with decimal values

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
        array=countingSort(array); // Call for counting sort function
        System.out.println("The array after the counting sort algorithm: ");
        System.out.println(Arrays.toString(array)); // print array after sort

    }

    // Counting Sort Algorithm
    private static int[] countingSort(int[] array)
    {
        int[] result = new int[array.length]; // Result array

        int max=0; // Highest value in the given array[]
        for(int x=0; x<array.length; x++) // Move through each array[] element
        {
            if(array[x]>max) // Find the highest value in array[]
            {
                max=array[x]; // Update the max
            }
        }

        int[] countArray = new int[max+1]; // Using the maximum value, create a 'count' array of size max+1

        for(int x=0; x<array.length; x++) // Iterate through the array elements again
        {
            countArray[array[x]]++; // Count the occurances of each value in the given array onto countArray[]
                                    // Example: if  array[]={1,2,2,4,2,1}  then   countArray[]={0,2,3,0,1}
        }

        for(int x=1; x<=max; x++) // Iterate through countArray[], starting from the second element
        {
            countArray[x]=countArray[x]+countArray[x-1]; // Cumulatively add each element with the element that came before it
                                                         // Example:  if  countArray[]={0,2,3,0,1}   then   countArray[]={0,2,5,5,6}
        }

        // The preparation is complete, sorting can now begin
        for(int x=array.length-1; x>=0; x--)    // Now iterate back down the given array. Iterating in reverse maintains a stable result.
        {
                                                        // Look up the index positions of each element using countArray[]
            result[countArray[array[x]]-1] = array[x];  // Place each element into result[] at the assigned position 
            countArray[array[x]]--;                     // Then decrease that element's count

                                                        // Example:     array[]={1,2,2,4,2,1}

                                                        //              countArray[]={0,2,5,5,6} --> {0,2,5,5,6}   --> {0,2,5,5,5}   --> {0,2,5,4,5}   --> {0,2,4,4,5}
                                                        //              result[]={0,0,0,0,0,0}   --> {0,0,0,0,0,4} --> {0,0,0,0,0,4} --> {0,0,0,0,0,4} --> {0,0,0,0,2,4}

                                                        //              countArray[] --> {0,2,3,4,5}   --> {0,2,2,4,5}   --> {0,1,2,4,5}   --> {0,0,2,4,5}
                                                        //              result[]     --> {0,0,0,2,2,4} --> {0,0,2,2,2,4} --> {0,1,2,2,2,4} --> {1,1,2,2,2,4}

                                                        // End:       result[]={1,1,2,2,2,4}
        }
        
        return result; // Finally, return the resulting sorted array
    }

}