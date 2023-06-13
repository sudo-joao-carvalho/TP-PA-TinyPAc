package pt.isec.pa.tinypac.model;

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
}
