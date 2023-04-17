package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

import static com.badlogic.gdx.math.MathUtils.lerp;
import static com.mygdx.game.Globals.*;
import static java.lang.Math.min;

public class Player {
    private float posX;
    private float posY;
    private float health;
    private float height;
    private float width;
    private float xVelocity = 0;
    private float yVelocity = 0;
    private float gravity = 1;
    private float speed = 4;
    private float jumpForce = 23;
    private boolean jumpPressed = false;
    private ShapeRenderer body;
    private boolean canJump = true;
    private float bulletSpeed = 50;
    boolean canFallThrough = false;
    boolean isFacingRight = true;
    float reload = 0;
    float fireRate = 3;
    float maxCoyoteSeconds = 0.08f;
    float coyoteSeconds = 0;
    boolean isOnFloor = false;
    Vector2 playerSpawn;
    int damage = 1;


    public Player(float x, float y, float health, float height, float width, ShapeRenderer body) {
        this.posX = x;
        this.posY = y;

        this.health = health;
        this.height = height;
        this.width = width;
        this.body = body;
    }
    public void init(float x, float y, float health, float height, float width, ShapeRenderer body){
        this.posX = x;
        this.posY = y;

        yVelocity = 0;
        xVelocity = 0;

        this.health = health;
        this.height = height;
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
    public void update(float deltaTime){
        shoot(deltaTime);
        movement();
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
        body.setColor(1,0,0,1);
        //the rectangle shape is drawn from the bottom left corner just so u know
        body.rect(posX, posY,width, height);
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

        if (isOnFloor){
            canJump = true;
            coyoteSeconds = maxCoyoteSeconds;
        } else{
            if (coyoteSeconds > 0){
                coyoteSeconds -= Gdx.graphics.getDeltaTime();
            }else{
                canJump = false;
            }
        }

        if (canJump == true ) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                yVelocity = jumpForce;
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
        canFallThrough = false;

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
    private void movement(){
    //fixme placeholder
        calculateVelocity();
        moveAndSlide(xVelocity, yVelocity, canFallThrough);
        changeSceneToggle();
    }

    public void moveAndSlide(float velX, float velY, boolean canFallThrough){

        isOnFloor = false;

        // This checks if you're going up so that there are no upwards collisions
        if(velY >= 0){
            this.posX += velX;
            this.posY += velY;
            return;
        }
        if(posY<=-50){
            this.playerSpawn = Globals.sceneHolder.getPlayerSpawn();
            init(playerSpawn.x, playerSpawn.y, 3-damage, 50,50,new ShapeRenderer());
            damage++;
            if(health<=0){damage = 1;}
            System.out.println(health);
        }
        // This makes a fake player that detects if the players final position collides with the platform
        Rectangle testRect = new Rectangle(getPosX() + velX, getPosY() + velY, getWidth(), getHeight());
        for(Platform p : Globals.platformHolder.getPlatforms()){
            if(this.posY < p.y + p.height -1){
                continue;
            }
            if (p.canFallThroughPlat  && canFallThrough){
                continue;
            }
            Rectangle platformRectangle = new Rectangle(p.x, p.y, p.width, p.height);
            if(testRect.overlaps(platformRectangle)){
                this.posX += velX;
                this.posY = p.y + p.height;

                isOnFloor = true;
                yVelocity = 0;
                return;
            }

        }
        this.posX += velX;
        this.posY += velY;
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
                bulletHolder.addBullet(posX, posY, tempSpeedx, tempSpeedy);
            } else if (isFacingRight) {
                bulletHolder.addBullet(posX, posY, bulletSpeed, 0);
            } else {
                bulletHolder.addBullet(posX, posY, -bulletSpeed, 0);
            }
            reload = 60/fireRate;
        }

    }

    public void changeSceneToggle(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            if(sceneHolder.getScene() == 0){
                sceneHolder.switchScene(1);
            } else if(platformHolder.getPlatformScene() == 1){
                sceneHolder.switchScene(2);
            } else if(platformHolder.getPlatformScene() == 2){
                sceneHolder.switchScene(3);
            } else{
                sceneHolder.switchScene(0);
            }

        }
    }

    private boolean tempCollision(Platform platform){
        if (platform.platformStanding(this)){
            while(platform.platformStanding(this)){
                posY +=1;
            }
            yVelocity = 0;
            canJump = true;
            return true;
        }
        if (posY < 0){
            posY = 0;
            yVelocity = 0;
            canJump = true;
            return true;
        }
        canJump = false;
        return false;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public boolean getCanFallThrough() {
        return canFallThrough;
    }
}