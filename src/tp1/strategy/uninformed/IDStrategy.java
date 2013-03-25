package tp1.strategy.uninformed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import tp1.engine.Node;
import tp1.problem.Game;
import tp1.problem.State;

public class IDStrategy extends UninformedStrategy {

	private int step = 1;
	private Stack<Node> openedDFS = new Stack<Node>();
	private Queue<Node> openedBFS = new LinkedList<Node>();
	private Set<State> visited = new HashSet<State>();

	private int addedNodes = 0;
	private int nonRepeatedStates = 0;
	private List<Integer> nodesPerStepLevels = new ArrayList<Integer>();

	public IDStrategy(Game game) {

		super(game);
		openedBFS.add(null);
	}

	@Override
	public String getName() {

		return "Iterative Deepening con step = " + step;
	}

	@Override
	public void addOpenedNode(Node node) {

		addedNodes++;
		State state = node.getState();

		if (visited.contains(state)) {
			return;
		}

		nonRepeatedStates++;

		visited.add(state);

		if (node.getLevel() % step != step - 1) {
			openedDFS.push(node);
		} else {
			openedBFS.add(node);
		}
	}

	@Override
	public Node popOpenedNode() {

		if (!openedDFS.isEmpty()) {
			return openedDFS.pop();
		}

		if (openedBFS.isEmpty()) {
			return null;
		}

		Node node = openedBFS.poll();

		if (node != null) {
			return node;
		}

		nodesPerStepLevels.add(visited.size());
		visited.clear();
		openedBFS.add(null);

		return openedBFS.poll();
	}

	public void setStep(int step) {

		this.step = step;
	}

	@Override
	public void printInfo() {

		int frontera = openedDFS.size() + openedBFS.size() - 1;

		int expandedInLastLevels = nonRepeatedStates + frontera - 1;

		for (Integer num : nodesPerStepLevels) {
			expandedInLastLevels -= num;
		}

		if (expandedInLastLevels > 0) {
			nodesPerStepLevels.add(expandedInLastLevels);
		}

		System.out.println("Estados generados: " + nonRepeatedStates);
		System.out.println("Nodos con estados repetidos: " + (addedNodes - nonRepeatedStates));
		System.out.println("Nœmero de nodos frontera al terminar la ejecuci—n: " + frontera);
		System.out.println("Nœmero de nodos expandidos: " + (nonRepeatedStates - frontera - 1));
		System.out.println("Nœmero de nodos expandidos cada 'step' niveles: "
				+ Arrays.toString(nodesPerStepLevels.toArray()));
	}
}
