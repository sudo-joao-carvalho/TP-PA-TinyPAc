package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Element;
import pt.isec.pa.tinypac.model.data.Level;
import pt.isec.pa.tinypac.model.data.mob.TinyPac;
import pt.isec.pa.tinypac.model.data.cell.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelManager implements IGameEngineEvolve {

    private int levelNumber;
    Level level;

    public LevelManager(int levelNumber){
        this.levelNumber = levelNumber;
        String filePath = "files/Level10" + levelNumber + ".txt";
        this.level = setMap(filePath);
    }

    public Level setMap(String filePath) {

        try{
            char[][] auxMap;

            File file = new File(filePath);
            if(!file.exists())
                return null;
            Scanner sc = new Scanner(file);

            String firstLine = sc.nextLine();
            int height = 1;
            int width = firstLine.length();

            while(sc.hasNextLine()){
                height++;
                sc.nextLine();
            }

            sc.close();

            //ler as letras para o array
            auxMap = new char[height][width];
            sc = new Scanner(file);

            for(int h = 0; h < height; h++){
                String line = sc.nextLine();
                for(int w = 0; w < width; w++){
                    auxMap[h][w] = line.charAt(w);
                }
            }

            Level auxLevel = new Level(/*levelNumber, */height, width);

            for(int h = 0; h < height; h++){
                for(int w = 0; w < width; w++){

                    char symbol = auxMap[h][w];
                    Element element = null;

                     element = switch(symbol){
                         case Wall.SYMBOL -> new Wall(auxLevel); //possivelmente vai ser necessario passar as coordenadas
                         case Warp.SYMBOL -> new Warp(auxLevel);
                         case FoodBall.SYMBOL -> new FoodBall(auxLevel);
                         case Fruit.SYMBOL -> new Fruit(auxLevel);
                         case TinyPac.SYMBOL -> new TinyPac(auxLevel); //cada uma destas classes tem que dar override ao getSymbol da IMazeElement
                         case PowerBall.SYMBOL -> new PowerBall(auxLevel);
                         case Portal.SYMBOL -> new Portal(auxLevel);
                         case GhostCave.SYMBOL -> new GhostCave(auxLevel);
                         default -> null;
                     };
                    auxLevel.addElement(element, h, w);

                }
            }

            sc.close();
            return auxLevel;

        }catch(FileNotFoundException e){
            System.out.println("File Not Found: " + filePath);
            return null;
        }

    }

    public Level getLevel(){
        return level;
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime){
        if(level == null)
            return ;
        level.evolve();
    }

    public boolean onlyOneSpecies(){
        return level.onlyOneSpecies();
    }
}
