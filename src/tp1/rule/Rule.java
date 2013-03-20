package tp1.rule;

import tp1.problem.State;

public interface Rule {

    public int getCost();

    public State eval(State state);
}
