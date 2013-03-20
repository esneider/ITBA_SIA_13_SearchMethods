package tp1.strategy.uninformed;

import java.util.ArrayList;
import java.util.List;

import tp1.problem.Game;
import tp1.rule.Rule;
import tp1.rule.UniformOpenCellRule;
import tp1.strategy.Strategy;

public abstract class UninformedStrategy implements Strategy {

    private Game game;

    public UninformedStrategy(Game game) {

        this.game = game;
    }

    @Override
    public List<Rule> getRules() {

        List<Rule> rules = new ArrayList<Rule>();

        for (int x = 0; x < game.getWidth(); x++) {
            for (int y = 0; y < game.getHeight(); y++) {
                rules.add(new UniformOpenCellRule(game, x, y));
            }
        }

        return rules;
    }

}
