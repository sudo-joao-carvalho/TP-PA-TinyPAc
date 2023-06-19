package pt.isec.pa.tinypac.model.fsm.states;

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

    @Override
    public boolean unpause(){
        changeState(EMobsState.MOVE);
        return true;
    }

    @Override
    public EMobsState getState(){return EMobsState.PAUSE;}

}