package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.Level;

public class Warp extends Cell{

    public static final char SYMBOL = 'W';
    public Warp(Level level){
        super(level);
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }
}
