package pt.isec.pa.tinypac.model.data.mob;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.cell.*;

import java.util.Collections;
import java.util.List;

public class TinyPac extends Element {
    public static final char SYMBOL = 'M';
    public static int SCORE = 0;
    private int lifes = 3;

    private boolean isOP = false;

    public TinyPac(Level level){
        super(level);
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public Element checkFood(int y, int x){
        Level.Position myPos = level.getPositionOf(this);

        List<Level.Position> lst = level.getElementNeighbors(myPos.y(), myPos.x(), Element.class);
        if (lst.isEmpty()) {
            return null;
        }
        Collections.shuffle(lst);

        for(Level.Position pos: lst){
            if(pos.y() == y && pos.x() == x){
                if(level.getElement(pos.y(), pos.x()) instanceof FoodBall || level.getElement(pos.y(), pos.x()) instanceof PowerBall)
                    return level.getElement(pos.y(), pos.x());
                else return null;
            }

        }

        return null;

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

    //@Override
    public void eat(int y, int x){

        if(checkFood(y, x) instanceof FoodBall)
            SCORE += 1;

        if(checkFood(y, x) instanceof PowerBall){
            SCORE += 10;
            enterOP();
        }
    }

    public void enterOP(){
        this.isOP = true;
    }

    public void leaveOP(){
        this.isOP = false;
    }

    public boolean isOP(){return this.isOP;}
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
                eat(myPos.y() + dy, myPos.x() + dx);
                level.addElement(new EmptyCell(level), myPos.y(), myPos.x());

                Level.Position newPos = new Level.Position(myPos.y() + dy, myPos.x() + dx);
                level.setPositionOf(newPos, this);
            }
        }
    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }

}
