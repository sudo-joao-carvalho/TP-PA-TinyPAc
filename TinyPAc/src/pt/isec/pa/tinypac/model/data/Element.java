package pt.isec.pa.tinypac.model.data;

import java.awt.event.KeyEvent;

public abstract class Element implements IMazeElement{

    protected Level level;

    protected Element(Level level) {
        this.level = level;
    } // o facto de ter aqui o construtor eu depois n tenho que repetir nas classes derivadas

    //abstract public void evolve();

    public boolean move(){return false;}
    public boolean eat(){return false;}
}
