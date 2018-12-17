package DaGame.states;

import DaGame.entity.Player;
import DaGame.graphics.Font;
import DaGame.graphics.Sprite;
import DaGame.util.KeyHandler;
import DaGame.util.MouseHandler;
import DaGame.util.Vector2f;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;
    private Player player;

    public PlayState(GameStateManager gsm){
        super(gsm);
        font = new Font("font/ZeldaFont.png", 16,16);
        player = new Player(new Sprite("entity/MainCharJava.png"), new Vector2f(300, 300), 120);
//        player = new Player(new Sprite("entity/professor_walk_cycle_no_hat.png", 64, 64), new Vector2f(300, 300), 128);
    }

    public void update(){
        player.update();
    }
    public void input(MouseHandler mouse, KeyHandler key){
        player.input(mouse, key);

    }
    public void render(Graphics2D g) {

        Sprite.drawArray(g,font,"this is me testing out the text on the screen", new Vector2f(100,100), 32,32,16,0);
        player.render(g);
    }
}
