package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.awt.*;
import java.util.Random;

public class FirstBoss extends Boss {

    Texture defaultTexture;
    Texture hitTexture;

    Texture deathTexture;
    FirstBossAttacks currentAttack;

    Random attackchoice;
    Texture shooting1;
    Texture shooting2;
    Texture shooting3;

    Boolean canGetHurt = true;
    private final ShapeRenderer bossBody = new ShapeRenderer();
    boolean bettername = false;
    boolean alreadyattacking = false;
    public FirstBoss(float x, float y, float health, float width, float height, ShapeRenderer shapeRenderer) {
        super(x, y, health, width, height, shapeRenderer);
        currentTexture = new Texture(Gdx.files.internal("Idle masterhand 1.png"));
        defaultTexture = new Texture(Gdx.files.internal("Idle masterhand 1.png"));
        hitTexture = new Texture(Gdx.files.internal("Hurt Hand.png"));
        deathTexture = new Texture(Gdx.files.internal("Dying hand.png"));
        shooting1 = new Texture(Gdx.files.internal("Shoot Hand 1.png"));
        shooting2 = new Texture(Gdx.files.internal("Shoot Hand 2.png"));
        shooting3 = new Texture(Gdx.files.internal("Shoot Hand 3.png"));

        bossBody.begin(ShapeRenderer.ShapeType.Filled);


        //the rectangle shape is drawn from the bottom left corner just so u know
        bossBody.rect(x, y, width, height);
        bossBody.end();
    }
    public void init(float x, float y, float health, float width, float height, ShapeRenderer shapeRenderer) {
        super.init(x, y, health, width, height, shapeRenderer);
        currentTexture = new Texture(Gdx.files.internal("Idle masterhand 1.png"));
        defaultTexture = new Texture(Gdx.files.internal("Idle masterhand 1.png"));
        hitTexture = new Texture(Gdx.files.internal("Hurt Hand.png"));
        deathTexture = new Texture(Gdx.files.internal("Dying hand.png"));

        bossBody.begin(ShapeRenderer.ShapeType.Filled);
        // bossBody.setColor(100,100,100,.01f);

        //the rectangle shape is drawn from the bottom left corner just so u know
        bossBody.rect(x, y, width, height);
        bossBody.end();
    }

    /**
     * this is where stuff that happens every frame is gonna go
     * | |
     * | |
     * | |
     * \ /
     * V
     */
    public void update(Player player) {

        if (health > 0) {
            movementpattern();
            if (canGetHurt = true) {
                for (int i = 0; i < Globals.bulletHolder.bullets.size(); i++) {
                    if (amIHit(Globals.bulletHolder.bullets.get(i))) {
                        currentTexture = hitTexture;
                        health = health - Globals.bulletHolder.bullets.get(i).damage;
                        Globals.bulletHolder.bullets.get(i).alreadyHitSomething();
                    } else {
                        currentTexture = defaultTexture;
                    }
                }
            }
            if(!alreadyattacking) {
                //get random number
                float chosenattack;
               chosenattack = attackchoice.nextInt(5)+1;
                //set currentAttack to appropriate Attack
                switch ((int) chosenattack) {
                    case 1:
                        TestBossFingerBullet.
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                }

            }
            else {
                currentAttack.update(player,this);
                if(currentAttack.isdone()){
                    alreadyattacking = false;
                }
            }

        } else if (health <= 0) {
            currentTexture = deathTexture;

        }

    }

    /**
     * this is where stuff that's drawn to the screen is gonna go (as in you put it in there it'll be drawn always)
     * | |
     * | |
     * \ /
     * V
     */
    public void render(SpriteBatch batch) {
        batch.draw(currentTexture, x, y, width, height);
    }

    public void movementpattern() {// boss probably moves around or maybe he doesnt this is just a test boss im making him do whatever but it should be here anyways

        if (x+width > Gdx.graphics.getWidth()){
            bettername = true;
        }
        if (x < 0){
            bettername = false;
        }
        if (bettername){
            x-= 5;
            y = (float) Math.sin(x*.01f)*100 + Gdx.graphics.getHeight()/2f;
        }else {
            x+= 10;
            y = (float) Math.sin(x*.5)* 10 + Gdx.graphics.getHeight()/2f;

        }
    }



    public boolean amIHit(Bullet bullet) {
        Rectangle bulletRectangle = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
        Rectangle bossRectangle = new Rectangle(x, y, width, height);
        return bossRectangle.overlaps(bulletRectangle);
    }
}
