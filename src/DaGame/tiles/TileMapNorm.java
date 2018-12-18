package DaGame.tiles;

import java.awt.Graphics2D;
import java.util.ArrayList;

import DaGame.graphics.Sprite;
import DaGame.util.AABB;
import DaGame.util.Vector2f;
import DaGame.tiles.blocks.Block;
import DaGame.tiles.blocks.NormBlock;



public class TileMapNorm extends TileMap {

    private ArrayList<Block> blocks;

    public TileMapNorm(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        blocks = new ArrayList<>();

        String[] block = data.split(",");
        for(int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            if(temp != 0) {
                blocks.add(new NormBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight));
            }
        }
    }

    public void render(Graphics2D g) {
       for ( int i = 0; i < blocks.size(); i++){
           blocks.get(i).render(g);
       }
    }

}
