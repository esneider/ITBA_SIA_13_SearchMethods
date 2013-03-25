package tp1.problem;

import java.io.FileNotFoundException;

import tp1.engine.Engine;
import tp1.strategy.Strategy;
import tp1.strategy.uninformed.BFSStrategy;
import tp1.strategy.uninformed.DFSStrategy;
import tp1.strategy.uninformed.IDStrategy;

public class Solver {

	public static void main(String[] args) {

		// parse input method

		int argsPos;
		Game game;

		if (args.length <= 2) {
			printUsage();
			return;
		}
		
		if (args[0].equals("-f")) {

			try {

				game = new Game(args[1]);
				argsPos = 2;

			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
				return;

			} catch (Exception e) {
				System.out.println("Invalid or corrupted file.");
				return;
			}

		} else if (args[0].equals("-r") && args.length >= 5) {

			try {
				
				game = new Game(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
				argsPos = 4;
				
			} catch (NumberFormatException e) {
				printUsage();
				return;
			}
			
		} else {
			printUsage();
			return;
		}

		// parse search method

		Strategy strategy;

		if (args[argsPos].equals("DFS")) {
			
			strategy = new DFSStrategy(game);
			
		} else if (args[argsPos].equals("BFS")) {
			
			strategy = new BFSStrategy(game);
			
		} else if (args[argsPos].equals("ID")) {
			
			strategy = new IDStrategy(game);
			
			if (args.length > argsPos + 1) {
				
				try {

					((IDStrategy)strategy).setStep(Integer.parseInt(args[++argsPos]));

				} catch (NumberFormatException e) {
					printUsage();
					return;
				}
			}
		} else {
			printUsage();
			return;
		}
		
		if (args.length > argsPos + 1) {
			printUsage();
			return;
		}

		new Engine().solve(new Problem(game, strategy));
	}
	
	private static void printUsage() {
		
		System.out.println("Usage:");
		System.out.println("\tsolver -f file strategy");
		System.out.println("\tsolver -r width height mines strategy");
		System.out.println("\nThe first option reads a game board from 'file', the second creates a random");
		System.out.println("game board with the given dimensions and mine count.");
		System.out.println("\nThe 'strategy' has to be one of DFS, BFS or ID. In the case ID is used, a step");
		System.out.println("can be given as an extra argument (the default step is 1).");
		System.out.println("\nFor example the following call will run iterative deepening with a step of 5");
		System.out.println("in a random 10x5 board with 8 mines:");
		System.out.println("\n\tsolver -r 10 5 8 ID 5");
	}
}
