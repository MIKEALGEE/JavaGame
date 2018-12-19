package DaGame.states;

import DaGame.util.KeyHandler;
import DaGame.util.MouseHandler;

import java.awt.*;

import static DaGame.states.GameStateManager.PAUSE;

public class PauseState extends GameState {
    public PauseState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {
    System.out.println("PAUSED");
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics2D g) {

    }
}
