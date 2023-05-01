package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class VulnerableState extends MobsStateAdapter {

    public VulnerableState(GameContext context, Game game){
        super(context, game);

        //SETTERS
    }

    @Override
    public boolean move(){
        changeState(EMobsState.MOVE);
        return true;
    }

    @Override
    public boolean endLevel(){
        changeState(EMobsState.END_LEVEL);
        return true;
    }

    @Override
    public EMobsState getState(){return EMobsState.VULNERABLE;}
}
