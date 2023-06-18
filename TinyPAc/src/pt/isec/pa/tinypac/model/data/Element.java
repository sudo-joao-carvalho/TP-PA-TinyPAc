package pt.isec.pa.tinypac.model.data;
import javafx.scene.input.KeyCode;

import java.io.Serializable;

public abstract class Element implements IMazeElement, Serializable {

    protected GameData gameData;

    protected Element(GameData gameData) {
        this.gameData = gameData;
    } // o facto de ter aqui o construtor eu depois n tenho que repetir nas classes derivadas

    abstract public void evolve(KeyCode key);

    public boolean eat(int y, int x){return false;}

    public boolean getGhostVulnerable(){return false;}
    public void setGhostVulnerable(boolean ghostVulnerable){}
}
