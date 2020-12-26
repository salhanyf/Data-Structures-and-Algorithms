import javax.management.RuntimeErrorException;

//a sorted list class that implemented using a sorted linked list. 
public class SortedList {
	
	//array of objects (jobs)
	Object [] jobs;
	int size = 0;
	
	//default constructor
	public SortedList () { 
		jobs = new Object [size]; 
		this.size = 0; 
	}
	
	//constructor
	public SortedList (int size) { 
		jobs = new Object [size]; 
		this.size = 0; 
	}
	
	//inserting a job
	public void enqueue (Job job) {
		//if queue is full
		if (isFull()) {
			throw new RuntimeErrorException(null, "Array is full.");  
		}
		
		//if queue is empty
		if (size == 0) {
			jobs[0] = job; 
			size++;
		}
		
		else {
			//comparing priority
			for (int i = 0; i < size ; i++ ) {
				if (job.compareTo((Job) jobs[i]) < 0) {
					for (int k = size; k >= i; k--) {
						if (k == i) {
							jobs[k] =  job; 
						}
						else {
							jobs[k] = jobs[k-1];
						}
					}
				size++; 
				return;
				}
				
				else if ((i+1) == size) {
					jobs[i+1] = job;
					size++; 
					return; 
				}
			}
		}
	}
	
	//removing a job
	public Job dequeue () {
		if (isEmpty()) {
			throw new RuntimeErrorException(null, "Array is empty.");  
		}
		
		Job temp = (Job) jobs[0]; 
		jobs[0] = null;
		
		//shifting indexes by 1 in the queue 
		for (int i = 1 ; i < size; i++) {
			jobs[i-1] = jobs[i]; 
		}
		size--;
		
		jobs[size] = null;
		return temp; 
	}
	
	//checks if the queue is empty
	public Boolean isEmpty() {
		return size == 0 ; 
	}
	
	//checks if the queue is full
	public Boolean isFull () {
		return size == jobs.length; 
	}
	

	//returns the job with maximum wait time
	public Job getMaxWait () { 
		Job temp = (Job) jobs[0];
		int index = -1;
		
		for (int i = size - 1 ; i > 0 ; i--) {
			if (temp.getEntryTime() > ((Job) jobs[i]).getEntryTime()
				&& ((Job) jobs[i]).getJobLength() - ((Job) jobs[i]).getCurrentJobLength() == 0){
				temp = (Job) jobs[i];
				index = i; 
				break; 
			}
		}
		//if found
		if (index >= 0) {
			for (int i=0, k=0 ; i < size ; i++) {
				if (i == index) {
					jobs[i]= null; 
					k = 1; 
				}
				else if (k == 1) {
					jobs[i-1] = jobs[i];
				}
			}
			size--;
			return temp; 
		}
		else 	
			return null; 
	}
	
	//to string
	@Override
	public String toString() {
		return jobs.toString();
	}

}//end of class
