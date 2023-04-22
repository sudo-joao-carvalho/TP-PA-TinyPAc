package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class MoveState extends MobsStateAdapter {

    public MoveState(GameContext context, Game game){
        super(context, game);

        //SETTERS
    }

    @Override
    public boolean eat(){
        changeState(EMobsState.EAT);
        return true;
    }

    @Override
    public boolean move(){
            changeState(EMobsState.MOVE);
            return true;
    }

    @Override
    public boolean endLevel(){
        if(game.getScore() == 100){
            changeState(EMobsState.END_LEVEL);
            return true;
        }

        changeState(EMobsState.MOVE);
        return false;

    }

    @Override
    public EMobsState getState(){return EMobsState.MOVE;}
}