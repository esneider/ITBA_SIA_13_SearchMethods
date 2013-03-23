package tp1.strategy.uninformed;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import tp1.engine.Node;
import tp1.problem.Game;
import tp1.problem.State;

public class BFSStrategy extends UninformedStrategy {

	private Set<State> openedHash = new HashSet<State>();
	private Queue<Node> opened = new LinkedList<Node>();
	
	public BFSStrategy(Game game) {
		
		super(game);
		opened.add(null);
	}

	@Override
	public String getName() {
		
		return "BFS Strategy";
	}

	@Override
	public void addOpenedNode(Node node) {

		State state = node.getState();

		if (!openedHash.contains(state)) {
		
			opened.add(node);
			openedHash.add(state);
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
		
		openedHash.clear();
		opened.add(null);
		
		return opened.poll();
	}
}
