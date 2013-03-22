package tp1.engine;

import tp1.problem.Problem;
import tp1.problem.State;
import tp1.rule.Rule;
import tp1.strategy.Strategy;

public class Engine {

    public void solve(Problem problem) {

    	Strategy strategy = problem.getStrategy();

        strategy.addOpenedNode(new Node(problem.getInitialState()));
        while (true) {

            Node node = strategy.popOpenedNode();
            if (node == null) {
                System.out.println("perdimo loco!");
                break;
            }

            if (strategy.isClosedNode(node)) {
                continue;
            }

            strategy.addClosedNode(node);

            if (problem.isGoalState(node.getState())) {
                System.out.println("ganamo vieja!");
                System.out.println(node);
                break;
            }

            explode(strategy, node);
        }
    }

    private void explode(Strategy strategy, Node node) {

        for (Rule rule : strategy.getRules()) {
            State newState = rule.eval(node.getState());

            if (newState == null) {
                continue;
            }

            Node newNode = new Node(node, newState, node.getCost() + rule.getCost());

            if (!strategy.isClosedNode(newNode)) {
                strategy.addOpenedNode(newNode);
            }
        }
    }
}
