package pt.isec.pa.tinypac.model.data.cell;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.GameData;

import java.io.Serializable;

public class Cell extends Element implements Serializable {

    private static final char SYMBOL = 'x';

    public Cell(GameData gameData){
        super(gameData); // depois na herança cada celula vai ter um type diferente
    }

    @Override
    public void evolve(KeyCode key) {

    }

    @Override
    public char getSymbol(){ //default
        return SYMBOL;
    }

    /*@Override
    public void evolve(){
    }*/
}
