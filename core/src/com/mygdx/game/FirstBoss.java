package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class FirstBoss extends Boss{
    SpriteBatch batch;
    Texture texture;

    public FirstBoss(float x, float y, float health, float width, float length, ShapeRenderer shapeRenderer) {
        super(x, y, health, width, length, shapeRenderer);
    texture = new Texture(Gdx.files.internal("Idle-masterhand-1"));
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

        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
    }
    public void movementpattern(){// boss probably moves around or maybe he doesnt this is just a test boss im making him do whatever but it should be here anyways

    }
}
