package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.awt.*;

public class FirstBoss extends Boss {

    Texture defaultTexture;
    Texture hitTexture;
    Texture currentTexture;
    Texture deathTexture;

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
            attackDelay++;
            if (attackDelay == 200) {
                attackDelay = 0;
                if (!alreadyattacking) {
                    attackChoice = attackChoosing.nextInt(7) + 1;
                }
                choiceAttackMethod(attackChoice, player);
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
    public void choiceAttackMethod(float attackChoice, Player player){
        if (attackChoice == 1 && !alreadyattacking){
            System.out.println("1st attack");
           // prepShot(player);
            alreadyattacking = true;
        }else if(attackChoice == 1 && alreadyattacking){
            System.out.println("1st attack again");
            fullSHOTattack(player);


            alreadyattacking = false;

        }
        if (attackChoice == 2 && !alreadyattacking){
            System.out.println("2nd attack");
           // prepShot(player);
            alreadyattacking = true;
        }else if(attackChoice == 2 && alreadyattacking){
            System.out.println("2nd attack again");
            fullSHOTattack(player);
            alreadyattacking = false;
        }
        if (attackChoice == 3 && !alreadyattacking){
            System.out.println("3rd attack");
          //  prepShot(player);
            alreadyattacking = true;
        }else if(attackChoice == 3 && alreadyattacking){
            System.out.println("3rd attack again");
          //  fullSHOTattack(player);
            alreadyattacking = false;
        }
        if (attackChoice == 4 && !alreadyattacking){
            System.out.println("4th attack");
         //   prepShot(player);
            alreadyattacking = true;
        }else if(attackChoice == 4 && alreadyattacking){
            System.out.println("4th attack again");
            fullSHOTattack(player);
            alreadyattacking = false;
        }
        if (attackChoice == 5 && !alreadyattacking){
            System.out.println("5th attack");
          //  prepShot(player);
            alreadyattacking = true;
        }else if(attackChoice == 5 && alreadyattacking){
            System.out.println("5th attack again");

            alreadyattacking = false;
        }
        if (attackChoice == 6 && !alreadyattacking){
            System.out.println("6th attack");
          //  prepShot(player);
            alreadyattacking = true;
        }else if(attackChoice == 6 && alreadyattacking){
            System.out.println("6th attack again");

            alreadyattacking = false;
        }
        if (attackChoice == 7 && !alreadyattacking){
            System.out.println("7th attack");
           // prepShot(player);
            alreadyattacking = true;
        }else if(attackChoice == 7 && alreadyattacking){
            System.out.println("7th attack again");

            alreadyattacking = false;
        }


    }
    public void prepShot(Player player){
        currentTexture = shooting1;

    }
    public void shootShot(Player player){
        currentTexture = shooting1;

    }
    public void pauseShot(Player player){
        currentTexture = shooting1;
    }

    public void fullSHOTattack(Player player){
        for (int i = 0; i < 600; i++) {
            if(i == 0){
                shootShot(player);
            }
            if (i == 60){
                pauseShot(player);
            }

            if (i == 120){
                prepShot(player);
            }
            if (i == 180){
                shootShot(player);

            }
            if (i == 240){
                pauseShot(player);

            }
            if (i == 300){
                prepShot(player);

            }
            if (i == 360){
                shootShot(player);

            }
            if (i == 420){
                pauseShot(player);

            }
            if (i == 480){
                prepShot(player);

            }

            if (i == 540){
                shootShot(player);

            }
            if (i == 600){
                pauseShot(player);

                alreadyattacking = false;
            }
        }
    }
    public boolean amIHit(Bullet bullet) {
        Rectangle bulletRectangle = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
        Rectangle bossRectangle = new Rectangle(x, y, width, height);
        return bossRectangle.overlaps(bulletRectangle);
    }
}
