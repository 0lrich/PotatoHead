package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.math.MathUtils.lerp;
import static com.mygdx.game.Globals.bulletHolder;
import static com.mygdx.game.Globals.platforms;
import static java.lang.Math.min;

public class Player {
    private float x;
    private float y;
    private float health;
    private float length;
    private float width;
    private float xVelocity = 0;
    private float yVelocity = 0;
    private float gravity = 1;
    private float speed = 4;
    private float jumpHeight = 23;
    private boolean jumpPressed = false;
    private ShapeRenderer body = new ShapeRenderer();
    private boolean canJump = true;
    private float bulletSpeed = 50;
    boolean canFallThrough = false;
    boolean isFacingRight = true;
    float reload = 0;
    float fireRate = 1;

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
    public void update(Platform platform, float deltaTime){
        shoot(deltaTime);
        movement(platform);
        if (reload > 0) reload -= 60 * deltaTime;
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
    private void calculateVelocity() {
        //fixme this is a template for getting keyboard input (this should actually be changing x and y velocity)

        yVelocity -= gravity;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xVelocity -= speed;
            isFacingRight = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xVelocity += speed;
            isFacingRight = true;
        }

        if (canJump == true ) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                yVelocity += jumpHeight;
                canJump = false;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            jumpPressed = true;
        }
        else{
            if (jumpPressed){
                yVelocity = min(yVelocity,2);
            }
            jumpPressed = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            canFallThrough = true;
        }

        xVelocity = lerp(xVelocity, 0, 0.25f);
    }

    /**
     *attempts to move along a vector (xVelocity,yVelocity) from current position and will stop being
     * able to move in said direction if there's something in the way
     * also has to make sure you cant go offscreen
     */
    private void movement(Platform platform){
    //fixme placeholder
        calculateVelocity();
        // x += xVelocity;
        //y += yVelocity;
        //tempCollision(platform);
        moveAndSlide(xVelocity, yVelocity);
    }

    public void moveAndSlide(float x, float y){
        if(y >= 0){
            this.x += x;
            this.y += y;
            return;
        }
        Rectangle testRect = new Rectangle(getX() + x, getY() + y, getWidth(), getLength());
        for(Platform p : Globals.platforms){

            if(this.y > p.y + p.height){
                continue;
            }

            Rectangle platformRectangle = new Rectangle(p.x, p.y, p.width, p.height);
            if(testRect.overlaps(platformRectangle)){
                this.x += x;
                this.y = p.y + p.height;
                canJump = true;
                yVelocity = 0;
                return;
            }
        }
        this.x += x;
        this.y += y;
        return;
    }
    public void shoot(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && reload < 1) {

            float tempSpeedx = 0;
            float tempSpeedy = 0;
            boolean isAim = false;

            if (Gdx.input.isKeyPressed(Input.Keys.I)) {
                tempSpeedy = bulletSpeed;
                isAim = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.K)) {
                tempSpeedy = -bulletSpeed;
                isAim = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                tempSpeedx = -bulletSpeed;
                isAim = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                tempSpeedx = bulletSpeed;
                isAim = true;
            }
            if (isAim) {
                bulletHolder.addBullet(x , y, tempSpeedx, tempSpeedy);
            } else if (isFacingRight) {
                bulletHolder.addBullet(x, y, bulletSpeed, 0);
            } else {
                bulletHolder.addBullet(x, y, -bulletSpeed, 0);
            }
            reload = 60/fireRate;
        }

    }

    private boolean tempCollision(Platform platform){
        if (platform.platformStanding(this)){
            while(platform.platformStanding(this)){
                y +=1;
            }
            yVelocity = 0;
            canJump = true;
            return true;
        }
        if (y < 0){
            y = 0;
            yVelocity = 0;
            canJump = true;
            return true;
        }
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

}
