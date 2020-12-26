import java.util.Scanner;
import java.util.Random;

public class MagicBoardGame_V1 {

	static final int goalVal= 0;
	static int goalRow= 0;
	static int goalCol= 0;

    private int[][] board;
    private boolean[][][] visited;

    private int size;
    private int row; 
    private int col;
    
    //constructor
    MagicBoardGame_V1(int size, int row, int col){
    	
    	this.size = size;
    	this.row = row;
        this.col = col;
        
        board = new int[size][size];
        visited = new boolean [size][size][4];
       
		prepareBoard(board, visited, size);
    }
    //get the value of the square we're at
    public int getMove(int i, int j) {
		return board[i][j];
	}

	//getters and setters
    public int getRow() {
        return this.row;
    }

    public void setRow(int i) {
        this.row = i;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int j) {
        this.col = j;
    }
	
    //filling the MagicBoard with random numbers to start the game
  	private static void prepareBoard(int[][] board, boolean[][][] visited, int size) {
  		Random rand = new Random();
  		boolean oneZero = false;
  		int num= 0;
  		
  		for (int i=0; i<board.length; i++) {
  			for (int j=0; j< board[i].length; j++) {
  		
  				//if start position then generate number between 1 and d-1
  				if (i==0 & j==0) {
  					num = rand.nextInt(size - 1) + 1;
  					board[i][j] = num;
  				}
  				//else generate number between 0 and d-1
  				else {
  					num = rand.nextInt(size);
  					//if when zero has been generated
  					if (num == 0 & !oneZero) {
  						board[i][j] = num;
  						goalRow = i;
  						goalCol = j;
  						oneZero = true;
  					}
  					else {
  						num = rand.nextInt(size - 1)+ (1);
  						board[i][j] = num;
  					}
  				}
  				for (int k=0; k<4; k++) {
  					visited[i][j][k]= false;
  				}
  			}//end of first loop
  		}//end of second loop	
  	}//end of prepareBoard

  	//printing out the board 
  	private static void printBoard(int[][] board) {
  		int boardSize = board.length;
  		System.out.println("\nYour Magic Board is:");
  		System.out.print(" ");
  		for (int i=0; i<(boardSize)*4-1; i++) {
  			System.out.print("-");
  		}
  		System.out.print("\n");
  		for (int i=0; i<board.length; i++) {
  			System.out.print("| ");
  			for (int j=0; j< board[i].length; j++) {
  				
  				System.out.print(board[i][j]);
  				if (j == board[i].length-1) {
  					break;
  				}
  				else
  					System.out.print("   ");
  			}
  			System.out.println(" |");
  			
  			if (i == board.length-1) {
  				break;
  			}
  			else
  				System.out.println("\n");
  		}
  		System.out.print(" ");
  		for (int i=0; i<=boardSize; i++) {
  			System.out.print("---");
  		}
  		System.out.print("\n");		
  	}//end of printBoard
	
  	//solving based on recursion
	public boolean recursionSolve(MagicBoardGame_V1 magicBoard, int row, int col){		
        int value = magicBoard.getMove(magicBoard.getRow(), magicBoard.getCol());

        //base case
        if(value == 0)
            return true;
        
        //recursive call
        for (int direction = 0; direction < 4; direction++) {
            
            if(magicBoard.move(magicBoard, value, direction)) {
            	if(recursionSolve(magicBoard, magicBoard.getRow(), magicBoard.getCol()))
                    return true;
            }   
            
        }//end of for
        return false;
    }//end of RecursionSolve
	
	//moving method
	public boolean move(MagicBoardGame_V1 magicBoard, int value, int direction){
		int i = magicBoard.getRow();
	    int j = magicBoard.getCol();
	    int temp = 0;
	    boolean ok = false;
	    
	    if(visited[i][j][direction]) {
	    	return false;
	    }	    
	    visited[i][j][direction] = true;
	   
	    switch(direction){
	    	//down
	    	case 0: 
	    		temp = i + value;
	    		if (temp >= board.length) {
	    			//System.out.println(" Cannot move down");
	    		}
	    		else {
	    			System.out.print("- Moving down by "+ getMove(row, col));
	    			setRow(temp);
	    			System.out.print(" at ("+ row +", " + col +")");
	    			System.out.println(" and now we're at "+ getMove(row, col));
	    			ok = true;
	    		}
	            break;   
	        //right    
	      	case 1: 
	        	temp = j + value;
	        	if (temp >= board.length) {
	    			//System.out.println(" Cannot move right");
	    		}
	        	else {
	        		System.out.print("- Moving right by "+ getMove(row, col));
	        		setCol(temp);
	        		System.out.print(" at ("+ row +", " + col +")");
	        		System.out.println(" and now we're at "+ getMove(row, col));
	        		ok = true;
	        	}
	        	break;
	        //up	
	    	case 2: 
	    		temp = i - value;
	    		if (temp < 0) {
	    			//System.out.println(" Cannot move up");
	    		}
	    		else {
	    			System.out.print("- Moving up by "+ getMove(row, col));
	    			setRow(temp);
	    			System.out.print(" at ("+ row +", " + col +")");
	    			System.out.println(" and now we're at "+ getMove(row, col));
	    			ok = true;
	    		}
	    		break;
	    	//left
	       	case 3: 
	       		temp = j - value;
	       		if (temp < 0) {
	       			//System.out.println(" Cannot move left");
	       		}
	       		else {
	       			System.out.print("- Moving left by "+ getMove(row, col));
	       			setCol(temp);
	       			System.out.print(" at ("+ row +", " + col +")");
	    			System.out.println(" and now we're at "+ getMove(row, col));
	    			ok = true;
	       		}
	       		break;
	    }//end of switch
	        return ok;        
	}//end of move

	public static void main(String[] args) {
		//declaration of the scanner object
		Scanner Scan = new Scanner(System.in);	
		
		System.out.println("Welcome to MagicBoard");
		System.out.println("---------------------");
		System.out.println();
		
		System.out.print("Please enter the number of squares: ");
		int input = Scan.nextInt();
		int size = input;
		while (size < 5 || size > 20) {
			System.out.println("Number of squares should be between 5 and 20");
			System.out.print("Please enter the number of squares: ");
			input = Scan.nextInt();
			size = input;
		}
		
		//starting from corner (0, 0)
		int startX= 0;
		int startY= 0;      
		MagicBoardGame_V1 magicBoard = new MagicBoardGame_V1(size, startX, startY);
		
		System.out.println("\nGoal square is at position: ("+ goalRow +", "+ goalCol+")");
		System.out.println("We're at position: ("+ startX +", "+ startY+")");
		
		printBoard(magicBoard.board);
		
		System.out.println("\nSolving using RecursionSolve:");
		
		if(magicBoard.recursionSolve(magicBoard, startX, startX))
			System.out.println("\nThis MagicBoard is solved");
		else
			System.out.println("\nThis MagicBoard cannot be solved..");
		
	
		Scan.close();
	}//end of main
}//end of class
