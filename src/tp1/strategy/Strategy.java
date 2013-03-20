package tp1.strategy;

import java.util.List;

import tp1.engine.Node;
import tp1.rule.Rule;

public interface Strategy {

    public String getName();

    public List<Rule> getRules();

    public void addNode(Node node);

    public Node popNode();
}
