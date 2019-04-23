package com.example.ziclon.switchtwist;

import android.graphics.Canvas;
import android.graphics.Rect;


public class ManagerAnimacio {

    private Animacio[] animacions;
    private int indexAnim;

    public ManagerAnimacio(Animacio[] animacions){
        this.animacions = animacions;
    }

    public void playAnimacio(int indexAnim){
        for(int i = 0; i < animacions.length; i++) {
            if(i == indexAnim) {
                if(!animacions[indexAnim].isRep()) {
                    animacions[i].play();
                }
            } else {
                animacions[i].stop();
            }
        }
        this.indexAnim = indexAnim;
    }

    public void draw(Canvas canvas, Rect rect) {
        if(animacions[indexAnim].isRep())
            animacions[indexAnim].draw(canvas, rect);
    }

    public void update() {
        if(animacions[indexAnim].isRep())
            animacions[indexAnim].update();
    }
}
