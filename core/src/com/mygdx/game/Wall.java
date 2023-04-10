package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Wall {
    float x;
    float y;
    float height;
    float width;
    ShapeRenderer wall;

    public Wall(float x, float y, float height, float width, ShapeRenderer wall) {
        this.height = height;
        this.width = width;
        this.wall = wall;
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
    public void update(Player player){
    }
    /**
     * this is where stuff that's drawn to the screen is going to go (as in you put it in there it'll be drawn always)
     *     | |
     *     | |
     *     \ /
     *      V
     */
    public void render () {
        wall.begin(ShapeRenderer.ShapeType.Filled);
        wall.rect(x,y,width, height);
        wall.end();
    }
}
