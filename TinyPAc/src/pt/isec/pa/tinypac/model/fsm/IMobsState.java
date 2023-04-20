package pt.isec.pa.tinypac.model.fsm;

public interface IMobsState {

    boolean move();
    boolean eat();
    boolean endLevel();

    EMobsState getState();
}
