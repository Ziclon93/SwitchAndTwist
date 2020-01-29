package com.example.ziclon.switchtwist;

import android.graphics.Canvas;



public interface Escena {

    void uptade(float l);
    void draw(Canvas canvas);
    void finalitzar();
    void Switch();
    void Twist();

    void setDirection(int dir);

    void Reset();

    boolean isGameWin();

    boolean isGameOver();

    boolean isTabacTrobat();


}
