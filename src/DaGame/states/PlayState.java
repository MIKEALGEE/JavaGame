package DaGame.states;

import DaGame.GamePanel;
import DaGame.entity.Npc;
import DaGame.entity.Player;
import DaGame.graphics.Font;
import DaGame.graphics.Sprite;
import DaGame.tiles.TileManager;
import DaGame.util.KeyHandler;
import DaGame.util.MouseHandler;
import DaGame.util.Vector2f;
import DaGame.util.Camera;
import DaGame.util.AABB;
import javafx.scene.media.MediaPlayer;

import javax.print.attribute.standard.Media;
import java.awt.Graphics2D;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    private Npc npc;
    private TileManager tm;
    private Camera cam;


    public static Vector2f map;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        cam = new Camera(new AABB(new Vector2f(0, 0), GamePanel.width + 64, GamePanel.height + 64));

        tm = new TileManager("tile/tilemap.xml", cam);


        npc = new Npc(cam, new Sprite("entity/linkFormatted.png"), new Vector2f(0 + (GamePanel.width / 2) - 32 + 150, 0 + (GamePanel.height / 2) - 32 + 150), 120);
        player = new Player(cam, new Sprite("entity/MainCharJava.png"), new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 120);
        cam.target(player);
    }

    public void update(double time) {
        Vector2f.setWorldVar(map.x, map.y);
        if(!gsm.getState(GameStateManager.PAUSE)) {
            player.update(npc, time);
            npc.update(player);
            cam.update();
        }

    }

    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.tick();

        if(!gsm.getState(GameStateManager.PAUSE)) {
            player.input(mouse, key);
            cam.input(mouse, key);
        }
        if (key.escape.clicked) {
            if(gsm.getState(GameStateManager.PAUSE)) {
                gsm.pop(GameStateManager.PAUSE);
            } else {
                gsm.add(GameStateManager.PAUSE);
            }
        }
    }

    public void render(Graphics2D g) {
        tm.render(g);
        String fps = GamePanel.oldFrameCount + " FPS";
        Sprite.drawArray(g, fps, new Vector2f(GamePanel.width - fps.length() * 32, 32), 32, 24);

        String tps = GamePanel.oldTickCount + " TPS";
        Sprite.drawArray(g, tps, new Vector2f(GamePanel.width - tps.length() * 32, 64), 32, 24);

        player.render(g);
        npc.render(g);
        cam.render(g);
    }
}