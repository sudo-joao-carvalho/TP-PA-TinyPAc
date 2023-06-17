package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.GameData;

import java.io.Serializable;

public class FoodBall extends Cell implements Serializable {

    public static final char SYMBOL = 'o';

    public FoodBall(GameData gameData){
        super(gameData);
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }
}
