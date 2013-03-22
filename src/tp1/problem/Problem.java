package tp1.problem;

import tp1.strategy.Strategy;

public class Problem {

    private Strategy strategy;
    private Game game;

    public Problem(Game game, Strategy strategy) {

        this.strategy = strategy;
        this.game = game;
    }

    public State getInitialState() {

        Cell board[][] = new Cell[game.getWidth()][game.getHeight()];

        for (Cell[] col : board) {
            for (Cell cell : col) {
                cell = Cell.CLOSED;
            }
        }

        return new State(board);
    }

    public boolean isGoalState(State state) {

        return state.getOpenedCellsCount() == game.getHeight() * game.getWidth() - game.getMineCount();
    }

    public Strategy getStrategy() {

        return strategy;
    }

    // TODO: add heuristic
}
