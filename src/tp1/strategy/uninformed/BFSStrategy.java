package tp1.strategy.uninformed;

import tp1.engine.Node;
import tp1.problem.Game;

public class BFSStrategy extends UninformedStrategy {

	public BFSStrategy(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "BFS Strategy";
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

	@Override
	public void addClosedNode(Node node) {
		//Guess what? We are not going to use this :D

	}

	@Override
	public boolean isClosedNode(Node newNode) {
		//Guess what? We are not going to use this :D
		return false;
	}

}
