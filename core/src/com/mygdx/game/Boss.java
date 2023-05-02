package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import static com.mygdx.game.Globals.bulletHolder;

public class Boss extends InGameObj{
    
    float x;
    float y;
    float health;
    float width;
    float height;
    ShapeRenderer shapeRenderer;
    Texture currentTexture;



    public Boss(float x, float y, float health, float width, float height) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.width = width;
        this.height = height;
        this.shapeRenderer = shapeRenderer;
        this.currentTexture = currentTexture;
    }

    /**
     * this is where stuff that happens every frame is gonna go
     *  | |
     *  | |
     *  | |
     *  \ /
     *   V
     */
    public void update(Player player){



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
