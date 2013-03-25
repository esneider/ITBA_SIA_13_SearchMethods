package tp1.problem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import tp1.engine.Engine;
import tp1.strategy.Strategy;
import tp1.strategy.uninformed.BFSStrategy;
import tp1.strategy.uninformed.DFSStrategy;
import tp1.strategy.uninformed.IDStrategy;

public class Solver {

	public static void main(String[] args) {

		int[][] board = null;
		Engine engine = new Engine();
		Problem problem = null;

		if (args.length >= 2) {
			String filename = args[0];

// TODO
			Game game = new Game(board);

			String strategyName = args[1];

			Strategy strategy = null;

			if (strategyName.equals("BFS")) {
				strategy = new BFSStrategy(game);
			} else if (strategyName.equals("DFS")) {
				strategy = new DFSStrategy(game);
			} else if (strategyName.equals("ID")) {
				IDStrategy IDstrategy = new IDStrategy(game);

				if (args.length == 3) {
					int step = Integer.parseInt(args[2]);
					IDstrategy.setStep(step);
				}

				strategy = IDstrategy;
			}

			if (strategy == null) {
				System.out.println("The name of the strategy is not valid. Please use DFS, BFS or ID");
			} else {
				problem = new Problem(game, strategy);

				engine.solve(problem);
			}
		}
	}
}
