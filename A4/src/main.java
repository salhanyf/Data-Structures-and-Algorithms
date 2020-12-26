import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class main {
	private final static int IDLENGTH = 8;
	protected static HashTable<Student> table;
	protected static Sequence<Student> sequence;
	protected static Scanner scan = new Scanner(System.in);

    protected static boolean useSequence;
    protected static boolean useTable;
    protected static boolean isLarge;
	
	//setting the size of the list
	public static void setThreshold(int threshold) {
        if(useTable) {
            table.setSIDCThreshold(threshold);
        }

        if(useSequence) {
            sequence.setSIDCThreshold(threshold);
        }
        chooseADT();
    }
	public static int getSize() {
        return (useSequence) ? sequence.getSize() : table.getSize();
    }
	
	//randomly generates new non-existing key of 8 digits
	public static void generate(int n) {
		for (int i = 0; i < n; i++) {
			String name = "name "+(i+1);
			Student temp = new Student(name);
			long key = generateRandomID();
			
			if(useSequence) {
				System.out.println("Adding to Sequence");
                sequence.add(key, temp);
            }
            if(useTable) {
            	System.out.println("Adding to Table");
                table.add(key, temp);
            }
		}
		System.out.print("Generating Successfully");
	}
	//Generates a random number with size 8 digits
	public static int generateRandomID() {
		int m = (int) Math.pow(10, IDLENGTH - 1);
      	return m + new Random().nextInt(9 * m);
	}
	
	//return all keys in IntelligentSIDC as a sorted sequence;
	public static void allKeys() {
		PrintWriter pw = null;
		String output = "log.txt";
		File log = new File(output);
        try {
            pw = new PrintWriter(new FileOutputStream(log));
        } catch (FileNotFoundException e) {
            System.out.println("Permission to write is not granted");
            System.exit(0);
        }
        
        System.out.println("\nSorting...");
        Long[] keys = new Long[getSize()];
        if(useSequence) {
            keys = sequence.allKeys();
        }

        if(useTable) {
            keys = table.allKeys();
        }
        System.out.println("");
        pw.println("----- Sorted keys -----");
        if(getSize() == 0) {
            pw.println("Empty");
        } else {
            for(Long each : keys) {
            	System.out.println(each);
                pw.println(each);
            }
        }
        System.out.println("Sorting done");
        pw.println("------------------------");
        pw.flush();
        System.out.println("A log file has been generated.");
    }

	//add an entry for the given key and value;
	public static void add(long key){
		//creating new student
		Student temp = new Student();
		
		//Adding to the structure
        if(useSequence) {
            System.out.println("\nAdding "+ key +" to Sequence");
            sequence.add(key, temp);
        }
        if(useTable) {
            System.out.println("\nAdding "+ key +" to Table");
            table.add(key, temp);
        }
        System.out.println("Added Successfully");
        chooseADT();
    }

	//remove the entry for the given key;
    public static void remove(long key) {
        if(useSequence) {
        	System.out.println("\nRemoving "+ key +" from Sequence");
            sequence.remove(key);
        }

        if(useTable) {
        	System.out.println("\nRemoving "+ key +" from Table");
            table.remove(key);
        }

        System.out.println("Removed Successfully");
        chooseADT();
    }
    
    //return the values of the given key
    public static void getValues(long key) {
        Student temp = (useSequence) ? sequence.getValues(key) : table.getValues(key);

        if(temp != null) {
            System.out.println(temp);
        }
    }
    
    //return the key for the successor of key
    public static void nextKey(long key) {
        long nextKey = (useSequence) ? sequence.nextKey(key) : table.nextKey(key);

        if(nextKey != 0) {
            System.out.println("Next key of " + key + " is: " + nextKey);
        }
    }
    
    //return the key for the predecessor of key
    public static void prevKey(long key){
        long prevKey = (useSequence) ? sequence.prevKey(key) : table.prevKey(key);

        if(prevKey != 0) {
            System.out.println("Prev key of " + key + " is: " + prevKey);
        }
    }
    
    //returns the number of keys that are within the specified range key1 and key2.
    public void rangeKey(long key1, long key2) {
    	if(useSequence) {
        	System.out.println("\ngetting keys from Sequence");
        	for(long i = key1; i <= key2 ; i++) {
        		sequence.getValues(i);
        	}
        }

        if(useTable) {
        	System.out.println("\ngetting keys from Table");
        	for(long i = key1; i <= key2 ; i++) {
        		table.getValues(i);
        	}
        }
    }
    
	//determining which ADT to use 
	public static void chooseADT() {
        if(useSequence) {
            isLarge = sequence.getSize() > sequence.getSIDCThreshold();
        }

        if(useTable) {
            isLarge = table.getSize() > table.getSIDCThreshold();
        }

        if(isLarge && useSequence) {
            table = sequence.restructure();
            useSequence = false;
            useTable = true;
            sequence = null;
        }

        if(!isLarge && useTable) {
            sequence = table.restructure();
            useSequence = true;
            useTable = false;
            table = null;
        }
    }
	
	//reading from the provided test files
	public static void readFile(int choice) {
        String path1 = "./CSTA_test_files/CSTA_test_file1.txt";
        String path2 = "./CSTA_test_files/CSTA_test_file2.txt";
        String path3 = "./CSTA_test_files/CSTA_test_file3.txt";
        File target = null;
        
        if (choice == 1) {
        	target= new File(path1);
        }
        else if(choice == 2) {
        	target = new File(path2);
        }
        else if(choice == 3) {
        	target = new File(path3);
        }
        Scanner input = null;
        try {
        	System.out.println("\nOpenning file...\n");
            input = new Scanner(new FileInputStream(target));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file");
            System.exit(0);
        }
        int counter = 0;
        while (input.hasNextLine()) {
            long key = input.nextLong();
            counter++;
            //System.out.println(key);
            Student temp = new Student("name "+counter);
            if(useSequence) {
                sequence.add(key,temp);
            }

            if(useTable) {
                table.add(key, temp);
            }
            chooseADT();
        }
    }
	
	public static void main(String[] args) {
		sequence = new Sequence<>();
		useSequence = true;
        useTable = false;
		table = null;
		
		System.out.println("Please choose a file:");
		System.out.println("1. test file 1 (10 000 entries)" +
                "\n2. test file 2 (100 000 entries)" +
                "\n3. test file 3 (1,000,000 entries)");
		int choice = scan.nextInt();
		if (choice == 1 || choice == 2 || choice == 3) {
			readFile(choice);
			
		}
		else {
			System.out.println("Wrong input");
			System.exit(0);
		}
		
		System.out.println("Do you want to create a log file? (Please enter 1 for yes)");
		int answer = scan.nextInt();
		
		if(answer == 1) {
			allKeys();
		}
		long inputKey;
		System.out.println("\nEnter key to add: ");
		inputKey = scan.nextLong();
		if (Long.toString(inputKey).length() != 8) {
			System.out.println("invalid key");
		}
		else {
			add(inputKey);
			allKeys();
		}
		
		System.out.println("\nEnter key to remove: ");
		inputKey = scan.nextLong();
		if (Long.toString(inputKey).length() != 8) {
			System.out.println("invalid key");
		}
		else {
			remove(inputKey);
			allKeys();
		}
		
		System.out.println("\nEnter key to get value: ");
		inputKey = scan.nextLong();
		if (Long.toString(inputKey).length() != 8) {
			System.out.println("invalid key");
		}
		else {
			getValues(inputKey);
		}
		
		System.out.println("\nEnter key to get nextKey: ");
		inputKey = scan.nextLong();
		if (Long.toString(inputKey).length() != 8) {
			System.out.println("invalid key");
		}
		else {
			nextKey(inputKey);
		}

		System.out.println("\nEnter key to get prevKey: ");
		inputKey = scan.nextLong();
		if (Long.toString(inputKey).length() != 8) {
			System.out.println("invalid key");
		}
		else {
			prevKey(inputKey);
		}
		
		System.out.println("\nEnter new threshold value: ");
		int threshold = scan.nextInt();
		if (threshold <= 0) {
			System.out.println("invalid threshold");
		}
		else {
			setThreshold(threshold);
		}
		
		System.out.println("\nEnter a number to generate: ");
		int number = scan.nextInt();
		if (number <= 0) {
			System.out.println("invalid input");
		}
		else {
			System.out.println("");
			generate(number);
		}
		
	}//end of main
}//end of class
