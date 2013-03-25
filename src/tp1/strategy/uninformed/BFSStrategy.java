package tp1.strategy.uninformed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import tp1.engine.Node;
import tp1.problem.Game;
import tp1.problem.State;

public class BFSStrategy extends UninformedStrategy {

	private Queue<Node> opened = new LinkedList<Node>();
	private Set<State> visited = new HashSet<State>();

	private int addedNodes = 0;
	private int nonRepeatedStates = 0;
	private List<Integer> nodesPerLevel = new ArrayList<Integer>();

	public BFSStrategy(Game game) {

		super(game);
		opened.add(null);
	}

	@Override
	public String getName() {

		return "Breadth First Search";
	}

	@Override
	public void addOpenedNode(Node node) {

		addedNodes++;
		State state = node.getState();

		if (!visited.contains(state)) {

			nonRepeatedStates++;
			opened.add(node);
			visited.add(state);
		}
	}

	@Override
	public Node popOpenedNode() {

		if (opened.isEmpty()) {
			return null;
		}

		Node node = opened.poll();

		if (node != null) {
			return node;
		}

		nodesPerLevel.add(visited.size());
		visited.clear();
		opened.add(null);

		return opened.poll();
	}

	@Override
	public void printInfo() {

		System.out.println("Estados generados: " + nonRepeatedStates);
		System.out.println("Nodos con estados repetidos: " + (addedNodes - nonRepeatedStates));
		System.out.println("Nœmero de nodos frontera al terminar la ejecuci—n: " + (opened.size() - 1));
		System.out.println("Nœmero de nodos expandidos: " + (nonRepeatedStates - opened.size()));
		System.out.println("Nœmero de nodos expandidos por nivel: " + Arrays.toString(nodesPerLevel.toArray()));
	}
}
