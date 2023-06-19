package pt.isec.pa.tinypac.model.fsm;

//import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.GameData;

import java.io.Serializable;

public abstract class MobsStateAdapter implements IMobsState, Serializable {

    protected GameData gameData;

    protected GameContext context;

    protected MobsStateAdapter(GameContext context, GameData gameData){
        this.context    = context;
        this.gameData   = gameData;
    }

    protected void changeState(EMobsState newState){context.changeState(newState.createState(context, gameData));}

    @Override
    public boolean save(){return false;}

    @Override
    public boolean load(){return false;}
    @Override
    public boolean evolve(){return false;}

    @Override
    public boolean pause(){return false;}

    @Override
    public boolean unpause(){return false;}

    @Override
    public boolean checkVulnerable(){return false;}

}
