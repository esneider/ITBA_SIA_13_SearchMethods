package tp1.problem;


public class State {

    private Cell board[][];

    public State(Cell board[][]) {

        this.board = board;
    }

    public Cell[][] getBoard() {

        return board;
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

    public boolean equals(State other) {

        Cell otherBoard[][] = other.getBoard();

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                if (board[x][y] != otherBoard[x][y]) {
                    return false;
                }
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
}
