package DaGame.states;

import DaGame.GamePanel;
import DaGame.entity.Npc;
import DaGame.entity.Player;
import DaGame.graphics.Font;
import DaGame.graphics.Sprite;
import DaGame.util.*;
import DaGame.tiles.TileManager;

import java.awt.*;

import static DaGame.states.GameStateManager.PAUSE;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    private Npc npc;
    private TileManager tm;
    private Camera cam;

    public static Vector2f map;

    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x,map.y);

        cam = new Camera(new AABB(new Vector2f(0,0), GamePanel.width + 64  , GamePanel.height +64 ));


        tm = new TileManager("tile/tilemap.xml",cam);
        npc = new Npc(cam,new Sprite("entity/linkformatted.png"),  new Vector2f(0 + (GamePanel.width / 2) - 32 + 15, 0 + (GamePanel.height / 2) - 32), 120);
        player = new Player(new Sprite("entity/MainCharJava.png"),  new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 120);
        cam.target(player);
//  player = new Player(new Sprite("entity/MainCharJava.png"), new Vector2f(300, 300), 120); //old set up. this was spawning in the same place.
//        player = new Player(new Sprite("entity/professor_walk_cycle_no_hat.png", 64, 64), new Vector2f(300, 300), 128);
    }

    public void update(){
        Vector2f.setWorldVar(map.x,map.y);
        npc.update(player);
        player.update(npc);
        cam.update();
    }
    public void input(MouseHandler mouse, KeyHandler key){
        key.escape.tick();
        player.input(mouse, key);
        cam.input(mouse,key);

        if(key.escape.clicked) {
            if(!gsm.getState(GameStateManager.PAUSE)){
                gsm.pop(GameStateManager.PAUSE);
            } else{
                gsm.add(GameStateManager.PAUSE);
            }

        }
    }
    public void render(Graphics2D g) {
        tm.render(g);

        Sprite.drawArray(g, GamePanel.charSpeech, new Vector2f(GamePanel.width -1200,GamePanel.height- 200), 42,42);
//        Sprite.drawArray(g,font, GamePanel.oldFrameCount + "FPS", new Vector2f(GamePanel.width -192,32),32,42);
        player.render(g);
        npc.render(g);
        cam.render(g);
    }
}
