import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.text.GameTextUI;
import pt.isec.pa.tinypac.ui.LanternaUI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameContext context = new GameContext();

        LanternaUI lanternaUI = new LanternaUI(context);
        GameEngine gameEngine = new GameEngine();

        gameEngine.registerClient((g,t) -> {
            context.evolve(g, t); //quando acontecer isto temos que fazer um Platform.runLater(() ->{ context.evolve; });
        });

        gameEngine.registerClient(lanternaUI);
        gameEngine.start(500);

        gameEngine.waitForTheEnd();
    }
}

//melhorar diagrama de estados... descriçoes meter move em tudo, meter so o endlevelsstate para o final do jogo, de resto voltar dos moves para o waitbegin