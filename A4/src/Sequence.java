import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Sequence<C> extends IntelligentSIDC<C> {
	
	ArrayList<Entry<C>> sequence;
	
	//constructor
	public Sequence() {
		this(0);
	}
	public Sequence(int n) {
		super();
		sequence = new ArrayList<Entry<C>>(n);
	}
	
	//add an entry for the given key and value;
	public void add(long key, C value) {
        if(Long.toString(key).length() != 8) {
        	//System.out.println("Sequence Error: Length is not 8.\n");
            return;
        }

        //clear the old key
        try {
            this.remove(key); 
        } catch(Exception e) {
            sequence.add(new Entry<C>(key, value));
            size++;
            return;
        }
        //System.out.println("Found duplicate for key " + key + ".\n");      
        sequence.add(new Entry<C>(key, value));
        size++;
    }
	
	//remove the entry for the given key
	public Entry<C> remove(long key) {
        try {
            int index = search(key);
            Entry<C> temp = sequence.get(index);
            sequence.remove(index);
            size--;
            return temp;

        } catch (NullPointerException e) {
            System.out.println("Key " + key + " does not exist");
            return null;
        }
    }
	
	public C getValues(long key) {
        try {
            int index = search(key);
            return sequence.get(index).getValue();

        } catch (NoSuchElementException e) {
            System.out.println("Key " + key + " does not exist");
        }

        return null;
    }
	
	public long nextKey(long key) {
        try {
            int index = search(key);
            if(sequence.get(index+1) != null) {
                return sequence.get(index+1).getKey();
            } else {
                throw new NoSuchElementException();
            }

        } catch (NoSuchElementException e) {
            System.out.println("Next key of " + key + " does not exist.");
        }
        return 0;
    }
	
	public long prevKey(long key) {
        try {
            int index = search(key);
            if(sequence.get(index-1) != null) {
                return sequence.get(index-1).getKey();
            } else {
                throw new NoSuchElementException();
            }

        } catch (NoSuchElementException e) {
            System.out.println("Prev key of " + key + " does not exist.");
        }
        return 0;
    }
	
	public int search(long key) throws NoSuchElementException {
        for (int i = 0; i<size; ++i) {
            if(sequence.get(i).getKey() == key) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }
	
	//return all keys in IntelligentSIDC as a sorted sequence;
	public Long[] allKeys() {
		Long[] result = new Long[super.getSize()];
        int ctr = 0;
        for(Entry<C> each : sequence) {
            result[ctr] = each.getKey();
            ctr++;
        }
        insertionSort(result);
        return result;
    }
	
	//sorting the sequence using insertion Sort
	public void insertionSort(Long[] keys) {
        int n = keys.length;
        for(int i = 1; i< n; ++i) {
            long current = keys[i];
            int j = i-1;

            while(j >= 0 && current < keys[j]) {
                keys[j+1] = keys[j];
                j--;

            }
            keys[j+1] = current;
        }
    }
	
	public HashTable<C> restructure() {
		HashTable<C> table = new HashTable<>(this.getSize());
        System.out.println("Restructuring data to HashTable");

        for(Entry<C> each : sequence) {
           table.add(each.getKey(), each.getValue());
        }
  
        table.setSIDCThreshold(this.getSIDCThreshold());
        return table;
    }

}//end of class Sequence
