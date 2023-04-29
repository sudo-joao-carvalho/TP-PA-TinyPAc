package pt.isec.pa.tinypac.model.data;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TinyPac extends Element{
    public static final char SYMBOL = 'M';

    /*private int cordX;
    private int cordY;*/

    public TinyPac(Level level/*int cordX, int cordY*/){
        super(level);
        /*this.cordX = cordX;
        this.cordY = cordY;*/
    }

    /*public int getCordX() {return this.cordX;}

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public int getCordY(){return this.cordY;}

    public void setCordY(int cordY){this.cordY = cordY;}*/

    @Override
    public char getSymbol(){
        return SYMBOL;
    }

    @Override
    public void evolve(){}
}
