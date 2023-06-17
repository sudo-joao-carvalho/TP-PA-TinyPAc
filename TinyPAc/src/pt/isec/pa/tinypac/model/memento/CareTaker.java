package pt.isec.pa.tinypac.model.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class CareTaker {

    IOriginator originator;
    Deque<IMemento> history;

    public CareTaker(IOriginator originator) {
        this.originator = originator;
    }
    public void save() {
        history.push(originator.save());
    }

}
