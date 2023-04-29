package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.Level;

public class FoodBall extends Cell{

    public static final char SYMBOL = 'o';

    public FoodBall(Level level){
        super(level);
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }
}
