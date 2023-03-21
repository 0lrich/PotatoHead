package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Bullet {
    private float x;
    private float y;
    private float size = 30;
    private ShapeRenderer shapeRenderer;
    private float xSpeed;
    private float ySpeed;

    Texture defaultTexture;
    float damage;

    public Bullet(float x, float y, ShapeRenderer shapeRenderer, float xSpeed, float ySpeed) {
        this.x = x + size/2;
        this.y = y + size/2;
        this.shapeRenderer = shapeRenderer;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        damage = 1;
        defaultTexture = new Texture(Gdx.files.internal("Blurry potato.png"));

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

        //Rectangle playerRectangle = new Rectangle(Bullet.getX(), Bullet.getY(), Bullet.getWidth(), Bullet.getLength());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0,0,1,1);
        //the rectangle shape is drawn from the bottom left corner just so u know
        shapeRenderer.rect(x,y,size,size);
        shapeRenderer.end();
        batch.draw(defaultTexture, x, y, size, size);
    }
    public void alreadyHitSomething(){
        damage = 0;
    }
}