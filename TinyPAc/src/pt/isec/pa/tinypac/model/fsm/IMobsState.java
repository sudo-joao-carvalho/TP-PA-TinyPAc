package pt.isec.pa.tinypac.model.fsm;

public interface IMobsState {

    boolean move();
    boolean vulnerable();
    boolean endLevel();

    EMobsState getState();
}
