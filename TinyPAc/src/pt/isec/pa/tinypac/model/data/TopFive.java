package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.memento.IMemento;
import pt.isec.pa.tinypac.model.memento.Memento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TopFive implements Serializable {
    List<Integer> top5;

    public TopFive(){
        top5 = new ArrayList<>();
    }

    public void addToTop5(int score){

        if(top5.size() < 5){
            top5.add(score);
        }else{
            boolean added = false; // Flag para indicar se a pontuação foi adicionada
            for (int i = 0; i < top5.size(); i++) {
                if (score >= top5.get(i)) {
                    top5.add(i, score);
                    added = true;
                    break; // Parar de percorrer a lista assim que a pontuação for adicionada
                }
            }
            if (added) {
                if (top5.size() == 6) {
                    top5.remove(5); // Remover a última pontuação se a lista tiver mais de 5 elementos
                }
            }
        }

    }

    public ArrayList<Integer> getTop5(){return (ArrayList<Integer>) top5;}

}
