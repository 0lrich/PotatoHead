package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mygdx.game.Globals.pitchForksTexture;
import static com.mygdx.game.Globals.truckWheelTexture;

public class Pitchforks extends Wall{

    public Pitchforks(){
        super(3800, 190, 100, 1800);

    }
    public void init(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void render (SpriteBatch batch) {
        batch.draw(pitchForksTexture, x, y, width, height);
    }
}
