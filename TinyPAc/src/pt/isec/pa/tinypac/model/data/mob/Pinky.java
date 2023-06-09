package pt.isec.pa.tinypac.model.data.mob;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;

public class Pinky extends Element {

    public static final char SYMBOL = 'P';
    private boolean ghostVulnerable = false;

    public Pinky(Level level) {
        super(level);
    }

    public boolean getGhostVulnerable(){return this.ghostVulnerable;}
    public void setGhostVulnerable(boolean ghostVulnerable){ this.ghostVulnerable = ghostVulnerable;}

    @Override
    public void evolve(KeyType key) {

    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }
}