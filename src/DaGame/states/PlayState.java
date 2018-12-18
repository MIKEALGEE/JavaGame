package DaGame.states;

import DaGame.GamePanel;
import DaGame.entity.Player;
import DaGame.graphics.Font;
import DaGame.graphics.Sprite;
import DaGame.util.KeyHandler;
import DaGame.util.MouseHandler;
import DaGame.util.Vector2f;
import DaGame.tiles.TileManager;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    private TileManager tm;

    public static Vector2f map;

    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x,map.y);


        tm = new TileManager("tile/tilemap.xml");
        font = new Font("font/ZeldaFont.png", 16,16);
        player = new Player(new Sprite("entity/MainCharJava.png"), new Vector2f(300, 300), 120);
//        player = new Player(new Sprite("entity/professor_walk_cycle_no_hat.png", 64, 64), new Vector2f(300, 300), 128);
    }

    public void update(){
        Vector2f.setWorldVar(map.x,map.y);
        player.update();
    }
    public void input(MouseHandler mouse, KeyHandler key){
        player.input(mouse, key);

    }
    public void render(Graphics2D g) {
        tm.render(g);
        Sprite.drawArray(g,font, GamePanel.charSpeech, new Vector2f(GamePanel.width -1200,GamePanel.height- 200), 42,42,18,0);
        player.render(g);
    }
}
