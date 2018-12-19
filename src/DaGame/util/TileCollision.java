package DaGame.util;

import DaGame.tiles.TileMapObj;
import DaGame.entity.Entity;
import DaGame.graphics.Sprite;
import DaGame.tiles.blocks.Block;
import DaGame.tiles.blocks.HoleBlock;


public class TileCollision {

    private Entity e;

    public TileCollision(Entity e) {
        this.e = e;
    }

    public boolean collisionTile(float ax, float ay) {
        for(int c = 0; c < 4; c++) {

            int xt = (int) ( (e.getBounds().getPos().x + ax) + (c % 2) * e.getBounds().getWidth() + e.getBounds().getXOffset()) / 64;
            int yt = (int) ( (e.getBounds().getPos().y + ay) + (c / 2) * e.getBounds().getHeight() + e.getBounds().getYOffset()) / 64;

            if(TileMapObj.event_blocks[xt + (yt * TileMapObj.height)] instanceof Block) {
                Block block = TileMapObj.event_blocks[xt + (yt * TileMapObj.height)];
                if(block instanceof HoleBlock) {
                    return collisionHole(ax, ay, xt, yt, block);
                }
                return block.update(e.getBounds());
            }
        }

        return false;
    }

    private boolean collisionHole(float ax, float ay, float xt, float yt, Block block) {
        int nextXt = (int) ((( (e.getBounds().getPos().x + ax) + e.getBounds().getXOffset()) / 64) + e.getBounds().getWidth() / 64);
        int nextYt = (int) ((( (e.getBounds().getPos().y + ay) + e.getBounds().getYOffset()) / 64) + e.getBounds().getHeight() / 64);

        if(block.isInside(e.getBounds())) {
            e.setFallen(true);
            return false;
        }
        else if((nextXt == yt + 1) || (nextXt == xt + 1) || (nextYt == yt - 1) || (nextXt == xt - 1)) {
            if(TileMapObj.event_blocks[nextXt + (nextYt * TileMapObj.height)] instanceof HoleBlock){
                Block nextblock = TileMapObj.event_blocks[nextXt + (nextYt * TileMapObj.height)];

                if(e.getBounds().getPos().x + e.getBounds().getXOffset() > block.getPos().x
                        && e.getBounds().getPos().y + e.getBounds().getYOffset() > block.getPos().y
                        && nextblock.getWidth() + nextblock.getPos().x > e.getBounds().getWidth() + (e.getBounds().getPos().x + e.getBounds().getXOffset())
                        && nextblock.getHeight() + nextblock.getPos().y > e.getBounds().getHeight() + (e.getBounds().getPos().y + e.getBounds().getYOffset())) {
                    e.setFallen(true);
                }
                return false;
            }
        }

        e.setFallen(false);
        return false;
    }
}

