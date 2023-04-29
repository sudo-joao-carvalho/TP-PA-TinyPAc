package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.cell.Wall;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TinyPac extends Element{
    public static final char SYMBOL = 'M';

    public TinyPac(Level level){
        super(level);
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }

    @Override
    public void evolve(){

        Level.Position myPos = level.getPositionOf(this);
        level.addElement(new TinyPac(level), myPos.y(), myPos.x() + 1);
        System.out.println("Novo");
    }
}
