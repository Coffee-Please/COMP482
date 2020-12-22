import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Project1{
   public static void main (String args[]) throws Exception{
      //declare local variables
      String greeting = "Welcome to the Roommate Stability Index!";
      String file_name = "input1.txt";
      File file = new File(file_name);
      Scanner sc = new Scanner(file);
      int persons = 0;
      int rooms = 0;
      Utility util = new Utility();
   
      System.out.println(greeting);//Set Greeting 
   
      persons = sc.nextInt();//get n

      rooms = persons/2;//get the number of rooms
   
      int preference_list[][] = new int[persons][persons - 1];//array to hold preferences per person
      
      boolean taken[] = new boolean[persons];//list to hold people who have not been proposed to/matched
      
      int proposed_roommates[] = new int[persons];//list to hold proposals
      
      //create the preference list
      util.create_preference_list(taken, preference_list, persons, sc);
      
      //get # of matchings
      util.get_number_of_matchings(persons, preference_list, proposed_roommates, rooms, taken);
           
   }//end main      
}//end class