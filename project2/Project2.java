/*
Priya Singh Project 2
12-18-2019
COMP 482 - Fall 2019
*/

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Project2{
   public static void main(String args[]) throws Exception{
      
      //local attributes
      int ith_target = 0, num_of_items = 0, items[] = null;
      int sort_result, quick_result = 0, mom_result = 0;
      
      //Open File
      File input_file = new File("input2.txt");
      Scanner sc = new Scanner(input_file);
      
      //Error
      if(!input_file.exists()){
      
         System.out.println("Error: File Not Found");
      
      }//end if
      
      //Read and Store
      ith_target = sc.nextInt();
   
      num_of_items = sc.nextInt();
      
      items = new int[num_of_items];
   
      //List of Elements
      for(int i = 0; i < num_of_items; i++){
      
         items[i] = sc.nextInt();
      
      }//end for
            
      //Case - There is only one item in the list
      if(num_of_items == 1){
         
      //print results
         System.out.println("Sort finds " + items[0]);
         System.out.println("Quick finds " + items[0]);
         System.out.println("MOM finds " + items[0]);            
      
         return ;
      
      }//end if
            
      //Find the ith element
      //Method 1: System Function
      sort_result = sort_by_function(items, ith_target, num_of_items);
      
      //Method 2: Quick Sort
      quick_result = quickSort(items, ith_target);
      
      //Method 3: Median of Medians
      mom_result = ithItem(ith_target, num_of_items, items);
      
      //print results
      System.out.println("Sort finds " + sort_result);
      System.out.println("Quick finds " + quick_result);
      System.out.println("MOM finds " + mom_result);
      
      return;
      
   }//end main
   
   //Method 1: System Function-----------------------------------------------------------
   static int sort_by_function(int items[], int ith_target, int num_of_items){
          
      //Sort Array
      Arrays.sort(items);
     
      //Return ith item 
      return items[ith_target];
      
   }//end sort
       
   //Method 2: Quick Sort----------------------------------------------------------------
   static int quickSort(int items[], int ith_target){
         
      quickSort(items, 0, items.length - 1, ith_target);
      
      return items[ith_target];
   
   } 
  
  //Selects the pivot
   static void quickSort(int items[], int low, int high, int ith_target){
      
      //Case - The indexes have crossed
      if(low >= high){
      
         return;
      
      }//end if
      
      int pivot = items[(low + high) / 2];//create the pivot
      int new_pivot = partition(items, low, high, pivot);//divide the array and sort
   
      //Recurse on the side containing the ith_target
      //if pivot value is smaller than the ith value
      if(new_pivot <= ith_target){
         
         quickSort(items, new_pivot, high, ith_target);//send new low index
            
      }//end if
      
      //else if the pivot value is greater than the left value
      else if(new_pivot >= ith_target){
      
         quickSort(items, low, new_pivot - 1, ith_target);//send new high index
      
      }//end else if
      
      return;
   
   }//end sorting
  
  //Sorts subarrays according to the pivot value
   static int partition(int items[], int left, int right, int pivot){
      
      //While the left and right indexes have not crossed
      while(left <= right){
      
      //Case 1 - Search left of the array for a larger value
         while(items[left] < pivot){
            
            //Move the left index right
            left++;
         
         }//end while
      
      //Case 2 - Search right of the array for a smaller value
         while(items[right] > pivot){
            
            //Move the right index left
            right--;
                        
         }//end while
         
      //Case 3 - Swap Left (Larger) <-> Right (Smaller)
         if(items[left] >= items[right]){
            //Local Variable
            int temp;
            
            //Swap
            temp = items[left];
            items[left] = items[right];
            items[right] = temp;
            
            //Move left and right one more step
            left++;
            right--;
            
         }//end if
      
      }//end while
      
      return left;//return next partition
      
   }//end partition
   
   //Method 3 - Median of Medians-------------------------------------------------------
   static int ithItem(int ith_target, int num_of_items, int items[]) {
      
      //local Attributes
      int result, pivot, j = 0, k = 0, l;
            
      int[] medians, smaller, larger;
        
      //sort the dataset 
      if (num_of_items <= 5) {
         
         //local variables
         int start_range = 0;
         int end_range = num_of_items;   
            
         //Sort each set of 5 elements
         Arrays.sort(items, start_range, end_range);            
         result = items[ith_target];
            
      }//end if
        
      //else, find the median of the subarray to use as a pivot
      else {
         
         //local variables   
         medians = new int[num_of_items / 5];//stores medians of list
         smaller = new int[num_of_items];//subarray of values smaller than the pivot
         larger = new int[num_of_items];//subarray of values larger than the pivot
         
         
         //store the median of the subarrays into medians[]   
         for (l = 0; l < (num_of_items / 5); l++) {
         
            //local variables
            int median = (5 * (l + 1)) - 3;
            int start_range = (5 * l);
            int end_range = (5 * (l + 1));
            
            //Sort each set of 5 elements
            Arrays.sort(items, start_range, end_range);
            
            //Get the median of the 5 elements
            medians[l] = items[median];
         
         }//end for
                        
         pivot = ithItem(num_of_items / 10, num_of_items / 5, medians);
           
         //Search items[] elements relative to the pivot
         for (l = 0; l < num_of_items; l++) {
         
            //if the value is greater than or equal to the pivot, place to
            //the left of the pivot    
            if (items[l] <= pivot){
                    
               smaller[j++] = items[l];
                    
            }//end if
            
            else{
                        
               larger[k++] = items[l];
                        
            }//end else
            
         }//end for
           
           
         if (ith_target < j){
                
            result = ithItem(ith_target, j, smaller);
                
         }//end if
            
         else{
              
            result = ithItem(ith_target - j, k, larger);
                    
         }//end else
         
      }//end else
        
      return result;
                
   }//end ithItem
   
}//end class