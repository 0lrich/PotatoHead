package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ModelInfluencer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import static com.mygdx.game.Globals.bulletHolder;

public abstract class Boss {
    float x;
    float y;
    float health;
    float width;
    float height;
    ShapeRenderer shapeRenderer;

    float attackDelay;
    Random attackChoosing = new Random();
    float attackChoice;

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
}
