package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.mob.TinyPac;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class WaitBeginState extends MobsStateAdapter{

    public WaitBeginState(GameContext context, Game game){
        super(context, game);

        //SETTERS
        TinyPac.SCORE = 0;

        if(game.getLevel().getLevelComplete()){
            int currentLevelNumber = game.getLevel().getLevelNumber();
            game.setLevel(new Level(currentLevelNumber + 1));
        }else{
            int currentLevelNumber = game.getLevel().getLevelNumber();
            game.setLevel(new Level(currentLevelNumber));
        }
    }

    @Override
    public boolean evolve(){
        //quando ele se mover vai passar para o estado seguinte que é MoveState
        changeState(EMobsState.MOVE);
        return true;
    }

    @Override
    public boolean pause(){return false;}

    @Override
    public EMobsState getState(){return EMobsState.WAIT_BEGIN;}

}
