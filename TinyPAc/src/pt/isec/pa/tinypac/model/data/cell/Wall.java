package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.GameData;

public class Wall extends Cell{

    public static final char SYMBOL = 'x';
    /*public Wall(int wallCordY, int wallCordX){
        super(wallCordY, wallCordX);
    }*/

    public Wall(GameData gameData){
        super(gameData);
    }
    @Override
    public char getSymbol(){
        return SYMBOL;
    }
}
