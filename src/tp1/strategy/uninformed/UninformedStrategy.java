package tp1.strategy.uninformed;

import java.util.ArrayList;
import java.util.List;

import tp1.problem.Game;
import tp1.rule.Rule;
import tp1.rule.UniformOpenCellRule;
import tp1.strategy.Strategy;

public abstract class UninformedStrategy implements Strategy {

    private List<Rule> rules = new ArrayList<Rule>();

    public UninformedStrategy(Game game) {

        for (int x = 0; x < game.getWidth(); x++) {
            for (int y = 0; y < game.getHeight(); y++) {
                rules.add(new UniformOpenCellRule(game, x, y));
            }
        }
    }

    @Override
    public List<Rule> getRules() {

        return rules;
    }

}
