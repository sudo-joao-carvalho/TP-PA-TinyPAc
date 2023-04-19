package pt.isec.pa.tinypac.model.data.cell;

public class PowerBall extends Cell{

    public PowerBall(int powerBallCordY, int powerBallCordX){
        super(powerBallCordY, powerBallCordX);
    }

    @Override
    public char getSymbol(){
        return 'O';
    }
}
