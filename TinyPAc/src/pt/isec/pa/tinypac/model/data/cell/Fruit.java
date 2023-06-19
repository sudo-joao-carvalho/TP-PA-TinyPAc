package pt.isec.pa.tinypac.model.data.cell;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.model.data.GameData;

import java.io.Serializable;

public class Fruit extends Cell implements Serializable {

    public static final char SYMBOL = 'F';

    private GameData.Position myPosition = null;

    //private boolean eaten= false;
    private int counter = 0;
    public Fruit(GameData gameData){
        super(gameData);
    }

    /*public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }*/

    @Override
    public void evolve(KeyCode key){
        if(counter == 0){
            GameData.Position myPos = gameData.getPositionOf(this);
            myPosition = myPos;
            counter++;
        }

        if(gameData.getBallsEaten() % 20 == 0){
            gameData.addElement(new Fruit(gameData), myPosition.y(), myPosition.x());
        }else return;

    }

    @Override
    public char getSymbol(){
        return SYMBOL;
    }
}
