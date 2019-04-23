package com.example.ziclon.switchtwist;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public abstract class Obstacle implements GameObject {

    protected Rect obstacle;
    protected int color;

    public Obstacle(int color, int x_inici,int y_inici, int x_final, int y_final){
        this.obstacle = new Rect(x_inici,y_inici,x_final, y_final);
        this.color = color;
    }

    public Obstacle(int x_inici,int y_inici, int x_final, int y_final){
        this.obstacle = new Rect(x_inici,y_inici,x_final, y_final);
    }

    abstract public Obstacle getObstacle();

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.color);
        canvas.drawRect(this.obstacle,paint);
    }

    public Rect getRect(){
        return this.obstacle;
    }

    public int getColor(){
        return this.color;
    }


}

