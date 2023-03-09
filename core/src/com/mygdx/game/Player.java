package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.math.MathUtils.lerp;

public class Player {
    float x;
    float y;
    float health;
    float length;
    float width;
    float xVelocity = 0;
    float yVelocity = 0;
    float speed = 5;
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
    public void update(Platform platform){
        movement();
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
    public void dispose () {}

    /**
     * Sets the X and Y Velocity property based on keyboard input
     * x velocity should be set based on if A or D is pressed
     * y velocity should be set based on if ONLY W is pressed :) and you're gonna have to add gravity
     * (^^^ should check to see of their allowed to jump, no double jumps ^^^)
     */
    private void calculateVelocity(){
        //fixme this is a template for getting keyboard input (this should actually be changing x and y velocity)
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            xVelocity -= speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            xVelocity += speed;
        }
    }

    /**
     *attempts to mvove along a vector (xVelocity,yVelocity) from current position and will stop being
     * able to move in said direction if there's something in the way
     * also has to make sure you cant go offscreen
     */
    private void movement(){
    //fixme placeholder
        calculateVelocity();
        x += xVelocity;
        y += yVelocity;
        xVelocity = lerp(xVelocity, 0, 0.25f);
        if (y < 0){
            y = 0;
            yVelocity = 0;
        }
    }
}
