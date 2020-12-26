//-----------------------------------------------------
//COMP352
//Assignment 1
//Programming Question - Iterative Approach
//-----------------------------------------------------
import java.io.*;
import java.util.*;

public class Version2 {

/**
 *
 * To solve this Iteratively, we use stacks or queues. 
 * Here i will use a stack, popping -> replacing with either 0's or 1's 
 * -> then pushing the character back into the stack 
 * Until the entire string is processed.
 *
 */

	//main method that where we calculate the runtime
	public static void main(String[] args) throws FileNotFoundException   
	{    
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the string with \na number of \"*\" masked characters: ");
	
		//to measure accurate beginning run time.
		long startTime = System.currentTimeMillis(); 
				
		String series = (input.next());
		//calling the method
		RevealStr(series); 
		
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
	
	
	public static void RevealStr(String series)	        
	{	              
		// Creates an string type object of Stack 	
		Stack<String> stack = new Stack();	 
		
		//pushing the series into the stack
		stack.push(series);    
		
		//The current index i
		int i;	      
		
		//Going through the entire stack, emptying it            
		while (!stack.empty())	            
		{	               		
			//Pop elements from stack and processes them	                 
			String j = stack.pop();	       
			
			//i stores where the first time * shows in the series in j	                
			if ((i = j.indexOf('*')) != -1)                
			{	                   
				//Replace the masked character with either 0 or 1 
				//and push it to the top of the stack	                   
				for (char ch = '0'; ch <= '1'; ch++)	                 
				{	                 
					j = j.substring(0, i) + ch +	                  	               				
							j.substring(i + 1);	            
					stack.push(j);	              
				}	           
			}	               
			//If there's no masked character          
			else	       
				System.out.println(j);	      
		}	     
	}	    
	
}
