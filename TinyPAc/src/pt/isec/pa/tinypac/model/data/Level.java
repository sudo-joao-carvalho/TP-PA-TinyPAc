package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.cell.*;

import java.io.*;
import java.util.Scanner;

public class Level {

    //private int levelNumber;
    private int height;
    private int width;
    Maze maze;

    public Level(/*int levelNumber, */int height, int width){
        //this.levelNumber = levelNumber;
        this.height = height;
        this.width = width;
        this.maze = new Maze(height, width);
    }

    public char[][] getMaze(){
        return maze.getMaze();
    }

    public void addElement(Element element, int y, int x){maze.set(y, x, element);}

    public char getElementAt(int y, int x){
        return maze.get(y, x).getSymbol();
    }

    public int getLevelHeight(){return this.height;}
    public int getLevelWidth(){return this.width;}

    /*public Maze setMaze(){

        Maze auxMaze = new Maze(mapHeight, mapWidth);

        for(int h = 0; h < mapHeight; h++){
            for(int w = 0; w < mapWidth; w++){

                char symbol = map[h][w];
                IMazeElement element = null;

                switch(symbol){
                    case 'x' -> element = new Wall(h, w); //possivelmente vai ser necessario passar as coordenadas
                    case 'W' -> element = new Warp(h, w);
                    case 'o' -> element = new FoodBall(h, w);
                    case 'F' -> element = new Fruit(h, w);
                    case 'M' -> element = new TinyPac(h, w); //cada uma destas classes tem que dar override ao getSymbol da IMazeElement
                    case 'O' -> element = new PowerBall(h, w);
                    case 'Y' -> element = new Portal(h, w);
                    case 'y' -> element = new GhostCave(h, w);
                }
                auxMaze.set(h, w, element);
            }
        }

        return auxMaze;
    }*/

    /*public char[][] setMap() throws IOException {

        char[][] auxMap;
        //primeira leitura do ficheiro
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader("../" + levelFile));
            String line;
            int height = 0;
            int width = 0;

            while((line = reader.readLine()) != null){ //OBTER OS VALORES DE ALTURA E LARGURA
                height++;
                width = Math.max(width, line.length());
            }

            reader.close();

            //segunda leitura do ficheiro
            auxMap = new char[height][width];
            reader = new BufferedReader(new FileReader("../" + levelFile));
            int y = 0;
            while((line = reader.readLine()) != null){ //PREENCHER O  map
                for(int x = 0; x < line.length(); x++){
                    auxMap[y][x] = line.charAt(x);

                    //colocar as coordenadas de cada elemento
                }
                y++;
            }

            reader.close();
            this.mapHeight = height;
            this.mapWidth = width;
            return auxMap;
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }*/

    /*public void printMap(){                           //DEBUG: ver se esta a ler e a printar bem o mapa
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }*/

    /*public char[][] getMap(){return this.map;}

    public int getMapHeight(){return this.mapHeight;}

    public int getMapWidth(){return this.mapWidth;}

    public int getLevelNumber(){return this.levelNumber;}*/
}
