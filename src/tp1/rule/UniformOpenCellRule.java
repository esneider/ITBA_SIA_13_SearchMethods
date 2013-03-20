package tp1.rule;

import tp1.problem.Cell;
import tp1.problem.Game;
import tp1.problem.State;

public class UniformOpenCellRule implements Rule {

    private int valAtXY;
    private int x;
    private int y;

    public UniformOpenCellRule(Game game, int x, int y) {

        this.valAtXY = game.getPosition(x, y);
        this.x = x;
        this.y = y;
    }

    @Override
    public int getCost() {

        return 1;
    }

    @Override
    public String toString() {

        return "Uniform open cell rule at (" + x + ", " + y + ")";
    }

    @Override
    public State eval(State state) {

        if (valAtXY == Game.MINE) {

            return null;
        }

        if (state.getCell(x, y) != Cell.CLOSED) {

            return null;
        }

        Cell board[][] = state.getBoard();

        board[x][y] = Cell.OPENED;

        return new State(board);
    }
}
