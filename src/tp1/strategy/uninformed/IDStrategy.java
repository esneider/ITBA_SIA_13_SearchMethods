package tp1.strategy.uninformed;

import tp1.engine.Node;
import tp1.problem.Game;

public class IDStrategy extends UninformedStrategy {

	int step = 1;
	int current = 0;
	
	public IDStrategy(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
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

}
