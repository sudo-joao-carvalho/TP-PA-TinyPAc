package pt.isec.pa.tinypac.model.data.cell;

public class Fruit extends Cell{

    public Fruit(int fruitCordY, int fruitCordX){
        super(fruitCordY, fruitCordX);
    }

    @Override
    public char getSymbol(){
        return 'F';
    }
}
