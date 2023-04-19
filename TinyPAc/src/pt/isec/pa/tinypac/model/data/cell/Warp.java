package pt.isec.pa.tinypac.model.data.cell;

public class Warp extends Cell{

    public Warp(int warpCordY, int warpCordX){
        super(warpCordY, warpCordX);
    }

    @Override
    public char getSymbol(){
        return 'W';
    }
}
