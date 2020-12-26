//-----------------------------------------------------
//COMP352
//Assignment 1
//Programming Question - Recursive Approach
//-----------------------------------------------------
import java.io.*;
import java.util.*;

public class Version1{	

/**
 *
 * To solve this recursively, we think of starting to check if 
 * the character is masked, and replaced it with either 0's or 1's 
 * and then recursively do the same to rest of the characters. 
 *
 */
	
	//main method that where we calculate the runtime
	public static void main(String[] args) throws FileNotFoundException {
        
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the string with \na number of \"*\" masked characters: ");
		
		//to measure accurate beginning run time.
		long startTime = System.currentTimeMillis(); 
		
		char[] series = (input.next()).toCharArray();
		//calling the method
		RevealStr(series, 0); 
		
		//to measure accurate ending run time.	
		System.out.println("\n\nNow for the run times.");
		
		long endTime   = System.currentTimeMillis(); 
	
		//to test and see if file if output file is being updated, compare values
		System.out.println(startTime);
		System.out.println(endTime);
		
		//redirecting the output to file
		PrintStream fileOut = new PrintStream("./out.txt"); 
		System.setOut(fileOut); 
		
		//printing in output file
		System.out.println(startTime);
		System.out.println(endTime);
		
		long totalTime = endTime - startTime;
		//displays the total time
		System.out.println("Total time to run program - "+  (totalTime/1000.0)+"s"); 

		//closing scanner
		input.close();    
	}
	  
	
	//method RevealStr
	private static void RevealStr(char series[], int i)
	{
		//Checks whether i == to the length of the array
		if (i == series.length) {
		System.out.println(series); //prints string 
		return;
		}		
		
		//here '*' replaced with 0 or 1 
		if (series[i] == '*') { 
			for (char ch = '0'; ch <= '1'; ch++)
			{
				// replace '*' with 0 and 1
				series[i] = ch;	                                
				RevealStr(series, i + 1);		                               
				// backtrack 		                              
				series[i] = '*';		                     
			}		                      
			return;		            
		}		            
		//recursion 
		RevealStr(series, i + 1);
	}

}