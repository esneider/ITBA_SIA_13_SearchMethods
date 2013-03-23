package tp1.strategy.uninformed;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import tp1.engine.Node;
import tp1.problem.Game;
import tp1.problem.State;

public class IDStrategy extends UninformedStrategy {

	int step = 1;
	Stack<Node> openedDFS = new Stack<Node>();
	private Set<State> openedHash = new HashSet<State>();
	private Queue<Node> openedBFS = new LinkedList<Node>();
	
	public IDStrategy(Game game) {
		
		super(game);
		openedBFS.add(null);
	}

	@Override
	public String getName() {
		
		return "ID Strategy";
	}

	@Override
	public void addOpenedNode(Node node) {
		
		if ((node.getLevel() == 0) || (node.getLevel() % this.step == 0)) {
			
			openedDFS.push(node);
		} else {
			
			State state = node.getState();
			
			if (!openedHash.contains(state)) {
				
				openedBFS.add(node);
				openedHash.add(state);
			}
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
		
		openedHash.clear();
		openedBFS.add(null);
		
		return openedBFS.poll();
	}

	public void setStep(int step) {
		
		this.step = step;
	}
}
