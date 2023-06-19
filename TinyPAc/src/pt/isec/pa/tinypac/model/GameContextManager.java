package pt.isec.pa.tinypac.model;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.TopFive;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;

public class GameContextManager implements IGameEngineEvolve {

    private static final String SAVE_FILE ="files/save.dat";
    private static final String TOP5_FILE ="files/topFive.dat";
    private GameContext fsm;
    PropertyChangeSupport pcs;

    public GameContextManager(){
        pcs = new PropertyChangeSupport(this);
    }

    public GameContext getFsm() {
        return fsm;
    }
    public void setFsmNull(){fsm = null;}

    public void addPropertyChangeListener(PropertyChangeListener listener){pcs.addPropertyChangeListener(listener);}

    public void start(){
        fsm = new GameContext();
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

        File file = new File(SAVE_FILE);
        try(FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fout)){
            oout.writeObject(fsm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pcs.firePropertyChange(null, null, null);
        return true;

    }

    public boolean saveTop5(){

        File file2 = new File(TOP5_FILE);
        try(FileOutputStream fout = new FileOutputStream(file2);
            ObjectOutputStream oout = new ObjectOutputStream(fout)){
            TopFive topFive = fsm.getTop5();
            oout.writeObject(topFive);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pcs.firePropertyChange(null, null, null);
        return true;

    }

    public boolean load(){
        File file = new File(SAVE_FILE);
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

    public boolean loadTop5(){

        File file2 = new File(TOP5_FILE);
        try (FileInputStream finp = new FileInputStream(file2);
             ObjectInputStream oinp = new ObjectInputStream(finp);)
        {
            TopFive top5 = (TopFive) oinp.readObject();
            fsm.setTop5(top5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pcs.firePropertyChange(null, null, null);
        return true;
    }

    public int getScore(){return fsm.getScore();}
    public int getLifes(){return fsm.getLifes();}
    public int getLevelNumber(){return fsm.getLevelNumber();}
    public int getNumOfFood(){return fsm.getNumOfFood();}

    public TopFive getTopFive(){return fsm.getTop5();}

    public ArrayList<Integer> getTopFiveArrayList(){return fsm.getTopFiveArrayList();}

    public IMazeElement[][] getMazeWithElements(){return fsm.getMazeWithElements();}
    public void retrieveKey(KeyCode key){
        fsm.retrieveKey(key);
        pcs.firePropertyChange(null, null, null);
    }

    public void evolve(IGameEngine g, long t) {
        if(fsm == null) return ;

        fsm.evolve();
        fsm.evolve(g, t);

        pcs.firePropertyChange(null, null, null);
    }
}
