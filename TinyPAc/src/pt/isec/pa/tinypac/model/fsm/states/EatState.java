package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class EatState extends MobsStateAdapter {

    public EatState(GameContext context, Game game){
        super(context, game);

        //SETTERS
    }

    @Override
    public boolean eat(){
        if(game.getScore() == 100){
            changeState(EMobsState.END_LEVEL);
            return true;
        }

        game.setScore(game.getScore() + 10);
        changeState(EMobsState.MOVE);
        return true;
    }

    @Override
    public EMobsState getState(){return EMobsState.EAT;}
}
