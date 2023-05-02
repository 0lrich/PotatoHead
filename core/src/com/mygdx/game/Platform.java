package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends InGameObj {
    float x;
    float y;
    float height;
    float width;

// if you wonder why i put this one here i think it'll be used for when a boss can make a floor not usable anymore ~ Olrich
    Boolean tangible;
    Boolean isFallingThrough;
    Boolean canFallThroughPlat;

    public Platform(float x, float y, float height, float width, Boolean tangible, boolean isFallThrough) {
        this.canFallThroughPlat = isFallThrough;
        this.height = height;
        this.width = width;
        this.tangible = tangible;
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
     * this is where stuff that's drawn to the screen is gonna go (as in you put it in there it'll be drawn always)
     *     | |
     *     | |
     *     \ /
     *      V
     */
    public void render () {
        Globals.globalRender.begin(ShapeRenderer.ShapeType.Filled);
        if(canFallThroughPlat == true){
            Globals.globalRender.setColor(0.5f,0.5f,0.5f,1);
        }else{
            Globals.globalRender.setColor(0.2f,0.2f,0.2f,1);
        }

        Globals.globalRender.rect(x,y,width, height);
        Globals.globalRender.end();
    }

    /**
     * Should only return true if the player yVelocity is negative
     * @param player the player to check for collision
     * @return returns true if the platform is considered colliding with the player
     */
    public boolean platformStanding(Player player) {
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
}
