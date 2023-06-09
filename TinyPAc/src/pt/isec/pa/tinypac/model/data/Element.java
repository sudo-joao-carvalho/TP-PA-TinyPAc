package pt.isec.pa.tinypac.model.data;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import javax.swing.*;
import java.awt.event.KeyEvent;

public abstract class Element implements IMazeElement{

    protected Level level;

    protected Element(Level level) {
        this.level = level;
    } // o facto de ter aqui o construtor eu depois n tenho que repetir nas classes derivadas

    abstract public void evolve(KeyType key);

    //public boolean evovle(){return false;}
    public boolean eat(int y, int x){return false;}

    public boolean getGhostVulnerable(){return false;}
    public void setGhostVulnerable(boolean ghostVulnerable){}
}
