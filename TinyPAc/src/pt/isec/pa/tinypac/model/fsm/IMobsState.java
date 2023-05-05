package pt.isec.pa.tinypac.model.fsm;

public interface IMobsState {

    boolean move();
    boolean vulnerable();
    boolean endLevel();

    EMobsState getState();

    //a engine vai estar sempre a chamar o evolve() mas depois em cada estado temos que mudar o que o evolve faz por exemplo no estado pause o evolve() nao faz nada
}
