package com.example.ziclon.switchtwist;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ObstacleLaser extends Obstacle {

    private Bitmap img;

    public ObstacleLaser(int color, int x_inici, int y_inici, int x_final, int y_final){
        super(color,x_inici,y_inici,x_final,y_final);

        if (color == Dades.color_Blau){
            img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.laserazul);
        }
        else if(color == Dades.color_Gris){
            img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.lasernegro);
        }
        else {
            img = BitmapFactory.decodeResource(Dades.Context_Actual.getResources(), R.drawable.laserrojo);
        }
    }

    @Override
    public Obstacle getObstacle() {
        return this;
    }

    @Override
    public void update(){}

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(img,null,obstacle,null);
    }


}
