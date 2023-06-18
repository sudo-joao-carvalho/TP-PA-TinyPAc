package pt.isec.pa.tinypac.model;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.GameData;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.memento.CareTaker;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;

public class GameContextManager implements IGameEngineEvolve {

    private static final String FILE ="files/save.dat";
    private GameContext fsm;
    private CareTaker careTaker;

    PropertyChangeSupport pcs;

    public GameContextManager(){
        pcs = new PropertyChangeSupport(this);
    }

    public GameContext getFsm() {
        return fsm;
    }
    //public void setFsmNull(){fsm = null;}

    public void addPropertyChangeListener(PropertyChangeListener listener){pcs.addPropertyChangeListener(listener);}

    public void start(){
        fsm = new GameContext();
        //this.careTaker = new CareTaker(fsm);
        pcs.firePropertyChange(null, null, null);
    }

    public EMobsState getState(){return fsm.getState();}

    public boolean evolve(){
        //if(fsm == null) return false;

        var ret = fsm.evolve();
        pcs.firePropertyChange(null, null, null);
        return ret;
    }//evolve de mudança de estado

    public boolean pause(){
        var ret = fsm.pause();
        pcs.firePropertyChange(null, null, null);
        return ret;
    }
    public boolean unpause(){
        var ret = fsm.unpause();
        pcs.firePropertyChange(null, null, null);
        return ret;
    }

    public boolean save(){

        File file = new File(FILE);
        try(FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fout)){
            oout.writeObject(fsm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pcs.firePropertyChange(null, null, null);
        return true;

    }

    public boolean load(){
        File file = new File(FILE);
        try (FileInputStream finp = new FileInputStream(file);
             ObjectInputStream oinp = new ObjectInputStream(finp);)
        {
            fsm = (GameContext) oinp.readObject();
        } catch (Exception e) {
            fsm = new GameContext();
        }

        pcs.firePropertyChange(null, null, null);
        return true;
    }

    public int getScore(){return fsm.getScore();}
    public int getLifes(){return fsm.getLifes();}
    public int getLevelNumber(){return fsm.getLevelNumber();}
    public IMazeElement[][] getMazeWithElements(){return fsm.getMazeWithElements();}
    public void retrieveKey(KeyCode key){
        fsm.retrieveKey(key);
        //currentKeyType = key;
        pcs.firePropertyChange(null, null, null);
    }

    public void evolve(IGameEngine g, long t) {
        if(fsm == null) return ;

        fsm.evolve();
        fsm.evolve(g, t);

        pcs.firePropertyChange(null, null, null);
    }
}
