package com.example.ziclon.switchtwist;

import android.graphics.Canvas;
import android.graphics.Rect;

/*
    Indices:
    0 - idle B
    1 - Up B
    2 - Down B
    3 - Right B
    4 - Left B
    5 - Idle R
    6 - Up R
    7 - Down R
    8 - Right R
    9 - Left R
     */

public class AnimacioDollManager {

    private Animacio[] animacions;
    private int indexAnim;

    AnimacioDollManager(Animacio[] animacions) {
        this.animacions = animacions;
    }

    void playAnimacio(int indexAnim) {
        for (int i = 0; i < animacions.length; i++) {
            if (i == indexAnim) {
                if (!animacions[indexAnim].isRep()) {
                    animacions[i].play();
                }
            } else {
                animacions[i].stop();
            }
        }
        this.indexAnim = indexAnim;
    }

    public void draw(Canvas canvas, Rect rect) {
        if (animacions[indexAnim].isRep())
            animacions[indexAnim].draw(canvas, rect);
    }

    public void update() {
        if (animacions[indexAnim].isRep())
            animacions[indexAnim].update();
    }
}
