package pt.isec.pa.tinypac;

import pt.isec.pa.tinypac.model.GameContextManager;
import pt.isec.pa.tinypac.ui.gui.MainJFX;

import javafx.application.Application;

public class Main {
    public static GameContextManager gameCManager;
    static{
        gameCManager = new GameContextManager();
    }
    public static void main(String[] args) {
        Application.launch(MainJFX.class,args);
    }
}