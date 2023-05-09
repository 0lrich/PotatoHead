package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Animation {
    Texture[] frames;
    int currentFrame;
    boolean isDone;
    boolean shouldLoop;

    public Animation(String[] frames, boolean shouldLoop) {
        this.frames = new Texture[frames.length];
        for (int i = 0; i < frames.length; i++) {
            this.frames[i] = new Texture(frames[i]);
        }

        currentFrame = 0;
        isDone = false;
        this.shouldLoop = shouldLoop;
    }

    public void update(){
        currentFrame++;
        if (currentFrame >= frames.length){
            currentFrame=0;
            if (!shouldLoop){
                isDone = true;
            }
        }
    }

    public Texture getCurrentFrame() {
        return frames[currentFrame];
    }
}
