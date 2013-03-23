package tp1.problem;

import java.util.Arrays;



public class State {

	private final int HASH_MOD = 999999937;

    private Cell board[][];
	private int hashCode = 0;

    public State(Cell board[][]) {

        this.board = board;
        
        for(int x = 0; x < board.length; x++) {
        	for(int y = 0; y < board[0].length; y++) {
        		hashCode = (hashCode + hashCodeXY(x, y, board[x][y])) % HASH_MOD;
        	}
        }
    }

    private State(Cell board[][], int hashCode) {

    	this.board = board;
    	this.hashCode = hashCode;
    }
    
    public State getChildFromCellChange(int x, int y, Cell cell) {

    	Cell newBoard[][] = new Cell[board.length][board[0].length];
   
    	for (int px = 0; px < board.length; px++) {
  
    		newBoard[px] = Arrays.copyOf(board[px], board[px].length);
    	}

    	newBoard[x][y] = cell;

    	return new State(newBoard, (hashCode - hashCodeXY(x, y, board[x][y]) + hashCodeXY(x, y, cell)) % HASH_MOD);
    }
    
    public Cell getCell(int x, int y) {

        return board[x][y];
    }

    public int getOpenedCellsCount() {

        int sol = 0;

        for (Cell[] col : board) {
            for (Cell cell : col) {
                if (cell == Cell.OPENED) {
                    sol++;
                }
            }
        }

        return sol;
    }

    private Cell[][] getBoard() {

        return board;
    }

    private int hashCodeXY(int x, int y, Cell cell) {

    	switch(cell) {
    	
			case OPENED: return (47*x + 53*y) % HASH_MOD;
			case CLOSED: return (31*x + 17*y) % HASH_MOD;
			case FLAG:   return (37*x + 19*y) % HASH_MOD;
    	}
    	
    	return 0;
    }
    
    @Override
    public boolean equals(Object other) {

        Cell otherBoard[][] = ((State)other).getBoard();

        for (int x = 0; x < board.length; x++) {
        	if (!Arrays.equals(board[x], otherBoard[x])) {
        		return false;
        	}
        }

        return true;
    }

    @Override
    public String toString() {

        String sol = "";

        for (Cell[] col : board) {
            for (Cell cell : col) {
                sol += cell;
            }
            sol += "\n";
        }

        return sol;
    }
    
    @Override
    public int hashCode() {

    	return hashCode;
    }
}
