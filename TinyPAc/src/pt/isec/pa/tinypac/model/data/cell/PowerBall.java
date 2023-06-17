package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.GameData;

import java.io.Serializable;

public class PowerBall extends Cell implements Serializable {

    public static final char SYMBOL = 'O';
    public PowerBall(GameData gameData){
        super(gameData);
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }
}
