import java.io.*;
import java.util.Scanner;

//Notes:Impelemnt Recursion for getting each unique matching

public class Utility{   
   
   //create preference list
   static void create_preference_list(boolean taken[], int preference_list[][], int persons, Scanner sc){
   
      //for n people
      for(int i = 0; i < persons; i++){
      
         taken[i] = false;//list the people without a roommate
      
         //and there preferences
         for(int j = 0; j < persons - 1; j++){
         
            preference_list[i][j] = sc.nextInt();//get their preferences and store them in a list
         
         }//end for
      }//end for    
      return;
   }
      
      
   static void get_number_of_matchings(int persons, int preference_list[][], int proposed_roommates[], int rooms, boolean taken[]){
      //declare local variables
      int stable_matches = 0;
      
      //for each person
      for(int i = 0; i < persons; i++){
      //and each possible unique match
         for(int j = persons - i; j < persons && j != i; j++){
         
            if(matching(i, j, persons, preference_list, proposed_roommates, rooms, taken)){//calculate the matching
               stable_matches++;
            }//end if
         
         }//end for
         
      }//end for
                        
      print_results(stable_matches);
         
   }//end get_number_of_matchings
      
  //check every matching for all combinations 
   static boolean matching(int person_1, int person_2, int persons, int preference_list[][], int proposed_roommates[], int rooms, boolean taken[]){
   
      int roommate;
      int preference = person_2;
      int unmatched = persons;
      
      //when there are no more free people
      if(unmatched == 0){                        
         return false;
      }//end if
               
      //start with current possible target             
      if(taken[preference] == false){//if preference has no proposal then
         proposed_roommates[person_1] = preference + 1;//preference accepts roommate
         proposed_roommates[preference] = person_1 + 1;//roommate accepts preference
                     
         //the tentative agreements
         taken[preference] = true;
         taken[person_1] = true;
                     
         unmatched -= 2;//unmatched reduces by 2
      }//end if 
         
      //while there are unmatched people
      while(unmatched > 0){
         
         //pair off roommates
         for(int i = 0; i < persons - 1; i++){//by roommate that is not taken
            for(int j = 0; j < persons - 1; j++){//by preferences j = n - 1
               //if the roommate has not made a tentative agreement
               if(taken[i] == false){
                  
                  //roommate proposes to the jth preference
                  preference = preference_list[i][j];
                  
                  //when there are no more free people
                  if(unmatched == 0){                        
                     break;
                  }//end if
                   
                  if(taken[preference - 1] == false){//if preference has no proposal then
                     proposed_roommates[i] = preference;//preference accepts roommate
                     proposed_roommates[preference - 1] = i + 1;//roommate accepts preference
                     
                     //the tentative agreements
                     taken[preference - 1] = true;
                     taken[i] = true;
                        
                     unmatched -= 2;//unmatched reduces by 2
                  }//end if 
                        
               }//end if
                        
               //if the roommate has been paired
               if(taken[i] == true){
                  break;//go to next roommate 
               }//end if
               
            }//end for
            
         }//end for
               
         //move on next preference for each roommate
         
      }//end while    
      
      //if there are no instabilities in the matchings
      if(!check_instability(persons, taken, preference, proposed_roommates, preference_list)){
         clean_lists(taken, proposed_roommates, persons);//clean lists
         return true;//there is a stable match
      }//end if
                  
      //else, there is an instability
      else{
         clean_lists(taken, proposed_roommates, persons);//clean lists
         return false;//it is not a stable match
      }//end else                
                        
   }//end stable_matching
   
   static void clean_lists(boolean taken[], int proposed_roommates[], int persons){
     
      for(int i = 0; i < persons; i++){
        //clean taken[]
         taken[i] = false;
        
        //clean proposed_roommates[]   
         proposed_roommates[i] = 0;
      
      }//end for
     
   }//end clean_lists
   
   //check proposed for any instabilities
   static boolean check_instability(int persons, boolean taken[], int preference, int proposed_roommates[], int preference_list[][]){
      //local variables
      boolean instability = false;
      boolean stable_match[] = new boolean[persons];
      
      //all people are not stable matched
      for(int i = 0; i < persons - 1; i++){
         stable_match[i] = false;
      }//end for
              
      //Case 1: preference prefers others than roommate
      //for all of preference's other preferences, check if they are open to proposal
      for(int i = 0; i < persons; i++){//for each matching
         
         int current_proposal = (i + 1);
                  
         //if the preference's 1st preference isn't roommate
         if(preference_list[proposed_roommates[i] - 1][0] != current_proposal){
            
            //go through checking the other preferences until you reach current roommate
            for(int j = 0; j < persons - 1; j++){
                     
               //if we land on current proposal
               if(preference_list[proposed_roommates[i] - 1][j] == current_proposal){
                  
                  stable_match[proposed_roommates[i] - 1] = true;//preference are stable matched
                  stable_match[i] = true;//roommate are stable matched
               
                  break;//stay with current proposal, move to next match
               }//end if
               
               int other_choice = preference_list[proposed_roommates[i] - 1][j];//pref's other choice
               
               int proposer = proposed_roommates[i];
               int competitor = proposed_roommates[other_choice - 1];//tentative agreementie of other_choice
               
               //if pref's choice is not stable-matched
               if(stable_match[other_choice - 1] != true){
                                 
               //preference_list[other_choice - 1][Arrays.binarySearch(preference_list[other_choice - 1], proposed_roommates[other_choice - 1] - 2)]
               //preference_list[other_choice - 1][Arrays.binarySearch(preference_list[other_choice - 1], proposed_roommates[other_choice - 1])]
               
                  //if other choice prefers proposal over current agreement; Higher index means less prefered
                  if(linear_search(preference_list, proposed_roommates, other_choice, proposer, persons) < linear_search(preference_list, proposed_roommates, other_choice, competitor, persons)){
                     instability = true;//instability
                     return instability;
                  }//end if                  
               }//end if   
            }//end for
            
            //else, move on to next matching
            
         }//end if
      }//end for
              
      //else, there is no instability         
   
      return false;//return false         
   
   }//end check_instability
   
   //linear search for checking the preferences priorities
   static int linear_search(int preference_list[][], int proposed_roommates[], int other_choice, int target, int persons){
      //local variables
      int index;
      
      //for the entire length of pref's preference list
      for(int i = 0; i < persons - 1; i++){
         //look for target to be compared
         if(preference_list[other_choice - 1][i] == target){
            return i;//return the index of target in preference_list
         }//end if
      }//end for
      return -1;//if not found, return error
   
   }//end linear_search
   
   //print output
   static void print_results(int stable_matchings){
      
      //if there are matchings, count and print matchings
      if(stable_matchings > 0){
         System.out.println("YES: " + stable_matchings);
      }//end if    
      
      //else print no
      else{
         System.out.println("NO");
      }//end else
            
   }//end print

}//end class
