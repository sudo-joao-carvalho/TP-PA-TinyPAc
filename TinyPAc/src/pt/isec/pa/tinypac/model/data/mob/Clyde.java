package pt.isec.pa.tinypac.model.data.mob;

import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;

public class Clyde extends Element {

    public static final char SYMBOL = 'C';

    public Clyde(Level level) {
        super(level);
    }

    /*@Override
    public void evolve() {

    }*/

    @Override
    public char getSymbol() {
        return SYMBOL;
    }
}