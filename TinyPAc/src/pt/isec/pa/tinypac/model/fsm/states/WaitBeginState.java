package pt.isec.pa.tinypac.model.fsm.states;

//import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

import java.io.Serializable;

public class WaitBeginState extends MobsStateAdapter implements Serializable {

    public WaitBeginState(GameContext context, GameData gameData){
        super(context, gameData);

        //SETTERS
        //gameCManager.getLevel().getTinyPac().setScore(0);

        /*if(game.getLevel().getLevelComplete()){
            int currentLevelNumber = game.getLevel().getLevelNumber();
            game.setLevel(new GameData(currentLevelNumber + 1));
        }else{
            int currentLevelNumber = game.getLevel().getLevelNumber();
            game.setLevel(new GameData(currentLevelNumber));
        }*/

        if(gameData.getLevelComplete()){
            int currentLevelNumber = gameData.getLevelNumber();
            context.setGameData(new GameData(currentLevelNumber + 1));
        }else{
            int currentLevelNumber = gameData.getLevelNumber();
            context.setGameData(new GameData(currentLevelNumber));
        }
    }

   /* @Override
    public boolean start(){
        changeState(EMobsState.MOVE);
        return true;
    }*/
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
