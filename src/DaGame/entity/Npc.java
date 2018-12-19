package DaGame.entity;

import DaGame.graphics.Sprite;
import DaGame.util.AABB;
import DaGame.util.Camera;
import DaGame.util.Vector2f;

import java.awt.*;

public class Npc extends Entity {

    private AABB sense;
    private int r;

    private Camera cam;

    public Npc(Camera cam, Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);

        this.cam = cam;

        acc = 1f;
        maxSpeed = 3f;
        r = 240;

        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);

        sense = new AABB(new Vector2f(origin.x + size / 2 - r / 2, origin.y + size / 2 - r / 2), r);
    }

    private void move(Player player) {
        if (sense.colCircleBox(player.getBounds())) {


            if (pos.y > player.pos.y + 1) {
                dy -= acc;
                up = true;
                down = false;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
            } else if (pos.y < player.pos.y -1 ) {
                dy += acc;
                down = true;
                up = false;

                if (dy > maxSpeed) {
                    dy = maxSpeed;
                }
            } else {
                dy = 0;
                up = false;
                down = false;
            }
            if (pos.x > player.pos.x + 1) {
                dx -= acc;
                left = true;
                right = false;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            } else if (pos.x < player.pos.x - 1) {
                dx += acc;
                left = false;
                right = true;
                if (dx > maxSpeed) {
                    dx = maxSpeed;
                }
            } else {
                dx = 0;
                right = false;
                left = false;
            }
        } else {
            up = false;
            down = false;
            left = false;
            right = false;
            dx = 0;
            dy = 0;
        }
    }



    public void update(Player player){
        if(cam.getBounds().collides(this.bounds)) {
            super.update();
            move(player);
            if (!fallen) {
                if (!tc.collisionTile(dx, 0)) {
                    sense.getPos().x += dx;
                    pos.x += dx;
                }
                if (!tc.collisionTile(0, dy)) {
                    sense.getPos().y += dy;
                    pos.y += dy;
                }
            }
        }
    }


    @Override
    public void render(Graphics2D g) {
        if(cam.getBounds().collides(this.bounds)) {
//            g.setColor(Color.green);
//            g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()),
//                    (int) bounds.getWidth(), (int) bounds.getHeight());

//            g.setColor(Color.blue);
//            g.drawOval((int) (sense.getPos().getWorldVar().x), (int) (sense.getPos().getWorldVar().y), r, r);

            g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
        }
    }


}
