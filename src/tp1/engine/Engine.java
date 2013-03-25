package tp1.engine;

import tp1.problem.Problem;
import tp1.problem.State;
import tp1.rule.Rule;
import tp1.strategy.Strategy;

public class Engine {

	public void solve(Problem problem) {

		long startTime = System.currentTimeMillis();

		Strategy strategy = problem.getStrategy();

		strategy.addOpenedNode(new Node(problem.getInitialState(), 0, null));

		while (true) {

			Node node = strategy.popOpenedNode();

			if (node == null) {

				printInfo(problem, node, startTime, false);
				break;
			}

			if (problem.isGoalState(node.getState())) {

				printInfo(problem, node, startTime, true);
				break;
			}

			explode(strategy, node);
		}
	}

	private void explode(Strategy strategy, Node node) {

		for (Rule rule : strategy.getRules()) {

			State newState = rule.eval(node.getState());

			if (newState != null) {

				strategy.addOpenedNode(new Node(newState, node.getCost() + rule.getCost(), node));
			}
		}
	}

	private void printInfo(Problem problem, Node node, long startTime, boolean win) {

		long finishTime = System.currentTimeMillis();

		System.out.println("Para el tablero:");
		System.out.print(problem);

		if (win) {
			System.out.println("Se encontr— la soluci—n ('.' = abierto, '#' = cerrado):");
			System.out.print(node);
		} else {
			// Shouldn't reach this... ever
			System.out.println("No se encontr— ninguna soluci—n");
		}

		System.out.println("Estrategia usada: " + problem.getStrategy().getName());
		System.out.println("Tiempo de ejecuci—n: " + (finishTime - startTime) / (double) 1000 + " s");
		System.out.println("Profundidad de la soluci—n: " + node.getLevel());

		problem.getStrategy().printInfo();
	}
}
