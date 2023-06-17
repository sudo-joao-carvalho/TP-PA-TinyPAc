package pt.isec.pa.tinypac.model.data;
import javafx.scene.input.KeyCode;

public abstract class Element implements IMazeElement{

    protected GameData gameData;

    protected Element(GameData gameData) {
        this.gameData = gameData;
    } // o facto de ter aqui o construtor eu depois n tenho que repetir nas classes derivadas

    abstract public void evolve(KeyCode key);

    //public boolean evovle(){return false;}
    public boolean eat(int y, int x){return false;}

    public boolean getGhostVulnerable(){return false;}
    public void setGhostVulnerable(boolean ghostVulnerable){}
}
