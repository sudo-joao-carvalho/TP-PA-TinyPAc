package pt.isec.pa.tinypac.model.data.mob;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.GameData;

import java.io.Serializable;

public class Inky extends Element implements Serializable {

    public static final char SYMBOL = 'I';
    private boolean ghostVulnerable = false;

    public Inky(GameData gameData) {
        super(gameData);
    }

    public boolean getGhostVulnerable(){return this.ghostVulnerable;}
    public void setGhostVulnerable(boolean ghostVulnerable){ this.ghostVulnerable = ghostVulnerable;}

    @Override
    public void evolve(KeyCode key) {

    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }
}