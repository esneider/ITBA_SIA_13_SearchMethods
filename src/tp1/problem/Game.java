package tp1.problem;

public class Game {

    public static final int MINE = -1;

    // (x,y) coordinates
    private int board[][];
    private int mines = 0;

    public Game(int board[][]) {

        this.board = board;
        countMines();
    }

    public int getHeight() {

        return board[0].length;
    }

    public int getWidth() {

        return board.length;
    }

    public int getMineCount() {

        return mines;
    }

    public int getPosition(int x, int y) {

        return board[x][y];
    }

    protected void countMines() {

        for (int[] col: board) {
            for (int pos: col) {
                if (pos == MINE) {
                    mines++;
                }
            }
        }
    }
}
