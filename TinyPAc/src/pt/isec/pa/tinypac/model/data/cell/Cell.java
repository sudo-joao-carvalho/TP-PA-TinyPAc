package pt.isec.pa.tinypac.model.data.cell;

import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Level;

public class Cell extends Element {

    private static final char SYMBOL = 'x';

    public Cell(Level level){
        super(level); // depois na herança cada celula vai ter um type diferente
    }

    @Override
    public char getSymbol(){ //default
        return SYMBOL;
    }

    /*@Override
    public void evolve(){
    }*/
}
