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

        for (int k = 0; k < board.length; k++) {
        	for (int j = 0; j < board[0].length; j++) {
    			board[k][j] = Cell.CLOSED;
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
    
    @Override
    public String toString() {

    	return game.toString();
    }
}
