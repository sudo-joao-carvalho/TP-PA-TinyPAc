package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.Level;

public class Fruit extends Cell{

    public static final char SYMBOL = 'F';
    public Fruit(Level level){
        super(level);
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }
}
