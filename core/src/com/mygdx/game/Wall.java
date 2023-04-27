package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.mygdx.game.Globals.globalRender;
import static java.lang.Math.abs;

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
        globalRender.setColor(0,0,0,1);
        globalRender.rect(x,y,width, height);
        globalRender.end();
    }

    public float resolveX(Rectangle testRect) {

        float minDistanceMoved = 100000;

        int intent = 1000;

        if (abs(x + testRect.width/2 + width/2 - testRect.x) < minDistanceMoved){
            minDistanceMoved = abs(x + testRect.width/2 + width/2 - testRect.x);
            intent = 0; //go right
        }
        if (abs(x - testRect.width/2 - width/2 - testRect.x) < minDistanceMoved){
            minDistanceMoved = abs(x - testRect.width/2 - width/2 - testRect.x);
            intent = 1; //go right
        }
        if (abs(y + testRect.height/2 + height/2 - testRect.y) < minDistanceMoved){
            minDistanceMoved = abs(y + testRect.height/2 + height/2 - testRect.y);
            intent = 3; //go right
        }
        if (abs(y - testRect.height/2 - height/2 - testRect.y) < minDistanceMoved){
            minDistanceMoved = abs(y - testRect.height/2 - height/2 - testRect.y);
            intent = 4; //go right
        }

        if (intent == 0){
            return x + testRect.width/2 + width/2;
        }
        if (intent == 1){
            return x - testRect.width/2 - width/2;
        }

        return testRect.x;

    }

    public float resolveY(Rectangle testRect) {

        float minDistanceMoved = 100000;

        int intent = 1000;

        if (abs(x + testRect.width/2 + width/2 - testRect.x) < minDistanceMoved){
            minDistanceMoved = abs(x + testRect.width/2 + width/2 - testRect.x);
            intent = 0; //go right
        }
        if (abs(x - testRect.width/2 - width/2 - testRect.x) < minDistanceMoved){
            minDistanceMoved = abs(x - testRect.width/2 - width/2 - testRect.x);
            intent = 1; //go right
        }
        if (abs(y + testRect.height/2 + height/2 - testRect.y) < minDistanceMoved){
            minDistanceMoved = abs(y + testRect.height/2 + height/2 - testRect.y);
            intent = 3; //go right
        }
        if (abs(y - testRect.height/2 - height/2 - testRect.y) < minDistanceMoved){
            minDistanceMoved = abs(y - testRect.height/2 - height/2 - testRect.y);
            intent = 4; //go right
        }

        if (intent == 3){
            return y + testRect.height/2 + height/2;
        }
        if (intent == 4){
            return y - testRect.height/2 - height/2;
        }

        return testRect.y;

    }

}
