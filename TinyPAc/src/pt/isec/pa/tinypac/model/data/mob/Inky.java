package pt.isec.pa.tinypac.model.data.mob;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;

public class Inky extends Element {

    public static final char SYMBOL = 'I';

    public Inky(Level level) {
        super(level);
    }

    @Override
    public void evolve(KeyType key) {

    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }
}