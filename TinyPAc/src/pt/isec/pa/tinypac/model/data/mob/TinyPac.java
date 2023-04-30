package pt.isec.pa.tinypac.model.data.mob;

import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.cell.Wall;

public class TinyPac extends Element {
    public static final char SYMBOL = 'M';

    public TinyPac(Level level){
        super(level);
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }

    /*@Override
    public void evolve(){
        move();
    }*/

    @Override
    public boolean move(){
        Level.Position myPos = level.getPositionOf(this);
        level.addElement(new Wall(level), myPos.y(), myPos.x());
        level.addElement(new TinyPac(level), myPos.y(), myPos.x() + 1);
        System.out.println("Novo");
        return true;
    }

    @Override
    public boolean eat(){
        return true;
    }
}
