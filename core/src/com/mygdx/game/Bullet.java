package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bullet {
    float x;
    float y;
    ShapeRenderer shapeRenderer;
    float xSpeed;
    float ySpeed;

    public Bullet(float x, float y, ShapeRenderer shapeRenderer, float xSpeed, float ySpeed) {
        this.x = x;
        this.y = y;
        this.shapeRenderer = shapeRenderer;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
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


    }
    /**
     * this is where stuff that's drawn to the screen is gonna go (as in you put it in there it'll be drawn always)
     *     | |
     *     | |
     *     \ /
     *      V
     */
    public void render () {

    }
}
