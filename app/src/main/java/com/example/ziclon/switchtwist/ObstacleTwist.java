package com.example.ziclon.switchtwist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class ObstacleTwist extends Obstacle{
    private Bitmap img;
    private int dir;

    public ObstacleTwist(int dir, int color, int x_inici,int y_inici, int x_final, int y_final){
        super(color,x_inici,y_inici, x_final, y_final);
        this.dir = dir;
        if (color == Dades.color_Blau){
            if(dir == 0){
                img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.arribab);
            }
            else if(dir == 1){
                img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.abajob);
            }
            else if(dir == 2){
                img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.derechab);
            }
            else if(dir == 3){
                img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.izquierdab);
            }

        }
        else{
            if(dir == 0){
                img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.arribar);
            }
            else if(dir == 1){
                img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.abajor);
            }
            else if(dir == 2){
                img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.derechar);
            }
            else if(dir == 3){
                img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.izquierdar);
            }
        }
    }

    @Override
    public void update() {

    }
    public void draw(Canvas canvas){

        canvas.drawBitmap(img,null,obstacle,null);
    }

    @Override
    public Obstacle getObstacle() {
        return this;
    }

    public int getDir(){
        return this.dir;
    }
}
