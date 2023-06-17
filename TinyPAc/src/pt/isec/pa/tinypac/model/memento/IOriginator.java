package pt.isec.pa.tinypac.model.memento;

public interface IOriginator {

    IMemento save();

    void restore(IMemento memento);
}
