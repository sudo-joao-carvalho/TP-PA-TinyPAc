package pt.isec.pa.tinypac.model.fsm.states;

//import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

import java.io.Serializable;

public class EndLevelState extends MobsStateAdapter implements Serializable {

    public EndLevelState(GameContext context, GameData gameData){
        super(context, gameData);

        //SETTERS
    }

    @Override
    public boolean evolve(){
        //changeState(EMobsState.WAIT_BEGIN);
        return true;
    }

    @Override
    public boolean pause(){return false;}

    @Override
    public EMobsState getState(){return EMobsState.END_LEVEL;}
}
