package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.math.MathUtils.lerp;
import static com.mygdx.game.Globals.*;
import static java.lang.Math.min;

public class Player extends InGameObj{
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
    private boolean canJump = true;
    private float bulletSpeed = 50;
    boolean canFallThrough = false;
    boolean isFacingRight = true;
    float reload = 0;
    float fireRate = 3;
    float maxCoyoteSeconds = 0.08f;
    float coyoteSeconds = 0;
    boolean isOnFloor = false;


    public Player(float x, float y, float health, float height, float width) {
        this.posX = x;
        this.posY = y;

        this.health = health;
        this.height = height;
        this.width = width;
    }
    public void init(float x, float y, float health, float height, float width){
        this.posX = x;
        this.posY = y;

        yVelocity = 0;
        xVelocity = 0;

        this.health = health;
        this.height = height;
        this.width = width;
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

        globalRender.begin(ShapeRenderer.ShapeType.Filled);
        globalRender.setColor(1,0,0,1);
        //the rectangle shape is drawn from the bottom left corner just so u know
        globalRender.rect(posX, posY,width, height);
        globalRender.end();
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
        //moveAndSlide(xVelocity, yVelocity, canFallThrough);
        moveAndSlideWalls(xVelocity, yVelocity);
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
    public void moveAndSlideWalls(float velX, float velY){
        isOnFloor = false;
        // This makes a fake player that detects if the players final position collides with the platform
        float tempMag = (float) Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
        float tempUnitVectorX = -velX / tempMag;
        float tempUnitVectorY = -velY / tempMag;
        if(Math.abs(tempUnitVectorY)<.0001f) tempUnitVectorY =0;
        if(Math.abs(tempUnitVectorX)<.0001f) tempUnitVectorX =0;
//        if(tempMag==0){
//            tempUnitVectorX = 0;
//            tempUnitVectorY = 0;
//        }


        Rectangle testRect = new Rectangle(getPosX() + velX, getPosY() + velY, getWidth(), getHeight());

            if(isRectCollideWithPlatforms(testRect)) {

                System.out.println("TestRect is overlapping with the platformRectangle");
                for(int i = 0; isRectCollideWithPlatforms(testRect); i++) {
                    if(i>tempMag){
                        System.out.println();
                    }
                    testRect.x += tempUnitVectorX;
                    if(!isRectCollideWithPlatforms(testRect)){
                        float tempVelY = -tempUnitVectorY * i;
                        this.posX = testRect.getX();
                        this.posY = testRect.getY();
                      //  this.yVelocity = tempVelY;
                        this.xVelocity = 0;
                        moveAndSlideWalls(0,tempVelY);
                        return;
                    }
                    testRect.y += tempUnitVectorY;
                    if(!isRectCollideWithPlatforms(testRect)){
                        if(tempMag==0){
                            System.out.println();
                        }
                        float tempVelX = -tempUnitVectorX * i;
                        this.posY = testRect.getY();
                        this.posX = testRect.getX();
                        //this.xVelocity = tempVelX;
                        this.yVelocity = 0;

                        moveAndSlideWalls(tempVelX,0);
                        if(-tempUnitVectorY<0) {
                            isOnFloor = true;
                        }
                        return;
                    }
                }
//                this.posX = testRect.getX();
//                this.posY = testRect.getY();
                //this.xVelocity = 0;
            }
//            this.posX += velX;
//            this.posY += velY;

            System.out.println("VELOCITY: (" + velX + ", " + velY + ")" + " POSITION: (" + posX + ", " + posY + ")");



        this.posX = testRect.getX();
        this.posY = testRect.getY();
    }

    public boolean isRectCollideWithPlatforms(Rectangle testRect){
        for(int i = 0; i < platformHolder.getPlatforms().size(); i++){
            Platform p = platformHolder.getPlatforms().get(i);
            Rectangle platformRectangle = new Rectangle(p.x, p.y, p.width, p.height);
            if(testRect.overlaps(platformRectangle)){
                return true;
            }
        }
        return false;
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
                bulletHolder.addBullet(posX,posY,tempSpeedx,tempSpeedy);
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