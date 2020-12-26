
public class IntelligentSIDC<E> {
	
	protected int SIDCThreshold;
	protected int size;

	//constructor
    public IntelligentSIDC() {
    	this(50000);
    }
    public IntelligentSIDC(int Threshold) {
        this.SIDCThreshold = Threshold;
    }

	//getters and setters
	public int getSIDCThreshold() {
		return SIDCThreshold;
	}
	public void setSIDCThreshold(int Threshold) {
		if (Threshold < 100 || Threshold > 500000) {
			System.out.println("Error: Threshold must be between 100 and 500,000.");
			System.exit(0);
		}
		else
			this.SIDCThreshold = Threshold;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isGraterthan(int size) {
        return (size >= SIDCThreshold);
    }

}//end of class IntelligentSIDC
