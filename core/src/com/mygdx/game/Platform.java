package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
    float x;
    float y;
    float height;
    float width;
    ShapeRenderer floor;
    float top;
    float side;

// if you wonder why i put this one here i think it'll be used for when a boss can make a floor not usable anymore ~ Olrich
    Boolean tangible;
    Boolean isFallingThrough;
    Boolean canFallThroughPlat;

    public Platform(float x, float y, float height, float width, ShapeRenderer floor, Boolean tangible, boolean isFallThrough) {
        // GEORGE!!!!!!!! makes sense why you took out the speed you should instead of talking to me like this just message thru the Teams to me
        //                  cause i could  miss this or if u put it in a weird spot and i just skip over it
        this.canFallThroughPlat = isFallThrough;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.floor = floor;
        this.tangible = tangible;
        this.top = this.height + this.y;
        this.side = this.width + this.x;
        //Globals.platformHolder.addPlatform(this);
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
        Rectangle playerRectangle = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
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
    public boolean getCanFallThroughPlat(){
        return canFallThroughPlat;
    }
    public float getTop(){
        return this.top;
    }
    public float getSide(){
        return this.side;
    }
    public float getX(){
        return this.x;
    }
}
