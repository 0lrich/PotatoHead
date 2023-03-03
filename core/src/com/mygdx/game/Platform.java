package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Platform {
    float x;
    float y;
    ShapeRenderer shapeRenderer;
    Boolean canJumpThrough;
    Boolean canFallThrough;
// if you wonder why i put this one here i think it'll be used for when a boss can make a floor not usable anymore ~ Olrich
    Boolean tangible;

}
