package tp1.engine;

import tp1.problem.State;

public class Node {

    private State state;
    private int cost = 0;
    private Node parent = null;

    public Node(State state) {

        this.state = state;
    }

    public Node(State state, int cost) {

        this(state);
        this.cost = cost;
    }

    public Node(Node parent, State state, int cost) {

        this(state, cost);
        this.parent = parent;
    }

    public void setParent(Node parent) {

        this.parent = parent;
    }

    public State getState() {

        return state;
    }

    public Node getParent() {

        return parent;
    }

    public int getCost() {

        return cost;
    }

    @Override
    public String toString() {

        return "" + state;
    }

    public String getSolution() {

        if (parent == null) {
            return "" + state;
        }

        return parent.getSolution() + "-----\n" + state;
    }
}
