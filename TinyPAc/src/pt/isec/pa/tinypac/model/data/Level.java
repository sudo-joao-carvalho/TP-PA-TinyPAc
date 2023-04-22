package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.cell.*;

import java.io.*;
import java.util.Scanner;

public class Level {

    Maze maze;
    private char [][]map;
    private int mapHeight;
    private int mapWidth;
    private String levelFile;
    private int levelNumber;

    public Level(int levelNumber){
        this.levelNumber = levelNumber;
        this.levelFile = "../Level10" + levelNumber + ".txt";
        this.map = setMap();
        this.maze = setMaze();
    }

    public char[][] getMaze(){
        return maze.getMaze();
    }

    public Maze setMaze(){

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
                    case 'Y' -> element = new Portal(h, w); //so pode existir uma instancia, fazer PADRAO SINGLETON
                    case 'y' -> element = new GhostCave(h, w);
                }
                auxMaze.set(h, w, element);
            }
        }

        return auxMaze;
    }

    public char[][] setMap() {

        try{
            char[][] auxMap;

            File file = new File(levelFile);
            Scanner sc = new Scanner(file);

            String firstLine = sc.nextLine();
            int height = 1;
            int width = firstLine.length();

            while(sc.hasNextLine()){
                height++;
                sc.nextLine();
            }

            this.mapHeight = height;
            this.mapWidth = width;
            sc.close();

            //ler as letras para o array
            auxMap = new char[mapHeight][mapWidth];
            sc = new Scanner(file);

            for(int h = 0; h < mapHeight; h++){
                String line = sc.nextLine();
                for(int w = 0; w < mapWidth; w++){
                    auxMap[h][w] = line.charAt(w);
                }
            }

            sc.close();
            return auxMap;

        }catch(FileNotFoundException e){
            System.out.println("File Not Found: " + levelFile);
            return null;
        }

    }

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

    public char[][] getMap(){return this.map;}

    public int getMapHeight(){return this.mapHeight;}

    public int getMapWidth(){return this.mapWidth;}

    public int getLevelNumber(){return this.levelNumber;}
}
