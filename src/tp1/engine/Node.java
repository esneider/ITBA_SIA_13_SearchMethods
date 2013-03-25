package tp1.engine;

import tp1.problem.State;

public class Node {

	private State state;
	private int cost;
	private Node parent;
	private int level = 0;

	public Node(State state, int cost, Node parent) {

		this.state = state;
		this.cost = cost;
		this.parent = parent;

		if (parent != null) {

			level = parent.getLevel() + 1;
		}
	}

	public int getLevel() {

		return level;
	}

	public State getState() {

		return state;
	}

	public int getCost() {

		return cost;
	}

	public String getSolution() {

		if (parent == null) {
			return state.toString();
		}

		return parent.getSolution() + "-----\n" + state;
	}

	@Override
	public String toString() {

		return state.toString();
	}
}
