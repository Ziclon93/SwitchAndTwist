package com.example.ziclon.switchtwist;

import android.graphics.Canvas;



public interface Escena {

    public void uptade(float l);
    public void draw(Canvas canvas);
    public void finalitzar();
    public void Switch();
    public void Twist();

    public void setDirection(int dir);

    public void Reset();

    public boolean isGameWin();

    public boolean isGameOver();

    public boolean isTabacTrobat();


}
