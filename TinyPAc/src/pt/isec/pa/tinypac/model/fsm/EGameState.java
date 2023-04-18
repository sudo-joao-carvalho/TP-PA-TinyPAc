package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.states.*;


import java.awt.*;

public enum EGameState {

    MENU, WAIT_BEGIN, PLAYING_LEVEL, PAUSED, GAMEOVER_MENU, WIN_MENU, NEXT_LEVEL;

    //FRABRICA DE OBJETOS
    IGameState createState(GameContext context, Game game){
        return switch(this){
            case MENU -> new MenuState(context, game);
            case WAIT_BEGIN -> new WaitBeginState(context, game); //neste estado ele espera que o utilizador pressione uma tecla
            case PLAYING_LEVEL -> new PlayingLevelState(context, game); //neste estado o jogo esta a ser jogado
            case PAUSED ->  new PausedState(context, game); //neste estado o jogo encontra-se em pausa
            case WIN_MENU -> new WinMenuState(context, game); //neste estado esta a ser exibido o menu de vitoria
            case GAMEOVER_MENU -> new GameOverMenuState(context, game); //neste estado esta a ser exibido o menu de derrota
            case NEXT_LEVEL -> new NextLevelState(context, game); //neste estado muda o nivel atual
        };

    }
}
