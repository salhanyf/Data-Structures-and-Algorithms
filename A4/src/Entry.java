
public class Entry<C> implements Comparable<Entry> {
	private long key;
	private C value;
	
	//constructor
	public Entry(long key, C value) {
		this.key = key;
		this.value = value;
	}
	
	//getters and setters
	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public C getValue() {
		return value;
	}

	public void setValue(C value) {
		this.value = value;
	}

	//compare method 
	@Override
	public int compareTo(Entry o) {
        return Long.compare(this.getKey(), o.getKey());
    }

}//end of class Entry