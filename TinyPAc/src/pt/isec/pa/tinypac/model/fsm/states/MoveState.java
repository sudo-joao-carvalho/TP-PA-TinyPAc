package pt.isec.pa.tinypac.model.fsm.states;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class MoveState extends MobsStateAdapter {

    public MoveState(GameContext context, Game game){
        super(context, game);

        //SETTERS
    }


    /*public void changePacmanDirection(KeyType key){
        game.getLevel().evolve(key);
    }*/

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
        }else{
            changeState(EMobsState.MOVE);
            return false;
        }
    }

    @Override
    public boolean pause(){return false;}

    @Override
    public EMobsState getState(){return EMobsState.MOVE;}
}
