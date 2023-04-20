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

/*class TestClient implements IGameEngineEvolve {
    int count = 0;
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        //System.out.printf("[%d] %d\n",currentTime,++count);
        //System.out.printf("ola\n");
        if (count >= 20) gameEngine.stop();
    }
}*/
public class Main {
    public static void main(String[] args) {
        /*IGameEngine gameEngine = new GameEngine();
        TestClient client = new TestClient();
        gameEngine.registerClient(client);
        gameEngine.start(500);
        gameEngine.waitForTheEnd();*/

        GameContext context = new GameContext();
        GameTextUI textUI = new GameTextUI(context);

        textUI.start();
    }
}