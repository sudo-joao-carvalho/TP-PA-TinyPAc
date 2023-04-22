import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.text.GameTextUI;

import java.io.IOException;

/*public class Main {
    public static void main(String[] args){
        //GameEngine engine = new GameEngine();
        GameContext context = new GameContext();
        GameTextUI textUI = new GameTextUI(context);

        textUI.start();
    }
}*/

public class Main {
    public static void main(String[] args) {

        GameContext context = new GameContext();
        GameTextUI textUI = new GameTextUI(context);

        textUI.menuUI();
    }
}