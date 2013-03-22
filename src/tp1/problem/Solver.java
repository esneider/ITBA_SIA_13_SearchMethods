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
		
		if (args.length >= 2)
		{
			String filename = args[0];
			
			try {
				
				FileReader in = new FileReader(filename);
				
				BufferedReader br = new BufferedReader(in);
				
				String line = null;
				int height = 0;
				int width = 0;
				String[] dimensions = null;
				try {
					dimensions = br.readLine().split(",");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				height = Integer.parseInt(dimensions[0]);
				width = Integer.parseInt(dimensions[1]);
				
				try {
					line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				board = new int[height][width];
				
				int i = 0;
				
				while (line != null) {
					
					String[] lineNumbers = line.split(",");
					
					int j = 0;
					
					for (String number : lineNumbers) {
						if (number == "*") {
							board[i][j] = Game.MINE;
						} else {
							board[i][j] = Integer.parseInt(number);
						}
						j++;
					}
					
					i++;
					
					try {
						line = br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				try {
					br.close();
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File not found");
			}
			
			Game game = new Game(board);
			
			String strategyName = args[1];
			
			Strategy strategy = null;
			
			if (strategyName == "BFS") {
				strategy = new BFSStrategy(game);
			} else if (strategyName == "DFS") {
				strategy = new DFSStrategy(game);
			} else if (strategyName == "ID") {
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
