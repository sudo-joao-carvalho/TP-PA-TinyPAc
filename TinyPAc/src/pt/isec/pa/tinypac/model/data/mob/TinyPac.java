package pt.isec.pa.tinypac.model.data.mob;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.cell.EmptyCell;
import pt.isec.pa.tinypac.model.data.cell.Portal;
import pt.isec.pa.tinypac.model.data.cell.Wall;

import java.util.Collections;
import java.util.List;

public class TinyPac extends Element {
    public static final char SYMBOL = 'M';

    public TinyPac(Level level){
        super(level);
    }

    public boolean isValidPosition(int y, int x){

        Level.Position myPos = level.getPositionOf(this);

        List<Level.Position> lst = level.getElementNeighbors(myPos.y(), myPos.x(), Element.class);
        if (lst.isEmpty()) {
            return false;
        }
        Collections.shuffle(lst);

        for(Level.Position pos: lst){
            if(pos.y() == y && pos.x() == x){
                return !(level.getElement(pos.y(), pos.x()) instanceof Wall) &&
                        !(level.getElement(pos.y(), pos.x()) instanceof Portal);
            }

        }

        return true;
    }
    @Override
    public void evolve(KeyType key){ //move

        Level.Position myPos = level.getPositionOf(this);
        int dx = 0;
        int dy = 0;
        switch (key) {
            case ArrowUp -> dy = -1;
            case ArrowDown -> dy = 1;
            case ArrowLeft -> dx = -1;
            case ArrowRight -> dx = 1;
        }
        if (dx != 0 || dy != 0) {
            // Verifica se o movimento é válido
            if (isValidPosition(myPos.y() + dy, myPos.x() + dx)) {
                level.addElement(new EmptyCell(level), myPos.y(), myPos.x());
                level.addElement(new TinyPac(level), myPos.y() + dy, myPos.x() + dx);
            }
        }
    }

    @Override
    public boolean eat(){
        return true;
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }
}
