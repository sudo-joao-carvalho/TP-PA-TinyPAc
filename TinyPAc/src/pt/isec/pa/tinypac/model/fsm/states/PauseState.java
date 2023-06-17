package pt.isec.pa.tinypac.model.fsm.states;

import com.googlecode.lanterna.input.KeyType;
//import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

import java.io.Serializable;

public class PauseState extends MobsStateAdapter implements Serializable {

    public PauseState(GameContext context, GameData gameData){
        super(context, gameData);

        //SETTERS
    }

    /*@Override
    public boolean evolve(){
        System.out.println("jogo pausado");
        changeState(EMobsState.MOVE);
        return true;
    }*/

    @Override
    public boolean unpause(){
        System.out.println("jogo unpaused");
        changeState(EMobsState.MOVE);
        return true;
    }

    @Override
    public EMobsState getState(){return EMobsState.PAUSE;}

}