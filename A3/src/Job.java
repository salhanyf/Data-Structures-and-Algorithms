import java.util.Random;

public class Job implements Comparable <Job> {
	
	//name of a process/job
	String jobName;

	//the needed CPU cycles for a job to terminate
	int jobLength;
	
	//the remaining length of a job at any given time
	int currentJobLength;
	
	//the initial priority of this job
	int jobPriority;

	//the final priority of a job at termination time
	int finalPriority;
	
	//the time a job entered the priority queue
	long entryTime;
	
	//when a job finally terminated
	long endTime;
	
	//the total wait time a process had to incur from
	//the time it entered the queue until it terminates
	long waitTime;
	
	Random ran = new Random ();
	public static int jobsCreated = 0; 
	public static int totalnumberofCycles = 0;
	
	//to track the current time
	public static long counter; 
	private int currentTime; 
	
	//constructor
	public Job() {
		this.jobName = "JOB_" + (jobsCreated + 1);
		//job length between 1 and 70
		this.jobLength = ran.nextInt(70)+1; 	
		this.currentJobLength = this.jobLength;
		//job priority between 1(highest) and 40(lowest)
		this.jobPriority = ran.nextInt(40)+1; 
		this.finalPriority = this.jobPriority;
		this.entryTime = ++jobsCreated;
		this.endTime = 0;
		this.waitTime = 0;	
	}
	
	//getters and setters
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getJobLength() {
		return jobLength;
	}

	public void setJobLength(int jobLength) {
		this.jobLength = jobLength;
	}

	public int getCurrentJobLength() {
		return currentJobLength;
	}

	public void setCurrentJobLength(int currentJobLength) {
		this.currentJobLength = currentJobLength;
	}

	public int getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(int jobPriority) {
		this.jobPriority = jobPriority;
	}

	public int getFinalPriority() {
		return finalPriority;
	}

	public void setFinalPriority(int finalPriority) {
		this.finalPriority = finalPriority;
	}

	public long getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(long entryTime) {
		this.entryTime = entryTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(long waitTime) {

		this.waitTime = waitTime;
	}
	
	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	
	@Override
	public String toString()
	{
		return "Now Executing " + jobName + ". Job Length: " + jobLength + " cycles; " 
	+ "Current remaining length: " + currentJobLength + " cycles\n" +
				"Initial Priority: " + jobPriority + "; Current Priority: " + finalPriority + "\n\n";
	}

	@Override
	public int compareTo(Job o) {
		if (o.finalPriority == this.finalPriority) {
			return (int) (this.currentTime - o.currentTime);
		}
		
		return this.finalPriority - o.finalPriority;
	}
}
	
