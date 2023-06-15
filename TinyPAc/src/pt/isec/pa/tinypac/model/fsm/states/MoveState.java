package pt.isec.pa.tinypac.model.fsm.states;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.mob.TinyPac;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class MoveState extends MobsStateAdapter {

    public MoveState(GameContext context, Game game){
        super(context, game);

        //SETTERS
    }

    public boolean checkLevelOver(){

        if(game.getLevel().getTinyPac().getScore() >= 10){
            game.getLevel().setLevelComplete();
            return true;
        }

        if(game.getLevel().getTinyPac().getLifes() == 0){
            return true;
        }

        return false;
    }

    public boolean checkVulnerable(){return game.getLevel().getTinyPac().isOP();}

    public void setGhostsVulnerable(boolean ghostsVulnerable){

        for(Element element : game.getLevel().getGhosts()){
            element.setGhostVulnerable(ghostsVulnerable);
        }
    }

    @Override
    public boolean evolve(){
        if(checkVulnerable()){
            setGhostsVulnerable(true);
            changeState(EMobsState.VULNERABLE);
            return true;
        }

        if(checkLevelOver()){
            if(game.getLevel().getLevelNumber() == 20){
                changeState(EMobsState.END_LEVEL);
                return true;
            }

            game.getLevel().getTinyPac().setScore(0);
            changeState(EMobsState.WAIT_BEGIN);
            return true;
        }

        changeState(EMobsState.MOVE);
        return true;

    }

    @Override
    public boolean pause(){
        System.out.println("jogo pausado");
        changeState(EMobsState.PAUSE);
        return true;
    }

    @Override
    public EMobsState getState(){return EMobsState.MOVE;}
}
