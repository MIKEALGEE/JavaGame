package DaGame.tiles.blocks;

import DaGame.util.AABB;
import DaGame.util.Vector2f;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class NormBlock extends Block {

    public NormBlock(BufferedImage img, Vector2f pos, int w, int h){
        super(img, pos,w,h);
    }

    public boolean update(AABB p) {
        return false;
    }
    public boolean isInside(AABB p){
        return false;
    }

    public void render(Graphics2D g){
        super.render(g);
    }
}
