package DaGame.tiles.blocks;

import DaGame.util.AABB;
import DaGame.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjBlock extends Block {

    public ObjBlock(BufferedImage img, Vector2f pos, int w, int h){
        super(img, pos,w,h);
    }

    public boolean update(AABB p) {
        return true;
    }

    public boolean isInside(AABB p){
        return false;
    }

    public void render(Graphics2D g){
        super.render(g);
        g.setColor(Color.white);
        g.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y,w,h);
    }

}
