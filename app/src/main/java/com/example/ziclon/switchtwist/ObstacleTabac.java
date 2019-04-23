package com.example.ziclon.switchtwist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.Toast;


public class ObstacleTabac extends Obstacle{

    private Bitmap img;

    public ObstacleTabac(int x_inici,int y_inici, int x_final, int y_final){

        super(x_inici,y_inici,x_final,y_final);
        //img = ContextCompat.getDrawable(Dades.Context_Actual,R.drawable.tobacco);
        img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.tobacco);
    }

    @Override
    public void update() { }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(img,null,obstacle,null);
    }

    @Override
    public Obstacle getObstacle() {
        return this;
    }





}
