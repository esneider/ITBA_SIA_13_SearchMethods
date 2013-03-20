package tp1.engine;

import java.util.ArrayList;
import java.util.List;

import tp1.problem.Problem;
import tp1.problem.State;
import tp1.rule.Rule;
import tp1.strategy.Strategy;

public class Engine {

    private List<Node> closed = new ArrayList<Node>();

    private Strategy strategy;

    public void engine(Problem problem) {

        strategy = problem.getStrategy();

        strategy.addNode(new Node(problem.getInitialState()));

        while (true) {

            Node node = strategy.popNode();

            if (node == null) {
                System.out.println("perdimo loco!");
                break;
            }

            if (visited(node)) {
                continue;
            }

            closed.add(node);

            if (problem.isGoalState(node.getState())) {
                System.out.println("ganamo vieja!");
                break;
            }

            explode(node);
        }
    }

    private void explode(Node node) {

        for (Rule rule : strategy.getRules()) {

            State newState = rule.eval(node.getState());

            if (newState == null) {
                continue;
            }

            Node newNode = new Node(node, newState, node.getCost() + rule.getCost());

            if (!visited(newNode)) {
                strategy.addNode(newNode);
            }
        }
    }

    private boolean visited(Node node) {

        for (Node other : closed) {
            if (node.getCost() > other.getCost() && node.equals(other)) {
                return true;
            }
        }

        return false;
    }
}
