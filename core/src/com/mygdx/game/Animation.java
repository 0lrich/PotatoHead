package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Animation {
    Texture[] frames;
    int currentFrame;
    boolean isDone;
    boolean shouldLoop;
    float frametime;
    float timeelapsed;
    public Animation(String[] frames, boolean shouldLoop, float frametime) {
        this.frames = new Texture[frames.length];
        for (int i = 0; i < frames.length; i++) {
            this.frames[i] = new Texture(frames[i]);
        }
        this.frametime = frametime;
        currentFrame = 0;
        timeelapsed = 0;
        isDone = false;
        this.shouldLoop = shouldLoop;
    }

    public void update() {
        timeelapsed += Gdx.graphics.getDeltaTime();
        if (timeelapsed >= frametime) {
            timeelapsed -= frametime;
            currentFrame++;

            if (currentFrame >= frames.length) {
                currentFrame = 0;
                if (!shouldLoop) {
                    isDone = true;
                }
            }
        }
    }
    public Texture getCurrentFrame() {
        return frames[currentFrame];
    }
}
