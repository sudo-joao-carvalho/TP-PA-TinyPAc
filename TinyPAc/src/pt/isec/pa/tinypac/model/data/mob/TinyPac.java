package pt.isec.pa.tinypac.model.data.mob;

import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.cell.EmptyCell;
import pt.isec.pa.tinypac.model.data.cell.Wall;

import javax.swing.*;
import java.awt.event.KeyEvent;

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
    public boolean move(/*KeyEvent e*/){
        Level.Position myPos = level.getPositionOf(this);
        level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
        level.addElement(new TinyPac(level), myPos.y(), myPos.x() + 1);

        /*int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_UP){
            Level.Position myPos = level.getPositionOf(this);
            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
            level.addElement(new TinyPac(level), myPos.y() - 1, myPos.x());
        }else if(keyCode == KeyEvent.VK_DOWN){
            Level.Position myPos = level.getPositionOf(this);
            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
            level.addElement(new TinyPac(level), myPos.y() + 1, myPos.x());
        }else if(keyCode == KeyEvent.VK_LEFT){
            Level.Position myPos = level.getPositionOf(this);
            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
            level.addElement(new TinyPac(level), myPos.y(), myPos.x() - 1);
        }else if(keyCode == KeyEvent.VK_RIGHT){
            Level.Position myPos = level.getPositionOf(this);
            level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
            level.addElement(new TinyPac(level), myPos.y(), myPos.x() + 1);
        }*/
        return true;
    }

    @Override
    public boolean eat(){
        return true;
    }
}
