package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Element;
//import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

import java.io.Serializable;

public class MoveState extends MobsStateAdapter implements Serializable {

    public MoveState(GameContext context, GameData gameData){
        super(context, gameData);

        //SETTERS
    }

    /*public boolean checkLevelOver(){

        if(gameData.getTinyPac().getScore() >= 2){
            gameData.setLevelComplete();
            return true;
        }

        if(gameData.getTinyPac().getLifes() == 0){
            return true;
        }

        return false;
    }*/

    public boolean checkVulnerable(){
        //System.out.println(gameData.getTinyPac().isOP());
        return gameData.getTinyPac().isOP();
    }

    public void setGhostsVulnerable(boolean ghostsVulnerable){

        for(Element element : gameData.getGhosts()){
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

        if(gameData.getNumOfFood() == 290){
            gameData.setLevelComplete();
            changeState(EMobsState.WAIT_BEGIN); //WAIT_BEGIN
            return true;
        }

        if(gameData.getTinyPac().getLifes() == 0){
            gameData.getTop5().addToTop5(gameData.getTinyPac().getScore());
            changeState(EMobsState.END_LEVEL);
            return true;
        }

        if(gameData.getLevelNumber() == 1){
            if(gameData.getLevelComplete()){
                gameData.getTop5().addToTop5(gameData.getTinyPac().getScore());
                changeState(EMobsState.END_LEVEL);
                return true;
            }
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
