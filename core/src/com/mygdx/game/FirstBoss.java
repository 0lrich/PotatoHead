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
    private ShapeRenderer bossBody = new ShapeRenderer();

    public FirstBoss(float x, float y, float health, float width, float height, ShapeRenderer shapeRenderer) {
        super(x, y, health, width, height, shapeRenderer);
        defaultTexture = new Texture(Gdx.files.internal("Idle masterhand 1.png"));
        hitTexture = new Texture(Gdx.files.internal("Hurt Hand.png"));

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
    public void update() {
        for (int i = 0; i < Globals.bulletHolder.bullets.size(); i++) {
            if (amIHit(Globals.bulletHolder.bullets.get(i))) {

            }
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


        batch.draw(texture, x, y, width, height);

    }

    public void movementpattern() {// boss probably moves around or maybe he doesnt this is just a test boss im making him do whatever but it should be here anyways

    }

    public boolean amIHit(Bullet bullet) {
        //idk why it forces me to make them all integers but im not gonna fix it i dont think it matters
        Rectangle bulletRectangle = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
        Rectangle bossRectangle = new Rectangle(x, y, width, height);
        if (bossRectangle.overlaps(bulletRectangle)) {



        return true;
        }
        return false;
    }
}
