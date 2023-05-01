package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.mob.TinyPac;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level {

    public record Position(int y, int x) {}

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

    public Element getOrganism(int y,int x) {
        IMazeElement e = maze.get(y,x);
        if (e instanceof Element element)
            return element;
        return null;
    }

    public Position getPositionOf(Element element) {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) == element)
                    return new Position(y,x);
        return null;
    }

    /*public void setPositionOf(int y, int x, Element element) {
        maze.set(y, x, element);
    }*/

    public void setPositionOf(Position position, Element element) {
        maze.set(position.y(), position.x(), element);
    }

    public boolean onlyOneSpecies() {
        int n = 0;

        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) instanceof Element element) {
                    if (element instanceof TinyPac)
                        n++;
                    /*else if (organism instanceof Virus)
                        nr_virus++;*/
                }
        return (n==0);
    }

    public boolean evolve() {
        int n = 0;

        List<Element> lst = new ArrayList<>();
        for(int y = 0; y < height;y++) {
            for (int x = 0; x < width; x++) {
                if (maze.get(y, x) instanceof Element element) {
                    lst.add(element);
                    if (element instanceof TinyPac)
                        //maze.set(y, x + 1, element);
                        n++;
                    /*else if (organism instanceof Virus)
                        nr_virus++;*/
                }
            }
        }

        Collections.shuffle(lst);
        for(var element : lst)
            //element.evolve();
            element.move();
        return true;
    }
}
