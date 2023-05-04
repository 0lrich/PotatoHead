package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TestBossTargetedPunch implements FirstBossAttacks{
        float damage = 1;
        float timer= 0;

    public TestBossTargetedPunch(Player player, FirstBossHand boss) {
        boss.currentTexture = boss.openHandGrab;
        update(player,boss);

    }

    @Override
    public void update(Player player, FirstBossHand boss) {
        Rectangle playerRectangle = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        Rectangle bossRectangle = new Rectangle(boss.x, boss.y, boss.width, boss.height);

        Vector2 bossCenter = new Vector2(boss.x +boss.width/2, boss.y +boss.height/2);

        Vector2 playerCenter = new Vector2(player.getPosX()+ player.getWidth()/2, player.getPosY() +player.getHeight()/2);

        float opposite = bossCenter.x -playerCenter.x;
        float adjacent = bossCenter.y -playerCenter.y;
        timer += Gdx.graphics.getDeltaTime();
        Vector2 target = new Vector2(bossCenter.x-playerCenter.x,bossCenter.y-playerCenter.y);
    if (!bossRectangle.overlaps(playerRectangle)){
        if ( 7 <= timer && timer <=20 ) {
            if (target.x > boss.x) {
                target.nor();
                boss.x += target.x * 20;
            } else {
                target.nor();
                boss.x -= target.x * 20;

            }
            if (target.y > 0) {
                target.nor();
                boss.y -= target.y * 20;
            } else {
                target.nor();
                boss.y += target.y * 20;
            }    isdone(boss);
        }else if (timer >5 ){
            boss.disabledMovementpattern = true;
            boss.currentTexture = boss.openHandGrab;
        }else {

        }
        }else {
        boss.currentTexture = boss.closedFist;
        if ( 7 <= timer && timer <=20 ) {
            boss.y += 10;
            player.posY = boss.y;
            player.posX = boss.x;
        }if (timer > 25){
            player.yVelocity -= 100;
            if(boss.rightHand == true){
                player.xVelocity += 50;
            }else{
                player.xVelocity -= 50;
            }
        }
        }


        isdone(boss);
    }

    @Override
    public boolean isdone(FirstBossHand boss) {
        if (timer>=10) {
            boss.disabledMovementpattern = false;
            return true;
        }
        return false;
    }
    }

