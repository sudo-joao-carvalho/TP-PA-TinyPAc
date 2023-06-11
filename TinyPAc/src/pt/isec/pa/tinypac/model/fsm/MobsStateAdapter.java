package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Game;

public abstract class MobsStateAdapter implements IMobsState {

    protected Game game;

    protected GameContext context;

    protected MobsStateAdapter(GameContext context, Game game){
        this.context    = context;
        this.game       = game;
    }

    protected void changeState(EMobsState newState){context.changeState(newState.createState(context, game));}

    @Override
    public boolean evolve(){return false;}

    @Override
    public boolean pause(){return false;}

    @Override
    public boolean unpause(){return false;}

    @Override
    public boolean checkVulnerable(){return false;}

}
