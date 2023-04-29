import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.text.GameTextUI;
import pt.isec.pa.tinypac.ui.LanternaUI;

import java.io.IOException;

/*public class Main {
    public static void main(String[] args){
        //GameEngine engine = new GameEngine();
        GameContext context = new GameContext();
        GameTextUI textUI = new GameTextUI(context);

        textUI.start();
    }
}*/

//terminal: colunas: 80 linhas 24

public class Main {
    public static void main(String[] args) throws IOException {


        GameContext context = new GameContext();
        GameTextUI textUI = new GameTextUI(context);

        GameEngine gameEngine = new GameEngine();
        LanternaUI lanternaUI = new LanternaUI(context.getGame().getLevelManager());
        gameEngine.registerClient(context.getGame().getLevelManager());
        //gameEngine.registerClient(textUI);
        gameEngine.start(500);

        gameEngine.waitForTheEnd();

        textUI.menuUI();
    }
}