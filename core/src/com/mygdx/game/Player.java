package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Player {
    float x;
    float y;
    float health;
    float length;
    float width;
    ShapeRenderer body = new ShapeRenderer();

    public Player(float x, float y, float health, float length, float width, ShapeRenderer body) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.length = length;
        this.width = width;
        this.body = body;
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
        //region BASIC MOVEMENT
    if(Gdx.input.isKeyPressed(Input.Keys.W)){
        y += 5;
    }
    if(Gdx.input.isKeyPressed(Input.Keys.S)){
        y -= 5;
    }
    if(Gdx.input.isKeyPressed(Input.Keys.A)){
        x -= 5;
    }
    if(Gdx.input.isKeyPressed(Input.Keys.D)){
        x += 5;
    }
        //endregion

        if(x < 0 ){
            x += 5;
        } else if(x + width >  Gdx.graphics.getWidth()){
            x  -= 5;
        }

        if(y + length >  Gdx.graphics.getHeight()){
            y -= 5;
        }else if(y < 0) {
            y += 5;
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
        body.begin(ShapeRenderer.ShapeType.Filled);
        body.setColor(0,0,0,1);
        //the rectangle shape is drawn from the bottom left corner just so u know
        body.rect(x,y,width,length);
        body.end();
    }
    public void dispose () {


    }
}
