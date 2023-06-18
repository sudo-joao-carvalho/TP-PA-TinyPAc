package pt.isec.pa.tinypac.model.fsm;

//import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.fsm.states.*;

public enum EMobsState {

    //FRABRICA DE OBJETOS
    WAIT_BEGIN, MOVE, VULNERABLE, PAUSE, END_LEVEL;

    IMobsState createState(GameContext context, GameData gameData){
        return switch(this){
            case WAIT_BEGIN -> new WaitBeginState(context, gameData);
            case MOVE -> new MoveState(context, gameData);
            case VULNERABLE -> new VulnerableState(context, gameData);
            case END_LEVEL -> new EndLevelState(context, gameData);
            case PAUSE -> new PauseState(context, gameData);
        };
    }
}
