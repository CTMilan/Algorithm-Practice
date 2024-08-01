package ctmilan.practice;
import java.util.Arrays;

public class Main {
    public static void main(String[] args)
    {
        // The initial arrays
        int[] array = {10,3,1,17,9,8,21,7};
        //int[] array = {15,5,3,7,12,4,14,16};
        //int[] array = {12,25,2,1,7,8,17,9};

        System.out.println("\nSorting via Hoare's Partition");
        System.out.println("Before Sort: "+Arrays.toString(array));
        // The first index value of the array length being sorted (defaulting to zero)
        int start = 0;
        // The last index value of the array length being sorted (defaulting to the last array element)
        int end = array.length-1;
        // Begin Quicksort Algo
        quicksort(array,start,end);
        System.out.println("After Sort: "+Arrays.toString(array)+"\n");

    }

    private static void quicksort(int[] arr, int start, int end){
        
        // Check to see if the index pivots have met. If true, then close recursion branch.
        if(start>=end)
        {
            return;
        }
        // Else? continue quicksort recursions...

        // Split and reorder the next portion of the array around the new pivot value, returning the index position of the pivot

        //int pivot = lomuto_partition(arr,start,end);
        //int pivot = naive_partition(arr,start,end);
        int pivot = hoares_partition(arr,start,end);

        // Sort the half of the array that is less than the pivot value
        quicksort(arr,start,pivot-1);
        // Sort the half of the array that is greater than the pivot value
        quicksort(arr,pivot+1,end);
    }
    // Utility function used to swap two array elements
    private static void swapUtil(int[] arr, int x, int y)
    {
        int n = arr[x];
        arr[x]=arr[y];
        arr[y]=n;
    }


    // The Lomuto Partition Scheme for Quicksort
    @SuppressWarnings("unused")
    private static int lomuto_partition(int[] arr, int start, int end){

        // The pivot index position
        int index=start;
        // The pivot value (Lomuto's Partition chooses the last element in the array)
        int pivot = arr[end];

        for(int x=start; x<end;x++)
        {
            if(arr[x]<=pivot)
            {
                swapUtil(arr, x, index);
                index++;
            }
        }
        swapUtil(arr,end,index);
        return index;
    }

    // The Naive Partition Scheme for Quicksort
    @SuppressWarnings("unused")
    private static int naive_partition(int[] arr, int start, int end){

        // Create a temporary array to sort elements into
        int[] tempArr = new int[(end-start)+1];
        // The pivot index position
        int index=0;
        // The pivot value (The Naive Partition chooses the last element in the array)
        int pivot = arr[end];

        //Begin by sorting all numbers smaller than the pivot
        for(int x=start; x<=end;x++)
        {
            if(arr[x]<pivot)
            {
                tempArr[index++]=arr[x];
            }
        }

        // Then save the pivot's final index position so it can be returned later
        int indexReturn = start+index;
        // Now return the pivot to it's original position
        tempArr[index++] = pivot;
        
        // Then sort all numbers larger than the pivot
        for(int x=start; x<=end;x++)
        {
            if(arr[x]>pivot)
            {
                tempArr[index++]=arr[x];
            }
        }

        for(int x=start; x<=end;x++)
        {
            arr[x] = tempArr[x-start];
        }
        
        return indexReturn;
    }

    // Hoare's Partition Scheme for Quicksort
    private static int hoares_partition(int[] arr, int start, int end){


        // The pivot value
        int pivot = arr[start];

        // Index of the left side of the array
        int indexLeft = start;
        // Index of the right side of the array
        int indexRight = end;

        while(true)
        {
            // Find the location of an element smaller than the pivot value
            while(arr[indexLeft]<pivot)
            {
                indexLeft++;
            }
            // Find the location of an element larger than the pivot value
            while(arr[indexRight]>pivot)
            {
                indexRight--;
            }
            // If the two locations are the same, return the index location since no more swapping can occur
            if(indexLeft==indexRight)
            {
                return indexRight;
            }
            // Swap the array elements
            swapUtil(arr, indexLeft, indexRight);
            // Repeat until no more swapping can occur, exit loop via the previous if-statement
        }
        
        
    }

}