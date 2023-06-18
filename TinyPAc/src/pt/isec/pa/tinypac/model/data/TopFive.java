package pt.isec.pa.tinypac.model.data;

import java.io.Serializable;
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
            for(int i = 0; i < top5.size(); i++){
                if(score >= top5.get(i)){
                    top5.add(i, score);
                }
            }

            if(top5.size() == 6){
                top5.remove(6);
            }
        }
    }
}
