package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.Level;

public class Portal extends Cell{

    public static final char SYMBOL = 'Y';
    public Portal(Level level){
        super(level);
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }
}
