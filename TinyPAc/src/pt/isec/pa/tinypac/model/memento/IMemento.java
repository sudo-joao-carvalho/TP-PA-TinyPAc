package pt.isec.pa.tinypac.model.memento;

public interface IMemento {

    default Object getSnapshot() { return null; } //devolve um objeto e depois damos cast para
}
