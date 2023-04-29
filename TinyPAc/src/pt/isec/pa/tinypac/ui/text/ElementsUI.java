package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.LevelManager;

public class ElementsUI implements IGameEngineEvolve {

    LevelManager levelManager;

    public ElementsUI(LevelManager levelManager) { this.levelManager = levelManager; }

    public void show() {
       // ModelLog.getInstance().getLog().forEach(System.out::println);
        //ModelLog.getInstance().reset();

        char [][] env = levelManager.getLevel().getMaze();
        System.out.println();
        for(int y=0;y<env.length;y++) {
            for(int x = 0; x< env[0].length;x++)
                System.out.print(env[y][x]);
            System.out.println();
        }
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime){
        show();

        if(levelManager.onlyOneSpecies())
            gameEngine.stop();
    }
}
