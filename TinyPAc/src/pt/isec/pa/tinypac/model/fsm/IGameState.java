package pt.isec.pa.tinypac.model.fsm;

public interface IGameState {

    /*boolean start();
    boolean pause();
    boolean unpause();
    boolean loadNextLevel();
    boolean gameOverMenu();
    boolean winMenu();*/

    String start();
    String pause();
    String resume();
    //String loadNextLevel();
    String showMenu();

    String keyPressed(); // funcao para sair do wait_begin

    //String changeLevel(); // funcao do nextLevel para ir para o wait_begin e começar um novo nivel

    String restart(); // NA FUNCAO RESTART TENHO QUE SABER QUAL O NIVEL PARA QUE O JOGADOR VAI PARA O WAIT_BEGIN SABER

    EGameState getState();
}
