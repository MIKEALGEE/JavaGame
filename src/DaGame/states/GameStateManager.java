package DaGame.states;

import DaGame.GamePanel;
import DaGame.graphics.Font;
import DaGame.graphics.Sprite;
import DaGame.util.KeyHandler;
import DaGame.util.MouseHandler;
import DaGame.util.Vector2f;

import java.awt.Graphics2D;


public class GameStateManager {

    private GameState states[];

    public static Vector2f map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int SPEECH = 3;

    public int onTopState = 0;

    public static Font font;

    public GameStateManager(){
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x,map.y);
        states = new GameState[4];

        font = new Font("font/ZeldaFont.png", 10,10);
        Sprite.currentFont = font;

        states[PLAY] = new PlayState(this);
    }

    public boolean getState(int state){
        return states[state] != null;
    }

    public void pop(int state) {
        states[state] = null;
    }

    public void add(int state){
        if(states[state] != null)
            return;
        if (state == PLAY){
            states[PLAY] = new PlayState(this);
        }
        if (state ==MENU){
            states[MENU] = new MenuState(this);
        }
        if (state == PAUSE){
            states[PAUSE] = new PauseState(this);
        }
        if (state == SPEECH){
            states[SPEECH] = new SpeechState(this);
        }
    }

    public void addAndpop(int state ){
        addAndpop(state, 0);
    }

    public void addAndpop(int state, int remove){
        pop(state);
        add(state);
    }

    public void update(){
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].update();
            }
            }
        }

    public void input(MouseHandler mouse, KeyHandler key){
        key.escape.tick();
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].input(mouse,key);
            }
        }

    }

    public void render(Graphics2D g){
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].render(g);
            }
        }
        }
    }
