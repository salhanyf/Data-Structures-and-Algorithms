import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class PriorityQueueSimulatorTester {

	public static void main(String[] args) throws FileNotFoundException, Exception{
		
		//int [] maxNumberOfJobs = {100,1000,10000,100000,1000000};
		//int [] maxNumberOfJobs = {100,1000,10000};
		int [] maxNumberOfJobs = {10, 1000};
		
		
		//Declaring output stream to write an output text file
		PrintWriter scan = new PrintWriter (new FileOutputStream("SortedOutput.txt")); 
		
		System.out.println("Program started for SortedList implementation...\n\n");
		for (int k =0 ; k <maxNumberOfJobs.length ; k++) {
			//set counter to 0
			Job.counter = 0; 
			Job.jobsCreated =0; 
			
			SortedList jobsInputArray = new SortedList (maxNumberOfJobs[k]);
			for (int i=0; i < maxNumberOfJobs[k] ; i++) {
				jobsInputArray.enqueue(new Job());
			}
	
			int counter =0; 
			int totalChanges = 0; 
			//average process waiting time
			long average = 0; 
			//to measure accurate beginning run time
			long start = System.currentTimeMillis();
			
			//while there's a job in queue
			while (!jobsInputArray.isEmpty()) {
				
				Job temp; 
				//to keep track of cycles
				Job.totalnumberofCycles++;
				temp = jobsInputArray.dequeue();
					
				//printing to console and output file
				scan.println("Now excuting "+ temp.getJobName() +". Job length: "+ temp.getJobLength()
					+" Cycles; Remaning length: "+ temp.getCurrentJobLength()
					+"; Initial priority "+ temp.getJobPriority() +"; Current priority "+
					temp.getFinalPriority()+"\n\n");
				
				System.out.println("Now excuting "+ temp.getJobName() +". Job length: "+ temp.getJobLength()
				+" Cycles; Remaning length: "+ temp.getCurrentJobLength()
				+"; Initial priority "+ temp.getJobPriority() +"; Current priority "+
				temp.getFinalPriority()+"\n\n");
					
				//while job is not finished
				if (temp.getCurrentJobLength() > 0) {
					temp.setCurrentJobLength(temp.getCurrentJobLength() - 1);
					
					Job.counter +=1;
					temp.setCurrentTime((int)Job.counter);
					jobsInputArray.enqueue(temp);
				}
				
				else {
					temp.setWaitTime((Job.counter - Job.jobsCreated));
					average += temp.getWaitTime();
					counter++;
					
					//when total of 30 processes are terminated we change the priority of the job with max wait time
					//to decrease the starvation of low priority jobs
					if (counter % 30 == 0) {
						temp = jobsInputArray.getMaxWait(); 
						if (temp != null ) {							
							temp.setFinalPriority(1);
							Job.counter +=1; 
									
							temp.setEntryTime(counter);
							jobsInputArray.enqueue(temp);
							totalChanges++; 
						}					
					}
				}
			}//end of while
			
			//to measure accurate ending run time
			long end = System.currentTimeMillis();
				
			scan.println("");
			System.out.println("");
			scan.println("Current system time (cycles): " + (Job.totalnumberofCycles));
			System.out.println("Current system time (cycles): " + (Job.totalnumberofCycles));
			scan.println("Total number of jobs executed:  " + ( Job.jobsCreated) +" Jobs");
			System.out.println("Total number of jobs executed:  " + ( Job.jobsCreated) +" Jobs");
			scan.println("Average process waiting time:  " + average/maxNumberOfJobs[k] +" Cycles.");
			System.out.println("Average process waiting time:  " + average/maxNumberOfJobs[k] +" Cycles.");
			scan.println("Total number of priority changes:  " + totalChanges);
			System.out.println("Total number of priority changes:  " + totalChanges);
			scan.println("Actual system time needed to execute all jobs:  " + (end- start) +"ms.");
			System.out.println("Actual system time needed to execute all jobs:  " + (end- start) +"ms.");
			scan.println("");
			System.out.println("");
			
			scan.flush();
		}
		scan.close();
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Program started for Array-Heap implementation...\n\n");
		
		//Declaring output stream to write an output text file
		PrintWriter scan1 = new PrintWriter (new FileOutputStream("ArrayHeap.txt")); 
		for (int k =0 ; k <maxNumberOfJobs.length ; k++) {
			Job.counter = 0; 
			Job.jobsCreated =0; 
			
			Array_Heap jobsInputArray = new Array_Heap (maxNumberOfJobs[k]);
			for (int i=0; i < maxNumberOfJobs[k] ; i++) {
				jobsInputArray.enqueue (new Job());
			}

			int counter = 0; 
			int totalChanges = 0 ; 
			long average = 0; 
			long start = System.currentTimeMillis();

			while (!jobsInputArray.isEmpty()) {
				Job temp; 
				Job.totalnumberofCycles++;
				temp = jobsInputArray.dequeue();
				
				//printing to console and output file
				scan1.println("Now excuting "+ temp.getJobName() +". Job length: "+ temp.getJobLength()
					+" Cycles; Remaning length: "+ temp.getCurrentJobLength()
					+"; Initial priority "+ temp.getJobPriority() +"; Current priority "+
					temp.getFinalPriority()+"\n\n");
				
				System.out.println("Now excuting "+ temp.getJobName() +". Job length: "+ temp.getJobLength()
				+" Cycles; Remaning length: "+ temp.getCurrentJobLength()
				+"; Initial priority "+ temp.getJobPriority() +"; Current priority "+
				temp.getFinalPriority()+"\n\n");

				if (temp.getCurrentJobLength() > 0) {
					temp.setCurrentJobLength(temp.getCurrentJobLength() -1);
					Job.counter +=1; 
					temp.setCurrentTime((int)Job.counter);

					jobsInputArray.enqueue(temp);

				}

				else {
					temp.setWaitTime((Job.counter -Job.jobsCreated));
					average += temp.getWaitTime();
					counter++;

					//when total of 30 processes are terminated we change the priority of the job with max wait time
					//to decrease the starvation of low priority jobs
					if (counter % 30 == 0) {
						temp = jobsInputArray.getMaxWait(); 
						if (temp != null ) {

							temp.setFinalPriority(1);
							Job.counter +=1; 

							temp.setEntryTime(counter);
							jobsInputArray.enqueue(temp);
							totalChanges++; 
						}
					}
				}
			}

			long end = System.currentTimeMillis();
						
			scan1.println("");
			System.out.println("");
			scan1.println("Current system time (cycles): " + (Job.totalnumberofCycles));
			System.out.println("Current system time (cycles): " + (Job.totalnumberofCycles));
			scan1.println("Total number of jobs executed:  " + ( Job.jobsCreated) +" Jobs");
			System.out.println("Total number of jobs executed:  " + ( Job.jobsCreated) +" Jobs");
			scan1.println("Average process waiting time:  " + average/maxNumberOfJobs[k] +" Cycles.");
			System.out.println("Average process waiting time:  " + average/maxNumberOfJobs[k] +" Cycles.");
			scan1.println("Total number of priority chnages:  " + totalChanges );
			System.out.println("Total number of priority changes:  " + totalChanges);
			scan1.println("Actual system time needed to execute all hobs:  " + (end- start) +"ms.");
			System.out.println("Actual system time needed to execute all jobs:  " + (end- start) +"ms.");
			scan1.println("");
			System.out.println("");
		
			jobsInputArray.clear();
			scan1.flush();
			
		}//end of for loop

		scan1.close();
	}//end of main
}//end of class
