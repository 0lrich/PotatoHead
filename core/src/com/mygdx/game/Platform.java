package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
    float x;
    float y;
    float height;
    float width;
    ShapeRenderer floor;

// if you wonder why i put this one here i think it'll be used for when a boss can make a floor not usable anymore ~ Olrich
    Boolean tangible;
    float xSpeed;
    float ySpeed;

    boolean isFallingThrough = false;
    public Platform(float x, float y, float length, float width, ShapeRenderer floor, Boolean tangible, float xSpeed, float ySpeed) {
        this.x = x;
        this.y = y;
        this.height = length;
        this.width = width;
        this.floor = floor;
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
        floor.rect(x,y,width, height);
        floor.end();
    }

    /**
     * Should only return true if the player yVelocity is negative
     * @param player the player to check for collision
     * @return returns true if the platform is considered colliding with the player
     */
    public boolean platformStanding(Player player) {//gonna be really messy way to figure out how to do floor physics
        Rectangle playerRectangle = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getLength());
        Rectangle platformRectangle = new Rectangle(x, y, width, height);
        if (platformRectangle.overlaps(playerRectangle)){
            if (player.getCanFallThrough()){
                isFallingThrough = true;
            }
            if (player.getyVelocity() < 0 && isFallingThrough == false) {
                return true;
            }

        }else {
            isFallingThrough = false;
            return false;
        }
        return false;
    }
}
