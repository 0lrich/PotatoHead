package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mygdx.game.Globals.potato;


public class Bullet extends InGameObj {
    private float x;
    private float y;
    private float size = 30;
    private ShapeRenderer shapeRenderer;
    private float xSpeed;
    private float ySpeed;

    Texture defaultTexture;
    float damage;
    boolean isActive = true;

    boolean isFriendly = true;

    public Bullet(float x, float y, float xSpeed, float ySpeed) {

        this.x = x + size/2;
        this.y = y + size/2;
        this.shapeRenderer = Globals.globalRender;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.isFriendly = isFriendly;
        damage = 1;
        defaultTexture = new Texture(Gdx.files.internal("Blurry potato.png"));

        //Rectangle playerRectangle = new Rectangle(Bullet.getX(), Bullet.getY(), Bullet.getWidth(), Bullet.getLength());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0,0,1,1);
        //the rectangle shape is drawn from the bottom left corner just so u know
        shapeRenderer.rect(x,y,size,size);
        shapeRenderer.end();
        //System.out.println("Bullet location: (" + x + ", " + y + ")");
        //potato.printLocation();
    }

    public Bullet(float x, float y, float size, float xSpeed, float ySpeed, Texture defaultTexture, float damage, boolean isFriendly) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.defaultTexture = defaultTexture;
        this.damage = damage;
        this.isFriendly = isFriendly;
    }

    /**
     * this is where stuff that happens every frame is gonna go
     *  | |
     *  | |
     *  | |
     *  \ /
     *   V
     */
    public void update(){
        x += xSpeed;
        y += ySpeed;
        if(x>Gdx.graphics.getWidth() * 200 || y>Gdx.graphics.getHeight() * 200|| x < -100 * 200 || y < - 100 * 200){
            free = true;
            isActive = false;
        }
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getSize(){
        return size;
    }
    /**
     * this is where stuff that's drawn to the screen is gonna go (as in you put it in there it'll be drawn always)
     *     | |
     *     | |
     *     \ /
     *      V
     */
    public void render (SpriteBatch batch) {
        batch.draw(defaultTexture, x, y, size, size);
        //batch.draw(defaultTexture, x, y, 0, 0, size, size, 1, 1, 0, defaultTexture, defaultTexture, size, size, false, false);

    }
    public void alreadyHitSomething(){

        damage = 0;
        isActive =false;
    }
}