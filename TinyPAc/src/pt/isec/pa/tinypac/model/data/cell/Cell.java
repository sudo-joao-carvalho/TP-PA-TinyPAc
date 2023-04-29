package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Level;

public class Cell extends Element {

    /*private int cordX;

    private int cordY;*/

    private static final char SYMBOL = 'x';

    public Cell(Level level/*, int cordX, int cordY*/){
        super(level/*, cordX, cordY*/); // depois na herança cada celula vai ter um type diferente
    }

    /*public int getCordX() {return this.cordX;}

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public int getCordY(){return this.cordY;}

    public void setCordY(int cordY){
        this.cordY = cordY;
    }*/

    @Override
    public char getSymbol(){ //default
        return SYMBOL;
    }

    @Override
    public void evolve(){

    }
}
