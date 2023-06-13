package pt.isec.pa.tinypac.model;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameContextManager {

    private GameContext fsm;

    PropertyChangeSupport pcs;

    public GameContextManager(){
        pcs = new PropertyChangeSupport(this);
    }

    public GameContext getFsm() {
        return fsm;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){pcs.addPropertyChangeListener(listener);}

    public void start(){
        fsm = new GameContext();
        /*GameEngine gameEngine = new GameEngine();

        gameEngine.registerClient((g,t) -> {
            fsm.evolve(g, t); //quando acontecer isto temos que fazer um Platform.runLater(() ->{ context.evolve; });
        });

        //gameEngine.registerClient(LanternaUI);
        gameEngine.start(350);

        gameEngine.waitForTheEnd();*/

        pcs.firePropertyChange(null, null, null);
    }

    public EMobsState getState(){return fsm.getState();}

    public boolean evolve(){
        var ret = fsm.evolve();
        pcs.firePropertyChange(null, null, null);
        return ret;
    }//evolve de mudança de estado

    public boolean pause(){
        var ret = fsm.pause();
        pcs.firePropertyChange(null, null, null);
        return ret;
    }
    public boolean unpause(){return fsm.unpause();}

    public Level getLevel(){return fsm.getLevel();}
    public IMazeElement[][] getMazeWithElements(){return fsm.getMazeWithElements();}
    public void retrieveKey(KeyCode key){
        fsm.retrieveKey(key);
    }

    public void evolve(IGameEngine g, long t) {
        fsm.evolve(g, t);
    }
}
