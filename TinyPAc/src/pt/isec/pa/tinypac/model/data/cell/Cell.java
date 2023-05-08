package pt.isec.pa.tinypac.model.data.cell;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Level;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;

public class Cell extends Element {

    private static final char SYMBOL = 'x';

    public Cell(Level level){
        super(level); // depois na herança cada celula vai ter um type diferente
    }

    @Override
    public void evolve(KeyType key) {

    }

    @Override
    public char getSymbol(){ //default
        return SYMBOL;
    }

    /*@Override
    public void evolve(){
    }*/
}
