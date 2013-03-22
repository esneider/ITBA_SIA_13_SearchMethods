package tp1.strategy.uninformed;

import java.util.Stack;

import tp1.engine.Node;
import tp1.problem.Game;

public class DFSStrategy extends UninformedStrategy {

	Stack<Node> opened = new Stack<Node>();
	
	public DFSStrategy(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "DFS Strategy";
	}

	@Override
	public void addOpenedNode(Node node) {
		
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
	public void addClosedNode(Node node) {
		//Since DFS hasn't got repeated states we do nothing

	}

	@Override
	public boolean isClosedNode(Node newNode) {
		//Since DFS hasn't got repeated states...
		return false;
	}

}
