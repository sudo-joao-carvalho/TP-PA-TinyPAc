package pt.isec.pa.tinypac.model.data;

import com.googlecode.lanterna.input.KeyType;
import pt.isec.pa.tinypac.model.data.cell.*;
import pt.isec.pa.tinypac.model.data.mob.TinyPac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Level {

    public record Position(int y, int x) {}

    private int height;
    private int width;
    private int levelNumber;
    Maze maze;

    public Level(int levelNumber/*int height, int width*/){
        this.levelNumber = levelNumber;
        String filePath = "files/Level10" + levelNumber + ".txt";
        this.maze = setMaze(filePath);
    }

    public Maze setMaze(String filePath) {

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

            this.height = height;
            this.width = width;

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

            //Level auxLevel = new Level(/*levelNumber, */height, width);
            Maze auxMaze = new Maze(height, width);

            for(int h = 0; h < height; h++){
                for(int w = 0; w < width; w++){

                    char symbol = auxMap[h][w];
                    Element element = null;

                     element = switch(symbol){
                         case Wall.SYMBOL -> new Wall(this); //possivelmente vai ser necessario passar as coordenadas
                         case Warp.SYMBOL -> new Warp(this);
                         case FoodBall.SYMBOL -> new FoodBall(this);
                         case Fruit.SYMBOL -> new Fruit(this);
                         case TinyPac.SYMBOL -> new TinyPac(this); //cada uma destas classes tem que dar override ao getSymbol da IMazeElement
                         case PowerBall.SYMBOL -> new PowerBall(this);
                         case Portal.SYMBOL -> new Portal(this);
                         case GhostCave.SYMBOL -> new GhostCave(this);
                         default -> null;
                     };
                    auxMaze.set(h ,w, element);

                }
            }

            sc.close();
            return auxMaze;

        }catch(FileNotFoundException e){
            System.out.println("File Not Found: " + filePath);
            return null;
        }

    }

    public void addElement(Element element, int y, int x){maze.set(y, x, element);}

    public char getElementAt(int y, int x){
        return maze.get(y, x).getSymbol();
    }

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

    public boolean evolve(KeyType key) {
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
            element.evolve(key);
            //element.move();
        return true;
    }

    public char[][] getMaze(){return this.maze.getMaze();}

}
