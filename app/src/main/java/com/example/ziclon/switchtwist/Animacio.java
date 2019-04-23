package com.example.ziclon.switchtwist;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public class Animacio {

    private Bitmap[] frames;
    private int frameIndex;

    private boolean isRep = false;
    public boolean isRep() {
        return isRep;
    }

    public void play(){
        isRep = true;
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
    }

    public void stop() {
        isRep = false;
    }

    private float frameTime;

    private long lastFrame;

    public Animacio(Bitmap[] frames, float animTime) {
        this.frames = frames;
        frameIndex = 0;

        frameTime = animTime/frames.length;

        lastFrame = System.currentTimeMillis();
    }

    public void draw(Canvas canvas, Rect destination){
        if(!isRep) return;
        scaleRect(destination);
        canvas.drawBitmap(frames[frameIndex],null,destination,new Paint());
    }

    private void scaleRect(Rect rect) {
        float whRatio = (float)frames[frameIndex].getWidth()/frames[frameIndex].getHeight();
        if(rect.width() > rect.height()) {
            rect.left = rect.top - (int)(rect.height() * whRatio);
        }else {
            rect.top = rect.bottom - (int)(rect.width() * (1/whRatio));
        }
    }

    public void update(){
        if(!isRep){
            return;
        }
        else if (System.currentTimeMillis() - lastFrame > frameTime*1000){
            frameIndex++;
            frameIndex = frameIndex >= frames.length ? 0:frameIndex;
            lastFrame = System.currentTimeMillis();
        }
    }

}
