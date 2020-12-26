import java.util.Scanner;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

public class MagicBoardGame_V2 {

	static final int goalVal= 0;
	static int goalRow= 0;
	static int goalCol= 0;

	Queue<int []> q = new LinkedList<int []>();
    private int[][] board;
    private boolean[][][] visited;
    
    int size;
    int row; 
    int col;
    
    //constructor
    MagicBoardGame_V2(int size, int row, int col){
    	
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

    //resetting visited
    public void resetVisited() {
    	for (int i=0; i<board.length; i++) {
    		for (int j=0; j< board[i].length; j++) {
    			for (int k=0; k<4; k++) {
    				visited[i][j][k]= false;
    			}
    		}
    	}
    }
    
    //filling the MagicBoard with random numbers to start the game
  	private void prepareBoard(int[][] board, boolean[][][] visited, int size) {
  		Random rand = new Random();
  		boolean oneZero = false;
  		int num= 0;
  		
  		for (int i=0; i<board.length; i++) {
  			for (int j=0; j< board[i].length; j++) {
  		
  				//if one of the corners then generate number between 1 and d-1
  				if ((i==0 & j==0) || (i==0 & j==4) || (i==4 & j==0) || (i==0 & j==0)) {
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
  		System.out.print("\n\n");
  	}//end of printBoard
  
	//solving based on queues	
  	private boolean iterativeSolve(MagicBoardGame_V2 magicBoard, int startRow, int startCol) {
		int i = startRow;
	    int j = startCol;
	    setRow(i);
	    setCol(j);
	    int value = getMove(row, col);
	    int direction = 0; 
	    int counter = 0;
	    boolean ok = false;
	    	    
	    int [] myQueue = {i, j, value, direction};
	    q.add(myQueue);
	    
		try {
			while(q.peek() != null) {
				myQueue = q.remove();
				value = getMove(myQueue[0], myQueue[1]);
				direction = myQueue[3];
				if (direction >= 4) 
					return false;

				if(visited[row][col][myQueue[3]]) {
					if (counter >= 2) {
						return false;
					}
					else
						counter++;
						direction++;
				}
				visited[row][col][myQueue[3]] = true;
				
				if (value == 0) {
					return true;
				}					
			    switch(direction){
			    	//down
		    		case 0: 
		    			i = row; j= col;
		    			if (i + value >= magicBoard.board.length) {
		    				//System.out.println(" Cannot move down");
		    				direction++;
		    				int [] newQueue = {row, col, getMove(magicBoard.row, magicBoard.col), direction};
		    				q.add(newQueue);
		    			}
		    			else {
		    				System.out.print("- Moving down by "+ getMove(magicBoard.row, magicBoard.col));
		    				magicBoard.setRow(i + value);
		    				direction = 0;
		    				int [] newQueue = {row, col, getMove(magicBoard.row, magicBoard.col), direction};
		    				q.add(newQueue);
		    				System.out.print(" at ("+ magicBoard.row +", " + magicBoard.col +")");
		    				System.out.println(" and now we're at "+ getMove(magicBoard.row, magicBoard.col));
		    				ok = true;
		    			}
		    			break;   
		    		//right
		    		case 1: 
		    			i = row; j= col;
		    			if (j + value >= magicBoard.board.length) {
		    				//System.out.println(" Cannot move right");
		    				direction++;
		    				int [] newQueue = {row, col, getMove(magicBoard.row, magicBoard.col), direction};
		    				q.add(newQueue);
		    			}
		    			else {
		    				System.out.print("- Moving right by "+ getMove(magicBoard.row, magicBoard.col));
		    				magicBoard.setCol(j + value);
		    				direction = 0;
		    				int [] newQueue = {row, col, getMove(magicBoard.row, magicBoard.col), direction};
		    				q.add(newQueue);
		    				System.out.print(" at ("+ magicBoard.row +", " + magicBoard.col +")");
		        			System.out.println(" and now we're at "+ getMove(magicBoard.row, magicBoard.col));
		        			ok = true;
		    			}
		    			break;
		    		//up
		    		case 2: 
		    			i = row; j= col;
		    			
		    			if (i - value < 0) {
		    				//System.out.println(" Cannot move up");
		    				direction++;
		    				int [] newQueue = {row, col, getMove(magicBoard.row, magicBoard.col), direction};
		    				q.add(newQueue);
		    			}
		    			else {
		    				System.out.print("- Moving up by "+ getMove(magicBoard.row, magicBoard.col));
		    				magicBoard.setRow(i - value);
		    				direction = 0;
		    				int [] newQueue = {row, col, getMove(magicBoard.row, magicBoard.col), direction};
		    				q.add(newQueue);
		    				System.out.print(" at ("+ magicBoard.row +", " + magicBoard.col +")");
		    				System.out.println(" and now we're at "+ getMove(magicBoard.row, magicBoard.col));
		    				ok = true;
		    			}
		    			break;
		    		//left
		    		case 3: 
		    			i = row; j= col;
		    			if (j - value < 0) {
		    				//System.out.println(" Cannot move left");
		    				direction++;
		    				int [] newQueue = {row, col, getMove(magicBoard.row, magicBoard.col), direction};
		    				q.add(newQueue);
		    			}
		    			else {
		    				System.out.print("- Moving left by "+ getMove(magicBoard.row, magicBoard.col));
		    				magicBoard.setCol(j - value);
		    				direction = 0;
		    				int [] newQueue = {row, col, magicBoard.getMove(magicBoard.row, magicBoard.col), direction};
		    				q.add(newQueue);
		    				System.out.print(" at ("+ magicBoard.row +", " + magicBoard.col +")");
		       				System.out.println(" and now we're at "+ getMove(magicBoard.row, magicBoard.col));
		       				ok = true;
		    			}
		    			break;
		    	}//end of switch    
			}//end of while
		}catch(Exception e) {
			e.printStackTrace();
			
		}//end of try
		
		return ok;
	}// end of iterativeSolve  	 	
	
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

		int startX= 0;
		int startY= 0;      
		MagicBoardGame_V2 magicBoard = new MagicBoardGame_V2(size, startX, startY);
		
		System.out.println("\nGoal square is at position: ("+ goalRow +", "+ goalCol+")");
		System.out.println("We're at position: ("+ startX +", "+ startY+")");
		
		printBoard(magicBoard.board);
		System.out.println("Solving using iterativeSolve:");
		boolean solveable = false;
		boolean finish = false;
		boolean t = false;
		int counter = 0;
		do {
			counter++;
			t = magicBoard.iterativeSolve(magicBoard,0,0);
			if (t) {
				solveable = true;
				finish = true;

			} else {
				System.out.println("Trying to solve from another corner..");
				magicBoard.resetVisited();
				counter++;
				t = magicBoard.iterativeSolve(magicBoard,0,4);
			}
			if (t) {
				solveable = true;
				finish = true;

			} else {
				System.out.println("Trying to solve from another corner..");	
				magicBoard.resetVisited();
				counter++;
				t = magicBoard.iterativeSolve(magicBoard,4,0);
			}
			if (t) {
				solveable = true;
				finish = true;
	
			} else {
				System.out.println("Trying to solve from another corner..");	
				magicBoard.resetVisited();
				counter++;
				t = magicBoard.iterativeSolve(magicBoard,4,4);
			}
			if(counter == 4) {
				break;
			}
		
		}while (finish != true);
		
		if(solveable)
			System.out.println("\nThis MagicBoard is solved");
		else
			System.out.println("\nThis MagicBoard cannot be solved..");
	
		Scan.close();
	}//end of main
}//end of class
