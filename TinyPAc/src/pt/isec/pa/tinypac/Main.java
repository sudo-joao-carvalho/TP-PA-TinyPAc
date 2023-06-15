package pt.isec.pa.tinypac;

import com.googlecode.lanterna.input.KeyType;
import javafx.application.Platform;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.gui.MainJFX;
import pt.isec.pa.tinypac.ui.text.GameTextUI;

import java.io.IOException;

/*public class Main {
    public static void main(String[] args) throws IOException {
        GameContext context = new GameContext();

        LanternaUI lanternaUI = new LanternaUI(context);
        GameEngine gameEngine = new GameEngine();

        gameEngine.registerClient((g,t) -> {
            context.evolve(g, t); //quando acontecer isto temos que fazer um Platform.runLater(() ->{ context.evolve; });
        });

        gameEngine.registerClient(lanternaUI);
        gameEngine.start(350);

        gameEngine.waitForTheEnd();
    }
}*/

import javafx.application.Application;

public class Main {
    public static GameContextManager gameCManager;
    static{
        gameCManager = new GameContextManager();
    }
    public static void main(String[] args) {
        Application.launch(MainJFX.class,args);
    }
}

//por JavaDocs -> pelo menos 2/3 classes -> pelo menos no modelo de dados
//fazer 3 testes sobre a maquina de estados
//melhorar diagrama de estados... descriçoes meter move em tudo, meter so o endlevelsstate para o final do jogo, de resto voltar dos moves para o waitbegin