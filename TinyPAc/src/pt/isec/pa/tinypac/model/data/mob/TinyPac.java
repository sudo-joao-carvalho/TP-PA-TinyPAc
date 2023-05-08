package pt.isec.pa.tinypac.model.data.mob;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.cell.EmptyCell;

public class TinyPac extends Element {
    public static final char SYMBOL = 'M';

    public TinyPac(Level level){
        super(level);
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }

    @Override
    public void evolve(KeyType key){ //move
        //Level.Position myPos = level.getPositionOf(this);
        //level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
        //level.addElement(new TinyPac(level), myPos.y(), myPos.x() + 1);

        //int keyCode = key.getKeyCode();

        Level.Position myPos = level.getPositionOf(this);
        if(key == KeyType.ArrowUp) {
            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
            level.addElement(new TinyPac(level), myPos.y() - 1, myPos.x());
        }else if(key == KeyType.ArrowDown){
            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
            level.addElement(new TinyPac(level), myPos.y() + 1, myPos.x());
        }else if(key == KeyType.ArrowLeft){
            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
            level.addElement(new TinyPac(level), myPos.y(), myPos.x() - 1);
        }else if(key == KeyType.ArrowRight){
            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
            level.addElement(new TinyPac(level), myPos.y(), myPos.x() + 1);
        }
    }

    @Override
    public boolean eat(){
        return true;
    }
}
