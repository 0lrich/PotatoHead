package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class FirstBossTargetedGrab implements FirstBossAttacks{
        float damage = 1;
        float timer= 0;
        boolean targetSet = false;
    Vector2 target = new Vector2(0,0);

    public FirstBossTargetedGrab(Player player, FirstBossHand boss) {
        boss.currentTexture = boss.openHandGrab;
        update(player,boss);

    }

    @Override
    public void update(Player player, FirstBossHand boss) {
        Rectangle playerRectangle = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        Rectangle bossRectangle = new Rectangle(boss.x, boss.y, boss.width, boss.height);




        timer += Gdx.graphics.getDeltaTime();

    if (!bossRectangle.overlaps(playerRectangle)){
        if ( 7 <= timer && timer <=20 ) {
            if(!targetSet) {
                Vector2 bossCenter = new Vector2(boss.x +boss.width/2, boss.y +boss.height/2);
                Vector2 playerCenter = new Vector2(player.getPosX()+ player.getWidth()/2, player.getPosY() +player.getHeight()/2);
               target= new Vector2(bossCenter.x - playerCenter.x, bossCenter.y - playerCenter.y);
                targetSet = true;
            }
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
        if (player.invulnerable){

        }
        boss.currentTexture = boss.closedFist;
        if ( 7 <= timer && timer <=20 ) {
            boss.y += 10;
            player.posY = boss.y+boss.height/2;
            player.posX = boss.x+boss.width/2;
        }if (timer > 25){
            player.yVelocity -= 100;
            if(boss.rightHand == true){
                player.xVelocity += 50;
                player.health -=1;
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
            targetSet = false;
            return true;
        }
        return false;
    }
    }

