package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Boss {
    float x;
    float y;
    float health;
    float width;
    float height;
    ShapeRenderer shapeRenderer;


    public Boss(float x, float y, float health, float width, float height, ShapeRenderer shapeRenderer) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.width = width;
        this.height = height;
        this.shapeRenderer = shapeRenderer;
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

    protected void init(float x, float y, float health, float width, float height, ShapeRenderer shapeRenderer) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.width = width;
        this.height = height;
        this.shapeRenderer = shapeRenderer;
    }
}
