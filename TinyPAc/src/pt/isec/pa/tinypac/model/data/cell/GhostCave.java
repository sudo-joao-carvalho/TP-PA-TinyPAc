package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.Level;

public class GhostCave extends Cell{

    public static final char SYMBOL = 'y';
    public GhostCave(Level level){
        super(level);
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }
}
