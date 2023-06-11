package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class EndLevelState extends MobsStateAdapter {

    public EndLevelState(GameContext context, Game game){
        super(context, game);

        //SETTERS
    }

    @Override
    public boolean evolve(){return false;}

    @Override
    public boolean pause(){return false;}

    @Override
    public EMobsState getState(){return EMobsState.END_LEVEL;}
}
