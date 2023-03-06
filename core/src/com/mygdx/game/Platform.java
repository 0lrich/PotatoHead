package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.awt.geom.Point2D;

public class Platform {
    float x;
    float y;
    float length;
    float width;
    ShapeRenderer floor;
    Boolean canJumpThrough;
    Boolean canFallThrough;
// if you wonder why i put this one here i think it'll be used for when a boss can make a floor not usable anymore ~ Olrich
    Boolean tangible;
    float xSpeed;
    float ySpeed;
    public Platform(float x, float y, float length, float width, ShapeRenderer floor, Boolean canJumpThrough, Boolean canFallThrough, Boolean tangible, float xSpeed, float ySpeed) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
        this.floor = floor;
        this.canJumpThrough = canJumpThrough;
        this.canFallThrough = canFallThrough;
        this.tangible = tangible;
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
    public void update(Player player){
        Vector2 TL = new Vector2(x,y+length); //top left corner
        Vector2 TR = new Vector2(x + width,y);//top right corner
        Vector2 BR = new Vector2(x+width,y);// bottom right corner
        Vector2 BL = new Vector2(x,y); // bottom left corner
        if (tangible){
            if (player.x <= TL.y){

            }
        }



    }
    /**
     * this is where stuff that's drawn to the screen is gonna go (as in you put it in there it'll be drawn always)
     *     | |
     *     | |
     *     \ /
     *      V
     */
    public void render () {
        floor.begin(ShapeRenderer.ShapeType.Filled);
        floor.setColor(0,0,0,1);
        floor.rect(x,y,width,length);
        floor.end();
    }
}
