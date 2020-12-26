//implementing array heap
public class Array_Heap {

	//array of objects (jobs)
	Job [] jobs;
	int size = 0;
	
	//default constructor
	public Array_Heap () {
		jobs = new Job[0]; 
		size = 0; 
	}
	
	//constructor
	public Array_Heap (int n) {
		jobs = new Job[n]; 
		size = 1; 
	}

	//inserting a job
	public void enqueue (Job job) {
		if (jobs.length == size) {
			//creating an array of jobs
			int num = size; 
			if (size == 0) {
				num = 2; 
			}

			Job[] jobstemp = new Job[num * 2];
			for (int i=0; i < jobs.length; i++) {
				jobstemp[i] = (Job) jobs[i];
			}
			jobs = jobstemp;
		}
		
		if (jobs[1] == null) {
			jobs[1] = job; 
			size++;
		}
		
		else {
			size++; 
			jobs[size-1] = job; 
			int index = size - 1 ;

			while (index > 1 && jobs[index].compareTo(jobs[parent(index)] ) < 0) {
				swap(index, parent(index));
				index = parent(index);
			}
		}
	}
	
	//removing a job
	public Job dequeue () throws Exception {
		if (isEmpty())
			throw new Exception();

		else {
			Job element = jobs[1];
			jobs[1]= jobs[size-1];
			jobs[size-1] = null; 
			size--; 
	
			heapify(1);
			if (size == 0||(jobs.length / size *100)<90) {
				Job[] temp = new Job[jobs.length/2]; 
				for (int i=0 ; i < temp.length ; i++) {
					temp[i] = jobs[i]; 
				}
				jobs = temp;
				return element; 
			}
			else {
				return element;
			}
		}
	}
	
	//to rebuild (reorder) the heap
	public void heapify (int index ) {	
		int left = leftChild(index);  
		int right = rightChild(index); 
		int smallest;   

		if (left <= size-1 && jobs[left].compareTo(jobs[index]) < 0)
			smallest = left;   
		else
			smallest = index;      

		if (right <= size-1 && jobs[right].compareTo(jobs[smallest]) < 0)
			smallest = right;  


		if (smallest != index) {
			swap(index, smallest);
			heapify(smallest);
		}
	}
	
	//swapping between two jobs
	public void swap (int j1, int j2) {
		Job temp = jobs[j1];
		jobs[j1] = jobs[j2];
		jobs[j2]=  temp;
	}

	//returns the job with maximum wait time
	public Job getMaxWait () {
		Job temp = (Job) jobs[1];
		int index = -1;
		for (int i=size-1 ; i > 1 ; i--) {
			if (temp.getEntryTime() >  ((Job) jobs[i]).getEntryTime() 
				&& ((Job) jobs[i]).getJobLength() - ((Job) jobs[i]).getCurrentJobLength() == 0){
				temp = (Job) jobs[i];
				index = i; 
				break; 
			}
		}
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

	public boolean isEmpty() {
		if (size==1) 
			return true;
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public boolean hasParent (int index) {
		return jobs[index/2] != null; 
	}

	public int parent (int index ) {
		return  index/2;
	}
	
	//checks if it is a left child
	public boolean external (int index) {
		return jobs[index*2] == null; 
	}
	
	//checks if it is a right child
	public boolean hasrightchild (int index) {
		return jobs[index*2+1] !=null; 
	}
	
	public static int rightChild (int i) {
		return 2*i +1;
	}
	
	public static int leftChild (int i) {
		return 2*i ;
	}

	public void clear() {
		for (int i=0; i < jobs.length ; i++ ) {
			jobs[i]= null; 
		}
		jobs = new Job[0]; 
		size = 0;
	}
}//end of class
