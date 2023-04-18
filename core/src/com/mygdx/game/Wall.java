package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.mygdx.game.Globals.globalRender;

public class Wall {
    float x;
    float y;
    float height;
    float width;

    public Wall(float x, float y, float height, float width) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
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
     * this is where stuff that's drawn to the screen is going to go (as in you put it in there it'll be drawn always)
     *     | |
     *     | |
     *     \ /
     *      V
     */
    public void render () {
        globalRender.begin(ShapeRenderer.ShapeType.Filled);
        globalRender.rect(x,y,width, height);
        globalRender.end();
    }
}
