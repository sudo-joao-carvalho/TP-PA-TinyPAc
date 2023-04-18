import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.text.GameTextUI;

public class Main {
    public static void main(String[] args) {
        GameContext context = new GameContext();
        GameTextUI textUI = new GameTextUI(context);

        textUI.start();
    }
}