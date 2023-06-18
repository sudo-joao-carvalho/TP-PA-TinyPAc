package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Element;
//import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

import java.io.Serializable;

public class VulnerableState extends MobsStateAdapter implements Serializable {

    public VulnerableState(GameContext context, GameData gameData){
        super(context, gameData);
        //SETTERS
        gameData.getTinyPac().enterOP();
    }
    /*public boolean checkLevelOver(){

        if(gameData.getTinyPac().getScore() >= 10){
            gameData.setLevelComplete();
            return true;
        }

        if(gameData.getTinyPac().getLifes() == 0){
            return true;
        }

        return false;
    }*/

    public void notOP(){
        gameData.getTinyPac().leaveOP();
    }

    public void setGhostsVulnerable(boolean ghostsVulnerable){

        for(Element element : gameData.getGhosts()){
            element.setGhostVulnerable(ghostsVulnerable);
        }
    }


    @Override
    public boolean evolve(){

        Thread timerThread = new Thread(() -> { //thread que faz com que esteja neste estado durante 10 segundos
            int seconds = 0;
            while (seconds < 10) {
                try {

                    /*if(checkLevelOver()){
                        if(gameData.getLevelNumber() == 2){
                            changeState(EMobsState.END_LEVEL);
                            return;
                        }

                        gameData.getTinyPac().setScore(0);
                        changeState(EMobsState.WAIT_BEGIN);
                        return;
                    }*/

                    //System.out.println("ola4");

                    if(gameData.getTinyPac().getScore() >= 2){
                        gameData.setLevelComplete();
                        changeState(EMobsState.WAIT_BEGIN);
                        return ;
                    }

                    if(gameData.getTinyPac().getLifes() == 0){
                        gameData.getTop5().addToTop5(gameData.getTinyPac().getScore());
                        changeState(EMobsState.END_LEVEL);
                        return ;
                    }

                    if(gameData.getLevelNumber() == 2){
                        gameData.getTop5().addToTop5(gameData.getTinyPac().getScore());
                        changeState(EMobsState.END_LEVEL);
                        return ;
                    }

                    changeState(EMobsState.MOVE);

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seconds++;
            }
            setGhostsVulnerable(false);
            notOP();
            changeState(EMobsState.MOVE);
        });
        timerThread.start();

        return true;
    }

    @Override
    public boolean pause(){
        System.out.println("jogo pausado");
        changeState(EMobsState.PAUSE);
        return true;
    }

    @Override
    public EMobsState getState(){return EMobsState.VULNERABLE;}
}
