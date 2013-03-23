package tp1.strategy.uninformed;

import java.util.Stack;

import tp1.engine.Node;
import tp1.problem.Game;

public class IDStrategy extends UninformedStrategy {

	int step = 1;
	Stack<Node> openedDFS = new Stack<Node>();
	
	public IDStrategy(Game game) {
		
		super(game);
	}

	@Override
	public String getName() {
		
		return "ID Strategy";
	}

	@Override
	public void addOpenedNode(Node node) {
		// TODO Auto-generated method stub

	}

	@Override
	public Node popOpenedNode() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStep(int step) {
		
		this.step = step;
	}

	class IDNode {
		
		public Node node;
		public int level;
		
		public IDNode(Node node, int level) {
			
			this.node = node;
			this.level = level;
		}
	}
}
