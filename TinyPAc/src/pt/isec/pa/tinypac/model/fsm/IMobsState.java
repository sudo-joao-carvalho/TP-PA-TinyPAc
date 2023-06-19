package pt.isec.pa.tinypac.model.fsm;

public interface IMobsState {

    boolean save();
    boolean load();
    boolean evolve();

    boolean pause();

    boolean unpause();

    EMobsState getState();

    boolean checkVulnerable();

}
