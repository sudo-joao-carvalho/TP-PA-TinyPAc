package pt.isec.pa.tinypac.model.data.cell;

public class FoodBall extends Cell{

    public FoodBall(int foodBallCordY, int foodBallCordX){
        super(foodBallCordY, foodBallCordX);
    }

    @Override
    public char getSymbol(){
        return 'o';
    }
}
