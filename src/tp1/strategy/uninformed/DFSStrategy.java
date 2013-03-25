package tp1.strategy.uninformed;

import java.util.Stack;

import tp1.engine.Node;
import tp1.problem.Game;

public class DFSStrategy extends UninformedStrategy {

	private Stack<Node> opened = new Stack<Node>();

	private int addedNodes = 0; // addedNodes == states for this DFS

	public DFSStrategy(Game game) {

		super(game);
	}

	@Override
	public String getName() {

		return "Depth First Search";
	}

	@Override
	public void addOpenedNode(Node node) {

		addedNodes++;
		opened.push(node);
	}

	@Override
	public Node popOpenedNode() {

		if (!opened.empty()) {
			return opened.pop();
		}

		return null;
	}

	@Override
	public void printInfo() {

		System.out.println("Estados generados: " + addedNodes);
		System.out.println("Nœmero de nodos frontera al terminar la ejecuci—n: " + opened.size());
		System.out.println("Nœmero de nodos expandidos: " + (addedNodes - opened.size() - 1));
	}
}
