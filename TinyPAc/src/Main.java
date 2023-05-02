import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.LevelManager;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.text.GameTextUI;
import pt.isec.pa.tinypac.ui.LanternaUI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        LevelManager levelManager = new LevelManager(1);
        GameContext context = new GameContext();
        GameTextUI textUI = new GameTextUI(context);

        GameEngine gameEngine = new GameEngine();
        LanternaUI lanternaUI = new LanternaUI(levelManager, context);

        gameEngine.registerClient(levelManager);
        gameEngine.registerClient(lanternaUI);
        gameEngine.start(500);

        gameEngine.waitForTheEnd();

        textUI.menuUI();
    }
}