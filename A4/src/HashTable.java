import java.util.Hashtable;
import java.util.Set;

public class HashTable<C> extends IntelligentSIDC<C> {
	
	Hashtable<Long, Entry<C>> table;

	public HashTable() {
		this(1000);
	}
	public HashTable(int n) {
		super();
		table = new Hashtable<Long, Entry<C>>(n);
	}
	
	//add an entry for the given key and value;
	public void add(long key, C value) {
		if( Long.toString(key).length() != 8 ){
            //System.out.println("Hashtable Error: Length is not 8.\n");
            return;
        }

		//checks if key exists
        Entry<C> entry = new Entry<>(key, value);
        if(table.containsKey(key)) {
            //System.out.println("Found duplicate for key " + key + ".\n");
            table.replace(key, entry);
            return;
        }
        table.put(key, entry);
        size++;
    }
	
	//remove the entry for the given key
	public Entry<C> remove(long key) {
        try {
            Entry<C> temp = table.remove(key);
            size--;
            return temp;
        } catch (NullPointerException e) {
            System.out.println("Key " + key + " does not exist");
        }
        return null;
    }
	
    //return the values of the given key
    public C getValues(long key) {
        try {
            return table.get(key).getValue();
        } catch (NullPointerException e)  {
            System.out.println("Key " + key + " does not exist");
        }
        return null;
    }
	
    //return the key for the successor of key
    public long nextKey(long key) {
        Set<Long> keys = table.keySet();
        Long[] allKeys = keys.toArray(new Long[keys.size()]);

        for(int i = 0; i<allKeys.length; ++i) {
            if(allKeys[i] == key) {
                if(i < allKeys.length - 1) {
                    return allKeys[i+1];
                } else {
                    System.out.println("Next key of " + key + " does not exist.");
                    return 0;
                }
            }
        }

        System.out.println("Next key of " + key + " does not exist.");
        return 0;
    }
	
    //return the key for the predecessor of key
    public long prevKey(long key) {
        //convert key set into array
    	Set<Long> keys = table.keySet();
    	Long[] allKeys = keys.toArray(new Long[keys.size()]);

        for(int i = 0; i<allKeys.length; ++i) {
            if(allKeys[i] == key) {
                if(i >= 1) {
                    return allKeys[i-1];
                } else {
                    System.out.println("Prev key of " + key + " does not exist.");
                    return 0;
                }
            }
        }

        System.out.println("Prev key of " + key + " does not exist.");
        return 0;
    }
    
	//returns all keys sorted using merge sort
    public Long[] allKeys() {
        //extract keys out from the hash table
        Set<Long> keys = table.keySet();
        Long[] arr = keys.toArray(new Long[keys.size()]);
        System.out.println("size is:"+ arr.length);
        mergeSort(arr, 0, arr.length-1); //Call Merge sort

        return arr;
    }
    
    public void mergeSort(Long[] arr, int l, int r) {
    	if(r <= l) {
        	return;
    	}
    	//partitioning
    	int mid = (l+r)/2;
    	mergeSort(arr, l, mid);
    	mergeSort(arr, mid+1, r);
    	//merge
    	merge(arr, l, mid , r);
    }
    
    //merge sort
    public void merge(Long[] arr, int l, int m, int r) {
    	int size1 = m - l + 1;
    	int size2 = r - m ;
    	//creating 2 sub arrays
        Long[] left = new Long[(int) size1];
        Long[] right = new Long[(int) size2];
    	
        //copying our subarrays into temporaries
        for (int i = 0; i < size1; ++i)
        	left[i] = arr[(int) (l + i)];
        for (int j = 0; j < size2; ++j)
        	right[j] = arr[(int) (m + 1 + j)];

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = l; i < r + 1; i++) {
            // If there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < left.length && rightIndex < right.length) {
                if (left[leftIndex] < right[rightIndex]) {
                   arr[i] = left[leftIndex];
                   leftIndex++;
                } else {
                    arr[i] = right[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < left.length) {
                // If all elements have been copied from rightArray, copy rest of leftArray
                arr[i] = left[leftIndex];
                leftIndex++;
            } else if (rightIndex < right.length) {
                // If all elements have been copied from leftArray, copy rest of rightArray
                arr[i] = right[rightIndex];
                rightIndex++;
            }
        }
    }
    
    public Sequence<C> restructure() {
        Sequence<C> sequence = new Sequence<>(this.getSize());
        System.out.println("Restructuring data to Sequence");

        table.forEach((k, v) -> {
            sequence.add(k, v.getValue());
        });
    
        sequence.setSIDCThreshold(this.getSIDCThreshold());
        return sequence;
    }
	
}//end of class HashTable
