package DaGame.entity;

import DaGame.graphics.Animation;
import DaGame.graphics.Sprite;
import DaGame.util.AABB;
import DaGame.util.KeyHandler;
import DaGame.util.MouseHandler;
import DaGame.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected final int FALLEN = 4;
    protected final int ATTACK = 5;
    protected final int UP = 3;
    protected final int DOWN = 2;
    protected final int RIGHT = 0;
    protected final int LEFT = 1;
    protected int currentAnimation;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected boolean fallen;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    protected float maxSpeed = 4f;
    protected float acc = 3f;
    protected float deacc = 0.30f;
    protected boolean attacking = false;

    protected AABB hitBounds;
    protected AABB bounds;



    public Entity(Sprite sprite, Vector2f origin, int size){
        this.sprite = sprite;
        pos = origin;
        this.size =size;

        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(new Vector2f(origin.x +(size / 2), origin.y),size, size);
        hitBounds.setXOffset(size / 2);
        ani = new Animation();
        setAnimation(RIGHT,sprite.getSpriteArray(RIGHT), 10);
    }

    public void setAnimation(int i, BufferedImage[] frames,int delay){
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public void setSize(int i){
        size = i; }

    public void setMaxSpeed(float f){
        maxSpeed = f; }
    public void setAcc(float f){
        acc = f; }
    public void setDeAcc(float f){
        deacc = f; }

    public AABB getBounds(){
        return bounds;
    }

    public int getSize(){
        return size;
    }

    public Animation getAnimation() {return ani;}



    public void update(){
        animate();
        setHitBoxDirection();
        ani.update();
    }

    public void animate() {
        if (up && !attacking) {
            if ((currentAnimation != UP || ani.getDelay() == -1)) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
                System.out.println("acc"+","+acc + "deacc" +"," + deacc);
            }
        } else if (down && !attacking) {
            if ((currentAnimation != DOWN || ani.getDelay() == -1)) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        } else if (left && !attacking) {
            if ((currentAnimation != LEFT || ani.getDelay() == -1)) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        } else if (right && !attacking) {
            if ((currentAnimation != RIGHT || ani.getDelay() == -1)) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        } else if (fallen) {
            if (currentAnimation != FALLEN || ani.getDelay() == -1) {
                setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 15);
            }
        } else if(attacking) {
            if(currentAnimation < 5) {
                setAnimation(currentAnimation + ATTACK, sprite.getSpriteArray(currentAnimation + ATTACK), attackDuration / 100);
            }
        }
        else {
            if(!attacking && currentAnimation > 4) {
                setAnimation(currentAnimation - 5, sprite.getSpriteArray(currentAnimation - 5), -1);
            } else if(!attacking) {
                setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
            }
        }
    }


    public void setHitBoxDirection(){
        if(up) {
            hitBounds.setYOffset(-size / 2);
            hitBounds.setXOffset(-size / 2);
        }
        else if(down) {
            hitBounds.setYOffset(size / 2);
            hitBounds.setXOffset(-size / 2);
        }
        else if(left) {
            hitBounds.setXOffset(-size);
            hitBounds.setYOffset(0);
        }
        else if(right) {
            hitBounds.setXOffset(0);
            hitBounds.setYOffset(0);
        }
    }
    public abstract void render(Graphics2D g);


    public void input(KeyHandler key, MouseHandler mouse){ }

}
