package pt.isec.pa.tinypac.model.data.mob;

import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;

public class Blinky extends Element {

    public static final char SYMBOL = 'B';

    public Blinky(Level level){
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
