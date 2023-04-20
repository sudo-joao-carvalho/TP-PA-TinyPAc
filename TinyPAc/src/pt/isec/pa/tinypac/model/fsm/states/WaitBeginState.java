package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.EMobsState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.MobsStateAdapter;

public class WaitBeginState extends MobsStateAdapter{

    public WaitBeginState(GameContext context, Game game){
        super(context, game);

        //SETTERS
    }

    /*@Override
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_UP){
            System.out.println("Carreguei na tecla para cima");
        }else if(keyCode == KeyEvent.VK_DOWN){
            System.out.println("Carreguei na tecla para baixo");
        }else if(keyCode == KeyEvent.VK_LEFT){
            System.out.println("Carreguei na tecla para a esquerda");
        }else if(keyCode == KeyEvent.VK_RIGHT){
            System.out.println("Carreguei na tecla para a direita");
        }
    }*/
    @Override
    public boolean move(){
        //quando ele se mover vai passar para o estado seguinte que é MoveState
        changeState(EMobsState.MOVE);
        return true;
    }

    @Override
    public EMobsState getState(){return EMobsState.WAIT_BEGIN;}
}
